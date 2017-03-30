package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.api.Dao;
import models.Response;
import play.db.jpa.JPA;

public class ResponseDaoImpl implements Dao<Response> {

    private final String GET_ALL = "from Response";

    private static ResponseDaoImpl dao;

    public static synchronized ResponseDaoImpl getInstance() {
        if (dao == null) {
            dao = new ResponseDaoImpl();
        }
        return dao;
    }

    @Override
    public List<Response> getAll() {
        Query query = JPA.em().createQuery(GET_ALL);
        return query.getResultList();
    }

    @Override
    public Response getById(int key) {
        return JPA.em().find(Response.class, key);
    }

    @Override
    public void save(Response response) {
        JPA.em().persist(response);
    }

    @Override
    public void delete(Response response) {
        JPA.em().remove(response);
    }

}
