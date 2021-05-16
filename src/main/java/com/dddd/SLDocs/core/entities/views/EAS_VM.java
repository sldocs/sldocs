package com.dddd.SLDocs.core.entities.views;

import javax.persistence.*;

@Entity
@Table(name = "EAS_VM")
public class EAS_VM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String csem;
    private String ccor;
    private String dname;
    private String group_names;
    private String lec_hours;
    private String lab_hours;
    private String pname;
    private String pract_hours;
    private String note;
    private String number_of_subgroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCsem() {
        return csem;
    }

    public void setCsem(String csem) {
        this.csem = csem;
    }

    public String getCcor() {
        return ccor;
    }

    public void setCcor(String ccor) {
        this.ccor = ccor;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getGroup_names() {
        return group_names;
    }

    public void setGroup_names(String group_names) {
        this.group_names = group_names;
    }

    public String getLec_hours() {
        return lec_hours;
    }

    public void setLec_hours(String lec_hours) {
        this.lec_hours = lec_hours;
    }

    public String getLab_hours() {
        return lab_hours;
    }

    public void setLab_hours(String lab_hours) {
        this.lab_hours = lab_hours;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPract_hours() {
        return pract_hours;
    }

    public void setPract_hours(String pract_hours) {
        this.pract_hours = pract_hours;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber_of_subgroups() {
        return number_of_subgroups;
    }

    public void setNumber_of_subgroups(String number_of_subgroups) {
        this.number_of_subgroups = number_of_subgroups;
    }
}
