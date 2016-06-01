package com.puntobat.uas.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by edu24 on 27/05/2016.
 */
public class StudentResult {
    private int id;
    private int idSpecialty;
    private String description;
    private String identificator;
    private String semesterReg;
    private ArrayList<EducationalObjective> listEducObj;
    private ArrayList<Aspect> listAspects;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public StudentResult(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getSemesterReg() {
        return semesterReg;
    }

    public void setSemesterReg(String semesterReg) {
        this.semesterReg = semesterReg;
    }

    public ArrayList<EducationalObjective> getListEducObj() {
        return listEducObj;
    }

    public void setListEducObj(ArrayList<EducationalObjective> listEducObj) {
        this.listEducObj = listEducObj;
    }

    public ArrayList<Aspect> getListAspects() {
        return listAspects;
    }

    public void setListAspects(ArrayList<Aspect> listAspects) {
        this.listAspects = listAspects;
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
