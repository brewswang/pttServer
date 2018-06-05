package com.dventus.ptt.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.dventus.api.common.util.MDMAPIException;
import com.dventus.common.exceptions.Severity;
import com.dventus.dao.common.Impl.GenericDaoImpl;
import com.dventus.ptt.ftpException.PttFtpException;
import com.dventus.ptt.testFileUpload.TestFileUploadInfo;
import com.dventus.ptt.testFileUpload.TestUploadResult;

public class PttFTP {

	private final static Logger logger = Logger.getLogger(PttFTP.class);

	private GenericDaoImpl dao;

	private TestFileUploadInfo testFileUploadInfo;

	public TestFileUploadInfo getTestFileUploadInfo() {
		return this.testFileUploadInfo;
	}

	public void setTestFileUploadInfo(TestFileUploadInfo testFileUploadInfoParam) {
		this.testFileUploadInfo = testFileUploadInfoParam;
	}

	public PttFTP(GenericDaoImpl daos) {

		this.dao = daos;

		this.testFileUploadInfo = new TestFileUploadInfo(daos);

	}

	public GenericDaoImpl getDao() {

		return this.dao;

	}

	public void setDao(GenericDaoImpl dataAccess) {

		this.dao = dataAccess;

	}

	public synchronized void uploadTestFile(String file) throws MDMAPIException, PttFtpException {

		try (final InputStream inputStream = new FileInputStream(file)) {

			final TestUploadResult testResult = this.testFileUploadInfo.uploadTest(inputStream);

			PttFTP.logger.info("File Name:- " + file);

			for (final String successful : testResult.getSuccessfullyAdded()) {
				PttFTP.logger.info("Successfully Added");
				PttFTP.logger.info(successful);
			}

			for (final String notfound : testResult.getNotFoundMeters()) {
				PttFTP.logger.info("Not found Meters");
				PttFTP.logger.info(notfound);
			}

			for (final String unrelated : testResult.getUnrelatedMeters()) {
				PttFTP.logger.info("Unrelated Meters");
				PttFTP.logger.info(unrelated);
			}

		} catch (final IOException e) {
			PttFTP.logger.error(e.getMessage());
			throw new PttFtpException(1, 1, Severity.high, "File Read Error", "PTT-FTP");
		}
	}

}
