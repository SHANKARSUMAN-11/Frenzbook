package com.example.frenzbook.DTO;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class SecondLoginRequestDTO implements Serializable
{
    @SerializedName("provider")
    private int provider=1;
    public int getProvider()
    {
        return provider;
    }
    public void setProvider(int provider)
    {
        this.provider = provider;
    }
    @Override
    public String toString() {
        return "SecondLoginRequestDTO{" +
                "provider=" + provider +
                '}';
    }
}