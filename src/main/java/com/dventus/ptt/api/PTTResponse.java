package com.dventus.ptt.api;

import com.dventus.ptt.servlet.StatusMessages;
import com.dventus.ptt.servlet.ResponseStatus;
import com.google.gson.JsonElement;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

public class PTTResponse {

    @Nullable @CheckForNull private ResponseStatus status;

    @Nullable @CheckForNull private StatusMessages message;

    @Nullable @CheckForNull private JsonElement payload;


    public PTTResponse(@Nullable ResponseStatus status) {
        this.status = status;
    }

    public PTTResponse(@Nullable ResponseStatus status, @Nullable JsonElement payload) {
        this.status = status;
        this.payload = payload;
    }

    public PTTResponse(@Nullable ResponseStatus status, @Nullable StatusMessages message) {
        this.status = status;
        this.message = message;
    }

    @Nullable
    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(@Nullable ResponseStatus status) {
        this.status = status;
    }

    @Nullable
    public StatusMessages getMessage() {
        return message;
    }

    public void setMessage(@Nullable StatusMessages message) {
        this.message = message;
    }

    @Nullable
    public JsonElement getPayload() {
        return payload;
    }

    public void setPayload(@Nullable JsonElement payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PTTResponse{" +
                "status=" + status +
                ", message=" + message +
                ", payload=" + payload +
                '}';
    }
}
