package com.puntobat.uas.constans;

import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.ImprovementPlan;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.StudentResult;
import com.puntobat.uas.model.Suggestion;
import com.puntobat.uas.model.User;

import java.util.ArrayList;

/**
 * Created by edu24 on 29/04/2016.
 */
public class UAS {

    public static int screenWidth;
    public static int screenHeight;
    public static String appLanguage = "es";

    public static User USER;

    public static ArrayList<Specialty> SPECIALTIES = new ArrayList<Specialty>();
    public static ArrayList<SpecialtyInfo> SPECIALTIESINFO = new ArrayList<SpecialtyInfo>();
    public static ImprovementPlan IMPROVEMENTPLAN;
    public static Suggestion SUGGESTION;
    public static Aspect ASPECT;
    public static Course COURSE;
    public static StudentResult STUDENTRESULT;
    public static EducationalObjective EDUCATIONALOBJECTIVE;
    public static Specialty SPECIALTY;

    public static int INFOINDEX;

    public static boolean esNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static StudentResult getStudResultByAspect(int id) {

        ArrayList<StudentResult> listAux = SPECIALTIESINFO.get(INFOINDEX).STUDENTRESULTS;

        for (int i = 0; i < listAux.size(); i++) {
            StudentResult stdResult = listAux.get(i);
            if (stdResult.getId() == id)
                return stdResult;
        }

        return null;
    }

    public static Specialty getSpecialtyById(int idSpecialty){
        for(Specialty specialty:SPECIALTIES)
            if(specialty.getId()==idSpecialty)
                return specialty;

        return null;
    }
}