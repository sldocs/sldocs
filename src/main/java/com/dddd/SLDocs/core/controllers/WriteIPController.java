package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.entities.views.PSL_VM;
import com.dddd.SLDocs.core.servImpls.PSL_VMServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class WriteIPController {
    private final PSL_VMServiceImpl pls_vmService;
    private final ProfessorServiceImpl professorService;

    public WriteIPController(PSL_VMServiceImpl pls_vmService, ProfessorServiceImpl professorService) {
        this.pls_vmService = pls_vmService;
        this.professorService = professorService;
    }


    @RequestMapping("IP")
    public String createExcel() {
        long m = System.currentTimeMillis();
        try {
            List<Professor> professors = professorService.ListAll();
            XSSFRow row;
            XSSFCell cell;

            int last_vert_cell_sum;
            int rownum;
            for (Professor professor : professors) {
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

                CellStyle style14B = workbook.createCellStyle();
                style14B.setVerticalAlignment(VerticalAlignment.CENTER);
                style14B.setAlignment(HorizontalAlignment.CENTER);
                style14B.setFont(font10);
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
                if (!professor.getName().equals("")) {
                    List<PSL_VM> psl_vmList;
                    if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0
                            | pls_vmService.getPSL_VMData("2", professor.getName()).size() != 0) {
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
                        rownum = 4;
                        sheet = workbook.getSheetAt(2);
                        if (pls_vmService.getPSL_VMData("1", professor.getName()).size() != 0) {
                            psl_vmList = pls_vmService.getPSL_VMData("1", professor.getName());
                            for (PSL_VM psl_vm : psl_vmList) {
                                row = sheet.createRow(rownum++);
                                cell = row.createCell(0);
                                cell = row.createCell(1);
                                cell.setCellStyle(style);
                                cell = row.createCell(2);
                                cell.setCellValue(psl_vm.getDname());
                                cell.setCellStyle(style);
                                cell = row.createCell(3);
                                cell.setCellStyle(style);


                                cell = row.createCell(4);
                                if (psl_vm.getCourse().isEmpty()) {
                                    cell.setCellValue(psl_vm.getCourse());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getCourse()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(5);
                                if (psl_vm.getStudents_number().isEmpty()) {
                                    cell.setCellValue(psl_vm.getStudents_number());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getStudents_number()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(6);
                                cell.setCellValue(psl_vm.getGroup_names());
                                cell.setCellStyle(style);

                                cell = row.createCell(7);
                                if (psl_vm.getLec_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getLec_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getLec_hours()));
                                }
                                cell.setCellStyle(style);

                                cell = row.createCell(9);
                                if (psl_vm.getConsult_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getConsult_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getConsult_hours()));
                                }
                                cell.setCellStyle(style);

                                cell = row.createCell(11);
                                if (psl_vm.getLab_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getLab_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getLab_hours()));
                                }
                                cell.setCellStyle(style);

                                cell = row.createCell(13);
                                if (psl_vm.getPract_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getPract_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getPract_hours()));
                                }
                                cell.setCellStyle(style);

                                cell = row.createCell(15);
                                if (psl_vm.getInd_task_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getInd_task_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getInd_task_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(17);
                                if (psl_vm.getCp_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getCp_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getCp_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(19);
                                if (psl_vm.getZalik_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getZalik_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getZalik_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(21);
                                if (psl_vm.getExam_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getExam_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getExam_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(23);
                                if (psl_vm.getDiploma_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getDiploma_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getDiploma_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(25);
                                if (psl_vm.getDec_cell().isEmpty()) {
                                    cell.setCellValue(psl_vm.getDec_cell());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getDec_cell()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(27);
                                if (psl_vm.getNdrs().isEmpty()) {
                                    cell.setCellValue(psl_vm.getNdrs());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getNdrs()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(29);
                                if (psl_vm.getAspirant_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getAspirant_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getAspirant_hours()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(31);
                                if (psl_vm.getPractice().isEmpty()) {
                                    cell.setCellValue(psl_vm.getPractice());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getPractice()));
                                }
                                cell.setCellStyle(style);
                                cell = row.createCell(33);
                                cell.setCellValue(0);
                                cell.setCellStyle(style);
                                cell = row.createCell(35);
                                if (psl_vm.getOther_forms_hours().isEmpty()) {
                                    cell.setCellValue(psl_vm.getOther_forms_hours());
                                } else {
                                    cell.setCellValue(Double.parseDouble(psl_vm.getOther_forms_hours()));
                                }
                                cell.setCellStyle(style);

                                for(int c=8;c<37;c+=2){
                                    cell = row.createCell(c);
                                    cell.setCellValue(0);
                                    cell.setCellStyle(style);
                                }
                                for(int c=37;c<49;c++){
                                    cell = row.createCell(c);
                                    cell.setCellValue(0);
                                    cell.setCellStyle(style);
                                }

                                cell = row.createCell(49);
                                int rowForFormula=rownum;
                                cell.setCellFormula("H"+rowForFormula+"+N"+rowForFormula+"+L"+rowForFormula+"+AL"+rowForFormula+"+AN"+rowForFormula+
                                        "+AP"+rowForFormula+"+J"+rowForFormula+"+P"+rowForFormula+"+AR"+rowForFormula+"+AT"+rowForFormula+
                                        "+AV"+rowForFormula+"+R"+rowForFormula+"+T"+rowForFormula+"+V"+rowForFormula+"+AF"+rowForFormula+
                                        "+Z"+rowForFormula+"+X"+rowForFormula+"+AD"+rowForFormula+"+AH"+rowForFormula+"+AJ"+rowForFormula+"+AB"+rowForFormula);
                                cell.setCellStyle(style);
                                row.setRowStyle(rowAutoHeightStyle);
                            }
                        }
                    }
                }
                iS.close();
                FileOutputStream outputStream = new FileOutputStream(professor.getName()+".xlsx");
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            }
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - m);

        return "redirect:/";
    }
}
