package com.example.frenzbook;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class secondcredentials implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String Password;

    public secondcredentials(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.Password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "secondcredentials{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
