package com.dventus.ptt.servlet;

import com.dventus.ptt.api.PTTResponse;
import com.dventus.ptt.fileUpload.Response;
import com.dventus.ptt.fileUpload.TestFileParser;
import com.dventus.ptt.jaxb.messages.TestFile;
import com.google.gson.Gson;
import org.apache.http.entity.ContentType;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.CheckForNull;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;

@WebServlet("/TestUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class UploadServer extends HttpServlet {

    private static final long serialVersionUID = 6779694829133085385L;

    @CheckForNull
    private transient ApplicationContext webApplicationContext;

    @Override
    public void init() {

        this.webApplicationContext = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final ApplicationContext context = this.getWebApplicationContext();

        if (context == null) {

            throw new ServletException(StatusMessages.INITIALIZATION_ERROR.name());

        }

        if (request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {

            final Collection<Part> parts = request.getParts();

            Response payload = new Response();

            for (final Part part : parts) {

                final TestFile testFile = new TestFile();

                final String fileName = UploadServer.getFileName(part);

                testFile.setFileName(fileName);

                testFile.setFileLength(BigInteger.valueOf(part.getSize()));

                if (!UploadServer.getFileName(part).endsWith(".csv")) {

                    throw new ServletException("Doesn't support this file format");
                }

                Context pttContext = (Context) context.getBean("pttContext");

                TestFileParser parser= new TestFileParser();

                Response result = parser.parseTestBenchCSV(part.getInputStream(), pttContext);

                payload.add(result);

            }

            Gson gson = new Gson();

            String jsonResponse = gson.toJson(new PTTResponse(ResponseStatus.OK, gson.toJsonTree(payload)));

            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());

            response.getWriter().write(jsonResponse);

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
