package com.dventus.ptt.dto;

import javax.annotation.CheckForNull;
import java.util.Date;

public class RemarkDto {
    @CheckForNull private  String userID;
    @CheckForNull private  Long time;
    @CheckForNull private  String content;
    private boolean activeStatus;


    public RemarkDto() { }

    @CheckForNull
    public String getUserID() {
        return userID;
    }

    public void setUserID(@CheckForNull String userID) {
        this.userID = userID;
    }

    @CheckForNull
    public Long getTime() {
        return time;
    }

    public void setTime(@CheckForNull Long time) {
        this.time = time;
    }

    @CheckForNull
    public String getContent() {
        return content;
    }

    public void setContent(@CheckForNull String content) {
        this.content = content;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setCurrentTime() {

        time = new Date().getTime();

    }
}
