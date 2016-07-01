package com.puntobat.uas.constans;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.puntobat.uas.helpers.CourseWithReport;
import com.puntobat.uas.helpers.CoursesBySemester;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.ImprovementPlan;
import com.puntobat.uas.model.Schedule;
import com.puntobat.uas.model.Semester;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.StudentResult;
import com.puntobat.uas.model.Suggestion;
import com.puntobat.uas.model.Teacher;
import com.puntobat.uas.model.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by edu24 on 29/04/2016.
 */
public class UAS {

    public static int screenWidth;
    public static int screenHeight;
    public static String appLanguage = "es";
    public static boolean ISREFRESHING = false;

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
    public static Schedule SCHEDULE;

    public static int INFOINDEX;

    public static String MYSHAREDPREFERENCENAME = "MyPrefs";
    public static String SPECIALTIESNUMBERKEY = "NumSpecs";
    public static String LOGGEDUSERSNUMBERKEY = "NumUsers";
    public static String ISLOGGEDKEY = "IsLogged";
    public static String LOGGEDUSERKEY = "LoggedUser";
    public static String SPECIALTIESKEY = "Specialty";
    public static String SPECIALTIESINFOKEY = "SpecialtyInfo";
    public static String USERNAMEKEY = "Username";
    public static String PASSWORDKEY = "Password";
    public static String LANGUAGEKEY = "Language";
    public static String USERKEY = "User";

    public static int IDCONTAINER;
    public static FragmentManager FRAGMENTMNGR;

    public static TextView ABTITLE;

    public static ArrayList<Teacher> TEACHERS;

    public static String COURSERESULTS;

    public static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean existsStudResByAspects(ArrayList<Aspect> list, StudentResult sr) {
        int id = sr.getId();
        for (Aspect aspect : list) {
            if (aspect.getIdStudentResult() == id)
                return true;
        }
        return false;
    }

    public static ArrayList<StudentResult> getStudResultsByAspects(ArrayList<Aspect> aspectsList) {

        ArrayList<StudentResult> newList = new ArrayList<StudentResult>();
        ArrayList<StudentResult> studResList = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).STUDENTRESULTS;

        for (StudentResult sr : studResList) {
            if (existsStudResByAspects(aspectsList, sr))
                newList.add(sr);
        }

