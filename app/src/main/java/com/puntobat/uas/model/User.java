package com.puntobat.uas.model;

import java.util.Date;

/**
 * Created by edu24 on 26/05/2016.
 */
public class User {

    private int id;
    private int idProfile;
    private String userName;
    private Teacher teacher;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public User(){}

    public int getIdUser() {
        return id;
    }

    public void setIdUser(int idUser) {
        this.id = idUser;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
