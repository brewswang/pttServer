package com.dventus.ptt.api;

import com.dventus.ptt.dto.Mapper;
import com.dventus.ptt.resource.Resource;
import com.dventus.ptt.servlet.Context;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface Processor {

    PTTResponse process(PTTRequest request);

    Context getContext();

    Query getQuery(String objectType, EntityManager entityManager);

    Mapper getMapper(String dto);

    Resource getResource(String objectType);

}
