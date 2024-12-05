package org.anhvt.springbootexcel.service;

import org.anhvt.springbootexcel.entity.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
    private static CellStyle cellStyleFormatNumber = null;

    public void writeExcel(List<Product> products, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        // Create sheet
        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name
        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);
        // Write data
        rowIndex++;
        for (Product product : products) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(product, row);
            rowIndex++;
        }

        // Write footer
        writeFooter(sheet, rowIndex);

        // Auto resize column width
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
    }

    // Create dummy data
    private static List<Product> getBooks() {
        List<Product> products = new ArrayList<>();
        Product product;
        for (int i = 1; i <= 5; i++) {
            product = new Product("name " + i, "cate " + i, i * 2, i * 1000);
            products.add(product);
        }
        return products;
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Title");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quantity");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total money");
    }

    // Write data
    private static void writeBook(Product product, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(0);
        cell.setCellValue(product.getName());

        cell = row.createCell(1);
        cell.setCellValue(product.getCategory());

        cell = row.createCell(2);
        cell.setCellValue(product.getPrice());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(3);
        cell.setCellValue(product.getStock());

        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(4, CellType.FORMULA);
        cell.setCellStyle(cellStyleFormatNumber);
        int currentRow = row.getRowNum() + 1;
        String columnPrice = CellReference.convertNumToColString(2);
        String columnQuantity = CellReference.convertNumToColString(3);
        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(6, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    public static List<Product> readExcel(String excelFilePath) throws IOException {
        List<Product> products = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Product product = new Product();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        product.setName(cellValue.toString());
                        break;
                    case 1:
                        product.setCategory(cellValue.toString());
                        break;
                    case 2:
                        product.setPrice(((Double) getCellValue(cell)).intValue());
                        break;
                    case 3:
                        product.setStock(((Double) getCellValue(cell)).intValue());
                        break;
                    default:
                        break;
                }

            }
            products.add(product);
        }

        workbook.close();
        inputStream.close();

        return products;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
