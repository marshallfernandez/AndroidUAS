package com.puntobat.uas;

import com.puntobat.uas.model.Specialty;
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
    public static Specialty SPECIALTY;

    public static boolean esNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}