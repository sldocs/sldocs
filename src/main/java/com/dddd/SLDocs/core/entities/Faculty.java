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
    private String eas_filename;
    private String psl_filename;
    private String ipzip_filename;
    @Lob
    @Column
    private byte[] eas_file;
    @Lob
    @Column
    private byte[] psl_file;
    @Lob
    @Column
    private byte[] ipzip_file;

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

    public String getEas_filename() {
        return eas_filename;
    }

    public void setEas_filename(String eas_filename) {
        this.eas_filename = eas_filename;
    }

    public String getPsl_filename() {
        return psl_filename;
    }

    public void setPsl_filename(String psl_filename) {
        this.psl_filename = psl_filename;
    }

    public byte[] getEas_file() {
        return eas_file;
    }

    public void setEas_file(byte[] eas_file) {
        this.eas_file = eas_file;
    }

    public byte[] getPsl_file() {
        return psl_file;
    }

    public void setPsl_file(byte[] psl_file) {
        this.psl_file = psl_file;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getIpzip_filename() {
        return ipzip_filename;
    }

    public void setIpzip_filename(String ipzip_filename) {
        this.ipzip_filename = ipzip_filename;
    }

    public byte[] getIpzip_file() {
        return ipzip_file;
    }

    public void setIpzip_file(byte[] ipzip_file) {
        this.ipzip_file = ipzip_file;
    }
}