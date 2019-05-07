package com.dventus.ptt.api;

import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.resource.Resource;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;
import com.google.gson.JsonElement;

import javax.persistence.EntityManager;

public class AddProcessor extends ProcessorImp {

    public AddProcessor(Context processContext) {
        super(processContext);
    }

    @Override
    public PTTResponse process(PTTRequest request) {

        final Context context = this.getContext();

        final EntityManager em = context.getEntityManager();

        try {

            em.getTransaction().begin();

            String objType = request.getObjType();

            JsonElement payload = request.getPayload();

            if(objType == null || payload == null) {

                AddProcessor.logger.error(StatusMessages.MALFORMATED_REQUEST);

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            }

            Resource resource = this.getResource(objType);

            if(resource != null) {

                return resource.add(request, context);
            }

            Mapper mapper = this.getMapper(objType);

            em.persist(mapper.convertJsonToEntity(payload));

            em.getTransaction().commit();

            return new PTTResponse(ResponseStatus.OK, StatusMessages.SAVE_SUCCESS);

        } catch (final Exception e) {

            AddProcessor.logger.error(e);

            return new PTTResponse(ResponseStatus.ERROR, StatusMessages.SAVE_ERROR);

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
