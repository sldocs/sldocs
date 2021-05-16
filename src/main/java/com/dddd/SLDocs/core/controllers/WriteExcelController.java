package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.views.EAS_VM;
import com.dddd.SLDocs.core.servImpls.DisciplineServiceImpl;
import com.dddd.SLDocs.core.servImpls.EAS_VMServiceImpl;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class WriteExcelController {

    private final String space_regex = "\\s+";
    private final String comma_regex = ",";
    private final String group_regex = "^([\\p{L}]{2})([-])([0-9]{3}|[\\p{L}][0-9]{3})([і].*|.[і]|[\\p{L}]*)?$";

    private final DisciplineServiceImpl disciplineService;
    private final EAS_VMServiceImpl eas_vmService;

    public WriteExcelController(DisciplineServiceImpl disciplineService, EAS_VMServiceImpl eas_vmService) {
        this.disciplineService = disciplineService;
        this.eas_vmService = eas_vmService;
    }

    @RequestMapping("EdAsSt")
    public String createExcel() {
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
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = 5;
            List<EAS_VM> data = eas_vmService.getEAS_VM13Data("1", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false);

            sheet = workbook.getSheetAt(1);
            rowCount = 5;
            data = eas_vmService.getEAS_VM4Data("1", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false);

            sheet = workbook.getSheetAt(2);
            rowCount = 5;
            data = eas_vmService.getEAS_VM13Data("2", "4.0");

            writeSheet(font, style, sheet, rowCount, data, false);

            sheet = workbook.getSheetAt(3);
            rowCount = 5;
            data = eas_vmService.getEAS_VM4Data("2", "4.0");

            writeSheet(font, style, sheet, rowCount, data, true);

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream("EdAsSt.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        return "redirect:/";
    }

    private void writeSheet(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, Boolean divider) {


        for (int i = 0; i < data.size(); i++) {

            if ((!(data.get(i).getLec_hours().equals("") | data.get(i).getLec_hours().equals("0.0"))) &&
                    (data.get(i).getLab_hours().equals("") | data.get(i).getLab_hours().equals("0.0")) &&
                    (data.get(i).getPract_hours().equals("") | data.get(i).getPract_hours().equals("0.0"))) {
                rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider);
            } else {
                if ((!(data.get(i).getLec_hours().equals("") | data.get(i).getLec_hours().equals("0.0"))) &&
                        (!(data.get(i).getLab_hours().equals("") | data.get(i).getLab_hours().equals("0.0"))) &&
                        (data.get(i).getPract_hours().equals("") | data.get(i).getPract_hours().equals("0.0"))) {
                    rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider);

                    rowCount = writeLab_hours(font, style, sheet, rowCount, data, i, divider);
                } else {
                    if ((!(data.get(i).getLec_hours().equals("") | data.get(i).getLec_hours().equals("0.0"))) &&
                            (data.get(i).getLab_hours().equals("") | data.get(i).getLab_hours().equals("0.0")) &&
                            (!(data.get(i).getPract_hours().equals("") | data.get(i).getPract_hours().equals("0.0")))) {
                        rowCount = writeLec_hours(font, style, sheet, rowCount, data, i, divider);

                        rowCount = writePract_hours(font, style, sheet, rowCount, data, i, divider);
                    } else {
                        if ((!(data.get(i).getLab_hours().equals("") | data.get(i).getLab_hours().equals("0.0"))) &&
                                ((data.get(i).getPract_hours().equals("") | data.get(i).getPract_hours().equals("0.0")))) {
                            rowCount = writeLab_hours(font, style, sheet, rowCount, data, i, divider);
                        } else {
                            if ((data.get(i).getLab_hours().equals("") | data.get(i).getLab_hours().equals("0.0")) &&
                                    ((!(data.get(i).getPract_hours().equals("") | data.get(i).getPract_hours().equals("0.0"))))) {
                                rowCount = writePract_hours(font, style, sheet, rowCount, data, i, divider);
                            }
                        }
                    }
                }
            }
        }
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
    }

    private int writePract_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i, Boolean divider) {
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
                        if (matcher.group(4).charAt(0) == 'і' | matcher.group(4).charAt(1) == 'і' | matcher.group(4).charAt(1) == 'е' | matcher.group(4).charAt(1) == '.') {
                            rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                        } else {
                            for (int i1 = 0; i1 < matcher.group(4).length(); i1++) {
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(matcher.group(1) + matcher.group(2) + matcher.group(3) + matcher.group(4).charAt(i1));
                                row = sheet.createRow(++rowCount);
                                cell = row.createCell(0);
                                cell = writeDnGn(font, style, data, i, row, cell);
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
                        rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                    }
                } else {
                    rowCount = writePract_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                }
            }
        }
        return rowCount;
    }

    private int writePract_hours_inner(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i, Matcher matcher, Boolean divider) {
        Row row;
        Cell cell;
        row = sheet.createRow(++rowCount);
        cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell);
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

    private void writePract_hours_inner_inner(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell, Boolean divider) {
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

    private int writeLab_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i, Boolean divider) {
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
                        if (matcher.group(4).charAt(0) == 'і' | matcher.group(4).charAt(1) == 'і' | matcher.group(4).charAt(1) == 'е' | matcher.group(4).charAt(1) == '.') {
                            rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                        } else {
                            for (int i1 = 0; i1 < matcher.group(4).length(); i1++) {
                                stringBuffer = new StringBuffer();
                                stringBuffer.append(matcher.group(1) + matcher.group(2) + matcher.group(3) + matcher.group(4).charAt(i1));
                                row = sheet.createRow(++rowCount);
                                cell = row.createCell(0);
                                cell = writeDnGn(font, style, data, i, row, cell);
                                cell.setCellValue(stringBuffer.toString());
                                cell = row.createCell(3);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                cell.setCellValue("");
                                cell = row.createCell(4);
                                style.setFont(font);
                                cell.setCellStyle(style);
                                writeLab_hours_inner_inner(font, style, data, i, row, cell, divider);
                            }
                        }
                    } else {
                        rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                    }
                } else {
                    rowCount = writeLab_hours_inner(font, style, sheet, rowCount, data, i, matcher, divider);
                }
            }
        }
        return rowCount;
    }

    private int writeLab_hours_inner(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i, Matcher matcher, Boolean divider) {
        Row row;
        Cell cell;
        row = sheet.createRow(++rowCount);
        cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell);
        cell.setCellValue(matcher.group(0));
        cell = row.createCell(3);
        style.setFont(font);
        cell.setCellStyle(style);
        cell.setCellValue("");
        cell = row.createCell(4);
        style.setFont(font);
        cell.setCellStyle(style);
        writeLab_hours_inner_inner(font, style, data, i, row, cell, divider);
        return rowCount;
    }

    private void writeLab_hours_inner_inner(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell, Boolean divider) {
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

    private int writeLec_hours(XSSFFont font, CellStyle style, XSSFSheet sheet, int rowCount, List<EAS_VM> data, int i, Boolean divider) {
        Row row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(0);
        cell = writeDnGn(font, style, data, i, row, cell);
        cell.setCellValue(data.get(i).getGroup_names());
        cell = row.createCell(3);
        style.setFont(font);
        cell.setCellStyle(style);
        if (divider) {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLec_hours())/10));
        } else {
            cell.setCellValue(Double.toString(Double.parseDouble(data.get(i).getLec_hours())/16));
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

    private Cell writeDnGn(XSSFFont font, CellStyle style, List<EAS_VM> data, int i, Row row, Cell cell) {
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
        return cell;
    }
}
