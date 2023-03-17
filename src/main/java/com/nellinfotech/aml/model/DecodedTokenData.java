package com.nellinfotech.aml.model;

public class DecodedTokenData {
    private int userid;
    private String sessionid;
    private String role;
    private String accessToken;
    
    public int getUserid() {
        return userid;
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public String getSessionid() {
        return sessionid;
    }
    
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
}
