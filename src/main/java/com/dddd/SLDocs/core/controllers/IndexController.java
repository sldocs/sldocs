package com.dddd.SLDocs.core.controllers;


import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.servImpls.FacultyServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import com.dddd.SLDocs.core.utils.email.Sender;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
public class IndexController {
    private final FacultyServiceImpl facultyService;
    private final ProfessorServiceImpl professorService;

    public IndexController(FacultyServiceImpl facultyService, ProfessorServiceImpl professorService) {
        this.facultyService = facultyService;
        this.professorService = professorService;
    }


    @GetMapping("/downloadEAS")
    public ResponseEntity downloadEAS() {
        Faculty faculty = facultyService.ListAll().get(0);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + faculty.getEas_filename() + "\"")
                .body(faculty.getEas_file());
    }

    @GetMapping("/downloadPSL")
    public ResponseEntity downloadPSL() {
        Faculty faculty = facultyService.ListAll().get(0);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + faculty.getPsl_filename() + "\"")
                .body(faculty.getPsl_file());
    }
    @GetMapping(value = "/downloadProfZip", produces="application/zip")
    public void downloadIpZip(HttpServletResponse response) {
        Faculty faculty = facultyService.ListAll().get(0);
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + faculty.getIpzip_filename() + "\"");
    }
}
