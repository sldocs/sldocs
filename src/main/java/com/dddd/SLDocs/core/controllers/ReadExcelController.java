package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Group;
import com.dddd.SLDocs.core.entities.StudyLoad;
import com.dddd.SLDocs.core.services.GroupService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ReadExcelController {

    private GroupService groupService;

    public ReadExcelController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("read")
    public String read(@RequestParam(value = "path") String path) throws IOException {

        System.out.println("path: " + path);
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        readTroops(workbook);
       //readAutumn(workbook;



        return "redirect:/";
    }

    public void readTroops(XSSFWorkbook workbook) throws IOException {
        XSSFSheet sheet = workbook.getSheetAt(2);
        Group group = new Group();
        int rows = 0;
        while (true) {
            XSSFRow row = sheet.getRow(rows);
            try {
                row.getCell(1);
                rows++;
            } catch (NullPointerException ex) {
                break;
            }
        }
        int cols = sheet.getRow(0).getLastCellNum();
        double course = 0;
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            for (int r = 2; r < rows; r++) {
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c < cols - 1; c++) {
                    XSSFCell cell = row.getCell(c);
                    if (c == 0) {
                        if (cell.getCellType().equals(CellType.NUMERIC)) {
                            course = cell.getNumericCellValue();
                        }
                        arrayList.add(course);
                    } else {
                        if (cell.equals(null)) {
                            arrayList.add("");
                        } else {
                            switch (cell.getCellType()) {
                                case STRING:
                                    arrayList.add(cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    arrayList.add(cell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    arrayList.add(cell.getBooleanCellValue());
                                    break;
                                case FORMULA:
                                    switch (cell.getCachedFormulaResultType()) {
                                        case NUMERIC:
                                            arrayList.add(cell.getNumericCellValue());
                                            break;
                                        case STRING:
                                            arrayList.add(cell.getStringCellValue());
                                            break;
                                    }
                                    break;
                                default:
                                    arrayList.add("");
                                    break;
                            }
                        }
                    }
                }
                group.setCourse(arrayList.get(0).toString());
                group.setName(arrayList.get(1).toString());
                group.setStudents_number(arrayList.get(2).toString());
                groupService.save(group);
                arrayList = new ArrayList<>();
                group = new Group();
            }
        } catch (NullPointerException ex) {
            System.out.println("puk puk");
        }
    }
    public void readAutumn(XSSFWorkbook workbook) throws IOException {
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println("sheet: " + sheet.getSheetName());

        StudyLoad studyLoad = new StudyLoad();
        Group group = new Group();
        int rows = 0;

        while (true) {
            XSSFRow row = sheet.getRow(rows);
            try {
                row.getCell(1);
                rows++;
            } catch (NullPointerException ex) {
                break;
            }
        }
        int cols = sheet.getRow(0).getLastCellNum();
        double course = 0;
        System.out.println("rows: " + rows);
        System.out.println("cols: " + cols);

        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            for (int r = 9; r < rows; r++) {
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c < cols - 1; c++) {
                    XSSFCell cell = row.getCell(c);
                    System.out.println("c: " + c);
                    System.out.println("r: " + r);

                    if (c == 0) {
                        if (cell.getCellType().equals(CellType.NUMERIC)) {
                            course = cell.getNumericCellValue();
                        }
                        arrayList.add(course);
                    } else {
                        if (cell.equals(null)) {
                            arrayList.add("");
                        } else {
                            switch (cell.getCellType()) {
                                case STRING:
                                    System.out.println(cell.getStringCellValue());
                                    arrayList.add(cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    System.out.println(cell.getNumericCellValue());
                                    arrayList.add(cell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    System.out.println(cell.getBooleanCellValue());
                                    arrayList.add(cell.getBooleanCellValue());
                                    break;
                                case FORMULA:
                                    switch (cell.getCachedFormulaResultType()) {
                                        case NUMERIC:
                                            System.out.println("Last evaluated as: " + cell.getNumericCellValue());
                                            arrayList.add(cell.getNumericCellValue());
                                            break;
                                        case STRING:
                                            System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
                                            arrayList.add(cell.getStringCellValue());
                                            break;
                                    }
                                    break;
                                default:
                                    arrayList.add("");
                                    break;
                            }
                        }
                    }
                    System.out.println();
                }
                studyLoad.getDiscipline().setName(arrayList.get(1).toString());
                group.setCourse(arrayList.get(0).toString());
                group.setName(arrayList.get(1).toString());
                group.setStudents_number(arrayList.get(2).toString());
                groupService.save(group);

                arrayList = new ArrayList<>();
                group = new Group();
                //studyLoad.getDiscipline().setName(arrayList.get(1).toString());
                //studyLoad.getGroup().setName(arrayList.get(1).toString());
            }
        } catch (NullPointerException ex) {
        }
    }
}
