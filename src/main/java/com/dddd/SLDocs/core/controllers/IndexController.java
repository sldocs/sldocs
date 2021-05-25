package com.dddd.SLDocs.core.controllers;


import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.servImpls.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Controller
public class IndexController {
    private final CurriculumServiceImpl curriculumService;
    private final DepartmentServiceImpl departmentService;
    private final DisciplineServiceImpl disciplineService;
    private final FacultyServiceImpl facultyService;
    private final ProfessorServiceImpl professorService;
    private final SpecialtyServiceImpl specialtyService;


    public IndexController(CurriculumServiceImpl curriculumService, DepartmentServiceImpl departmentService,
                           DisciplineServiceImpl disciplineService, FacultyServiceImpl facultyService,
                           ProfessorServiceImpl professorService, SpecialtyServiceImpl specialtyService) {
        this.curriculumService = curriculumService;
        this.departmentService = departmentService;
        this.disciplineService = disciplineService;
        this.facultyService = facultyService;
        this.professorService = professorService;
        this.specialtyService = specialtyService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String viewIndexPage(Model model) {
        String easfn="";
        String pslfn="";
        String ipzipfn="";
        try {
            easfn = facultyService.ListAll().get(0).getEas_filename();
            pslfn = facultyService.ListAll().get(0).getPsl_filename();
            ipzipfn = facultyService.ListAll().get(0).getIpzip_filename();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        boolean eas = true;
        boolean psl = true;
        boolean ip = true;
       if(easfn == null || easfn.isEmpty() || easfn.equals("")){
            eas = false;
        }
       if(pslfn==null || pslfn.isEmpty() || pslfn.equals("")){
            psl = false;
        }
        if(ipzipfn==null || ipzipfn.isEmpty() || ipzipfn.equals("")){
            ip = false;
        }
        model.addAttribute("eas", eas);
        model.addAttribute("psl", psl);
        model.addAttribute("ip", ip);
        return "index";
    }
    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String viewDeletePage() {
        return "deleteAllConf";
    }
    @RequestMapping(path = "/deleteWOProfs", method = RequestMethod.GET)
    public String viewDeleteWOprofsPage() {
        return "deleteWOprofsConf";
    }

    @RequestMapping(path = "/deleteAll", method = RequestMethod.GET)
    public String deleteAll(Model model) {
        curriculumService.deleteAll();
        departmentService.deleteAll();
        disciplineService.deleteAll();
        facultyService.deleteAll();
        professorService.deleteAll();
        specialtyService.deleteAll();
        return "deleteAllSuc";
    }


    @RequestMapping(path = "/deleteWithoutProfs", method = RequestMethod.GET)
    public String deleteWithoutProfs(Model model) {
        curriculumService.deleteAll();
        departmentService.deleteAll();
        disciplineService.deleteAll();
        facultyService.deleteAll();
        specialtyService.deleteAll();
        return "deleteWOprofsSuc";
    }

    @GetMapping("/downloadEAS")
    public ResponseEntity downloadEAS() throws UnsupportedEncodingException {
        Faculty faculty = facultyService.ListAll().get(0);

        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rfc5987_encode(faculty.getEas_filename()) + "\"")
                .body(faculty.getEas_file());
    }

    @GetMapping("/downloadPSL")
    public ResponseEntity downloadPSL() throws UnsupportedEncodingException {
        Faculty faculty = facultyService.ListAll().get(0);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rfc5987_encode(faculty.getPsl_filename()) + "\"")
                .body(faculty.getPsl_file());
    }

    @GetMapping(value = "/downloadProfZip", produces = "application/zip")
    public ResponseEntity downloadIpZip() throws UnsupportedEncodingException {
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rfc5987_encode(facultyService.ListAll().get(0).getIpzip_filename()) + "\"")
                .body(facultyService.ListAll().get(0).getIpzip_file());
    }


    public static String rfc5987_encode(final String s) throws UnsupportedEncodingException {
        final byte[] s_bytes = s.getBytes(StandardCharsets.UTF_8);
        final int len = s_bytes.length;
        final StringBuilder sb = new StringBuilder(len << 1);
        final char[] digits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        final byte[] attr_char = {'!','#','$','&','+','-','.','0','1','2','3','4','5','6','7','8','9',           'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','^','_','`',                        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','|', '~'};
        for (int i = 0; i < len; ++i) {
            final byte b = s_bytes[i];
            if (Arrays.binarySearch(attr_char, b) >= 0)
                sb.append((char) b);
            else {
                sb.append('%');
                sb.append(digits[0x0f & (b >>> 4)]);
                sb.append(digits[b & 0x0f]);
            }
        }

        return sb.toString();
    }
}
