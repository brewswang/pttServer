package com.dventus.ptt.servlet;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.Properties;

public interface Context {

    EntityManager getEntityManager();

    Properties getQueries();

    Map getMapperBin();

    Map getResourceBin();

}
