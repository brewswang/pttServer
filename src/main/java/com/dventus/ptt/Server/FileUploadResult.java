package com.dventus.ptt.Server;

import com.dventus.ptt.testFileUpload.TestUploadResult;

public class FileUploadResult {

	public FileUploadResult(TestUploadResult testUploadResultInput, String fileNameInput) {
		super();
		this.testUploadResult = testUploadResultInput;
		this.fileName = fileNameInput;
	}

	private TestUploadResult testUploadResult;

	private String fileName;

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileNameInput) {
		this.fileName = fileNameInput;
	}

	public TestUploadResult getTestUploadResult() {
		return this.testUploadResult;
	}

	public void setTestUploadResult(TestUploadResult testUploadResultParam) {
		this.testUploadResult = testUploadResultParam;
	}
}
