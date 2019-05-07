package com.dventus.ptt.dto;

import javax.annotation.CheckForNull;

public class UserDto {

    @CheckForNull protected String userId;
    @CheckForNull protected String userGroup;
    @CheckForNull protected String firstName;
    @CheckForNull protected String middleName;
    @CheckForNull protected String lastName;
    @CheckForNull protected String phoneNumber;
    @CheckForNull protected String email;
    @CheckForNull protected String token;
    @CheckForNull protected Long tokenExpireTime;

    public UserDto() {
    }

    @CheckForNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@CheckForNull String userId) {
        this.userId = userId;
    }

    @CheckForNull
    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(@CheckForNull String userGroup) {
        this.userGroup = userGroup;
    }

    @CheckForNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@CheckForNull String firstName) {
        this.firstName = firstName;
    }

    @CheckForNull
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@CheckForNull String middleName) {
        this.middleName = middleName;
    }

    @CheckForNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@CheckForNull String lastName) {
        this.lastName = lastName;
    }

    @CheckForNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@CheckForNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @CheckForNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@CheckForNull String email) {
        this.email = email;
    }

    @CheckForNull
    public String getToken() {
        return token;
    }

    public void setToken(@CheckForNull String token) {
        this.token = token;
    }

    @CheckForNull
    public Long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(@CheckForNull Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }
}