package com.iot.client.utils.repository;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author datdv
 *
 * @param <T>
 */
public class RepositoryCustomUtils<T> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * getResultList
     *
     * @param sql
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql) {
        return getResultList(sql, (String) null, null , null);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql , Class zClass) {
        return getResultList(sql, null, null , zClass);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, Map<String, Object> parameters) {
        return getResultList(sql, null, parameters , null);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param parameters
     * @param zClass
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, Map<String, Object> parameters , Class zClass){
        return getResultList(sql , null , parameters , zClass);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param parameters
     * @param zClass
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, Map<String, Object> parameters , Class zClass , Pageable pageable){
        return getResultList(sql , null , parameters , zClass , pageable);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, Map<String, Object> parameters , String resultSetMappingName , Pageable pageable){
        return getResultList(sql , resultSetMappingName , parameters , null , pageable);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, Map<String, Object> parameters , String resultSetMappingName){
        return getResultList(sql , resultSetMappingName , parameters , null , null);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param resultSetMappingName
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, String resultSetMappingName) {
        return getResultList(sql, resultSetMappingName, null , null);
    }

    /**
     * getResultList
     *
     * @param sql
     * @param resultSetMappingName
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, String resultSetMappingName, Map<String, Object> parameters , Class zClass) {
        //EntityManager entityManager = null;
        try {
            Query query = createQuery(entityManager, sql, resultSetMappingName, parameters , zClass);
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * getResultList
     *
     * @param sql
     * @param resultSetMappingName
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> List<T> getResultList(String sql, String resultSetMappingName, Map<String, Object> parameters , Class zClass , Pageable pageable) {
        try {
            Query query = createQuery(entityManager, sql, resultSetMappingName, parameters , zClass);
            if (pageable != null) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * createQuery
     *
     * @param entityManager
     * @param sql
     * @param resultSetMappingName
     * @param parameters
     * @param zClass
     * @return
     */
    private Query createQuery(EntityManager entityManager, String sql, String resultSetMappingName,
                              Map<String, Object> parameters , Class zClass) {
        Query query = null;
        if (StringUtils.isEmpty(resultSetMappingName)) {
            if(zClass != null){
                query = entityManager.createNativeQuery(sql, zClass);
            } else {
                query = entityManager.createNativeQuery(sql);
            }
        } else {
            query = entityManager.createNativeQuery(sql, resultSetMappingName);
        }

        if (parameters == null) {
            return query;
        }

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query;
    }

    /**
     * getSingleResult
     *
     * @param sql
     * @param <T>
     * @return
     */
    public <T> T getSingleResult(String sql) {
        return getSingleResult(sql, null, null , null);
    }

    /**
     * getSingleResult
     *
     * @param sql
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> T getSingleResult(String sql, Map<String, Object> parameters) {
        return getSingleResult(sql, null, parameters , null);
    }

    /**
     * getSingleResult
     *
     * @param sql
     * @param resultSetMappingName
     * @param parameters
     * @param <T>
     * @return
     */
    public <T> T getSingleResult(String sql, String resultSetMappingName, Map<String, Object> parameters , Class zClass) {
        try {
            Query query = createQuery(entityManager, sql, resultSetMappingName, parameters , zClass);
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    public <T> T getSingleResult(String sql, String resultSetMappingName, Map<String, Object> parameters){
        return getSingleResult(sql, resultSetMappingName, parameters, null);
    }
}
