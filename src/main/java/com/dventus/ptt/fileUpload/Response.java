package com.dventus.ptt.fileUpload;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<String> notFound;

    private List<String> successful;

    private List<String> unrelated;

    private List<String> activeRemark;

    public Response() {

        this.notFound = new ArrayList<>();

        this.successful = new ArrayList<>();

        this.unrelated = new ArrayList<>();

        this.activeRemark = new ArrayList<>();

    }

    public void setNotFound(String notFound) {
        this.notFound.add(notFound);
    }

    public void setSuccessful(String successful) {
        this.successful.add(successful);
    }

    public void setUnrelated(String unrelated) {
        this.unrelated.add(unrelated);
    }

    public void setActiveRemark(String activeRemark) { this.activeRemark.add(activeRemark); }

    public void add(Response response){
        this.notFound.addAll(response.notFound);
        this.successful.addAll(response.successful);
        this.unrelated.addAll(response.unrelated);
        this.activeRemark.addAll(response.activeRemark);
    }
}
