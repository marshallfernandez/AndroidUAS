package com.puntobat.uas.helpers;

import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.ImprovementPlan;
import com.puntobat.uas.model.Semester;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.SpecialtyConf;
import com.puntobat.uas.model.StudentResult;
import com.puntobat.uas.model.Suggestion;

import java.util.ArrayList;

/**
 * Created by edu24 on 30/05/2016.
 */
public class SpecialtyInfo {
    
    public Specialty SPECIALTY;
    public ArrayList<Aspect> ASPECTS;
    public ArrayList<Course> COURSES;
    public ArrayList<StudentResult> STUDENTRESULTS;
    public ArrayList<EducationalObjective> EDUCATIONALOBJECTIVES;
    public ArrayList<Suggestion> SUGGESTIONS;
    public ArrayList<ImprovementPlan> IMPROVEMENTPLANS;
    public String HTMLREPORT;
    public SpecialtyConf SPECCONFIG;
    public ArrayList<Semester> PERIODSEMESTERS;
    public ArrayList<CoursesBySemester> COURSESBYSEMESTER;

    public SpecialtyInfo(){}

}
