package org.anhvt.springbootwatermark.service;

import org.anhvt.springbootwatermark.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${upload.path}")
    private String uploadPath;

    private String uploadOriginalPath = "uploads/users";

    // overlay settings
    private final String WARTERMARK = "\u00a9 trunganhvu";


    /**
     * Stores the given file by generating a unique folder using UUID,
     * saving the file in the specified directory, and applying a text watermark.
     *
     * @param file the MultipartFile to be stored
     * @return a success message indicating the file was uploaded successfully
     * @throws IOException if an I/O error occurs during file operations
     */
    public String watermarkFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            // Add a unique folder using UUID
            String uuidFolder = UUID.randomUUID().toString();
            String workFolder = uploadOriginalPath + "/" + uuidFolder;

            // Create the folder if it doesn't exist
            Path root = Paths.get(workFolder);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            // Save original file
            Files.copy(file.getInputStream(), root.resolve(fileName));

            // Get the file extension
            String fileExt = FileUtils.getExtension(new File(fileName));

            // New file name
            String newCroppedFileName = FileUtils.getFileNameWithoutExtension(fileName) + "_cropped."+ fileExt;

            File input = new File(workFolder + "/" + fileName);
            File output = new File(workFolder + "/" + newCroppedFileName);

            // adding text as overlay to an image
            addTextWatermark2(WARTERMARK, fileExt, input, output);

            return "Uploaded the file successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    /**
     * Resizes the given image to a smaller size and stores it in a new file.
     *
     * @param file the MultipartFile to be resized
     * @return a success message indicating the file was uploaded successfully
     */
    public String resizedImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            // Add a unique folder using UUID
            String uuidFolder = UUID.randomUUID().toString();
            String workFolder = uploadOriginalPath + "/" + uuidFolder;

            // Create the folder if it doesn't exist
            Path root = Paths.get(workFolder);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            // Save original file
            Files.copy(file.getInputStream(), root.resolve(fileName));

            // Get the file extension
            String fileExt = FileUtils.getExtension(new File(fileName));
            // New file name
            String newResizedFileName = FileUtils.getFileNameWithoutExtension(fileName) + "_resized." + fileExt;

            File input = new File(workFolder + "/" + fileName);
            File outputCropped = new File(workFolder + "/" + newResizedFileName);

            //resize image and save to path --start resize
            BufferedImage img = null;
            try {
                img = ImageIO.read(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage resized_image = resizeImage(img,50,50);
            ImageIO.write(resized_image, "png", outputCropped);

            String message = "";
            return "Uploaded the file successfully: " + file.getOriginalFilename();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    private static void addTextWatermark(String wartermark, String type, File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);

        // determine image type and handle correct transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // initializes necessary graphic properties
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
        w.setComposite(alphaChannel);
        w.setColor(Color.GRAY);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 150));
        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(wartermark, w);

        // calculate center of the image
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        // add wartermark overlay to the image
        w.drawString(wartermark, centerX, centerY);
        ImageIO.write(watermarked, type, destination);
        w.dispose();
    }

    public File removeWatermark(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        // Add a unique folder using UUID
        String uuidFolder = UUID.randomUUID().toString();
        String workFolder = uploadOriginalPath + "/" + uuidFolder;

        // Create the folder if it doesn't exist
        Path root = Paths.get(workFolder);
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        // Save original file
        Files.copy(file.getInputStream(), root.resolve(fileName));
        // Get the file extension
        String fileExt = FileUtils.getExtension(new File(fileName));
        // New file name
        String newCroppedFileName = FileUtils.getFileNameWithoutExtension(fileName) + "_remove_watermark."+ fileExt;

        File input = new File(workFolder + "/" + fileName);
        File output = new File(workFolder + "/" + newCroppedFileName);


        // Đọc file MultipartFile thành BufferedImage
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

        // Kích thước và vị trí vùng watermark
        int watermarkX = 100; // Xác định toạ độ x (ví dụ)
        int watermarkY = 100; // Xác định toạ độ y (ví dụ)
        int watermarkWidth = 200; // Chiều rộng watermark
        int watermarkHeight = 50;  // Chiều cao watermark

        // Áp dụng làm mờ vùng watermark
//        BufferedImage processedImage = removeWatermarkWithBlur(originalImage, watermarkX, watermarkY, watermarkWidth, watermarkHeight);
        BufferedImage processedImage = removeWatermarkByColor(originalImage, new Color(128, 128, 128, 102),
                new Color(255, 255, 255));

        ImageIO.write(processedImage, fileExt, output);

        return output;
    }

    private BufferedImage removeWatermarkWithBlur(BufferedImage image, int x, int y, int width, int height) {
        // Kernel để áp dụng làm mờ
        float[] kernel = {
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f,
                1 / 9f, 1 / 9f, 1 / 9f
        };
        ConvolveOp blur = new ConvolveOp(new Kernel(3, 3, kernel), ConvolveOp.EDGE_NO_OP, null);

        // Chỉ áp dụng làm mờ cho vùng watermark
        BufferedImage subImage = image.getSubimage(x, y, width, height);
        BufferedImage blurredSubImage = blur.filter(subImage, null);

        // Vẽ vùng đã làm mờ trở lại ảnh gốc
        image.getGraphics().drawImage(blurredSubImage, x, y, null);
        return image;
    }

    public BufferedImage removeWatermarkByColor(BufferedImage image, Color watermarkColor, Color backgroundColor) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                if (new Color(rgb, true).equals(watermarkColor)) {
                    image.setRGB(x, y, backgroundColor.getRGB());
                }
            }
        }
        return image;
    }

    /**
     * Adds a text watermark to an image and saves the result to a destination file.
     * The watermark is applied in a grid pattern across the image with specified transparency.
     *
     * 50 and 80 are the transparency values
     *
     * @param watermark   the text to be used as the watermark
     * @param type        the image format type (e.g., "png", "jpg")
     * @param source      the source image file to which the watermark will be added
     * @param destination the destination file where the watermarked image will be saved
     * @throws IOException if an I/O error occurs during reading or writing the image
     */
    private static void addTextWatermark2(String watermark, String type, File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);

        // Determine image type and handle transparency
        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);

        // Initialize graphics
        Graphics2D w = (Graphics2D) watermarked.getGraphics();
        w.drawImage(image, 0, 0, null);

        // Set transparency
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f); // Adjust transparency here
        w.setComposite(alphaChannel);
        w.setColor(Color.GRAY);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50)); // Adjust font size here

        // Calculate watermark dimensions
        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(watermark, w);

        int watermarkWidth = (int) rect.getWidth();
        int watermarkHeight = (int) rect.getHeight();

        // Add watermark in a grid pattern
        for (int y = 0; y < image.getHeight(); y += watermarkHeight + 50) { // Adjust spacing here
            for (int x = 0; x < image.getWidth(); x += watermarkWidth + 80) { // Adjust spacing here
                w.drawString(watermark, x, y + fontMetrics.getAscent());
            }
        }

        // Save the watermarked image
        ImageIO.write(watermarked, type, destination);
        w.dispose();
    }


    BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}