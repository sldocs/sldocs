package com.dddd.SLDocs.core.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @Lob
    @Column
    private byte[] edasstxlsx;
    @Lob
    @Column
    private byte[] edasstpdf;
    @Lob
    @Column
    private byte[] pslxlsx;
    @Lob
    @Column
    private byte[] pslpdf;


    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.ALL})
    private Set<Department> departments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public byte[] getEdasstxlsx() {
        return edasstxlsx;
    }

    public void setEdasstxlsx(byte[] edasstxlsx) {
        this.edasstxlsx = edasstxlsx;
    }

    public byte[] getEdasstpdf() {
        return edasstpdf;
    }

    public void setEdasstpdf(byte[] edasstpdf) {
        this.edasstpdf = edasstpdf;
    }

    public byte[] getPslxlsx() {
        return pslxlsx;
    }

    public void setPslxlsx(byte[] pslxlsx) {
        this.pslxlsx = pslxlsx;
    }

    public byte[] getPslpdf() {
        return pslpdf;
    }

    public void setPslpdf(byte[] pslpdf) {
        this.pslpdf = pslpdf;
    }
}