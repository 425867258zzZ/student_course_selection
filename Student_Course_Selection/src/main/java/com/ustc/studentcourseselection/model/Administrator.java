package com.ustc.studentcourseselection.model;

/**
 * @author ºÎê»
 */
public class Administrator extends BaseObject{
    String username;
    String password;

    public Administrator() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
