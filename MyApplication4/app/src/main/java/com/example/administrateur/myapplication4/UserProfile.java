package com.example.administrateur.myapplication4;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;

    public UserProfile(){

    }

    public UserProfile(String userAge, String userEmail, String userName) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

