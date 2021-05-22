package com.dddd.SLDocs.core.entities;

public class StudyLoad {

    private Curriculum curriculum;
    private Department department;
    private Discipline discipline;
    private Faculty faculty;
    private Professor professor;
    private Specialty specialty;


    public StudyLoad() {
        curriculum = new Curriculum();
        department = new Department();
        discipline = new Discipline();
        faculty = new Faculty();
        professor = new Professor();
        specialty = new Specialty();
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
