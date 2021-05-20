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
    private String posada;
    private String nauk_stupin;
    private String vch_zvana;
    private String stavka;
    private String note;
    @Lob
    @Column
    private byte[] indplanxlsx;
    @Lob
    @Column
    private byte[] indplanpdf;



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

    public byte[] getIndplanxlsx() {
        return indplanxlsx;
    }

    public void setIndplanxlsx(byte[] indplanxlsx) {
        this.indplanxlsx = indplanxlsx;
    }

    public byte[] getIndplanpdf() {
        return indplanpdf;
    }

    public void setIndplanpdf(byte[] indplanpdf) {
        this.indplanpdf = indplanpdf;
    }

    public Set<Curriculum> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(Set<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }
}
