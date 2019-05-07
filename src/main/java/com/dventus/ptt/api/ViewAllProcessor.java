package com.dventus.ptt.api;

import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;
import org.eclipse.jdt.annotation.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class ViewAllProcessor extends ProcessorImp {


    public ViewAllProcessor(Context context) {
        super(context);
    }

    @Override
    public PTTResponse process(PTTRequest request) {

        EntityManager em = getContext().getEntityManager();

        try {

            String objType = request.getObjType();

            if(objType == null) {

                ViewProcessor.logger.error(StatusMessages.MALFORMATED_REQUEST);

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.MALFORMATED_REQUEST);

            }

            em.getTransaction().begin();

            final Query query = this.getQuery(objType.toLowerCase(), em);

            // limit number of records returning to prevent memory leak
            query.setMaxResults(50);

            final List<?> results = query.getResultList();

            em.getTransaction().commit();

            if (results == null) {

                return new PTTResponse(ResponseStatus.ERROR, StatusMessages.NOT_FOUND);

            } else {

                Mapper mapper = this.getMapper(objType);

                // lambda function parameter cannot be annotated with @NonNull
                // therefore, checkers nullness check has to be suppressed
                @SuppressWarnings("nullness")
                List<? extends @NonNull Object> confs = results.stream()
                        .map(conf ->mapper.convertToDto(conf))
                        .collect(Collectors.toList());

                return new PTTResponse(ResponseStatus.OK, gson.toJsonTree(confs));
            }

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
