package com.dventus.ptt.resource;

import com.dventus.ptt.api.PTTRequest;
import com.dventus.ptt.api.PTTResponse;
import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.jaxb.messages.Meter;
import com.dventus.ptt.jaxb.messages.Remark;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.QueryParameter;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;
import com.google.gson.JsonElement;

import javax.annotation.CheckForNull;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RemarkResource implements Resource{


    public PTTResponse add(PTTRequest request, Context processContext) {

        RemarkResource.logger.info("inside remark resouce add method");

            EntityManager em = processContext.getEntityManager();

        try {

            em.getTransaction().begin();

            JsonElement payload = request.getPayload();

            if(payload == null) {

                RemarkResource.logger.error("payload is null");

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            }

            @CheckForNull String meterId = payload.getAsJsonObject().get("meterId").getAsString();

            if(meterId == null) {

                RemarkResource.logger.error("payload is null");

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            }

            // read meter
            final String sqlString = (String) processContext.getQueries().get("METER");

            Query query = processContext.getEntityManager().createQuery(sqlString);

            query.setParameter(QueryParameter.id.name(), meterId);

            final Meter meter = (Meter) query.getSingleResult();

            RemarkResource.logger.info("found meter =================" + meter.toString());

            // create remark object from payload
            Mapper mapper = (Mapper) processContext.getMapperBin().get("remark");

            Remark newRemark = (Remark) mapper.convertJsonToEntity(payload);

            // add remark object to meter
            List<Remark> existingRemarks = meter.getRemark();

            if(existingRemarks == null) {

                existingRemarks = new ArrayList<Remark>();

            }

            existingRemarks.add(newRemark);

            meter.setRemark(existingRemarks);

            //persist meter
            em.merge(meter);

            em.getTransaction().commit();

            return new PTTResponse(ResponseStatus.OK, StatusMessages.SAVE_SUCCESS);

        } catch (Exception e) {

            e.printStackTrace();

            return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

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
