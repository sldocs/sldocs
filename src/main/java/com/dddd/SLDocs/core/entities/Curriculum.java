package com.dddd.SLDocs.core.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String year;
    private String lec_hours;
    private String lab_hours;
    private String pract_seminars;
    private String other_forms;
    private String course_projects;
    private String tasks;
    private String zalik;
    private String note;
    private String number_of_streams;
    private String number_of_subgroups;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "curriculum_discipline",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> disciplines = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "curriculum_professor",
            joinColumns = @JoinColumn(name = "curriculum_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Set<Professor> professors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id")
    private Specialty specialty;

    @OneToMany(mappedBy = "curriculum")
    private Set<Group> groups = new HashSet<>();

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getPract_seminars() {
        return pract_seminars;
    }

    public void setPract_seminars(String pract_seminars) {
        this.pract_seminars = pract_seminars;
    }

    public String getOther_forms() {
        return other_forms;
    }

    public void setOther_forms(String other_forms) {
        this.other_forms = other_forms;
    }

    public String getCourse_projects() {
        return course_projects;
    }

    public void setCourse_projects(String course_projects) {
        this.course_projects = course_projects;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getZalik() {
        return zalik;
    }

    public void setZalik(String zalik) {
        this.zalik = zalik;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber_of_streams() {
        return number_of_streams;
    }

    public void setNumber_of_streams(String number_of_streams) {
        this.number_of_streams = number_of_streams;
    }

    public String getNumber_of_subgroups() {
        return number_of_subgroups;
    }

    public void setNumber_of_subgroups(String number_of_subgroups) {
        this.number_of_subgroups = number_of_subgroups;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
