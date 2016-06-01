package com.puntobat.uas.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.constans.FrameworkConstans;
import com.puntobat.uas.controller.intent.HTTPConnector;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.Criterio;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.ImprovementPlan;
import com.puntobat.uas.model.ImprovementPlanType;
import com.puntobat.uas.model.Schedule;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.StudentResult;
import com.puntobat.uas.model.Suggestion;
import com.puntobat.uas.model.Teacher;
import com.puntobat.uas.model.User;
import com.puntobat.uas.request.LoginRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by luisbanon on 14/07/15.
 */
public class GetController extends Controller {

    public GetController(Context context) {
        super(context);
    }

    public boolean login(LoginRequest login) {

        boolean connect = false;

        HTTPConnector poster = new HTTPConnector();
        Gson gs = new Gson();
        String result = "";

        try {
            String req = gs.toJson(login);
            String path = FrameworkConstans.SERVER_DOMAIN + FrameworkConstans.LOGIN_PATH;
            result = poster.postREST(path, req, poster.JSON_TYPE);
        } catch (Exception d) {
            d.printStackTrace();
            return false;
        }

        try {
            JSONObject jsonResponse = new JSONObject(result);
            FrameworkConstans.TOKEN = jsonResponse.getString("token");

            JSONObject userObject = jsonResponse.getJSONObject("user");
            JSONObject teacherObject = userObject.getJSONObject("professor");
            User user = new User();
            Teacher teacher = new Teacher();

            teacher.setId(teacherObject.getInt("IdDocente"));
            teacher.setIdSpecialty(Integer.valueOf(teacherObject.getString("IdEspecialidad")));
            teacher.setIdUser(Integer.valueOf(teacherObject.getString("IdUsuario")));
            teacher.setCode(teacherObject.getString("Codigo"));
            teacher.setName(teacherObject.getString("Nombre"));
            teacher.setLastName(teacherObject.getString("ApellidoPaterno"));
            teacher.setSecondLastName(teacherObject.getString("ApellidoMaterno"));
            teacher.setEmail(teacherObject.getString("Correo"));
            teacher.setCharge(teacherObject.getString("Cargo"));
            teacher.setValid(Integer.valueOf(teacherObject.getString("Vigente")));
            teacher.setDescription(teacherObject.getString("Descripcion"));

            user.setIdUser(userObject.getInt("IdUsuario"));
            user.setIdProfile(Integer.valueOf(userObject.getString("IdPerfil")));
            user.setUserName(userObject.getString("Usuario"));
            user.setTeacher(teacher);

            UAS.USER = user;

            connect = true;

        } catch (Exception d) {

        }

        return connect;
    }

    public ArrayList<Specialty> getSpecialties() {

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Specialty> listSpecialties = new ArrayList<Specialty>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + FrameworkConstans.SPECIALTIES_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<Specialty>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Specialty specialty = new Specialty();

                specialty.setId(object.getInt("IdEspecialidad"));
                specialty.setCode(object.getString("Codigo"));
                specialty.setName(object.getString("Nombre"));
                specialty.setDescription(object.getString("Descripcion"));

                listSpecialties.add(specialty);
            }

