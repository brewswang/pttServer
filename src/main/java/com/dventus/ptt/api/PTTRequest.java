package com.dventus.ptt.api;

import com.google.gson.JsonElement;

import javax.annotation.CheckForNull;
import java.sql.Timestamp;

public class PTTRequest {

    private final String operation;
    @CheckForNull   private String objType;
    @CheckForNull   private String objectID;
    @CheckForNull   private Timestamp startDate ;
    @CheckForNull   private Timestamp endDate ;
    @CheckForNull   private JsonElement payload;
    @CheckForNull   private int limit;
    @CheckForNull   private int offset;
    @CheckForNull   private String sort;

    public PTTRequest(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setObjType(@CheckForNull String objType) {
        this.objType = objType;
    }

    @CheckForNull public String getObjType() {
        return objType;
    }

    @CheckForNull public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    @CheckForNull public Timestamp getStartDate() {
        if(this.startDate != null){

            return new Timestamp(this.startDate.getTime());
        }
        return null;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = new Timestamp(startDate.getTime());
    }

    @CheckForNull public Timestamp getEndDate() {
        if(this.endDate != null){

            return new Timestamp(this.endDate.getTime());
        }
        return null;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = new Timestamp(endDate.getTime());
    }

    @CheckForNull public JsonElement getPayload() {
        return payload;
    }

    public void setPayload(@CheckForNull JsonElement payload) {
        this.payload = payload;
    }

    @CheckForNull public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @CheckForNull  public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @CheckForNull
    public String getSort() {
        return sort;
    }

    public void setSort(@CheckForNull String sort) {
        this.sort = sort;
    }
}
