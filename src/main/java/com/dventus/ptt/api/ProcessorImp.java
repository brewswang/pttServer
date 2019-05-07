package com.dventus.ptt.api;
import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.resource.Resource;
import com.dventus.ptt.servlet.Context;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class ProcessorImp implements Processor {

    protected static final Logger logger = Logger.getLogger(ProcessorImp.class);

    private Context context;

    protected Gson gson;

    public ProcessorImp(Context context) {

        this.context = context;

        this.gson = new Gson();

    }


    @Override
    public Context getContext() {

        return this.context;

    }

    @Override
    public Query getQuery(String objectType, EntityManager em) {

        final String sqlString = (String) getContext().getQueries().get(objectType.toUpperCase());

        Query query = em.createQuery(sqlString);

        return query;

    }

    @Override
    public Mapper getMapper(String objectType) {

        return (Mapper) getContext().getMapperBin().get(objectType.toLowerCase());

    }

    @Override
    public Resource getResource(String objectType) {

        return (Resource) getContext().getResourceBin().get(objectType.toLowerCase());

    }

}
