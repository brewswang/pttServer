package com.dventus.ptt.api;

import com.dventus.ptt.jaxb.messages.User;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.QueryParameter;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;
import com.google.gson.JsonElement;

import javax.annotation.CheckForNull;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginProcessor extends ProcessorImp{

    public LoginProcessor(Context context) {
        super(context);
    }

    @Override
    public PTTResponse process(PTTRequest request) {

        EntityManager em = getContext().getEntityManager();

        try {

            // get username and password from request payload

            String objType = request.getOperation();

            JsonElement payload = request.getPayload();
            
            if (payload == null ) {
                
                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);
                
            }

            @CheckForNull String userId = payload.getAsJsonObject().get("userID").getAsString();

            @CheckForNull String password = payload.getAsJsonObject().get("userPassword").getAsString();

            if(userId == null || password == null) {

                ViewProcessor.logger.error(StatusMessages.MALFORMATED_REQUEST);

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            } else {

                em.getTransaction().begin();

                final Query query = this.getQuery(objType.toLowerCase(), em);

                query.setParameter(QueryParameter.userId.name(), userId);

                final User user = (User) query.getSingleResult();

                em.getTransaction().commit();

                if (user == null) {

                    return new PTTResponse(ResponseStatus.ERROR, StatusMessages.LOGIN_ERROR);

                } else {

                    String encryptedPassword = this.encrypt(password);

                    if (encryptedPassword.equalsIgnoreCase(user.getUserPassword())) {

                        return new PTTResponse(
                                ResponseStatus.OK,
                                gson.toJsonTree(
                                        this.getMapper("user").convertToDto(user)
                                ));

                    }

                    return new PTTResponse(ResponseStatus.ERROR, StatusMessages.LOGIN_ERROR);

                }
            }


        } catch (UnsupportedEncodingException e) {

            LoginProcessor.logger.error(e);

            return new PTTResponse(ResponseStatus.ERROR, StatusMessages.INTERNAL_SERVER_ERROR );

        } catch (NoSuchAlgorithmException e) {

            LoginProcessor.logger.error(e);

            return new PTTResponse(ResponseStatus.ERROR, StatusMessages.INTERNAL_SERVER_ERROR );

        } finally {

            if (em.getTransaction().isActive()) {
                  em.getTransaction().rollback();
            }

            if (em.isOpen()) {
                 em.close();
            }
        }

    }


    private String encrypt(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        MessageDigest md =  MessageDigest.getInstance("MD5");;

        final byte[] passwordByte = password.getBytes("UTF-8");

        md.update(passwordByte);

        final byte[] digest = md.digest();

        final String encryptedPassword = DatatypeConverter
                .printHexBinary(digest).toLowerCase();

        return encryptedPassword;
    }

}
