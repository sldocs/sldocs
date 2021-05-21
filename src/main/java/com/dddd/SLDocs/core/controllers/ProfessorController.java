package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.core.entities.Faculty;
import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.servImpls.FacultyServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class ProfessorController {

    private final ProfessorServiceImpl professorService;
    private final FacultyServiceImpl facultyService;

    public ProfessorController(ProfessorServiceImpl professorService, FacultyServiceImpl facultyService) {
        this.professorService = professorService;
        this.facultyService = facultyService;
    }

    @RequestMapping("/professors")
    public String viewProfessorsPage(Model model) {
        List<Professor> professors = professorService.listUnedited();
        model.addAttribute("professors", professors);
        return "professors";
    }

    @RequestMapping("/professor/save")
    public String saveAndViewProfessorsPage(@RequestParam("id") long professor_id,
                                            @RequestParam("name") String name, @RequestParam("posada") String posada,
                                            @RequestParam("nauk_stupin") String nauk_stupin,
                                            @RequestParam("vch_zvana") String vch_zvana, @RequestParam("stavka") String stavka,
                                            @RequestParam("note") String note, @RequestParam("email_address") String email_address,
                                            Model model) {
        Professor professor=professorService.getByID(professor_id);
        professor.setName(name);
        professor.setPosada(posada);
        professor.setNauk_stupin(nauk_stupin);
        professor.setVch_zvana(vch_zvana);
        professor.setStavka(stavka);
        professor.setNote(note);
        professor.setEmail_address(email_address);
        professorService.save(professor);
        List<Professor> professors = professorService.listUnedited();
        model.addAttribute("professors", professors);
        return "professors";
    }

    @RequestMapping("/professor/sendTo")
    public String sendProfTo(@RequestParam("to") String to) {
        List<File> fileList;
        return "";
    }

    @GetMapping("/professor/download")
    public ResponseEntity downloadIp(@RequestParam("prof_name") String prof_name) {
        Professor professor = professorService.findByName(prof_name);
        String p = "Vikladach.xlsx";
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + p + "\"")
                .body(professor.getIp_file());
    }

}
