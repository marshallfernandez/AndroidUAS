package com.puntobat.uas.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.puntobat.uas.constans.UAS;
import com.puntobat.uas.constans.FrameworkConstans;
import com.puntobat.uas.controller.intent.HTTPConnector;
import com.puntobat.uas.helpers.SpecialtyInfo;
import com.puntobat.uas.model.Accreditor;
import com.puntobat.uas.model.Aspect;
import com.puntobat.uas.model.Course;
import com.puntobat.uas.model.Criterio;
import com.puntobat.uas.model.EducationalObjective;
import com.puntobat.uas.model.ImprovementPlan;
import com.puntobat.uas.model.ImprovementPlanType;
import com.puntobat.uas.model.Schedule;
import com.puntobat.uas.model.Semester;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.SpecialtyConf;
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

    public void updateToken(LoginRequest login) {

        HTTPConnector poster = new HTTPConnector();
        Gson gs = new Gson();
        String result = "";

        try {
            String req = gs.toJson(login);
            String path = FrameworkConstans.SERVER_DOMAIN + FrameworkConstans.LOGIN_PATH;
            result = poster.postREST(path, req, poster.JSON_TYPE);
        } catch (Exception d) {
            d.printStackTrace();
        }

        try {
            JSONObject jsonResponse = new JSONObject(result);
            FrameworkConstans.TOKEN = jsonResponse.getString("token");

            JSONObject userObject = jsonResponse.getJSONObject("user");
            User user = new User();
            Teacher teacher = null;
            Accreditor accreditor = null;

            if (!userObject.isNull("professor")) {
                JSONObject teacherObject = userObject.getJSONObject("professor");
                teacher = new Teacher();

                teacher.setId(teacherObject.getInt("IdDocente"));
                if (!teacherObject.isNull("IdEspecialidad"))
                    teacher.setIdSpecialty(Integer.valueOf(teacherObject.getString("IdEspecialidad")));
                if (!teacherObject.isNull("IdUsuario"))
                    teacher.setIdUser(Integer.valueOf(teacherObject.getString("IdUsuario")));
                teacher.setCode(teacherObject.getString("Codigo"));
                teacher.setName(teacherObject.getString("Nombre"));
                teacher.setLastName(teacherObject.getString("ApellidoPaterno"));
                teacher.setSecondLastName(teacherObject.getString("ApellidoMaterno"));
                teacher.setEmail(teacherObject.getString("Correo"));
                teacher.setCharge(teacherObject.getString("Cargo"));
                if (!teacherObject.isNull("Vigente"))
                    teacher.setValid(Integer.valueOf(teacherObject.getString("Vigente")));
                teacher.setDescription(teacherObject.getString("Descripcion"));
            }

            if (!userObject.isNull("accreditor")) {
                JSONObject accredObject = userObject.getJSONObject("accreditor");
                accreditor = new Accreditor();

                accreditor.setId(accredObject.getInt("IdAcreditador"));
                if (!accredObject.isNull("IdEspecialidad"))
                    accreditor.setIdSpecialty(Integer.valueOf(accredObject.getString("IdEspecialidad")));
                if (!accredObject.isNull("IdUsuario"))
                    accreditor.setIdUser(Integer.valueOf(accredObject.getString("IdUsuario")));
                accreditor.setName(accredObject.getString("Nombre"));
                accreditor.setLastName(accredObject.getString("ApellidoPaterno"));
                accreditor.setSecLastName(accredObject.getString("ApellidoMaterno"));
                accreditor.setEmail(accredObject.getString("Correo"));
                if (!accredObject.isNull("Vigente"))
                    accreditor.setValid(Integer.valueOf(accredObject.getString("Vigente")));
            }

            user.setIdUser(userObject.getInt("IdUsuario"));
            user.setIdProfile(Integer.valueOf(userObject.getString("IdPerfil")));
            user.setUserName(userObject.getString("Usuario"));

            user.setTeacher(teacher);
            user.setAccreditor(accreditor);

            UAS.USER = user;

        } catch (Exception d) {

        }
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
            User user = new User();
            Teacher teacher = null;
            Accreditor accreditor = null;

            if (!userObject.isNull("professor")) {
                JSONObject teacherObject = userObject.getJSONObject("professor");
                teacher = new Teacher();

                teacher.setId(teacherObject.getInt("IdDocente"));
                if (!teacherObject.isNull("IdEspecialidad"))
                    teacher.setIdSpecialty(Integer.valueOf(teacherObject.getString("IdEspecialidad")));
                if (!teacherObject.isNull("IdUsuario"))
                    teacher.setIdUser(Integer.valueOf(teacherObject.getString("IdUsuario")));
                teacher.setCode(teacherObject.getString("Codigo"));
                teacher.setName(teacherObject.getString("Nombre"));
                teacher.setLastName(teacherObject.getString("ApellidoPaterno"));
                teacher.setSecondLastName(teacherObject.getString("ApellidoMaterno"));
                teacher.setEmail(teacherObject.getString("Correo"));
                teacher.setCharge(teacherObject.getString("Cargo"));
                if (!teacherObject.isNull("Vigente"))
                    teacher.setValid(Integer.valueOf(teacherObject.getString("Vigente")));
                teacher.setDescription(teacherObject.getString("Descripcion"));
            }

            if (!userObject.isNull("accreditor")) {
                JSONObject accredObject = userObject.getJSONObject("accreditor");
                accreditor = new Accreditor();

                accreditor.setId(accredObject.getInt("IdAcreditador"));
                if (!accredObject.isNull("IdEspecialidad"))
                    accreditor.setIdSpecialty(Integer.valueOf(accredObject.getString("IdEspecialidad")));
                if (!accredObject.isNull("IdUsuario"))
                    accreditor.setIdUser(Integer.valueOf(accredObject.getString("IdUsuario")));
                accreditor.setName(accredObject.getString("Nombre"));
                accreditor.setLastName(accredObject.getString("ApellidoPaterno"));
                accreditor.setSecLastName(accredObject.getString("ApellidoMaterno"));
                accreditor.setEmail(accredObject.getString("Correo"));
                if (!accredObject.isNull("Vigente"))
                    accreditor.setValid(Integer.valueOf(accredObject.getString("Vigente")));
            }

            user.setIdUser(userObject.getInt("IdUsuario"));
            user.setIdProfile(Integer.valueOf(userObject.getString("IdPerfil")));
            user.setUserName(userObject.getString("Usuario"));

            user.setTeacher(teacher);
            user.setAccreditor(accreditor);

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
                Teacher teacher = null;

                specialty.setId(object.getInt("IdEspecialidad"));
                specialty.setCode(object.getString("Codigo"));
                specialty.setName(object.getString("Nombre"));
                specialty.setDescription(object.getString("Descripcion"));

                if (!object.isNull("coordinator")) {
                    teacher = new Teacher();
                    JSONObject teacherObj = object.getJSONObject("coordinator");
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
                }

                specialty.setTeacher(teacher);

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
                if (!object.isNull("IdResultadoEstudiantil"))
                    aspect.setIdStudentResult(Integer.valueOf(object.getString("IdResultadoEstudiantil")));
                aspect.setName(object.getString("Nombre"));
                if (!object.isNull("Estado"))
                    aspect.setStatus(Integer.valueOf(object.getString("Estado")));

                if (!object.isNull("criterion")) {

                    JSONArray criteriaArray = object.getJSONArray("criterion");

                    for (int j = 0; j < criteriaArray.length(); j++) {
                        JSONObject object1 = (JSONObject) criteriaArray.get(j);
                        Criterio criterio = new Criterio();

                        criterio.setId(object1.getInt("IdCriterio"));
                        if (!object1.isNull("IdAspecto"))
                            criterio.setIdAspect(Integer.valueOf(object1.getString("IdAspecto")));
                        criterio.setName(object1.getString("Nombre"));
                        if (!object1.isNull("Estado"))
                            criterio.setStatus(Integer.valueOf(object1.getString("Estado")));

                        lstCrit.add(criterio);
                    }
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
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.EVALUATED_COURSES_PATH;
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
                ArrayList<Semester> lstSemesters = new ArrayList<Semester>();

                course.setId(object.getInt("IdCurso"));
                if (!object.isNull("IdEspecialidad"))
                    course.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                course.setAcademicLevel(object.getString("NivelAcademico"));
                course.setCode(object.getString("Codigo"));
                course.setName(object.getString("Nombre"));

                if (!object.isNull("schedules")) {

                    JSONArray schedArray = object.getJSONArray("schedules");

                    for (int j = 0; j < schedArray.length(); j++) {
                        JSONObject object1 = (JSONObject) schedArray.get(j);
                        Schedule schedule = new Schedule();
                        ArrayList<Teacher> lstTeachers = new ArrayList<Teacher>();

                        schedule.setId(object1.getInt("IdHorario"));
                        if (!object1.isNull("IdCursoxCiclo"))
                            schedule.setIdCourseXSemester(Integer.valueOf(object1.getString("IdCursoxCiclo")));
                        schedule.setCode(object1.getString("Codigo"));
                        if (!object1.isNull("TotalAlumnos"))
                            schedule.setTotalStudents(Integer.valueOf(object1.getString("TotalAlumnos")));

                        if (!object1.isNull("professors")) {

                            JSONArray teachArray = object1.getJSONArray("professors");

                            for (int k = 0; k < teachArray.length(); k++) {
                                JSONObject object2 = (JSONObject) teachArray.get(k);
                                Teacher teacher = new Teacher();

                                teacher.setId(object2.getInt("IdDocente"));
                                if (!object2.isNull("IdEspecialidad"))
                                    teacher.setIdSpecialty(Integer.valueOf(object2.getString("IdEspecialidad")));
                                if (!object2.isNull("IdUsuario"))
                                    teacher.setIdUser(Integer.valueOf(object2.getString("IdUsuario")));
                                teacher.setCode(object2.getString("Codigo"));
                                teacher.setName(object2.getString("Nombre"));
                                teacher.setLastName(object2.getString("ApellidoPaterno"));
                                teacher.setSecondLastName(object2.getString("ApellidoMaterno"));
                                teacher.setEmail(object2.getString("Correo"));
                                teacher.setCharge(object2.getString("Cargo"));
                                if (!object2.isNull("Vigente"))
                                    teacher.setValid(Integer.valueOf(object2.getString("Vigente")));
                                teacher.setDescription(object2.getString("Descripcion"));

                                lstTeachers.add(teacher);
                            }

                        }

                        schedule.setTeachers(lstTeachers);

                        lstSched.add(schedule);
                    }

                }

                if (!object.isNull("semesters")) {

                    JSONArray semestersArray = object.getJSONArray("semesters");

                    for (int j = 0; j < semestersArray.length(); j++) {
                        JSONObject object1 = (JSONObject) semestersArray.get(j);
                        Semester semester = new Semester();

                        semester.setId(object1.getInt("IdCicloAcademico"));
                        if (!object1.isNull("IdCiclo"))
                            semester.setIdSemester(Integer.valueOf(object1.getString("IdCiclo")));
                        if (!object1.isNull("IdEspecialidad"))
                            semester.setIdSpecialty(Integer.valueOf(object1.getString("IdEspecialidad")));
                        if (!object1.isNull("IdDocente"))
                            semester.setIdTeacher(Integer.valueOf(object1.getString("IdDocente")));
                        if (!object1.isNull("IdPeriodo"))
                            semester.setIdPeriod(Integer.valueOf(object1.getString("IdPeriodo")));
                        if (!object1.isNull("Numero"))
                            semester.setNumber(Integer.valueOf(object1.getString("Numero")));
                        if (!object1.isNull("Vigente"))
                            semester.setValid(Integer.valueOf(object1.getString("Vigente")));
                        semester.setDescription(object1.getString("Descripcion"));
                        if (!object1.isNull("FechaInicio"))
                            semester.setStartDate(object1.getString("FechaInicio"));
                        if (!object1.isNull("FechaFin"))
                            semester.setEndDate(object1.getString("FechaFin"));

                        lstSemesters.add(semester);
                    }

                }

                course.setSchedules(lstSched);
                course.setSemesters(lstSemesters);

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
                if (!object.isNull("IdEspecialidad"))
                    studentResult.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                studentResult.setDescription(object.getString("Descripcion"));
                studentResult.setIdentificator(object.getString("Identificador"));
                studentResult.setSemesterReg(object.getString("CicloRegistro"));
                if (!object.isNull("Estado"))
                    studentResult.setStatus(Integer.valueOf(object.getString("Estado")));


                if (!object.isNull("educational_objectives")) {
                    JSONArray educObjArray = object.getJSONArray("educational_objectives");

                    for (int j = 0; j < educObjArray.length(); j++) {
                        JSONObject object1 = (JSONObject) educObjArray.get(j);
                        EducationalObjective educObj = new EducationalObjective();

                        educObj.setId(object1.getInt("IdObjetivoEducacional"));
                        if (!object1.isNull("IdEspecialidad"))
                            educObj.setIdSpecialty(Integer.valueOf(object1.getString("IdEspecialidad")));
                        educObj.setNumber(object1.getInt("Numero"));
                        educObj.setDescription(object1.getString("Descripcion"));
                        educObj.setSemesterReg(object1.getString("CicloRegistro"));
                        if (!object1.isNull("Estado"))
                            educObj.setStatus(Integer.valueOf(object1.getString("Estado")));

                        lstEducObj.add(educObj);
                    }

                }

                if (!object.isNull("aspects")) {
                    JSONArray aspectArray = object.getJSONArray("aspects");

                    for (int j = 0; j < aspectArray.length(); j++) {
                        JSONObject object1 = (JSONObject) aspectArray.get(j);
                        Aspect aspect = new Aspect();

                        aspect.setId(object1.getInt("IdAspecto"));
                        if (!object1.isNull("IdResultadoEstudiantil"))
                            aspect.setIdStudentResult(Integer.valueOf(object1.getString("IdResultadoEstudiantil")));
                        aspect.setName(object1.getString("Nombre"));
                        if (!object1.isNull("Estado"))
                            aspect.setStatus(Integer.valueOf(object1.getString("Estado")));

                        lstAspects.add(aspect);
                    }

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
                if (!object.isNull("IdEspecialidad"))
                    eduObj.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                if (!object.isNull("Numero"))
                    eduObj.setNumber(Integer.valueOf(object.getString("Numero")));
                eduObj.setDescription(object.getString("Descripcion"));
                eduObj.setSemesterReg(object.getString("CicloRegistro"));
                if (!object.isNull("Estado"))
                    eduObj.setStatus(Integer.valueOf(object.getString("Estado")));

                if (!object.isNull("students_results")) {
                    JSONArray stuResArray = object.getJSONArray("students_results");

                    for (int j = 0; j < stuResArray.length(); j++) {
                        JSONObject object1 = (JSONObject) stuResArray.get(j);
                        StudentResult stuRes = new StudentResult();

                        stuRes.setId(object1.getInt("IdResultadoEstudiantil"));
                        if (!object1.isNull("IdEspecialidad"))
                            stuRes.setIdSpecialty(Integer.valueOf(object1.getString("IdEspecialidad")));
                        stuRes.setDescription(object1.getString("Descripcion"));
                        stuRes.setIdentificator(object1.getString("Identificador"));
                        stuRes.setSemesterReg(object1.getString("CicloRegistro"));
                        if (!object1.isNull("Estado"))
                            stuRes.setStatus(Integer.valueOf(object1.getString("Estado")));

                        listStuRes.add(stuRes);
                    }

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

                impPlan.setId(object.getInt("IdPlanMejora"));
                if (!object.isNull("IdTipoPlanMejora"))
                    impPlan.setIdImprovementPlanType(Integer.valueOf(object.getString("IdTipoPlanMejora")));
                if (!object.isNull("IdEspecialidad"))
                    impPlan.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));

                /*String aux = object.getString("IdArchivoEntrada");

                if (aux != null)
                    impPlan.setIdEntryFile(Integer.valueOf(aux));
                else
                    impPlan.setIdEntryFile(-1);*/

                if (!object.isNull("IdDocente"))
                    impPlan.setIdTeacher(Integer.valueOf(object.getString("IdDocente")));
                impPlan.setIdentificator(object.getString("Identificador"));
                impPlan.setCauseAnalisis(object.getString("AnalisisCausal"));
                impPlan.setFind(object.getString("Hallazgo"));
                impPlan.setDescription(object.getString("Descripcion"));
                impPlan.setImplementationDate(object.getString("FechaImplementacion"));
                impPlan.setStatus(object.getString("Estado"));


                if (!object.isNull("type_improvement_plan")) {
                    JSONObject impPlanTypeObj = object.getJSONObject("type_improvement_plan");

                    imPlanType.setId(impPlanTypeObj.getInt("IdTipoPlanMejora"));
                    imPlanType.setIdSpecialty(Integer.valueOf(impPlanTypeObj.getString("IdEspecialidad")));
                    imPlanType.setCode(impPlanTypeObj.getString("Codigo"));
                    imPlanType.setTopic(impPlanTypeObj.getString("Tema"));
                    imPlanType.setDescription(impPlanTypeObj.getString("Descripcion"));

                }

                if (!object.isNull("teacher")) {
                    JSONObject teacherObj = object.getJSONObject("teacher");

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

                }

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
                ImprovementPlan impPlan = new ImprovementPlan();
                Teacher teacher = new Teacher();

                suggestion.setId(object.getInt("IdSugerencia"));
                if (!object.isNull("IdPlanMejora"))
                    suggestion.setIdImprovePlan(Integer.valueOf(object.getString("IdPlanMejora")));
                if (!object.isNull("IdDocente"))
                suggestion.setIdTeacher(Integer.valueOf(object.getString("IdDocente")));
                if (!object.isNull("IdEspecialidad"))
                suggestion.setIdSpecialty(Integer.valueOf(object.getString("IdEspecialidad")));
                suggestion.setDate(object.getString("Fecha"));
                suggestion.setTitle(object.getString("Titulo"));
                suggestion.setDescription(object.getString("Descripcion"));

                if (!object.isNull("improvement_plan")) {
                    JSONObject impPlanObj = object.getJSONObject("improvement_plan");

                    impPlan.setId(impPlanObj.getInt("IdPlanMejora"));
                    impPlan.setIdImprovementPlanType(Integer.valueOf(impPlanObj.getString("IdTipoPlanMejora")));
                    impPlan.setIdSpecialty(Integer.valueOf(impPlanObj.getString("IdEspecialidad")));
                    impPlan.setIdEntryFile(Integer.valueOf(impPlanObj.getString("IdArchivoEntrada")));
                    impPlan.setIdTeacher(Integer.valueOf(impPlanObj.getString("IdDocente")));
                    impPlan.setIdentificator(impPlanObj.getString("Identificador"));
                    impPlan.setCauseAnalisis(impPlanObj.getString("AnalisisCausal"));
                    impPlan.setFind(impPlanObj.getString("Hallazgo"));
                    impPlan.setDescription(impPlanObj.getString("Descripcion"));
                    impPlan.setImplementationDate(impPlanObj.getString("FechaImplementacion"));
                    impPlan.setStatus(impPlanObj.getString("Estado"));
                    impPlan.setFileURL(impPlanObj.getString("file_url"));
                }

                if (!object.isNull("teacher")) {
                    JSONObject teacherObj = object.getJSONObject("teacher");

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

                }

                suggestion.setImprovementPlan(impPlan);
                suggestion.setTeacher(teacher);

                listSuggestions.add(suggestion);
            }

            return listSuggestions;

        } catch (Exception d) {
            return new ArrayList<Suggestion>();
        }
    }

    public String getHtmlReport(int idSpecialty) {
        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Suggestion> listSuggestions = new ArrayList<Suggestion>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.HTMLREPORT_PATH;
            result = poster.getRESTWithToken(path);
            return result;
        } catch (Exception d) {
            d.printStackTrace();
            return "";
        }
    }

    public SpecialtyConf getSpecialtyConf(int idSpecialty) {
        HTTPConnector poster = new HTTPConnector();
        String result = "";
        SpecialtyConf specialtyConf;

        try {
            String path = FrameworkConstans.SERVER_DOMAIN + "faculties/" + String.valueOf(idSpecialty) + FrameworkConstans.SPECCONFIG_PATH;
            result = poster.getRESTWithToken(path);
        } catch (Exception d) {
            d.printStackTrace();
            return new SpecialtyConf();
        }

        try {

            // esto es si el json devuelve un dato con varios destinos dentro
            JSONObject object = new JSONObject(result);
            JSONObject object1 = object.getJSONObject("configuration");

            specialtyConf = new SpecialtyConf();

            specialtyConf.setId(object1.getInt("IdConfEspecialidad"));
            specialtyConf.setAceptThreshold(object1.getString("UmbralAceptacion"));
            specialtyConf.setCriteriaLevel(object1.getString("CantNivelCriterio"));
            specialtyConf.setLevelExpect(object1.getString("NivelEsperado"));

            return specialtyConf;

        } catch (Exception d) {
            return new SpecialtyConf();
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
            newSpecialtyInfo.HTMLREPORT = new String(getHtmlReport(idSpecialty));
            newSpecialtyInfo.SPECCONFIG = getSpecialtyConf(idSpecialty);

            newSpecialtiesInfo.add(newSpecialtyInfo);
        }

        return newSpecialtiesInfo;

    }
}
