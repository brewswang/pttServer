package com.dventus.ptt.servlet;

import com.dventus.ptt.api.PTTRequest;
import com.dventus.ptt.api.PTTResponse;
import com.dventus.ptt.api.Processor;
import com.dventus.servlet.DventusServlet;
import com.google.gson.Gson;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class PttServer extends DventusServlet {

    transient static private final Logger logger = Logger.getLogger(PttServer.class);

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final ApplicationContext context = this.getWebApplicationContext();

        if(context == null){

            PttServer.logger.error(StatusMessages.INITIALIZATION_ERROR.name());

            throw new ServletException(StatusMessages.INITIALIZATION_ERROR.name());

        }

        Gson gson = new Gson();

        final PTTRequest request = gson.fromJson(req.getReader(), PTTRequest.class);

        Map< String, Processor> prossorFactories = (Map<String, Processor>) context.getBean("processorBin");

        final Processor processor = prossorFactories.get(request.getOperation().toLowerCase());

        final PTTResponse response;

        String jsonResponse;

        try {

            // Check if the user is not logged in before processing request
            // if the user session is empty and the request is not login return "LOGIN_REQUIRED"
            // error message
            if ( req.getSession().isNew() && !request.getOperation().equalsIgnoreCase("login")) {

                jsonResponse = gson.toJson(new PTTResponse(ResponseStatus.ERROR, StatusMessages.LOGIN_REQUIRED));

                req.getSession().invalidate();

            } else {

                response = processor.process(request);

                jsonResponse = gson.toJson(response);

            }

            // invalidate user session for logout
            if( request.getOperation().equalsIgnoreCase("logout")) {

                req.getSession().invalidate();

            }

        } catch (Exception e) {

            PttServer.logger.error(e);

            e.printStackTrace();

            req.getSession().invalidate();

            jsonResponse = gson.toJson(new PTTResponse(ResponseStatus.ERROR, StatusMessages.REQUEST_ERROR));

        }

        resp.setContentType(ContentType.APPLICATION_JSON.getMimeType());

        resp.getWriter().write(jsonResponse);

    }
}
