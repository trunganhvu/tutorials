package org.anhvt.springbootexcel.controller;

import org.anhvt.springbootexcel.entity.Product;
import org.anhvt.springbootexcel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ExcelService excelService;

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    /**
     * API Import sản phẩm từ file Excel
     */
    @PostMapping("/import")
    public ResponseEntity<List<Product>> importProducts(@RequestParam("file") MultipartFile file) {
        try {
            String tempFilePath = TEMP_DIR + File.separator + file.getOriginalFilename();
            file.transferTo(new File(tempFilePath));

            List<Product> products = excelService.readExcel(tempFilePath);

            // Xóa file tạm sau khi xử lý
            new File(tempFilePath).delete();

            return ResponseEntity.ok(products);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * API Export sản phẩm ra file Excel
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportProducts() {
        try {
            // Tạo dữ liệu mẫu (có thể thay thế bằng dữ liệu từ database)
            Product product1 = new Product("Laptop", "Electronics", 1500, 10);
            Product product2 = new Product("Phone", "Electronics", 800, 20);
            List<Product> products = List.of(
                    product1, product2
            );

            // Tạo file Excel tạm
            String tempFilePath = TEMP_DIR + File.separator + "products.xlsx";
            excelService.writeExcel(products, tempFilePath);

            // Đọc dữ liệu file để trả về cho client
            byte[] fileData = java.nio.file.Files.readAllBytes(new File(tempFilePath).toPath());

            // Xóa file tạm sau khi xử lý
            new File(tempFilePath).delete();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=products.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileData);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
