package com.puntobat.uas;

/**
 * Created by edu24 on 29/04/2016.
 */
public class UAS{

    public static int screenWidth;
	public static int screenHeight;
    public static int fontSize;
    public static String appLanguage="es";

    public static boolean esNumero(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}