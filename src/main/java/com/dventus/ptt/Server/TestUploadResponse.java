package com.dventus.ptt.Server;

import javax.annotation.CheckForNull;

import com.dventus.ptt.testFileUpload.TestUploadResult;

public class TestUploadResponse {

	@CheckForNull
	private String status;

	@CheckForNull
	private TestUploadResult testUploadResult;

	@CheckForNull
	public String getStatus() {
		return this.status;
	}

	public void setStatus(@CheckForNull String statusParam) {
		this.status = statusParam;
	}

	@CheckForNull
	public TestUploadResult getTestUploadResult() {
		return this.testUploadResult;
	}

	public void setTestUploadResult(@CheckForNull TestUploadResult testUploadResultParam) {
		this.testUploadResult = testUploadResultParam;
	}



}
