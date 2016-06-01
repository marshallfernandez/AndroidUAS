package com.puntobat.uas.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by edu24 on 27/05/2016.
 */
public class Aspect {

    private int id;
    private int idStudentResult;
    private String name;
    private ArrayList<Criterio> listCriterios;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public Aspect(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudentResult() {
        return idStudentResult;
    }

    public void setIdStudentResult(int idStudentResult) {
        this.idStudentResult = idStudentResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Criterio> getListCriterios() {
        return listCriterios;
    }

    public void setListCriterios(ArrayList<Criterio> listCriterios) {
        this.listCriterios = listCriterios;
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
