package com.puntobat.uas.model;

import java.util.Date;

/**
 * Created by edu24 on 30/05/2016.
 */
public class Suggestion {

    private int id;
    private int idImprovePlanType;
    private int idTeacher;
    private int idSpecialty;
    private Date date;
    private String title;
    private String description;
    private Teacher teacher;
    private ImprovementPlanType improvementPlanType;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public Suggestion(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImprovePlanType() {
        return idImprovePlanType;
    }

    public void setIdImprovePlanType(int idImprovePlanType) {
        this.idImprovePlanType = idImprovePlanType;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ImprovementPlanType getImprovementPlanType() {
        return improvementPlanType;
    }

    public void setImprovementPlanType(ImprovementPlanType improvementPlanType) {
        this.improvementPlanType = improvementPlanType;
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
