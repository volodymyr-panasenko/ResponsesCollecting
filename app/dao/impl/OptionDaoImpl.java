package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.api.Dao;
import models.Option;
import play.db.jpa.JPA;

public class OptionDaoImpl implements Dao<Option> {

    private final String GET_ALL = "from Option";

    private static OptionDaoImpl dao;

    public static synchronized OptionDaoImpl getInstance() {
        if (dao == null) {
            dao = new OptionDaoImpl();
        }
        return dao;
    }

    @Override
    public List<Option> getAll() {
        Query query = JPA.em().createQuery(GET_ALL);
        return query.getResultList();
    }

    @Override
    public Option getById(int key) {
        return JPA.em().find(Option.class, key);
    }

    @Override
    public void save(Option option) {
        JPA.em().persist(option);
    }

    @Override
    public void delete(Option option) {
        JPA.em().remove(option);
    }

}
