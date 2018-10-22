package com.dventus.ptt.ftp;

import java.io.IOException;

import javax.annotation.CheckForNull;

import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletContext;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.Nullable;

public class Ftplet extends DefaultFtplet {

	private static Logger logger = Logger.getLogger(Ftplet.class);

	private PttFTPFactory feederFactory;

	private boolean deleteAfterUpload = false;

	@CheckForNull
	@Nullable
	private FtpletContext ftpletContext;

	@CheckForNull
	@Nullable
	private PttFTP pttFtp;

	@CheckForNull
	@Nullable
	public PttFTP getPttFtp() {
		return this.pttFtp;
	}

	@Override
	public void init(@Nullable FtpletContext ftpletContext1) throws FtpException {
		if (ftpletContext1 != null) {
			this.setFtpletContext(ftpletContext1);
		}

		this.pttFtp = this.getPttFTPFactory().createFeeder();

	}

	public Ftplet(PttFTPFactory feederFactory1) {

		this.feederFactory = feederFactory1;

	}

	private static String realFile(FtpSession session, FtpFile ftpFile) {

		final String filePath = session.getUser().getHomeDirectory() + ftpFile.getName();

		Ftplet.logger.info("File recieved [ " + filePath + " ]");

		return filePath;

	}

	@Override
	public FtpletResult onUploadEnd(@Nullable FtpSession session, @Nullable FtpRequest request)
			throws FtpException, IOException {

		if (session == null || request == null) {

			throw new FtpException("Session cannot be null");

		}

		final FtpFile ftpFile = Ftplet.ftpFile(session, request);

		final String file = Ftplet.realFile(session, ftpFile);

		Ftplet.logger.info("File state " + file);

		/*
		 * Save file in the database
		 */

		try {

			final PttFTP pttFtpLocal = this.getPttFtp();

			if (pttFtpLocal != null) {
				pttFtpLocal.uploadTestFile(file);
			}

			if (this.isDeleteAfterUpload()) {

				final boolean deleted = true;

				if (deleted) {

					Ftplet.logger.debug("File has been deleted" + file);

				}

			}

		} catch (final Exception e) {

			Ftplet.logger.error("--------Unknown Exception--------- ", e);

		}

		final FtpletResult ftplet = super.onUploadEnd(session, request);

		return ftplet;
	}

	@CheckForNull
	@Nullable
	protected FtpletContext getFtpletContext() {
		return this.ftpletContext;
	}

	protected void setFtpletContext(FtpletContext ftpletContext1) {
		this.ftpletContext = ftpletContext1;
	}

	private static FtpFile ftpFile(FtpSession session, FtpRequest request) throws FtpException {
		return session.getFileSystemView().getFile(request.getArgument());
	}

	public PttFTPFactory getPttFTPFactory() {
		return this.feederFactory;
	}

	public void setPttFTPFactory(PttFTPFactory pttFTPFactory1) {
		this.feederFactory = pttFTPFactory1;
	}

	public boolean isDeleteAfterUpload() {
		return this.deleteAfterUpload;
	}

	public void setDeleteAfterUpload(boolean deleteAfterUploads) {
		this.deleteAfterUpload = deleteAfterUploads;
	}

}
