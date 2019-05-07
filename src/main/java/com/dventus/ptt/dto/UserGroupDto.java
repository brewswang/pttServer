package com.dventus.ptt.dto;

import javax.annotation.CheckForNull;

public class UserGroupDto {

    @CheckForNull
    protected String groupId;

    public UserGroupDto() {
    }

    @CheckForNull
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(@CheckForNull String groupId) {
        this.groupId = groupId;
    }
}
