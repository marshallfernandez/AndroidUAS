package com.puntobat.uas.model;

import java.util.Date;

/**
 * Created by edu24 on 30/05/2016.
 */
public class Suggestion {

    private int id;
    private int idImprovePlan;
    private int idTeacher;
    private int idSpecialty;
    private String date;
    private String title;
    private String description;
    private Teacher teacher;
    private ImprovementPlan improvementPlan;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImprovePlan() {
        return idImprovePlan;
    }

    public void setIdImprovePlan(int idImprovePlan) {
        this.idImprovePlan = idImprovePlan;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public ImprovementPlan getImprovementPlan() {
        return improvementPlan;
    }

    public void setImprovementPlan(ImprovementPlan improvementPlan) {
        this.improvementPlan = improvementPlan;
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
