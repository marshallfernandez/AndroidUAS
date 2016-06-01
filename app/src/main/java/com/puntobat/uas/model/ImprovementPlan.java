package com.puntobat.uas.model;

import java.util.Date;

/**
 * Created by edu24 on 30/05/2016.
 */
public class ImprovementPlan {

    private int id;
    private int idImprovementPlanType;
    private int idSpecialty;
    private int idEntryFile;
    private int idTeacher;
    private String identificator;
    private String causeAnalisis;
    private String find;
    private String description;
    private Date implementationDate;
    private String status;
    private ImprovementPlanType improvementPlanType;
    private Teacher teacher;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public ImprovementPlan(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImprovementPlanType() {
        return idImprovementPlanType;
    }

    public void setIdImprovementPlanType(int idImprovementPlanType) {
        this.idImprovementPlanType = idImprovementPlanType;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public int getIdEntryFile() {
        return idEntryFile;
    }

    public void setIdEntryFile(int idEntryFile) {
        this.idEntryFile = idEntryFile;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getCauseAnalisis() {
        return causeAnalisis;
    }

    public void setCauseAnalisis(String causeAnalisis) {
        this.causeAnalisis = causeAnalisis;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getImplementationDate() {
        return implementationDate;
    }

    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ImprovementPlanType getImprovementPlanType() {
        return improvementPlanType;
    }

    public void setImprovementPlanType(ImprovementPlanType improvementPlanType) {
        this.improvementPlanType = improvementPlanType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
