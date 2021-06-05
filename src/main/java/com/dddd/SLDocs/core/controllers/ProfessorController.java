package com.dddd.SLDocs.core.controllers;

import com.dddd.SLDocs.auth.servImpls.UserService;
import com.dddd.SLDocs.core.entities.Professor;
import com.dddd.SLDocs.core.servImpls.FacultyServiceImpl;
import com.dddd.SLDocs.core.servImpls.ProfessorServiceImpl;
import com.dddd.SLDocs.core.utils.email.Sender;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dddd.SLDocs.core.utils.email.Sender.rfc5987_encode;

@Controller
public class ProfessorController {

    private final ProfessorServiceImpl professorService;
    private final UserService userService;

    public ProfessorController(ProfessorServiceImpl professorService, FacultyServiceImpl facultyService, UserService userService) {
        this.professorService = professorService;
        this.userService = userService;
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

    @RequestMapping("/professors/docs")
    public String viewProfDocsSendPage(Model model) {
        List<Professor> professors = professorService.listUnedited();
        model.addAttribute("professors", professors);
        return "professors_docs";
    }

    @RequestMapping("/professor/sendIpTo")
    public String sendIpTo(@RequestParam("name") String name, @RequestParam("email") String email) {
        Professor professor = professorService.findByName(name);
       try{ Sender.Send(email, professor.getIp_filename());
       }catch (NullPointerException ex){
           return "errors/noFilesYet";
       }
        return getTimeString(professor);
    }



    @RequestMapping("/professor/sendPslTo")
    public String sendPslTo(@RequestParam("name") String name, @RequestParam("email") String email) {
        Professor professor = professorService.findByName(name);
        try{ Sender.Send(email, professor.getPsl_filename());
        }catch (NullPointerException ex){
            return "errors/noFilesYet";
        }
        return getTimeString(professor);
    }

    @RequestMapping("/professor/sendIpToAll")
    public String sendIpToAll() {
        List<Professor> professors = professorService.listWithEmails();
        String time_regex = "([0-9[-]]+)(T)(.{5})";
        StringBuilder stringBuffer = new StringBuilder();
        for(Professor professor : professors){
            Sender.Send(professor.getEmail_address(),professor.getIp_filename());
            Pattern pattern = Pattern.compile(time_regex);
            Matcher matcher = pattern.matcher(java.time.LocalDateTime.now().toString());
            while (matcher.find()) {
                stringBuffer.append(matcher.group(1)).append(" ").append(matcher.group(3));

            }
            professor.setEmailed_date(stringBuffer.toString());
            professorService.save(professor);
            stringBuffer = new StringBuilder();
        }
        return "redirect:/professors/docs";
    }

    @RequestMapping("/professor/sendPslToAll")
    public String sendPslToAll() {
        List<Professor> professors = professorService.listWithEmails();
        String time_regex = "([0-9[-]]+)(T)(.{5})";
        StringBuilder stringBuffer = new StringBuilder();
        for(Professor professor : professors){
            Sender.Send(professor.getEmail_address(),professor.getPsl_filename());
            Pattern pattern = Pattern.compile(time_regex);
            Matcher matcher = pattern.matcher(java.time.LocalDateTime.now().toString());
            while (matcher.find()) {
                stringBuffer.append(matcher.group(1)).append(" ").append(matcher.group(3));

            }
            professor.setEmailed_date(stringBuffer.toString());
            professorService.save(professor);
            stringBuffer = new StringBuilder();
        }
        return "redirect:/professors/docs";
    }

    @GetMapping("/professor/downloadIp")
    public ResponseEntity downloadIp(@RequestParam("prof_name") String prof_name) throws IOException {
        Professor professor = professorService.findByName(userService.getUserByUsername(prof_name).getName());
        File someFile = new File(professor.getIp_filename());
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rfc5987_encode(professor.getIp_filename()) + "\"")
                .body(FileUtils.readFileToByteArray(someFile));
    }

    @GetMapping("/professor/downloadPsl")
    public ResponseEntity downloadPsl(@RequestParam("prof_name") String prof_name) throws IOException {
        Professor professor = professorService.findByName(userService.getUserByUsername(prof_name).getName());
        File someFile = new File(professor.getPsl_filename());
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rfc5987_encode(professor.getPsl_filename()) + "\"")
                .body(FileUtils.readFileToByteArray(someFile));
    }

    private String getTimeString(Professor professor) {
        String time_regex = "([0-9[-]]+)(T)(.{5})";
        StringBuilder stringBuffer = new StringBuilder();
        Pattern pattern = Pattern.compile(time_regex);
        Matcher matcher = pattern.matcher(java.time.LocalDateTime.now().toString());
        while (matcher.find()) {
            stringBuffer.append(matcher.group(1)).append(" ").append(matcher.group(3));
        }
        professor.setEmailed_date(stringBuffer.toString());
        professorService.save(professor);
        return "redirect:/professors/docs";
    }

}
