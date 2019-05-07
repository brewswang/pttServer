package com.dventus.ptt.api;

import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.QueryParameter;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ValidateProcessor extends ProcessorImp {
    public ValidateProcessor(Context context) {
        super(context);
    }

    @Override
    public PTTResponse process(PTTRequest request) {

        EntityManager em = getContext().getEntityManager();

        try {

            String objType = request.getObjType();

            String objId = request.getObjectID();

            if(objType == null || objId == null) {

                ViewProcessor.logger.error(StatusMessages.MALFORMATED_REQUEST);

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            } else {

                em.getTransaction().begin();

                final Query query = this.getQuery(objType.toLowerCase(), em);

                query.setParameter(QueryParameter.id.name(), objId);

                final Object result = query.getSingleResult();

                em.getTransaction().commit();

                Mapper mapper = this.getMapper(objType);

                if (result == null) {

                    return new PTTResponse(ResponseStatus.OK, StatusMessages.VALID);

                } else {

                    return new PTTResponse(ResponseStatus.OK, gson.toJsonTree(mapper.convertToDto(result)));

                }
            }

        } catch (final NoResultException e) {

            return new PTTResponse(ResponseStatus.OK, StatusMessages.VALID);

        } finally {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            if (em.isOpen()) {

                em.close();

            }

        }
    }
}
