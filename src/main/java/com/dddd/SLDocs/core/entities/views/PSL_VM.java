package com.dddd.SLDocs.core.entities.views;

import javax.persistence.*;

@Entity
@Table(name = "PSL_VM")
public class PSL_VM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String year;
    private String course;
    private String csem;
    private String number_of_subgroups;
    private String students_number;
    private String group_names;
    private String lec_hours;
    private String consult_hours;
    private String lab_hours;
    private String pract_hours;
    private String ind_task_hours;
    private String cp_hours;
    private String zalik_hours;
    private String exam_hours;
    private String diploma_hours;
    private String dec_cell;
    private String ndrs;
    private String aspirant_hours;
    private String practice;
    private String other_forms_hours;
    private String pname;
    private String dname;
    private String dep_name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCsem() {
        return csem;
    }

    public void setCsem(String csem) {
        this.csem = csem;
    }

    public String getNumber_of_subgroups() {
        return number_of_subgroups;
    }

    public void setNumber_of_subgroups(String number_of_subgroups) {
        this.number_of_subgroups = number_of_subgroups;
    }

    public String getStudents_number() {
        return students_number;
    }

    public void setStudents_number(String students_number) {
        this.students_number = students_number;
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

    public String getConsult_hours() {
        return consult_hours;
    }

    public void setConsult_hours(String consult_hours) {
        this.consult_hours = consult_hours;
    }

    public String getLab_hours() {
        return lab_hours;
    }

    public void setLab_hours(String lab_hours) {
        this.lab_hours = lab_hours;
    }

    public String getPract_hours() {
        return pract_hours;
    }

    public void setPract_hours(String pract_hours) {
        this.pract_hours = pract_hours;
    }

    public String getInd_task_hours() {
        return ind_task_hours;
    }

    public void setInd_task_hours(String ind_task_hours) {
        this.ind_task_hours = ind_task_hours;
    }

    public String getCp_hours() {
        return cp_hours;
    }

    public void setCp_hours(String cp_hours) {
        this.cp_hours = cp_hours;
    }

    public String getZalik_hours() {
        return zalik_hours;
    }

    public void setZalik_hours(String zalik_hours) {
        this.zalik_hours = zalik_hours;
    }

    public String getExam_hours() {
        return exam_hours;
    }

    public void setExam_hours(String exam_hours) {
        this.exam_hours = exam_hours;
    }

    public String getDiploma_hours() {
        return diploma_hours;
    }

    public void setDiploma_hours(String diploma_hours) {
        this.diploma_hours = diploma_hours;
    }

    public String getDec_cell() {
        return dec_cell;
    }

    public void setDec_cell(String dec_cell) {
        this.dec_cell = dec_cell;
    }

    public String getNdrs() {
        return ndrs;
    }

    public void setNdrs(String ndrs) {
        this.ndrs = ndrs;
    }

    public String getAspirant_hours() {
        return aspirant_hours;
    }

    public void setAspirant_hours(String aspirant_hours) {
        this.aspirant_hours = aspirant_hours;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getOther_forms_hours() {
        return other_forms_hours;
    }

    public void setOther_forms_hours(String other_forms_hours) {
        this.other_forms_hours = other_forms_hours;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