            return listSpecialties;

        } catch (Exception d) {
            return new ArrayList<Specialty>();
        }
    }

    public ArrayList<Aspect> getAspects(int idSpecialty) {

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Aspect> listAspects = new ArrayList<Aspect>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.ASPECTS_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<Aspect>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Aspect aspect = new Aspect();
                ArrayList<Criterio> lstCrit = new ArrayList<Criterio>();

                aspect.setId(object.getInt("IdAspecto"));
                aspect.setIdStudentResult(Integer.valueOf(object.getString("IdResultadoEstudiantil")));
                aspect.setName(object.getString("Nombre"));

                JSONArray criteriaArray = object.getJSONArray("criterion");

                for (int j = 0; j < criteriaArray.length(); j++) {
                    JSONObject object1 = (JSONObject) criteriaArray.get(j);
                    Criterio criterio = new Criterio();

                    criterio.setId(object1.getInt("IdCriterio"));
                    criterio.setIdAspect(Integer.valueOf(object1.getString("IdAspecto")));
                    criterio.setName(object1.getString("Nombre"));

                    lstCrit.add(criterio);
                }

                aspect.setListCriterios(lstCrit);

                listAspects.add(aspect);
            }

            return listAspects;

        } catch (Exception d) {
            return new ArrayList<Aspect>();
        }
    }

    public ArrayList<Course> getCourses(int idSpecialty) {

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Course> listCourses = new ArrayList<Course>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.COURSES_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<Course>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Course course = new Course();
                ArrayList<Schedule> lstSched = new ArrayList<Schedule>();

                course.setId(object.getInt("IdCurso"));
                course.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                course.setAcademicLevel(object.getString("NivelAcademico"));
                course.setCode(object.getString("Codigo"));
                course.setName(object.getString("Nombre"));

                JSONArray schedArray = object.getJSONArray("schedules");

                for (int j = 0; j < schedArray.length(); j++) {
                    JSONObject object1 = (JSONObject) schedArray.get(j);
                    Schedule schedule = new Schedule();
                    ArrayList<Teacher> lstTeachers = new ArrayList<Teacher>();

                    schedule.setId(object1.getInt("IdHorario"));
                    schedule.setIdCourseXSemester(Integer.valueOf(object1.getString("IdCursoxCiclo")));
                    schedule.setCode(object1.getString("Codigo"));
                    schedule.setTotalStudents(Integer.valueOf(object1.getString("TotalAlumnos")));

                    JSONArray teachArray = object1.getJSONArray("professors");

                    for (int k = 0; k < teachArray.length(); k++) {
                        JSONObject object2 = (JSONObject) teachArray.get(k);
                        Teacher teacher = new Teacher();

                        teacher.setId(object2.getInt("IdDocente"));
                        teacher.setIdSpecialty(Integer.valueOf(object2.getString("IdEspecialidad")));
                        teacher.setIdUser(Integer.valueOf(object2.getString("IdUsuario")));
                        teacher.setCode(object2.getString("Codigo"));
                        teacher.setName(object2.getString("Nombre"));
                        teacher.setLastName(object2.getString("ApellidoPaterno"));
                        teacher.setSecondLastName(object2.getString("ApellidoMaterno"));
                        teacher.setEmail(object2.getString("Correo"));
                        teacher.setCharge(object2.getString("Cargo"));
                        teacher.setValid(Integer.valueOf(object2.getString("Vigente")));
                        teacher.setDescription(object2.getString("Descripcion"));

                        lstTeachers.add(teacher);
                    }

                    schedule.setTeachers(lstTeachers);

                    lstSched.add(schedule);
                }

                course.setSchedules(lstSched);

                listCourses.add(course);
            }

            return listCourses;

        } catch (Exception d) {
            return new ArrayList<Course>();
        }
    }

    public ArrayList<StudentResult> getStudentResults(int idSpecialty) {

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<StudentResult> listStudResult = new ArrayList<StudentResult>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.STUDENT_RESULTS_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<StudentResult>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                StudentResult studentResult = new StudentResult();
                ArrayList<EducationalObjective> lstEducObj = new ArrayList<EducationalObjective>();
                ArrayList<Aspect> lstAspects = new ArrayList<Aspect>();

                studentResult.setId(object.getInt("IdResultadoEstudiantil"));
                studentResult.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                studentResult.setDescription(object.getString("Descripcion"));
                studentResult.setIdentificator(object.getString("Identificador"));
                studentResult.setSemesterReg(object.getString("CicloRegistro"));

                JSONArray educObjArray = object.getJSONArray("educational_objectives");

                for (int j = 0; j < educObjArray.length(); j++) {
                    JSONObject object1 = (JSONObject) educObjArray.get(j);
                    EducationalObjective educObj = new EducationalObjective();

                    educObj.setId(object1.getInt("IdObjetivoEducacional"));
                    educObj.setIdSpecialty(Integer.valueOf(object1.getString("IdEspecialidad")));
                    educObj.setNumber(object1.getInt("Numero"));
                    educObj.setDescription(object1.getString("Descripcion"));
                    educObj.setSemesterReg(object1.getString("CicloRegistro"));

                    lstEducObj.add(educObj);
                }

                JSONArray aspectArray = object.getJSONArray("aspects");

                for (int j = 0; j < aspectArray.length(); j++) {
                    JSONObject object1 = (JSONObject) aspectArray.get(j);
                    Aspect aspect = new Aspect();

                    aspect.setId(object1.getInt("IdAspecto"));
                    aspect.setIdStudentResult(Integer.valueOf(object1.getString("IdResultadoEstudiantil")));
                    aspect.setName(object1.getString("Nombre"));

                    lstAspects.add(aspect);
                }

                studentResult.setListAspects(lstAspects);
                studentResult.setListEducObj(lstEducObj);

                listStudResult.add(studentResult);
            }

            return listStudResult;

        } catch (Exception d) {
            return new ArrayList<StudentResult>();
        }
    }

    public ArrayList<EducationalObjective> getEducationalObjectives(int idSpecialty) {

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<EducationalObjective> listEduObj = new ArrayList<EducationalObjective>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.EDUCATIONAL_OBJECTIVES_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<EducationalObjective>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                EducationalObjective eduObj = new EducationalObjective();
                ArrayList<StudentResult> listStuRes = new ArrayList<StudentResult>();

                eduObj.setId(object.getInt("IdObjetivoEducacional"));
                eduObj.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                eduObj.setNumber(Integer.valueOf(object.getString("Numero")));
                eduObj.setDescription(object.getString("Descripcion"));
                eduObj.setSemesterReg(object.getString("CicloRegistro"));

                JSONArray stuResArray = object.getJSONArray("students_results");

                for (int j = 0; j < stuResArray.length(); j++) {
                    JSONObject object1 = (JSONObject) stuResArray.get(j);
                    StudentResult stuRes = new StudentResult();

                    stuRes.setId(object1.getInt("IdResultadoEstudiantil"));
                    stuRes.setIdSpecialty(Integer.valueOf(object1.getString("IdEspecialidad")));
                    stuRes.setDescription(object1.getString("Descripcion"));
                    stuRes.setIdentificator(object1.getString("Identificador"));
                    stuRes.setSemesterReg(object1.getString("CicloRegistro"));

                    listStuRes.add(stuRes);
                }

                eduObj.setStudentResults(listStuRes);

                listEduObj.add(eduObj);
            }

            return listEduObj;

        } catch (Exception d) {
            return new ArrayList<EducationalObjective>();
        }
    }

    public ArrayList<ImprovementPlan> getImprovementPlans(int idSpecialty) {
        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<ImprovementPlan> listImpPlan = new ArrayList<ImprovementPlan>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.IMPROVEMENT_PLANS_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<ImprovementPlan>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                ImprovementPlan impPlan = new ImprovementPlan();
                ImprovementPlanType imPlanType = new ImprovementPlanType();
                Teacher teacher = new Teacher();
                JSONObject impPlanTypeObj = object.getJSONObject("type_improvement_plan");
                JSONObject teacherObj = object.getJSONObject("teacher");

                impPlan.setId(object.getInt("IdPlanMejora"));
                impPlan.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                impPlan.setIdImprovementPlanType(Integer.valueOf(object.getString("IdTipoPlanMejora")));
                impPlan.setIdEntryFile(Integer.valueOf(object.getString("IdArchivoEntrada")));
                impPlan.setIdTeacher(Integer.valueOf(object.getString("IdDocente")));
                impPlan.setIdentificator(object.getString("Identificador"));
                impPlan.setCauseAnalisis(object.getString("AnalisisCausal"));
                impPlan.setFind(object.getString("Hallazgo"));
                impPlan.setDescription(object.getString("Descripcion"));
                impPlan.setImplementationDate(Date.valueOf(object.getString("FechaImplementacion")));
                impPlan.setStatus(object.getString("Estado"));

                imPlanType.setId(impPlanTypeObj.getInt("IdTipoPlanMejora"));
                imPlanType.setIdSpecialty(Integer.valueOf(impPlanTypeObj.getString("IdEspecialidad")));
                imPlanType.setCode(impPlanTypeObj.getString("Codigo"));
                imPlanType.setTopic(impPlanTypeObj.getString("Tema"));

                teacher.setId(teacherObj.getInt("IdDocente"));
                teacher.setIdSpecialty(Integer.valueOf(teacherObj.getString("IdEspecialidad")));
                teacher.setIdUser(Integer.valueOf(teacherObj.getString("IdUsuario")));
                teacher.setCode(teacherObj.getString("Codigo"));
                teacher.setName(teacherObj.getString("Nombre"));
                teacher.setLastName(teacherObj.getString("ApellidoPaterno"));
                teacher.setSecondLastName(teacherObj.getString("ApellidoMaterno"));
                teacher.setEmail(teacherObj.getString("Correo"));
                teacher.setCharge(teacherObj.getString("Cargo"));
                teacher.setValid(Integer.valueOf(teacherObj.getString("Vigente")));
                teacher.setDescription(teacherObj.getString("Descripcion"));

                impPlan.setImprovementPlanType(imPlanType);
                impPlan.setTeacher(teacher);

                listImpPlan.add(impPlan);
            }

            return listImpPlan;

        } catch (Exception d) {
            return new ArrayList<ImprovementPlan>();
        }
    }

    public ArrayList<Suggestion> getSuggestions(int idSpecialty) {
        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Suggestion> listSuggestions = new ArrayList<Suggestion>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.SUGGESTIONS_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new ArrayList<Suggestion>();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            //JSONObject jsonResponse = new JSONObject(result);
            //JSONArray jsonArray = jsonResponse.getJSONArray("destinos");

            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Suggestion suggestion = new Suggestion();
                ImprovementPlanType imPlanType = new ImprovementPlanType();
                Teacher teacher = new Teacher();
                JSONObject impPlanTypeObj = object.getJSONObject("type_improvement_plan");
                JSONObject teacherObj = object.getJSONObject("teacher");

                suggestion.setId(object.getInt("IdPlanMejora"));
                suggestion.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                suggestion.setIdImprovePlanType(Integer.valueOf(object.getString("IdTipoPlanMejora")));
                suggestion.setIdTeacher(Integer.valueOf(object.getString("IdDocente")));
                suggestion.setTitle(object.getString("Titulo"));
                suggestion.setDescription(object.getString("Descripcion"));
                suggestion.setDate(Date.valueOf(object.getString("Fecha")));

                imPlanType.setId(impPlanTypeObj.getInt("IdTipoPlanMejora"));
                imPlanType.setIdSpecialty(Integer.valueOf(impPlanTypeObj.getString("IdEspecialidad")));
                imPlanType.setCode(impPlanTypeObj.getString("Codigo"));
                imPlanType.setTopic(impPlanTypeObj.getString("Tema"));

                teacher.setId(teacherObj.getInt("IdDocente"));
                teacher.setIdSpecialty(Integer.valueOf(teacherObj.getString("IdEspecialidad")));
                teacher.setIdUser(Integer.valueOf(teacherObj.getString("IdUsuario")));
                teacher.setCode(teacherObj.getString("Codigo"));
                teacher.setName(teacherObj.getString("Nombre"));
                teacher.setLastName(teacherObj.getString("ApellidoPaterno"));
                teacher.setSecondLastName(teacherObj.getString("ApellidoMaterno"));
                teacher.setEmail(teacherObj.getString("Correo"));
                teacher.setCharge(teacherObj.getString("Cargo"));
                teacher.setValid(Integer.valueOf(teacherObj.getString("Vigente")));
                teacher.setDescription(teacherObj.getString("Descripcion"));

                suggestion.setImprovementPlanType(imPlanType);
                suggestion.setTeacher(teacher);

                listSuggestions.add(suggestion);
            }

            return listSuggestions;

        } catch (Exception d) {
            return new ArrayList<Suggestion>();
        }
    }

    public ArrayList<SpecialtyInfo> getSpecialtiesInfo() {
        ArrayList<SpecialtyInfo> newSpecialtiesInfo = new ArrayList<SpecialtyInfo>();

        for (Specialty specialty : UAS.SPECIALTIES) {
            SpecialtyInfo newSpecialtyInfo = new SpecialtyInfo();
            int idSpecialty = specialty.getId();

            newSpecialtyInfo.SPECIALTY = specialty;
            newSpecialtyInfo.ASPECTS = new ArrayList<Aspect>(getAspects(idSpecialty));
            newSpecialtyInfo.COURSES = new ArrayList<Course>(getCourses(idSpecialty));
            newSpecialtyInfo.STUDENTRESULTS = new ArrayList<StudentResult>(getStudentResults(idSpecialty));
            newSpecialtyInfo.EDUCATIONALOBJECTIVES = new ArrayList<EducationalObjective>(getEducationalObjectives(idSpecialty));
            newSpecialtyInfo.SUGGESTIONS = new ArrayList<Suggestion>(getSuggestions(idSpecialty));
            newSpecialtyInfo.IMPROVEMENTPLANS = new ArrayList<ImprovementPlan>(getImprovementPlans(idSpecialty));

            newSpecialtiesInfo.add(newSpecialtyInfo);
        }

        return newSpecialtiesInfo;

    }
}
