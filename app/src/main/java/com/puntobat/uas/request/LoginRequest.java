package com.puntobat.uas.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luisbanon on 26/05/16.
 */
public class LoginRequest {

    @SerializedName("user")
    public String user;

    @SerializedName("password")
    public String password;
}
