package com.dventus.ptt.Server;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

import javax.annotation.CheckForNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dventus.api.common.util.MDMAPIException;
import com.dventus.dao.common.Impl.GenericDaoImpl;
import com.dventus.ptt.jaxb.messages.TestFile;
import com.dventus.ptt.testFileUpload.TestFileUploadInfo;
import com.google.gson.Gson;

@WebServlet("/TestUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UploadServer extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 6779694829133085385L;

	private static Logger logger = Logger.getLogger(UploadServer.class);

	@CheckForNull
	private transient ApplicationContext webApplicationContext;

	@Override
	public void init() throws ServletException {

		this.webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final ApplicationContext ctx =  this.getWebApplicationContext();

		if(ctx == null) {
			throw new ServletException("Context Missing");
		}

		final GenericDaoImpl dao = (GenericDaoImpl) ctx.getBean("pttDao");

		UploadServer.logger.info("request header=" + request.getContentType());

		if (request.getContentType() != null
				&& request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {

			final Collection<Part> parts = request.getParts();
			UploadServer.logger.info(parts.toString());

			final TestFileUploadInfo testFileUploadInfo = new TestFileUploadInfo(dao);

			for (final Part part : parts) {

				final TestFile testFile = new TestFile();
				testFile.setFileName(UploadServer.getFileName(part));
				testFile.setFileLength(BigInteger.valueOf(part.getSize()));
				UploadServer.logger.info("part name=" + UploadServer.getFileName(part));

				if (!UploadServer.getFileName(part).endsWith(".csv")) {

					throw new ServletException("Doesn't support this file format");
				}

				try {
					final TestUploadResponse testUploadResponse = new TestUploadResponse();

					testUploadResponse.setTestUploadResult(testFileUploadInfo.uploadTest(part.getInputStream()));

					testUploadResponse.setStatus("Ok");

					final Gson gson = new Gson();

					final String respJson = gson.toJson(testUploadResponse);

					response.getWriter().write(respJson);

				} catch (final MDMAPIException e) {
					throw new ServletException(e.getMessage());
				}
			}

		}
	}

	@CheckForNull
	public ApplicationContext getWebApplicationContext() {
		return this.webApplicationContext;
	}

	public void setWebApplicationContext(@CheckForNull ApplicationContext webApplicationContext1) {
		this.webApplicationContext = webApplicationContext1;
	}

	private static String getFileName(Part part) {
		for (final String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "file.xlsx";
	}
}
