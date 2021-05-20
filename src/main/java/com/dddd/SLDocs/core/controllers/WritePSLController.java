package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.entities.views.PSL_VM;
import com.dddd.SLDocs.core.servImpls.PSL_VMServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class WritePSLController {

    private final PSL_VMServiceImpl pls_vmService;
    private final ProfessorServiceImpl professorService;

    public WritePSLController(PSL_VMServiceImpl pls_vmService, ProfessorServiceImpl professorService) {
        this.pls_vmService = pls_vmService;
        this.professorService = professorService;
    }

    @RequestMapping("PSL")
    public String writePSL() {

        long m = System.currentTimeMillis();
        try {
            FileInputStream inputStream = new FileInputStream("PSLExample.xlsx");
            XSSFWorkbookFactory workbookFactory = new XSSFWorkbookFactory();
            XSSFWorkbook workbook = workbookFactory.create(inputStream);

            XSSFFont defaultFont = workbook.createFont();
            defaultFont.setFontHeightInPoints((short) 12);
            defaultFont.setFontName("Times New Roman");
            defaultFont.setBold(false);
            defaultFont.setItalic(false);

            XSSFFont font10 = workbook.createFont();
            font10.setFontHeightInPoints((short) 10);
            font10.setFontName("Times New Roman");
            font10.setBold(false);
            font10.setItalic(false);

            XSSFFont font14 = workbook.createFont();
            font14.setFontHeightInPoints((short) 14);
            font14.setFontName("Times New Roman");
            font14.setBold(false);
            font14.setItalic(false);

            XSSFFont font14Bold = workbook.createFont();
            font14Bold.setFontHeightInPoints((short) 14);
            font14Bold.setFontName("Times New Roman");
            font14Bold.setBold(true);
            font14Bold.setItalic(false);

            CellStyle style = workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFont(font10);
            style.setWrapText(true);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);


            CellStyle style14 = workbook.createCellStyle();
            style14.setVerticalAlignment(VerticalAlignment.CENTER);
            style14.setAlignment(HorizontalAlignment.CENTER);
            style14.setFont(font14);
            style14.setWrapText(true);
            style14.setBorderBottom(BorderStyle.THIN);
            style14.setBorderLeft(BorderStyle.THIN);
            style14.setBorderRight(BorderStyle.THIN);
            style14.setBorderTop(BorderStyle.THIN);

            CellStyle style14Bold = workbook.createCellStyle();
            style14Bold.setFont(font14Bold);
            style14Bold.setWrapText(true);
            style14Bold.setBorderBottom(BorderStyle.THIN);
            style14Bold.setBorderLeft(BorderStyle.THIN);
            style14Bold.setBorderRight(BorderStyle.THIN);
            style14Bold.setBorderTop(BorderStyle.THIN);

            CellStyle style14RightAl = workbook.createCellStyle();
            style14RightAl.setVerticalAlignment(VerticalAlignment.CENTER);
            style14RightAl.setAlignment(HorizontalAlignment.RIGHT);
            style14RightAl.setBorderBottom(BorderStyle.THIN);
            style14RightAl.setBorderLeft(BorderStyle.THIN);
            style14RightAl.setBorderRight(BorderStyle.THIN);
            style14RightAl.setBorderTop(BorderStyle.THIN);
            style14RightAl.setFont(font14);
            style14RightAl.setWrapText(true);

            CellStyle styleThickBotBord = workbook.createCellStyle();
            styleThickBotBord.setFont(font14Bold);
            styleThickBotBord.setWrapText(true);
            styleThickBotBord.setBorderBottom(BorderStyle.THICK);
            styleThickBotBord.setBorderLeft(BorderStyle.THIN);
            styleThickBotBord.setBorderRight(BorderStyle.THIN);
            styleThickBotBord.setBorderTop(BorderStyle.THIN);

            CellStyle styleThickBotRightBord = workbook.createCellStyle();
            styleThickBotRightBord.setFont(font14Bold);
            styleThickBotRightBord.setWrapText(true);
            styleThickBotRightBord.setBorderBottom(BorderStyle.THICK);
            styleThickBotRightBord.setBorderLeft(BorderStyle.THIN);
            styleThickBotRightBord.setBorderRight(BorderStyle.THICK);
            styleThickBotRightBord.setBorderTop(BorderStyle.THIN);

            CellStyle styleThickBotTopBord = workbook.createCellStyle();
            styleThickBotTopBord.setFont(font14Bold);
            styleThickBotTopBord.setWrapText(true);
            styleThickBotTopBord.setBorderBottom(BorderStyle.THICK);
            styleThickBotTopBord.setBorderLeft(BorderStyle.THIN);
            styleThickBotTopBord.setBorderRight(BorderStyle.THIN);
            styleThickBotTopBord.setBorderTop(BorderStyle.THICK);

            CellStyle styleThickBotTopRightBord = workbook.createCellStyle();
            styleThickBotTopRightBord.setFont(font14Bold);
            styleThickBotTopRightBord.setWrapText(true);
            styleThickBotTopRightBord.setBorderBottom(BorderStyle.THICK);
            styleThickBotTopRightBord.setBorderLeft(BorderStyle.THIN);
            styleThickBotTopRightBord.setBorderRight(BorderStyle.THICK);
            styleThickBotTopRightBord.setBorderTop(BorderStyle.THICK);

            CellStyle styleThickRightBord = workbook.createCellStyle();
            styleThickRightBord.setFont(font14);
            styleThickRightBord.setWrapText(true);
            styleThickRightBord.setBorderBottom(BorderStyle.THIN);
            styleThickRightBord.setBorderLeft(BorderStyle.THIN);
            styleThickRightBord.setBorderRight(BorderStyle.THICK);
            styleThickRightBord.setBorderTop(BorderStyle.THIN);

            XSSFCellStyle rowAutoHeightStyle = workbook.createCellStyle();
            rowAutoHeightStyle.setWrapText(true);

            List<Professor> professors = professorService.ListAll();

            XSSFRow row;
            XSSFCell cell;

            int last_vert_cell_sum;
            int rownum;
            for (Professor professor : professors) {
                if (!professor.getName().equals("")) {
                    List<PSL_VM> psl_vmList;
                    if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0
                            | pls_vmService.getPSL_VMData("2", professor.getName()).size() != 0) {
                        XSSFSheet sheet = workbook.cloneSheet(1, professor.getName());
                        row = sheet.getRow(2);
                        cell = row.getCell(0);
                        cell.setCellValue(professor.getName());
                        cell.setCellStyle(style14);

                        cell = row.createCell(4);
                        cell.setCellValue(professor.getStavka());
                        cell.setCellStyle(style14);

                        cell = row.getCell(5);
                        if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("1", professor.getName());
                        } else {
                            psl_vmList = pls_vmService.getPSL_VMData("2", professor.getName());
                        }
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 5, 18);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell.setCellStyle(style14);
                        RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, sheet);
                        RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, sheet);
                        RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, sheet);
                        RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, sheet);
                        cell.setCellValue("Кафедра " + psl_vmList.get(0).getDep_name() + " на " + psl_vmList.get(0).getYear());
                        cell.setCellStyle(style14);
                        rownum = 5;
                        if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("1", professor.getName());
                            for (PSL_VM psl_vm : psl_vmList) {
                                row = sheet.createRow(++rownum);
                                cell = row.createCell(0);
                                cell = writeDisciplines(style, row, cell, psl_vm);
                                cell.setCellFormula("SUM(E" + (rownum + 1) + ":S" + (rownum + 1) + ")");
                                cell.setCellStyle(style);
                                row.setRowStyle(rowAutoHeightStyle);
                            }
                        }
                        rownum = writeLine(style, rownum, sheet);
                        String[] ends1 = {"КЕРІВНИЦТВО", "здобувач"};
                        rownum = writeKerivnictvo(style, style14Bold, rownum, sheet, ends1);
                        String[] ends2 = {"Аспірант", "Докторант", "Магістр", "Фахівець", "Курсові", "Курсові 5 курс"};
                        rownum = writeKerivnictvo(style, style14RightAl, rownum, sheet, ends2);


                        int autumn_sum=rownum;
                        row = sheet.createRow(rownum++);
                        cell = row.createCell(0);
                        cell.setCellValue("Усього за осінь");
                        cell.setCellStyle(styleThickBotTopBord);

                        for (int k = 1; k < 4; k++) {
                            cell = row.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(styleThickBotTopBord);
                        }
                        String[] sums = {"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
                        int cell_count = 4;
                        last_vert_cell_sum = rownum - 2;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + "7:" + sum + last_vert_cell_sum + "),0)");
                            cell.setCellStyle(styleThickBotTopBord);
                        }
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("ROUND(SUM(T7:" + "T" + last_vert_cell_sum + "),0)");
                        cell.setCellStyle(styleThickBotTopRightBord);

                        row = sheet.createRow(rownum++);
                        cell = row.createCell(0);
                        cell.setCellValue("весна");
                        cell.setCellStyle(style14);
                        sheet.addMergedRegion(new CellRangeAddress(
                                rownum - 1, //first row (0-based)
                                rownum - 1, //last row  (0-based)
                                0, //first column (0-based)
                                19  //last column  (0-based)
                        ));
                        row = sheet.createRow(rownum++);
                        cell = row.createCell(0);
                        cell.setCellValue("Норматив");
                        cell.setCellStyle(styleThickBotTopBord);
                        for (int n = 1; n < 19; n++) {
                            switch (n) {
                                case 8:
                                    cell = row.createCell(n);
                                    cell.setCellValue("0,25");
                                    cell.setCellStyle(styleThickBotTopBord);
                                    break;
                                case 11:
                                    cell = row.createCell(n);
                                    cell.setCellValue("0,33");
                                    cell.setCellStyle(styleThickBotTopBord);
                                    break;
                                case 13 | 14:
                                    cell = row.createCell(n);
                                    cell.setCellValue("0,5");
                                    cell.setCellStyle(styleThickBotTopBord);
                                    break;
                                default:
                                    cell = row.createCell(n);
                                    cell.setCellValue("");
                                    cell.setCellStyle(styleThickBotTopBord);
                                    break;
                            }
                        }
                        cell = row.createCell(19);
                        cell.setCellValue("");
                        cell.setCellStyle(styleThickBotTopRightBord);

                        int first_sum_cell_spring = rownum;
                        if (pls_vmService.getPSL_VMData("2", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("2", professor.getName());
                            for (PSL_VM psl_vm : psl_vmList) {
                                row = sheet.createRow(rownum++);
                                cell = row.createCell(0);
                                cell = writeDisciplines(style, row, cell, psl_vm);
                                cell.setCellFormula("SUM(E" + rownum + ":S" + rownum + ")");
                                cell.setCellStyle(style);
                                row.setRowStyle(rowAutoHeightStyle);
                            }
                        }
                        rownum = writeLine(style, rownum, sheet);

                        rownum = writeKerivnictvo(style, style14Bold, rownum, sheet, ends1);
                        rownum = writeKerivnictvo(style, style14RightAl, rownum, sheet, ends2);

                        row = sheet.createRow(rownum++);
                        cell = row.createCell(0);
                        cell.setCellValue("Усього за весну");
                        cell.setCellStyle(styleThickBotTopBord);

                        for (int k = 1; k < 4; k++) {
                            cell = row.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(styleThickBotTopBord);
                        }
                        cell_count = 4;
                        last_vert_cell_sum = rownum - 2;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + first_sum_cell_spring + ":" + sum + last_vert_cell_sum + "),0)");
                            cell.setCellStyle(styleThickBotTopBord);
                        }
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("ROUND(SUM(T" + first_sum_cell_spring + ":" + "T" + last_vert_cell_sum + "),0)");
                        cell.setCellStyle(styleThickBotTopRightBord);

                        row = sheet.createRow(rownum);
                        cell = row.createCell(0);
                        cell.setCellValue("Загальний ПІДСУМОК");
                        cell.setCellStyle(styleThickBotBord);


                        for (int k = 1; k < 4; k++) {
                            cell = row.createCell(k);
                            cell.setCellValue("");
                            cell.setCellStyle(styleThickBotBord);
                        }
                        cell_count = 4;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + (autumn_sum+1) + "+" + sum +rownum + "),0)");
                            cell.setCellStyle(styleThickBotBord);
                        }
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("ROUND(SUM(T" + (autumn_sum+1) + "+" + "T" + rownum + "),0)");
                        cell.setCellStyle(styleThickBotTopRightBord);
                        sheet.setFitToPage(true);
                        sheet.getPrintSetup().setLandscape(true);
                    }
                }
            }

            workbook.removeSheetAt(1);
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream("Навантаження_по_викладачах_20_21.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - m);
        return "redirect:/";
    }

    private int writeLine(CellStyle style, int rownum, XSSFSheet sheet) {
        XSSFRow row;
        XSSFCell cell;
        row = sheet.createRow(rownum++);
        for (int l = 0; l < 19; l++) {
            cell = row.createCell(l);
            cell.setCellStyle(style);
        }
        cell = row.createCell(19);
        cell.setCellStyle(style);
        return rownum;
    }

    private XSSFCell writeDisciplines(CellStyle style, XSSFRow row, XSSFCell cell, PSL_VM psl_vm) {
        cell.setCellValue(psl_vm.getDname());
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(psl_vm.getCourse());
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue(psl_vm.getStudents_number());
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue(psl_vm.getGroup_names());
        cell.setCellStyle(style);
        cell = row.createCell(4);
        if (psl_vm.getLec_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getLec_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getLec_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(5);
        if (psl_vm.getConsult_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getConsult_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getConsult_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(6);
        if (psl_vm.getLab_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getLab_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getLab_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(7);
        if (psl_vm.getPract_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getPract_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getPract_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(8);
        if (psl_vm.getInd_task_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getInd_task_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getInd_task_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(9);
        if (psl_vm.getCp_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getCp_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getCp_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(10);
        if (psl_vm.getZalik_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getZalik_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getZalik_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(11);
        if (psl_vm.getExam_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getExam_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getExam_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(12);
        if (psl_vm.getDiploma_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getDiploma_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getDiploma_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(13);
        if (psl_vm.getDec_cell().isEmpty()) {
            cell.setCellValue(psl_vm.getDec_cell());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getDec_cell()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(14);
        if (psl_vm.getNdrs().isEmpty()) {
            cell.setCellValue(psl_vm.getNdrs());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getNdrs()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(15);
        if (psl_vm.getAspirant_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getAspirant_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getAspirant_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(16);
        if (psl_vm.getPractice().isEmpty()) {
            cell.setCellValue(psl_vm.getPractice());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getPractice()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue(0);
        cell.setCellStyle(style);
        cell = row.createCell(18);
        if (psl_vm.getOther_forms_hours().isEmpty()) {
            cell.setCellValue(psl_vm.getOther_forms_hours());
        } else {
            cell.setCellValue(Double.parseDouble(psl_vm.getOther_forms_hours()));
        }
        cell.setCellStyle(style);
        cell = row.createCell(19);
        return cell;
    }

    private int writeKerivnictvo(CellStyle style, CellStyle style14Bold, int rownum, XSSFSheet sheet, String[] ends1) {
        XSSFRow row;
        XSSFCell cell;
        for (String end : ends1) {
            row = sheet.createRow(rownum++);
            cell = row.createCell(0);
            cell.setCellValue(end);
            cell.setCellStyle(style14Bold);
            for (int l = 1; l < 20; l++) {
                cell = row.createCell(l);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }
        }
        return rownum;
    }
}
