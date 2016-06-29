package com.puntobat.uas.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by edu24 on 27/05/2016.
 */
public class EducationalObjective {
    private int id;
    private int idSpecialty;
    private int number;
    private String description;
    private String semesterReg;
    private int status;
    private ArrayList<StudentResult> studentResults;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public EducationalObjective(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemesterReg() {
        return semesterReg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSemesterReg(String semesterReg) {
        this.semesterReg = semesterReg;
    }

    public ArrayList<StudentResult> getStudentResults() {
        return studentResults;
    }

    public void setStudentResults(ArrayList<StudentResult> studentResults) {
        this.studentResults = studentResults;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
