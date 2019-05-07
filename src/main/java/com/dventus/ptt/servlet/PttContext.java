package com.dventus.ptt.servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import java.util.Map;
import java.util.Properties;

public class PttContext implements Context {

    private EntityManagerFactory emf;

    private Properties queries;

    private Map mapperBin;

    private Map resourceBin;

    public PttContext(EntityManagerFactory emf, Properties queries, Map mapperBin, Map resourceBin) {

        this.queries = queries;

        this.emf = emf;

        this.mapperBin = mapperBin;

        this.resourceBin = resourceBin;

    }

    @Override
    public EntityManager getEntityManager() {

        EntityManager em = this.emf.createEntityManager();

        em.setFlushMode(FlushModeType.COMMIT);

        return em;

    }

    @Override
    public Properties getQueries() {
        return this.queries;
    }

    @Override
    public Map getMapperBin() { return this.mapperBin; }

    @Override
    public Map getResourceBin() { return this.resourceBin; }

}
