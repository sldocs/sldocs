package com.dddd.SLDocs.core.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String full_name;
    private String posada;
    private String nauk_stupin;
    private String vch_zvana;
    private String stavka;
    private String note;
    private String ip_filename;
    private String psl_filename;
    private String email_address;
    private String emailed_date;
    private String autumn_asp;
    private String spring_asp;
    private String asp_num;

    @ManyToMany(mappedBy = "professors", fetch = FetchType.LAZY)
    private Set<Curriculum> curriculums = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosada() {
        return posada;
    }

    public void setPosada(String posada) {
        this.posada = posada;
    }

    public String getNauk_stupin() {
        return nauk_stupin;
    }

    public void setNauk_stupin(String nauk_stupin) {
        this.nauk_stupin = nauk_stupin;
    }

    public String getVch_zvana() {
        return vch_zvana;
    }

    public void setVch_zvana(String vch_zvana) {
        this.vch_zvana = vch_zvana;
    }

    public String getStavka() {
        return stavka;
    }

    public void setStavka(String stavka) {
        this.stavka = stavka;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIp_filename() {
        return ip_filename;
    }

    public void setIp_filename(String ip_filename) {
        this.ip_filename = ip_filename;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public Set<Curriculum> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(Set<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public String getEmailed_date() {
        return emailed_date;
    }

    public void setEmailed_date(String emailed_date) {
        this.emailed_date = emailed_date;
    }

    public String getPsl_filename() {
        return psl_filename;
    }

    public void setPsl_filename(String psl_filename) {
        this.psl_filename = psl_filename;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAutumn_asp() {
        return autumn_asp;
    }

    public void setAutumn_asp(String autumn_asp) {
        this.autumn_asp = autumn_asp;
    }

    public String getSpring_asp() {
        return spring_asp;
    }

    public void setSpring_asp(String spring_asp) {
        this.spring_asp = spring_asp;
    }

    public String getAsp_num() {
        return asp_num;
    }

    public void setAsp_num(String asp_num) {
        this.asp_num = asp_num;
    }
}
