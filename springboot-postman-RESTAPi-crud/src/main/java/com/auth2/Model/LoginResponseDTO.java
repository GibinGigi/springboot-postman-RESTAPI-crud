package com.auth2.Model;

public class LoginResponseDTO {
    private ApplicationUser username;
    private String jwt;


    public LoginResponseDTO(){
        super();
    }


    public LoginResponseDTO(ApplicationUser user, String jwt){
        this.username = username;
        this.jwt = jwt;
    }


    public ApplicationUser getUser(){
        return this.username;
    }


    public void setUser(ApplicationUser username){
        this.username = username;
    }


    public String getJwt(){
        return this.jwt;
    }


    public void setJwt(String jwt){
        this.jwt = jwt;
    }


}