        return newList;
    }

    public static Specialty getSpecialtyById(int idSpecialty) {
        for (Specialty specialty : SPECIALTIES)
            if (specialty.getId() == idSpecialty)
                return specialty;

        return null;
    }

    private static boolean semesterExists(String str, ArrayList<String> list) {
        for (String string : list) {
            if (string.compareTo(str) == 0)
                return true;
        }

        return false;
    }

    public static ArrayList<String> getSemestersByEducObjs() {
        ArrayList<String> semesters = new ArrayList<String>();
        ArrayList<EducationalObjective> listAux = SPECIALTIESINFO.get(INFOINDEX).EDUCATIONALOBJECTIVES;

        for (EducationalObjective educationalObjective : listAux) {
            if (!semesterExists(educationalObjective.getSemesterReg(), semesters))
                semesters.add(new String(educationalObjective.getSemesterReg()));
        }

        return semesters;
    }

    public static ArrayList<String> getSemestersByStudResults() {
        ArrayList<String> semesters = new ArrayList<String>();
        ArrayList<StudentResult> listAux = SPECIALTIESINFO.get(INFOINDEX).STUDENTRESULTS;

        for (StudentResult studentResult : listAux) {
            if (!semesterExists(studentResult.getSemesterReg(), semesters))
                semesters.add(new String(studentResult.getSemesterReg()));
        }

        return semesters;
    }

    public static ArrayList<String> getLoggedUsers(SharedPreferences sp) {

        ArrayList<String> users = new ArrayList<String>();

        if (sp.contains(UAS.LOGGEDUSERSNUMBERKEY)) {
            int loggedUsersNum = sp.getInt(UAS.LOGGEDUSERSNUMBERKEY, 0);

            for (int i = 1; i <= loggedUsersNum; i++) {
                String string = "";
                string = sp.getString("User" + i + UAS.USERNAMEKEY, "");
                users.add(string);
            }
        }

        return users;
    }

    public static int getExistsUser(SharedPreferences sp, String userName) {
        int numUser = -1;

        if (sp.contains(UAS.LOGGEDUSERSNUMBERKEY)) {
            int total = sp.getInt(UAS.LOGGEDUSERSNUMBERKEY, 0);
            for (int i = 1; i <= total; i++)
                if (sp.getString("User" + i + UAS.USERNAMEKEY, "").equalsIgnoreCase(userName))
                    return i;
        }

        return numUser;
    }

    public static int getUserIndex(SharedPreferences sp, String userName) {
        int numUser = 1;

        if (sp.contains(UAS.LOGGEDUSERSNUMBERKEY)) {
            int total = sp.getInt(UAS.LOGGEDUSERSNUMBERKEY, 0);
            for (int i = 1; i <= total; i++)
                if (sp.getString("User" + i + UAS.USERNAMEKEY, "").equalsIgnoreCase(userName))
                    return i;
        }

        return numUser;
    }

    public static void deleteUserInfo(SharedPreferences myPref, int index) {
        SharedPreferences.Editor prefsEditor = myPref.edit();
        Gson gson = new Gson();
        int newUsersNum = myPref.getInt(UAS.LOGGEDUSERSNUMBERKEY, 0);

        int numSpecs = myPref.getInt("User" + index + UAS.SPECIALTIESNUMBERKEY, 0);

        //saving info
        for (int i = 1; i <= numSpecs; i++) {
            prefsEditor.remove("User" + index + UAS.SPECIALTIESKEY + i);
            prefsEditor.remove("User" + index + UAS.SPECIALTIESINFOKEY + i);
        }

        //saving user
        String jsonUser = gson.toJson(UAS.USER);
        prefsEditor.remove("User" + index + UAS.USERKEY);
        prefsEditor.putString("User" + index + UAS.USERKEY, jsonUser);

        prefsEditor.remove("User" + index + UAS.SPECIALTIESNUMBERKEY);
        prefsEditor.remove("User" + index + UAS.USERNAMEKEY);
        prefsEditor.remove("User" + index + UAS.PASSWORDKEY);
        prefsEditor.remove("User" + index + UAS.LANGUAGEKEY);

        prefsEditor.remove(UAS.LOGGEDUSERSNUMBERKEY);
        prefsEditor.putInt(UAS.LOGGEDUSERSNUMBERKEY, newUsersNum - 1);

        prefsEditor.remove(UAS.ISLOGGEDKEY);
        prefsEditor.putBoolean(UAS.ISLOGGEDKEY, false);

        prefsEditor.remove(UAS.LOGGEDUSERKEY);

        prefsEditor.commit();
    }

    private static boolean existsLevel(int l, ArrayList<Integer> list) {
        for (Integer i : list) {
            if (l == i)
                return true;
        }
        return false;
    }

    public static ArrayList<Integer> getLevelsByCourses(ArrayList<CourseWithReport> courses) {
        ArrayList<CourseWithReport> aux = courses;
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (CourseWithReport c : aux) {
            int l = Integer.valueOf(c.course.getAcademicLevel());
            if (!existsLevel(l, list))
                list.add(l);
        }

        Collections.sort(list,Collections.<Integer>reverseOrder());

        return list;
    }

    public static String getDateFormat(String badFormat){
        String newDate = new String(badFormat);
        return newDate.substring(0,10);
    }

    public static ArrayList<String> getSemestersNames(ArrayList<Semester> list){
        ArrayList<String> newList = new ArrayList<String>();

        for(Semester s:list){
            String desc=new String(s.getDescription());
            newList.add(desc);
        }

        return newList;
    }

    public static ArrayList<CourseWithReport> getCoursesBySemester(String semester){

        ArrayList<CoursesBySemester> aux = UAS.SPECIALTIESINFO.get(UAS.INFOINDEX).COURSESBYSEMESTER;

        for(CoursesBySemester coursesBySemester:aux){
            if(coursesBySemester.semester.getDescription().compareToIgnoreCase(semester)==0) {
                return coursesBySemester.coursesWithReport;
            }
        }

        return new ArrayList<CourseWithReport>();
    }
}