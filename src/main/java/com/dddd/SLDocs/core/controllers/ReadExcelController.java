package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Group;
import com.dddd.SLDocs.core.entities.StudyLoad;
import com.dddd.SLDocs.core.servImpls.*;
import javassist.compiler.SymbolTable;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class ReadExcelController {

    private final String space_regex = "\\s+";
    private final String comma_regex = ",";

    private final String group_regex = "^([\\p{L}]{2})([-])([0-9]{3}|[\\p{L}][0-9]{3})([і].*|.[і]|[\\p{L}]*)?$";

    private final CurriculumServiceImpl curriculumService;

    private final DepartmentServiceImpl departmentService;

    private final DisciplineServiceImpl disciplineService;

    private final FacultyServiceImpl facultyService;

    private final GroupServiceImpl groupService;

    private final ProfessorServiceImpl professorService;

    private final SpecialtyServiceImpl specialtyService;

    public ReadExcelController(CurriculumServiceImpl curriculumService, DepartmentServiceImpl departmentService,
                               DisciplineServiceImpl disciplineService, FacultyServiceImpl facultyService,
                               GroupServiceImpl groupService, ProfessorServiceImpl professorService,
                               SpecialtyServiceImpl specialtyService) {
        this.curriculumService = curriculumService;
        this.departmentService = departmentService;
        this.disciplineService = disciplineService;
        this.facultyService = facultyService;
        this.groupService = groupService;
        this.professorService = professorService;
        this.specialtyService = specialtyService;
    }

    @RequestMapping("read")
    public String read(@RequestParam(value = "path") String path) throws IOException {

        System.out.println("path: " + path);
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //readTroops(workbook);
        long m = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            readAutumn(workbook, i);
        }

        System.out.println(System.currentTimeMillis() - m);

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
            System.out.println("end of the file or NPE");
        }
    }

    public void readAutumn(XSSFWorkbook workbook, int sheet_num) throws IOException {
        XSSFSheet sheet = workbook.getSheetAt(sheet_num);
        StudyLoad studyLoad = new StudyLoad();
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
        ArrayList<Object> arrayList = new ArrayList<>();
        ArrayList<Object> dep_fac_sem = new ArrayList<>();
        try {
            XSSFRow row = sheet.getRow(6);
            dep_fac_sem.add(row.getCell(0));
            dep_fac_sem.add(row.getCell(16));
            dep_fac_sem.add(row.getCell(31));
            StringBuffer stringBuffer = new StringBuffer();
            for (int p = 0; p < 2; p++) {
                String[] values = dep_fac_sem.get(p).toString().split(space_regex);

                for (int i = 1; i < values.length; i++) {
                    stringBuffer.append(values[i] + " ");
                }
                dep_fac_sem.set(p, stringBuffer);
                stringBuffer = new StringBuffer();
            }
            row = sheet.getRow(3);
            String[] values;
            for (int pp = 0; pp < row.getLastCellNum(); pp++) {
                if (!(row.getCell(pp) == null)) {
                    if (!(row.getCell(pp).getStringCellValue().equals(""))) {
                        values = row.getCell(pp).getStringCellValue().split(space_regex);
                        dep_fac_sem.add(values[6]);
                    }
                }
            }

            if (facultyService.findByName(dep_fac_sem.get(1).toString()) == null) {
                studyLoad.getDepartment().setName(dep_fac_sem.get(0).toString());
                studyLoad.getFaculty().setName(dep_fac_sem.get(1).toString());
                studyLoad.getFaculty().getDepartments().add(studyLoad.getDepartment());
                studyLoad.getDepartment().setFaculty(studyLoad.getFaculty());
                facultyService.save(studyLoad.getFaculty());
                departmentService.save(studyLoad.getDepartment());
            }

            for (int r = 10; r < rows; r++) {
                row = sheet.getRow(r);
                for (int c = 0; c < cols + 1; c++) {

                    XSSFCell cell = row.getCell(c);
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
                    System.out.println();
                }
                studyLoad.getGroup().setCourse(arrayList.get(3).toString());
                studyLoad.getCurriculum().setNumber_of_streams(arrayList.get(6).toString());
                studyLoad.getCurriculum().setNumber_of_subgroups(arrayList.get(7).toString());
                studyLoad.getCurriculum().setLec(arrayList.get(8).toString());
                studyLoad.getCurriculum().setLab(arrayList.get(9).toString());
                studyLoad.getCurriculum().setPract_seminars(arrayList.get(10).toString());
                studyLoad.getCurriculum().setOther_forms(arrayList.get(11).toString());
                studyLoad.getCurriculum().setCourse_projects(arrayList.get(12).toString());
                studyLoad.getCurriculum().setTasks(arrayList.get(13).toString());
                studyLoad.getCurriculum().setZalik(arrayList.get(14).toString());
                studyLoad.getCurriculum().setExam(arrayList.get(15).toString());
                studyLoad.getCurriculum().setLec_hours(arrayList.get(16).toString());
                studyLoad.getCurriculum().setConsult_hours(arrayList.get(17).toString());
                studyLoad.getCurriculum().setLab_hours(arrayList.get(18).toString());
                studyLoad.getCurriculum().setPract_hours(arrayList.get(19).toString());
                studyLoad.getCurriculum().setInd_task_hours(arrayList.get(20).toString());
                studyLoad.getCurriculum().setCp_hours(arrayList.get(21).toString());
                studyLoad.getCurriculum().setZalik_hours(arrayList.get(22).toString());
                studyLoad.getCurriculum().setExam_hours(arrayList.get(23).toString());
                studyLoad.getCurriculum().setDiploma_hours(arrayList.get(24).toString());
                studyLoad.getCurriculum().setDec_cell(arrayList.get(25).toString());
                studyLoad.getCurriculum().setNdrs(arrayList.get(26).toString());
                studyLoad.getCurriculum().setAspirants(arrayList.get(27).toString());
                studyLoad.getCurriculum().setPractice(arrayList.get(28).toString());
                studyLoad.getCurriculum().setOther_forms_hours(arrayList.get(30).toString());
                studyLoad.getCurriculum().setHourly_wage(arrayList.get(32).toString());
                studyLoad.getCurriculum().setTotal(arrayList.get(33).toString());
                studyLoad.getCurriculum().setNote(arrayList.get(34).toString());
                if (professorService.findByName(arrayList.get(35).toString()) == null) {
                    studyLoad.getProfessor().setName(arrayList.get(35).toString());
                    professorService.save(studyLoad.getProfessor());
                    studyLoad.getCurriculum().getProfessors().add(studyLoad.getProfessor());
                } else {
                    studyLoad.getCurriculum().getProfessors().add(professorService.findByName(arrayList.get(35).toString()));
                }

                if (disciplineService.findByName(arrayList.get(1).toString()) == null) {
                    studyLoad.getDiscipline().setName(arrayList.get(1).toString());
                    disciplineService.save(studyLoad.getDiscipline());
                    studyLoad.getCurriculum().getDisciplines().add(studyLoad.getDiscipline());
                } else {
                    studyLoad.getCurriculum().getDisciplines().add(disciplineService.findByName(arrayList.get(1).toString()));
                }

                String[] values_groups = arrayList.get(5).toString().split(comma_regex);
                Pattern pattern = Pattern.compile(group_regex);
                Matcher matcher;
                for (int i = 0; i < values_groups.length; i++) {
                    matcher = pattern.matcher(values_groups[i].trim());
                    while (matcher.find()) {
                        if (Character.isDigit(matcher.group(3).charAt(0))) {
                            if (!matcher.group(4).equals("") && matcher.group(4).length() > 1) {
                                if (matcher.group(4).charAt(0) == 'і' | matcher.group(4).charAt(1) == 'і' | matcher.group(4).charAt(1) == 'е') {
                                    readGroups(studyLoad, matcher, dep_fac_sem);
                                } else {
                                    for (int j = 0; j < matcher.group(4).length(); j++) {
                                        stringBuffer = new StringBuffer();
                                        stringBuffer.append(matcher.group(1) + matcher.group(2) + matcher.group(3) + matcher.group(4).charAt(j));
                                        studyLoad.getGroup().setName(stringBuffer.toString());
                                        studyLoad.getGroup().setYear(dep_fac_sem.get(3).toString());
                                        studyLoad.getGroup().setSemester(dep_fac_sem.get(2).toString());
                                        studyLoad.getCurriculum().getGroups().add(studyLoad.getGroup());
                                        studyLoad.getDepartment().getGroups().add(studyLoad.getGroup());
                                        studyLoad.getGroup().setCurriculum(studyLoad.getCurriculum());
                                        studyLoad.getGroup().setDepartment(departmentService.findByName(dep_fac_sem.get(0).toString()));
                                        groupService.save(studyLoad.getGroup());
                                    }
                                }
                            } else {
                                readGroups(studyLoad, matcher, dep_fac_sem);
                            }
                        } else {
                            readGroups(studyLoad, matcher, dep_fac_sem);
                        }
                    }
                }


                curriculumService.save(studyLoad.getCurriculum());
                arrayList = new ArrayList<>();
                studyLoad = new StudyLoad();
            }
        } catch (NullPointerException ex) {
            System.out.println("end of the file or NPE");
        }
    }

    private void readGroups(StudyLoad studyLoad, Matcher matcher, ArrayList<Object> dep_fac_sem) {
        studyLoad.getGroup().setName(matcher.group(0));
        studyLoad.getGroup().setYear(dep_fac_sem.get(3).toString());
        studyLoad.getGroup().setSemester(dep_fac_sem.get(2).toString());
        studyLoad.getCurriculum().getGroups().add(studyLoad.getGroup());
        studyLoad.getGroup().setCurriculum(studyLoad.getCurriculum());
        studyLoad.getGroup().setDepartment(departmentService.findByName(dep_fac_sem.get(0).toString()));
        groupService.save(studyLoad.getGroup());
    }
}
