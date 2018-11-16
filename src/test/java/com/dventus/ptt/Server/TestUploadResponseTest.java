package com.dventus.ptt.Server;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dventus.ptt.testFileUpload.TestUploadResult;
import com.google.gson.Gson;

public class TestUploadResponseTest {

	@Test
	public void test() {

		final TestUploadResult testUploadResult = new TestUploadResult();
		final List<String> notFounds = new ArrayList<>();
		notFounds.add("01123435353");
		notFounds.add("01123435353");
		notFounds.add("01123435353");
		notFounds.add("01123435353");
		notFounds.add("01123435353");
		testUploadResult.setNotFoundMeters(notFounds);

		final List<String> successfullyUploaded = new ArrayList<>();
		successfullyUploaded.add("01123435353");
		successfullyUploaded.add("01123435353");
		successfullyUploaded.add("01123435353");
		successfullyUploaded.add("01123435353");
		successfullyUploaded.add("01123435353");
		testUploadResult.setSuccessfullyAdded(successfullyUploaded);

		final List<String> unrelated = new ArrayList<>();
		unrelated.add("01123435353");
		unrelated.add("01123435353");
		unrelated.add("01123435353");
		unrelated.add("01123435353");
		unrelated.add("01123435353");
		testUploadResult.setUnrelatedMeters(unrelated);

		final FileUploadResult fileUploadResult1 = new FileUploadResult(testUploadResult, "fileName.csv1");
		final FileUploadResult fileUploadResult2 = new FileUploadResult(testUploadResult, "fileName.csv1");

		final TestUploadResponse testUploadResponse = new TestUploadResponse();
		testUploadResponse.getFileUploadResults().add(fileUploadResult2);
		testUploadResponse.getFileUploadResults().add(fileUploadResult1);
		testUploadResponse.setStatus("Ok");

		final Gson gson = new Gson();
		System.out.print(gson.toJson(testUploadResponse));

	}

}
