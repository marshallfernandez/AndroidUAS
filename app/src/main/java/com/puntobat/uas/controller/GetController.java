package com.puntobat.uas.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.puntobat.uas.UAS;
import com.puntobat.uas.constans.FrameworkConstans;
import com.puntobat.uas.controller.intent.HTTPConnector;
import com.puntobat.uas.model.Specialty;
import com.puntobat.uas.model.Teacher;
import com.puntobat.uas.model.User;
import com.puntobat.uas.request.LoginRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luisbanon on 14/07/15.
 */
public class GetController extends Controller{

    public GetController(Context context){
        super (context);
    }

    public boolean login(LoginRequest login){

        boolean connect = false;

        HTTPConnector poster = new HTTPConnector();
        Gson gs = new Gson();
        String result = "";

        try {
            String req = gs.toJson(login);
            String path = FrameworkConstans.SERVER_DOMAIN+ FrameworkConstans.LOGIN_PATH ;
            result = poster.postREST(path, req, poster.JSON_TYPE);
        } catch (Exception d) {
            d.printStackTrace();
            return false;
        }

        try{
            JSONObject jsonResponse = new JSONObject(result);
            FrameworkConstans.TOKEN = jsonResponse.getString("token");

            JSONObject userObject = jsonResponse.getJSONObject("user");
            JSONObject teacherObject = userObject.getJSONObject("professor");
            User user = new User();
            Teacher teacher  = new Teacher();

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

        } catch (Exception d){

        }

        return connect;
    }

    public ArrayList<Specialty> getSpecialties(){

        HTTPConnector poster = new HTTPConnector();
        String result = "";
        ArrayList<Specialty> listSpecialties = new ArrayList<Specialty>();

        try {
            String path = FrameworkConstans.SERVER_DOMAIN+ FrameworkConstans.SPECIALTIES_PATH;
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

            for (int i = 0; i <jsonArray.length();i++){
                JSONObject object = (JSONObject)jsonArray.get(i);
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
}
