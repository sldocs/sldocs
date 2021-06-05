package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.entities.views.PSL_VM;
import com.dddd.SLDocs.core.servImpls.FacultyServiceImpl;
import com.dddd.SLDocs.core.servImpls.PSL_VMServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class WriteIPController {
    private final PSL_VMServiceImpl pls_vmService;
    private final ProfessorServiceImpl professorService;
    private final FacultyServiceImpl facultyService;
    private double total_sum;

    public WriteIPController(PSL_VMServiceImpl pls_vmService, ProfessorServiceImpl professorService,
                             FacultyServiceImpl facultyService) {
        this.pls_vmService = pls_vmService;
        this.professorService = professorService;
        this.facultyService = facultyService;
    }


    @RequestMapping("/IP")
    public String createExcel() {
        long m = System.currentTimeMillis();
        try {
            List<Professor> professors = professorService.ListAll();
            XSSFRow row;
            XSSFCell cell;
            File zipFile = new File("Інд_плани.zip");
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zipOS = new ZipOutputStream(fos);
            int last_vert_cell_sum;
            int rownum;
            for (Professor professor : professors) {
                if (!professor.getName().equals("")) {
                    total_sum =0;
                    List<PSL_VM> psl_vmList;
                    FileInputStream iS = new FileInputStream("IndPlanExample.xlsx");
                    XSSFWorkbookFactory wbF = new XSSFWorkbookFactory();
                    XSSFWorkbook workbook = wbF.create(iS);

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

                    XSSFFont font12 = workbook.createFont();
                    font12.setFontHeightInPoints((short) 12);
                    font12.setFontName("Times New Roman");
                    font12.setBold(false);
                    font12.setItalic(false);

                    XSSFFont font12Bold = workbook.createFont();
                    font12Bold.setFontHeightInPoints((short) 12);
                    font12Bold.setFontName("Times New Roman");
                    font12Bold.setBold(true);
                    font12Bold.setItalic(false);

                    XSSFFont font12I = workbook.createFont();
                    font12I.setFontHeightInPoints((short) 12);
                    font12I.setFontName("Times New Roman");
                    font12I.setBold(false);
                    font12I.setItalic(true);

                    XSSFFont font12BI = workbook.createFont();
                    font12BI.setFontHeightInPoints((short) 12);
                    font12BI.setFontName("Times New Roman");
                    font12BI.setBold(true);
                    font12BI.setItalic(true);

                    XSSFFont font14 = workbook.createFont();
                    font14.setFontHeightInPoints((short) 14);
                    font14.setFontName("Times New Roman");
                    font14.setBold(false);
                    font14.setItalic(false);

                    XSSFFont font16 = workbook.createFont();
                    font16.setFontHeightInPoints((short) 16);
                    font16.setFontName("Times New Roman");
                    font16.setBold(false);
                    font16.setItalic(false);

                    XSSFFont font20 = workbook.createFont();
                    font16.setFontHeightInPoints((short) 20);
                    font16.setFontName("Times New Roman");
                    font16.setBold(true);
                    font16.setItalic(false);

                    CellStyle style = workbook.createCellStyle();
                    style.setVerticalAlignment(VerticalAlignment.CENTER);
                    style.setAlignment(HorizontalAlignment.CENTER);
                    style.setFont(font10);
                    style.setWrapText(true);
                    style.setBorderBottom(BorderStyle.THIN);
                    style.setBorderLeft(BorderStyle.THIN);
                    style.setBorderRight(BorderStyle.THIN);
                    style.setBorderTop(BorderStyle.THIN);

                    CellStyle style12 = workbook.createCellStyle();
                    style12.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12.setAlignment(HorizontalAlignment.LEFT);
                    style12.setFont(font12);
                    style12.setWrapText(true);
                    style12.setBorderBottom(BorderStyle.THIN);
                    style12.setBorderLeft(BorderStyle.THIN);
                    style12.setBorderRight(BorderStyle.THIN);
                    style12.setBorderTop(BorderStyle.THIN);

                    CellStyle style12I = workbook.createCellStyle();
                    style12I.setFont(font12I);
                    style12I.setWrapText(true);


                    CellStyle style12Bold = workbook.createCellStyle();
                    style12Bold.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12Bold.setAlignment(HorizontalAlignment.LEFT);
                    style12Bold.setFont(font12Bold);
                    style12Bold.setWrapText(true);
                    style12Bold.setBorderBottom(BorderStyle.THIN);
                    style12Bold.setBorderLeft(BorderStyle.THIN);
                    style12Bold.setBorderRight(BorderStyle.THIN);
                    style12Bold.setBorderTop(BorderStyle.THIN);

                    CellStyle style12BI = workbook.createCellStyle();
                    style12BI.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12BI.setAlignment(HorizontalAlignment.CENTER);
                    style12BI.setFont(font12Bold);
                    style12BI.setWrapText(true);
                    style12BI.setBorderBottom(BorderStyle.THIN);
                    style12BI.setBorderLeft(BorderStyle.THIN);
                    style12BI.setBorderRight(BorderStyle.THIN);
                    style12BI.setBorderTop(BorderStyle.THIN);

                    CellStyle style12ThickBotTop = workbook.createCellStyle();
                    style12ThickBotTop.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12ThickBotTop.setAlignment(HorizontalAlignment.CENTER);
                    style12ThickBotTop.setFont(font12Bold);
                    style12ThickBotTop.setWrapText(true);
                    style12ThickBotTop.setBorderBottom(BorderStyle.THICK);
                    style12ThickBotTop.setBorderLeft(BorderStyle.THIN);
                    style12ThickBotTop.setBorderRight(BorderStyle.THIN);
                    style12ThickBotTop.setBorderTop(BorderStyle.THICK);

                    CellStyle style12ThickBotTopRight = workbook.createCellStyle();
                    style12ThickBotTopRight.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12ThickBotTopRight.setAlignment(HorizontalAlignment.CENTER);
                    style12ThickBotTopRight.setFont(font12Bold);
                    style12ThickBotTopRight.setWrapText(true);
                    style12ThickBotTopRight.setBorderBottom(BorderStyle.THICK);
                    style12ThickBotTopRight.setBorderLeft(BorderStyle.THIN);
                    style12ThickBotTopRight.setBorderRight(BorderStyle.THICK);
                    style12ThickBotTopRight.setBorderTop(BorderStyle.THICK);

                    CellStyle style12ThickBotTopRal = workbook.createCellStyle();
                    style12ThickBotTopRal.setVerticalAlignment(VerticalAlignment.CENTER);
                    style12ThickBotTopRal.setAlignment(HorizontalAlignment.RIGHT);
                    style12ThickBotTopRal.setFont(font12Bold);
                    style12ThickBotTopRal.setWrapText(true);
                    style12ThickBotTopRal.setBorderBottom(BorderStyle.THICK);
                    style12ThickBotTopRal.setBorderLeft(BorderStyle.THIN);
                    style12ThickBotTopRal.setBorderRight(BorderStyle.THIN);
                    style12ThickBotTopRal.setBorderTop(BorderStyle.THICK);

                    CellStyle style14B = workbook.createCellStyle();
                    style14B.setVerticalAlignment(VerticalAlignment.CENTER);
                    style14B.setAlignment(HorizontalAlignment.CENTER);
                    style14B.setFont(font14);
                    style14B.setWrapText(true);
                    style14B.setBorderBottom(BorderStyle.THIN);
                    style14B.setBorderLeft(BorderStyle.THIN);
                    style14B.setBorderRight(BorderStyle.THIN);
                    style14B.setBorderTop(BorderStyle.THIN);

                    CellStyle style16Bot = workbook.createCellStyle();
                    style16Bot.setVerticalAlignment(VerticalAlignment.CENTER);
                    style16Bot.setAlignment(HorizontalAlignment.CENTER);
                    style16Bot.setFont(font16);
                    style16Bot.setWrapText(true);
                    style16Bot.setBorderBottom(BorderStyle.THIN);

                    CellStyle style20Bot = workbook.createCellStyle();
                    style20Bot.setVerticalAlignment(VerticalAlignment.CENTER);
                    style20Bot.setAlignment(HorizontalAlignment.CENTER);
                    style20Bot.setFont(font20);
                    style20Bot.setWrapText(true);
                    style20Bot.setBorderBottom(BorderStyle.THIN);

                    XSSFCellStyle rowAutoHeightStyle = workbook.createCellStyle();
                    rowAutoHeightStyle.setWrapText(true);
                    if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0
                            || pls_vmService.getPSL_VMData("2", professor.getName()).size() != 0) {
                        if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("1", professor.getName());
                        } else {
                            psl_vmList = pls_vmService.getPSL_VMData("2", professor.getName());
                        }
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        row = sheet.getRow(8);
                        cell = row.getCell(1);
                        cell.setCellValue(psl_vmList.get(0).getFac_name());
                        cell.setCellStyle(style16Bot);
                        row = sheet.getRow(11);
                        cell = row.getCell(1);
                        cell.setCellValue(psl_vmList.get(0).getDep_name());
                        cell.setCellStyle(style16Bot);
                        row = sheet.getRow(23);
                        cell = row.getCell(0);
                        cell.setCellValue(professor.getName());
                        cell.setCellStyle(style20Bot);
                        row = sheet.getRow(33);
                        cell = row.getCell(0);
                        cell.setCellValue(psl_vmList.get(0).getYear());
                        cell.setCellStyle(style12BI);
                        cell = row.getCell(1);
                        cell.setCellValue(professor.getPosada());
                        cell.setCellStyle(style12BI);
                        cell = row.getCell(2);
                        cell.setCellValue(professor.getNauk_stupin());
                        cell.setCellStyle(style12BI);
                        cell = row.getCell(3);
                        cell.setCellValue(professor.getVch_zvana());
                        cell.setCellStyle(style12BI);
                        cell = row.getCell(4);
                        cell.setCellValue(professor.getStavka());
                        cell.setCellStyle(style12BI);
                        cell = row.getCell(5);
                        cell.setCellValue(professor.getNote());
                        cell.setCellStyle(style12BI);


                        rownum = 4;
                        sheet = workbook.getSheetAt(2);
                        if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("1", professor.getName());
                            rownum = writeHours(cell, rownum, psl_vmList, style, rowAutoHeightStyle, sheet);
                        }
                        String[] ends1 = {"КЕРІВНИЦТВО"};
                        rownum = writeKerivnictvo(rownum, style, style12Bold, sheet, ends1);
                        String[] ends2 = {"                  Аспірант", "                  Докторанти",
                                "                  Магістр", "                  Фахівець",
                                "                  Курсові", "                  Курсові 5 курс"};
                        rownum = writeKerivnictvo(rownum, style, style12, sheet, ends2);
                        row = sheet.createRow(rownum++);
                        int autumn_sum = rownum;
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(rownum - 1, rownum - 1, 1, 6);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell = row.createCell(1);
                        cell.setCellValue("РАЗОМ ЗА I СЕМЕСТР");
                        cell.setCellStyle(style12ThickBotTopRal);
                        RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, sheet);
                        RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, sheet);
                        RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, sheet);
                        RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, sheet);

                        String[] sums = {"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD",
                                "AE", "AF", "AG", "AH", "AI", "AJ", "AK", "AL", "AM", "AN", "AO",
                                "AP", "AQ", "AR", "AS", "AT", "AU", "AV", "AW"};
                        int cell_count = 7;
                        last_vert_cell_sum = rownum - 1;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + "5:" + sum + last_vert_cell_sum + "),0)");
                            cell.setCellStyle(style12ThickBotTop);
                        }
                        cell = row.createCell(cell_count++);
                        cell.setCellFormula("H" + rownum + "+J" + rownum + "+L" + rownum + "+N" + rownum + "+P" + rownum +
                                "+R" + rownum + "+T" + rownum + "+V" + rownum + "+X" + rownum + "+Z" + rownum +
                                "+AB" + rownum + "+AD" + rownum + "+AF" + rownum + "+AH" + rownum + "+AL" + rownum + "+AJ" + rownum +
                                "+AN" + rownum + "+AP" + rownum + "+AR" + rownum + "+AT" + rownum + "+AV" + rownum);
                        cell.setCellStyle(style12ThickBotTop);
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("I" + rownum + "+K" + rownum + "+M" + rownum + "+O" + rownum + "+Q" + rownum +
                                "+S" + rownum + "+U" + rownum + "+W" + rownum + "+Y" + rownum + "+AA" + rownum + "+AC" + rownum +
                                "+AE" + rownum + "+AG" + rownum + "+AI" + rownum + "+AK" + rownum + "+AM" + rownum + "+AO" + rownum +
                                "+AQ" + rownum + "+AS" + rownum + "+AU" + rownum + "+AW" + rownum);
                        cell.setCellStyle(style12ThickBotTopRight);

                        if (pls_vmService.getPSL_VMData("2", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("2", professor.getName());

                            rownum = writeHours(cell, rownum, psl_vmList, style, rowAutoHeightStyle, sheet);
                        }
                        rownum = writeKerivnictvo(rownum, style, style12Bold, sheet, ends1);
                        rownum = writeKerivnictvo(rownum, style, style12, sheet, ends2);
                        row = sheet.createRow(rownum++);
                        int spring_sum = rownum;
                        cellRangeAddress = new CellRangeAddress(rownum - 1, rownum - 1, 1, 6);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell = row.createCell(1);
                        cell.setCellValue("РАЗОМ ЗА II СЕМЕСТР");
                        cell.setCellStyle(style12ThickBotTopRal);
                        RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, sheet);
                        RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, sheet);
                        RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, sheet);
                        RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, sheet);
                        cell_count = 7;
                        last_vert_cell_sum = rownum - 1;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + (autumn_sum + 1) + ":" + sum + last_vert_cell_sum + "),0)");
                            cell.setCellStyle(style12ThickBotTop);
                        }
                        cell = row.createCell(cell_count++);
                        cell.setCellFormula("H" + rownum + "+J" + rownum + "+L" + rownum + "+N" + rownum + "+P" + rownum +
                                "+R" + rownum + "+T" + rownum + "+V" + rownum + "+X" + rownum + "+Z" + rownum +
                                "+AB" + rownum + "+AD" + rownum + "+AF" + rownum + "+AH" + rownum + "+AL" + rownum + "+AJ" + rownum +
                                "+AN" + rownum + "+AP" + rownum + "+AR" + rownum + "+AT" + rownum + "+AV" + rownum);
                        cell.setCellStyle(style12ThickBotTop);
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("I" + rownum + "+K" + rownum + "+M" + rownum + "+O" + rownum + "+Q" + rownum +
                                "+S" + rownum + "+U" + rownum + "+W" + rownum + "+Y" + rownum + "+AA" + rownum + "+AC" + rownum +
                                "+AE" + rownum + "+AG" + rownum + "+AI" + rownum + "+AK" + rownum + "+AM" + rownum + "+AO" + rownum +
                                "+AQ" + rownum + "+AS" + rownum + "+AU" + rownum + "+AW" + rownum);
                        cell.setCellStyle(style12ThickBotTopRight);

                        row = sheet.createRow(rownum++);
                        cellRangeAddress = new CellRangeAddress(rownum - 1, rownum - 1, 1, 6);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell = row.createCell(1);
                        cell.setCellValue("УСЬОГО за навчальний рік");
                        cell.setCellStyle(style12ThickBotTopRal);
                        RegionUtil.setBorderBottom(cell.getCellStyle().getBorderBottom(), cellRangeAddress, sheet);
                        RegionUtil.setBorderTop(cell.getCellStyle().getBorderTop(), cellRangeAddress, sheet);
                        RegionUtil.setBorderLeft(cell.getCellStyle().getBorderLeft(), cellRangeAddress, sheet);
                        RegionUtil.setBorderRight(cell.getCellStyle().getBorderRight(), cellRangeAddress, sheet);
                        cell_count = 7;
                        for (String sum : sums) {
                            cell = row.createCell(cell_count++);
                            cell.setCellFormula("ROUND(SUM(" + sum + autumn_sum + "+" + sum + spring_sum + "),0)");
                            cell.setCellStyle(style12ThickBotTop);
                        }
                        cell = row.createCell(cell_count++);
                        cell.setCellFormula("H" + rownum + "+J" + rownum + "+L" + rownum + "+N" + rownum + "+P" + rownum +
                                "+R" + rownum + "+T" + rownum + "+V" + rownum + "+X" + rownum + "+Z" + rownum +
                                "+AB" + rownum + "+AD" + rownum + "+AF" + rownum + "+AH" + rownum + "+AL" + rownum + "+AJ" + rownum +
                                "+AN" + rownum + "+AP" + rownum + "+AR" + rownum + "+AT" + rownum + "+AV" + rownum);
                        cell.setCellStyle(style12ThickBotTop);
                        cell = row.createCell(cell_count);
                        cell.setCellFormula("I" + rownum + "+K" + rownum + "+M" + rownum + "+O" + rownum + "+Q" + rownum +
                                "+S" + rownum + "+U" + rownum + "+W" + rownum + "+Y" + rownum + "+AA" + rownum + "+AC" + rownum +
                                "+AE" + rownum + "+AG" + rownum + "+AI" + rownum + "+AK" + rownum + "+AM" + rownum + "+AO" + rownum +
                                "+AQ" + rownum + "+AS" + rownum + "+AU" + rownum + "+AW" + rownum);
                        cell.setCellStyle(style12ThickBotTopRight);

                        row = sheet.createRow(rownum++);
                        for (int c = 0; c < 51; c++) {
                            row.createCell(c);
                        }
                        row = sheet.createRow(rownum++);
                        cellRangeAddress = new CellRangeAddress(rownum - 1, rownum - 1, 1, 9);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell = row.createCell(1);
                        cell.setCellValue("Затвердженно на засіданні кафедри \"____\"___________20__р. Протокол № ____");
                        cell.setCellStyle(style12I);
                        for (int c = 10; c < 33; c++) {
                            row.createCell(c);
                        }
                        cellRangeAddress = new CellRangeAddress(rownum - 1, rownum - 1, 33, 44);
                        sheet.addMergedRegion(cellRangeAddress);
                        cell = row.createCell(33);
                        cell.setCellValue("Затвідувач кафедри _________________________");
                        cell.setCellStyle(style12I);

                        row = sheet.createRow(rownum++);
                        for (int c = 0; c < 51; c++) {
                            row.createCell(c);
                        }
                        row = sheet.createRow(rownum);
                        cell = row.createCell(2);
                        cell.setCellValue(professor.getName());
                        cell.setCellStyle(style12I);
                        for (int c = 3; c < 51; c++) {
                            row.createCell(c);
                        }
                        sheet.setFitToPage(true);
                        sheet.getPrintSetup().setFitWidth((short) 1);
                        sheet.getPrintSetup().setFitHeight((short) 0);
                        sheet.getPrintSetup().setLandscape(true);
                        sheet = workbook.getSheetAt(1);
                        row= sheet.getRow(18);
                        cell=row.createCell(3);
                        cell.setCellValue(total_sum);
                        cell.setCellStyle(style14B);
                        sheet.setFitToPage(true);
                        sheet.getPrintSetup().setLandscape(true);
                    }
                    iS.close();
                    File someFile = new File(professor.getName() + ".xlsx");
                    FileOutputStream outputStream = new FileOutputStream(someFile);
                    workbook.write(outputStream);
                    workbook.close();
                    outputStream.close();

                    professor.setIp_filename(someFile.getName());
                    professorService.save(professor);
                    writeToZipFile(someFile.getName(), zipOS);
                }
            }
            Faculty faculty = facultyService.ListAll().get(0);
            zipOS.flush();
            zipOS.close();
            faculty.setIpzip_filename(zipFile.getName());
            facultyService.save(faculty);

            fos.close();
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - m);

        return "redirect:/";
    }

    private int writeKerivnictvo(int rownum, CellStyle style, CellStyle style12Bold, XSSFSheet sheet, String[] ends1) {
        XSSFRow row;
        XSSFCell cell;
        for (String end : ends1) {
            row = sheet.createRow(rownum++);
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(end);
            cell.setCellStyle(style12Bold);
            for (int l = 3; l < 51; l++) {
                cell = row.createCell(l);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }
        }
        return rownum;
    }

    private int writeHours(XSSFCell cell, int rownum, List<PSL_VM> psl_vmList, CellStyle style, XSSFCellStyle rowAutoHeightStyle, XSSFSheet sheet) {
        XSSFRow row;
        for (PSL_VM psl_vm : psl_vmList) {
            row = sheet.createRow(rownum++);
            row.createCell(0);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(psl_vm.getDname());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellStyle(style);


            cell = row.createCell(4);
            if (psl_vm.getCourse().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getCourse()));
            }
            cell.setCellStyle(style);
            cell = row.createCell(5);
            if (psl_vm.getStudents_number().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getStudents_number()));
            }
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(psl_vm.getGroup_names());
            cell.setCellStyle(style);

            cell = row.createCell(7);
            if (psl_vm.getLec_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getLec_hours()));
                total_sum+=Double.parseDouble(psl_vm.getLec_hours());
            }
            cell.setCellStyle(style);

            cell = row.createCell(9);
            if (psl_vm.getConsult_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getConsult_hours()));
                total_sum+=Double.parseDouble(psl_vm.getConsult_hours());
            }
            cell.setCellStyle(style);

            cell = row.createCell(11);
            if (psl_vm.getLab_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getLab_hours()));
                total_sum+=Double.parseDouble(psl_vm.getLab_hours());
            }
            cell.setCellStyle(style);

            cell = row.createCell(13);
            if (psl_vm.getPract_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getPract_hours()));
                total_sum+=Double.parseDouble(psl_vm.getPract_hours());
            }
            cell.setCellStyle(style);

            cell = row.createCell(15);
            if (psl_vm.getInd_task_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getInd_task_hours()));
                total_sum+=Double.parseDouble(psl_vm.getInd_task_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(17);
            if (psl_vm.getCp_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getCp_hours()));
                total_sum+=Double.parseDouble(psl_vm.getCp_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(19);
            if (psl_vm.getZalik_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getZalik_hours()));
                total_sum+=Double.parseDouble(psl_vm.getZalik_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(21);
            if (psl_vm.getExam_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getExam_hours()));
                total_sum+=Double.parseDouble(psl_vm.getExam_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(23);
            if (psl_vm.getDiploma_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getDiploma_hours()));
                total_sum+=Double.parseDouble(psl_vm.getDiploma_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(25);
            if (psl_vm.getDec_cell().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getDec_cell()));
                total_sum+=Double.parseDouble(psl_vm.getDec_cell());
            }
            cell.setCellStyle(style);
            cell = row.createCell(27);
            if (psl_vm.getNdrs().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getNdrs()));
                total_sum+=Double.parseDouble(psl_vm.getNdrs());
            }
            cell.setCellStyle(style);
            cell = row.createCell(29);
            if (psl_vm.getAspirant_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getAspirant_hours()));
                total_sum+=Double.parseDouble(psl_vm.getAspirant_hours());
            }
            cell.setCellStyle(style);
            cell = row.createCell(31);
            if (psl_vm.getPractice().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getPractice()));
                total_sum+=Double.parseDouble(psl_vm.getPractice());
            }
            cell.setCellStyle(style);
            cell = row.createCell(33);
            cell.setCellValue(0);
            cell.setCellStyle(style);
            cell = row.createCell(35);
            if (psl_vm.getOther_forms_hours().isEmpty()) {
                cell.setCellValue(0);
            } else {
                cell.setCellValue(Double.parseDouble(psl_vm.getOther_forms_hours()));
                total_sum+=Double.parseDouble(psl_vm.getOther_forms_hours());
            }
            cell.setCellStyle(style);

            for (int c = 8; c < 37; c += 2) {
                cell = row.createCell(c);
                cell.setCellValue(Double.parseDouble("0"));
                cell.setCellStyle(style);
            }
            for (int c = 37; c < 49; c++) {
                cell = row.createCell(c);
                cell.setCellValue(0);
                cell.setCellStyle(style);
            }

            cell = row.createCell(49);
            cell.setCellFormula("H" + rownum + "+J" + rownum + "+L" + rownum + "+N" + rownum + "+P" + rownum +
                    "+R" + rownum + "+T" + rownum + "+V" + rownum + "+X" + rownum + "+Z" + rownum +
                    "+AB" + rownum + "+AD" + rownum + "+AF" + rownum + "+AH" + rownum + "+AL" + rownum + "+AJ" + rownum +
                    "+AN" + rownum + "+AP" + rownum + "+AR" + rownum + "+AT" + rownum + "+AV" + rownum);
            cell.setCellStyle(style);
            cell = row.createCell(50);
            cell.setCellFormula("I" + rownum + "+K" + rownum + "+M" + rownum + "+O" + rownum + "+Q" + rownum +
                    "+S" + rownum + "+U" + rownum + "+W" + rownum + "+Y" + rownum + "+AA" + rownum + "+AC" + rownum +
                    "+AE" + rownum + "+AG" + rownum + "+AI" + rownum + "+AK" + rownum + "+AM" + rownum + "+AO" + rownum +
                    "+AQ" + rownum + "+AS" + rownum + "+AU" + rownum + "+AW" + rownum);
            cell.setCellStyle(style);
            row.setRowStyle(rowAutoHeightStyle);
        }
        return rownum;
    }

    public static void writeToZipFile(String path, ZipOutputStream zipStream)
            throws FileNotFoundException, IOException {

        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(path);
        zipStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }

        zipStream.closeEntry();
        fis.close();
    }
}
