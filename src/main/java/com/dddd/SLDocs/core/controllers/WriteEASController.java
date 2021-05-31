package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.views.EAS_VM;
import com.dddd.SLDocs.core.servImpls.EAS_VMServiceImpl;
import com.dddd.SLDocs.core.servImpls.FacultyServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class WriteEASController {

    private final String comma_regex = ",";
    private final String group_regex = "^([\\p{L}]{2})([-])([0-9]{3}|[\\p{L}][0-9]{3})(.[.].*|[і].*|.[і].*|[\\p{L}]*)?$";

    private final EAS_VMServiceImpl eas_vmService;
    private final FacultyServiceImpl facultyService;

    public WriteEASController(EAS_VMServiceImpl eas_vmService, FacultyServiceImpl facultyService) {
        this.eas_vmService = eas_vmService;
        this.facultyService = facultyService;
    }

    @RequestMapping("/EdAsSt")
    public String createExcel() {
        long m = System.currentTimeMillis();
        try {
            FileInputStream inputStream = new FileInputStream(new File("EdAsStExample.xlsx"));
            XSSFWorkbookFactory workbookFactory = new XSSFWorkbookFactory();
            XSSFWorkbook workbook = workbookFactory.create(inputStream);

            XSSFFont defaultFont = workbook.createFont();
            defaultFont.setFontHeightInPoints((short) 12);
            defaultFont.setFontName("Times New Roman");
            defaultFont.setColor(IndexedColors.BLACK.getIndex());
            defaultFont.setBold(false);
            defaultFont.setItalic(false);

            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Times New Roman");
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(false);
            font.setItalic(false);


            CellStyle style = workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setWrapText(true);


            XSSFCellStyle rowAutoHeightStyle = workbook.createCellStyle();
            rowAutoHeightStyle.setWrapText(true);

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = 5;
            List<EAS_VM> data = eas_vmService.getEAS_VM13Data("1", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false, workbook, rowAutoHeightStyle);

            sheet = workbook.getSheetAt(1);
            rowCount = 5;
            data = eas_vmService.getEAS_VM4Data("1", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false, workbook, rowAutoHeightStyle);

            sheet = workbook.getSheetAt(2);
            rowCount = 5;
            data = eas_vmService.getEAS_VM13Data("2", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false, workbook, rowAutoHeightStyle);

            sheet = workbook.getSheetAt(3);
            rowCount = 5;
            data = eas_vmService.getEAS_VM4Data("2", "4.0");

            writeSheet(font, style, sheet, rowCount, data, true, workbook, rowAutoHeightStyle);

            inputStream.close();
            File someFile = new File("Відомість_учбових_доручень.xlsx");
            FileOutputStream outputStream = new FileOutputStream(someFile);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            List<Faculty> faculties = facultyService.ListAll();
            faculties.get(0).setEas_filename(someFile.getName());
            facultyService.save(faculties.get(0));

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - m);
        return "redirect:/";
    }

    private void writeSheet(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount,
                            List<EAS_VM> data, Boolean divider, XSSFWorkbook workbook, XSSFCellStyle rowAutoHeightStyle) {


        for (int i = 0; i < data.size(); i++) {

            if ((!(data.get(i).getLec_hours().equals("") || data.get(i).getLec_hours().equals("0.0"))) &&
                    (data.get(i).getLab_hours().equals("") || data.get(i).getLab_hours().equals("0.0")) &&
                    (data.get(i).getPract_hours().equals("") || data.get(i).getPract_hours().equals("0.0"))) {
                rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);
            } else {
                if ((!(data.get(i).getLec_hours().equals("") || data.get(i).getLec_hours().equals("0.0"))) &&
                        (!(data.get(i).getLab_hours().equals("") || data.get(i).getLab_hours().equals("0.0"))) &&
                        (data.get(i).getPract_hours().equals("") || data.get(i).getPract_hours().equals("0.0"))) {
                    rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);

                    rowCount = writeLab_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);
                } else {
                    if ((!(data.get(i).getLec_hours().equals("") || data.get(i).getLec_hours().equals("0.0"))) &&
                            (data.get(i).getLab_hours().equals("") || data.get(i).getLab_hours().equals("0.0")) &&
                            (!(data.get(i).getPract_hours().equals("") || data.get(i).getPract_hours().equals("0.0")))) {
                        rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);

                        rowCount = writePract_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);
                    } else {
                        if ((!(data.get(i).getLab_hours().equals("") || data.get(i).getLab_hours().equals("0.0"))) &&
                                ((data.get(i).getPract_hours().equals("") || data.get(i).getPract_hours().equals("0.0")))) {
                            rowCount = writeLab_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);
                        } else {
                            if ((data.get(i).getLab_hours().equals("") || data.get(i).getLab_hours().equals("0.0")) &&
                                    ((!(data.get(i).getPract_hours().equals("") || data.get(i).getPract_hours().equals("0.0"))))) {
                                rowCount = writePract_hours(font, style, sheet, rowCount, data, i, divider, rowAutoHeightStyle);
                            }
                        }
                    }
                }
            }
        }
        int rows = 6;
        DataFormatter df = new DataFormatter();
        XSSFRow row;
        while (true) {
            row = sheet.getRow(rows);
            try {
                if (df.formatCellValue(row.getCell(0)).equals("")) {
                    break;
                }
                rows++;
            } catch (NullPointerException ex) {
                break;
            }
        }

        CellStyle styleBig = workbook.createCellStyle();
        styleBig.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBig.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont fontBig = workbook.createFont();
        fontBig.setFontHeightInPoints((short) 24);
        fontBig.setFontName("Times New Roman");
        fontBig.setColor(IndexedColors.BLACK.getIndex());
        fontBig.setBold(true);
        fontBig.setItalic(false);

        row = sheet.getRow(2);
        String space_regex = "\\s+";
        String[] res = row.getCell(0).toString().split(space_regex);
        StringBuilder stringBuilder = new StringBuilder();
        for (int p = 0; p < res.length; p++) {
            if (p == 4) {
                stringBuilder.append(data.get(0).getYear()).append(" ");
            } else {
                stringBuilder.append(res[p]).append(" ");
            }
        }
        row.getCell(0).setCellValue(stringBuilder.toString());
        styleBig.setFont(fontBig);
        row.getCell(0).setCellStyle(styleBig);


        row = sheet.createRow(rows + 1);
        Cell cell = row.createCell(1);
        cell.setCellValue("\"_____\" ______________________ 2020 р.");
        CellStyle style2 = workbook.createCellStyle();
        XSSFFont fontCustom = workbook.createFont();
        fontCustom.setFontHeightInPoints((short) 12);
        fontCustom.setFontName("Times New Roman");
        fontCustom.setColor(IndexedColors.BLACK.getIndex());
        fontCustom.setItalic(true);
        style2.setFont(fontCustom);
        cell.setCellStyle(style2);
        cell = row.createCell(2);
        cell.setCellValue("В.о. зав. кафедрою");
        style2 = workbook.createCellStyle();
        fontCustom = workbook.createFont();
        fontCustom.setFontHeightInPoints((short) 12);
        fontCustom.setFontName("Times New Roman");
        fontCustom.setColor(IndexedColors.BLACK.getIndex());
        fontCustom.setItalic(true);
        fontCustom.setBold(true);
        style2.setFont(fontCustom);
        cell.setCellStyle(style2);
        cell = row.createCell(4);
        style2 = workbook.createCellStyle();
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style2);
        cell = row.createCell(5);
        style2 = workbook.createCellStyle();
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style2);
        cell = row.createCell(6);
        cell.setCellValue("Михайло ГОДЛЕВСЬКИЙ");
        style2 = workbook.createCellStyle();
        style2.setFont(font);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style2);
        row = sheet.createRow(rows + 2);
        cell = row.createCell(4);
        cell.setCellValue("(підпис)");
        sheet.setFitToPage(true);
        sheet.getPrintSetup().setLandscape(true);
    }

    private int writePract_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i,
                                 Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        Row row;
        Cell cell;
        StringBuffer stringBuffer;
        String[] values_groups = data.get(i).getGroup_names().split(comma_regex);
        Pattern pattern = Pattern.compile(group_regex);
        Matcher matcher;
        for (int j = 0; j < values_groups.length; j++) {
            matcher = pattern.matcher(values_groups[j].trim());
            while (matcher.find()) {
                if (Character.isDigit(matcher.group(3).charAt(0))) {
                    if (!matcher.group(4).equals("") && matcher.group(4).length() > 1) {
                        if (matcher.group(4).charAt(0) == 'і' || matcher.group(4).charAt(1) == 'і' || matcher.group(4).charAt(1) == 'е' || matcher.group(4).charAt(1) == '.') {
                            rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                        } else {
                            for (int i1 = 0; i1 < matcher.group(4).length(); i1++) {
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(matcher.group(1) + matcher.group(2) + matcher.group(3) + matcher.group(4).charAt(i1));
                                row = sheet.createRow(++rowCount);
                                cell = row.createCell(0);
                                cell = writeDnGn(font, style, data, i, row, cell, rowAutoHeightStyle);
                                cell.setCellValue(stringBuffer.toString());
                                cell = row.createCell(3);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                cell.setCellValue("");
                                cell = row.createCell(4);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                writePract_hours_inner_inner(font, style, data, i, row, cell, divider);
                            }
                        }
                    } else {
                        rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                    }
                } else {
                    rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                }
            }
        }
        return rowCount;
    }

    private int writePract_hours_inner(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i,
                                       Matcher matcher, Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        Row row;
        Cell cell;
        row = sheet.createRow(++rowCount);
        cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell, rowAutoHeightStyle);
        cell.setCellValue(matcher.group(0));
        cell = row.createCell(3);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("");
        cell = row.createCell(4);
        style.setFont(font);
        cell.setCellStyle(style);
        writePract_hours_inner_inner(font, style, data, i, row, cell, divider);
        return rowCount;
    }

    private void writePract_hours_inner_inner(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell,
                                              Boolean divider) {
        cell.setCellValue("");
        cell = row.createCell(5);
        style.setFont(font);
        cell.setCellStyle(style);
        if (divider) {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getPract_hours()) /
                    Double.parseDouble(data.get(i).getNumber_of_subgroups()) / 10));
        } else {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getPract_hours()) /
                    Double.parseDouble(data.get(i).getNumber_of_subgroups()) / 16));
        }
        cell = row.createCell(6);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(data.get(i).getPname());
        cell = row.createCell(7);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(data.get(i).getNote());
    }

    private int writeLab_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i,
                               Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        Row row;
        Cell cell;
        StringBuffer stringBuffer;
        String[] values_groups = data.get(i).getGroup_names().split(comma_regex);
        Pattern pattern = Pattern.compile(group_regex);
        Matcher matcher;
        for (int j = 0; j < values_groups.length; j++) {
            matcher = pattern.matcher(values_groups[j].trim());
            while (matcher.find()) {
                if (Character.isDigit(matcher.group(3).charAt(0))) {
                    if (!matcher.group(4).equals("") && matcher.group(4).length() > 1) {
                        if (matcher.group(4).charAt(0) == 'і' || matcher.group(4).charAt(1) == 'і' || matcher.group(4).charAt(1) == 'е' || matcher.group(4).charAt(1) == '.') {
                            rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                        } else {
                            for (int i1 = 0; i1 < matcher.group(4).length(); i1++) {
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(matcher.group(1) + matcher.group(2) + matcher.group(3) + matcher.group(4).charAt(i1));
                                row = sheet.createRow(++rowCount);
                                cell = row.createCell(0);
                                cell = writeDnGn(font, style, data, i, row, cell, rowAutoHeightStyle);
                                cell.setCellValue(stringBuffer.toString());
                                cell = row.createCell(3);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                cell.setCellValue("");
                                cell = row.createCell(4);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                writeLab_hours_inner_inner(font, style, data, i, row, cell, divider, rowAutoHeightStyle);
                            }
                        }
                    } else {
                        rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                    }
                } else {
                    rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider, rowAutoHeightStyle);
                }
            }
        }
        return rowCount;
    }

    private int writeLab_hours_inner(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i,
                                     Matcher matcher, Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        Row row;
        Cell cell;
        row = sheet.createRow(++rowCount);
        cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell, rowAutoHeightStyle);
        cell.setCellValue(matcher.group(0));
        cell = row.createCell(3);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("");
        cell = row.createCell(4);
        style.setFont(font);
        cell.setCellStyle(style);
        writeLab_hours_inner_inner(font, style, data, i, row, cell, divider, rowAutoHeightStyle);
        return rowCount;
    }

    private void writeLab_hours_inner_inner(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell,
                                            Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        if (divider) {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLab_hours()) /
                    Double.parseDouble(data.get(i).getNumber_of_subgroups()) / 10));
        } else {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLab_hours()) /
                    Double.parseDouble(data.get(i).getNumber_of_subgroups()) / 16));
        }
        cell = row.createCell(5);
        style.setFont(font);
        cell.setCellStyle(style);
        writePnN(font, style, data, i, row, cell);
    }

    private int writeLec_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i,
                               Boolean divider, XSSFCellStyle rowAutoHeightStyle) {
        Row row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell, rowAutoHeightStyle);
        cell.setCellValue(data.get(i).getGroup_names());
        cell = row.createCell(3);
        style.setFont(font);
        cell.setCellStyle(style);
        if (divider) {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLec_hours()) / 10));
        } else {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLec_hours()) / 16));
        }
        cell = row.createCell(4);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("");
        cell = row.createCell(5);
        style.setFont(font);
        cell.setCellStyle(style);
        writePnN(font, style, data, i, row, cell);
        return rowCount;
    }

    private void writePnN(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell) {
        cell.setCellValue("");
        cell = row.createCell(6);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(data.get(i).getPname());
        cell = row.createCell(7);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(data.get(i).getNote());
    }

    private Cell writeDnGn(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell, XSSFCellStyle rowAutoHeightStyle) {
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(i + 1);
        cell = row.createCell(1);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue(data.get(i).getDname());
        cell = row.createCell(2);
        style.setFont(font);
        cell.setCellStyle(style);

        row.setRowStyle(rowAutoHeightStyle);
        return cell;
    }
}
