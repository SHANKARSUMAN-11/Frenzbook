package com.example.frenzbook.DTO;
public class SignUpResponseDTO
{
    private boolean success;
    private String message;
    public boolean isSuccess()
    {
        return success;
    }
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    @Override
    public String toString() {
        return "SignUpResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}