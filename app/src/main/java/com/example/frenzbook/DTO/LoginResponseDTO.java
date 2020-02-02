package com.example.frenzbook.DTO;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class LoginResponseDTO  implements Serializable
{
    @SerializedName("accessToken")
    private String accessToken;
    private String tokenType;
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getAccessToken(){
        return accessToken;
    }
    public void setTokenType(String tokenType){
        this.tokenType = tokenType;
    }
    public String getTokenType(){
        return tokenType;
    }
    @Override
    public String toString(){
        return
                "LoginResponseDTO{" +
                        "accessToken = '" + accessToken + '\'' +
                        ",tokenType = '" + tokenType + '\'' +
                        "}";
    }
}