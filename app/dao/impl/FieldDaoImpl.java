package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.api.Dao;
import models.Field;
import play.db.jpa.JPA;

public class FieldDaoImpl implements Dao<Field> {

    private final String GET_ALL = "from Field";
    private final String GET_BY_LABEL = "from Field where label = :label";
    private static FieldDaoImpl dao;

    public static synchronized FieldDaoImpl getInstance() {
        if (dao == null) {
            dao = new FieldDaoImpl();
        }
        return dao;
    }

    @Override
    public List<Field> getAll() {
        Query query = JPA.em().createQuery(GET_ALL);
        return query.getResultList();
    }

    @Override
    public Field getById(int key) {
        return JPA.em().find(Field.class, key);
    }

    @Override
    public void save(Field field) {
        JPA.em().persist(field);
    }

    @Override
    public void delete(Field field) {
        JPA.em().remove(field);
    }

    public Field getByLabel(String label) {
        Query query = JPA.em().createQuery(GET_BY_LABEL);
        query.setParameter("label", label);
        return (Field) query.getSingleResult();
    }

    public void deleteByLabel(String label) {
        Field field = getByLabel(label);
        delete(field);
    }

}
