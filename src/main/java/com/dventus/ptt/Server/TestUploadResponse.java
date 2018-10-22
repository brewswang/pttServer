package com.dventus.ptt.Server;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.CheckForNull;

public class TestUploadResponse {

	public TestUploadResponse() {
		super();
		this.fileUploadResults = new ArrayList<>();

	}

	@CheckForNull
	private String status;

	private final List<FileUploadResult> fileUploadResults;

	public List<FileUploadResult> getFileUploadResults() {
		return this.fileUploadResults;
	}

	@CheckForNull
	public String getStatus() {
		return this.status;
	}

	public void setStatus(@CheckForNull String statusParam) {
		this.status = statusParam;
	}

}
