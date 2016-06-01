package com.puntobat.uas.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by edu24 on 27/05/2016.
 */
public class Schedule {

    private int id;
    private int idCourseXSemester;
    private String code;
    private int totalStudents;
    private ArrayList<Teacher> teachers;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public Schedule(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCourseXSemester() {
        return idCourseXSemester;
    }

    public void setIdCourseXSemester(int idCourseXSemester) {
        this.idCourseXSemester = idCourseXSemester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
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
