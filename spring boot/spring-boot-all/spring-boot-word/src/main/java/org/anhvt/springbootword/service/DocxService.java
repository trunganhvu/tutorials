package org.anhvt.springbootword.service;

import fr.opensagres.poi.xwpf.converter.xhtml.Base64EmbedImgManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DocxService {
    @Autowired
    private TemplateEngine templateEngine;

    public byte[] exportDocx() {
        try (XWPFDocument document = new XWPFDocument()) {
            // Tạo nội dung trong file .docx
            createDocxContent(document);

            // Ghi dữ liệu vào file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.write(outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void createDocxContent(XWPFDocument document) {
        // Tạo tiêu đề
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleRun = title.createRun();
        titleRun.setText("Example DOCX Export");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // Tạo đoạn văn bản
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("This is an example of exporting a .docx file from Spring Boot.");
        paragraphRun.setFontSize(12);

        // Tạo bảng
        XWPFTable table = document.createTable();
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("Column 1");
        tableRowOne.addNewTableCell().setText("Column 2");

        XWPFTableRow tableRowTwo = table.createRow();
        tableRowTwo.getCell(0).setText("Value 1");
        tableRowTwo.getCell(1).setText("Value 2");
    }

    /**
     * WIP
     * @return
     */
    public byte[] exportDocxFromTemplate() {
        // Tạo nội dung từ template HTML
        String htmlContent = renderHtmlContent();
        try (XWPFDocument document = new XWPFDocument()) {
            document.createParagraph().createRun().setText(htmlContent);
            document.createStyles();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            XHTMLOptions options = XHTMLOptions.create().indent(4).setImageManager(new Base64EmbedImgManager());
            XHTMLConverter.getInstance().convert(document, outputStream, options);
            document.write(outputStream);

            FileOutputStream out = new FileOutputStream("example.docx");
            document.write(out);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String renderHtmlContent() {
        Context context = new Context();
        context.setVariable("title", "Example DOCX Export with Template");
        context.setVariable("content", "This is content generated from a template.");
        context.setVariable("rows", List.of(
                Map.of("column1", "Value 1", "column2", "Value 2"),
                Map.of("column1", "Value 3", "column2", "Value 4")
        ));

        return templateEngine.process("docxTemplate", context);
    }
}
