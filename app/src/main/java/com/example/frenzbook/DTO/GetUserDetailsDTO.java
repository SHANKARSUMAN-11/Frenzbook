package com.example.frenzbook.DTO;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class GetUserDetailsDTO implements Serializable
{
    @SerializedName("id")
    private Long id;
    private String accessToken;
    private String name;
    private String email;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
}