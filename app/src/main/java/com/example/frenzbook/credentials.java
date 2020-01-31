package com.example.frenzbook;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class credentials implements Serializable {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String Password;

    public credentials(String email,String password){
        this.email=email;
        this.Password=password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    @Override
    public String toString() {
        return "credentials{" +
                "email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
