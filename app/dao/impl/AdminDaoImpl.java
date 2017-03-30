package dao.impl;

import java.util.List;

import javax.persistence.Query;

import dao.api.Dao;
import models.Admin;
import play.db.jpa.JPA;

public class AdminDaoImpl implements Dao<Admin> {

    private final String GET_ALL = "from Admin";
    private final String GET_BY_LOGIN = "from Admin where login = :login";

    private static AdminDaoImpl dao;

    public static synchronized AdminDaoImpl getInstance() {
        if (dao == null) {
            dao = new AdminDaoImpl();
        }
        return dao;
    }

    @Override
    public List<Admin> getAll() {
        Query query = JPA.em().createQuery(GET_ALL);
        return query.getResultList();
    }

    @Override
    public Admin getById(int key) {
        return JPA.em().find(Admin.class, key);
    }

    @Override
    public void save(Admin admin) {
        JPA.em().persist(admin);
    }

    @Override
    public void delete(Admin admin) {
        JPA.em().remove(admin);
    }

    public Admin getByLogin(String login) {
        Query query = JPA.em().createQuery(GET_BY_LOGIN);
        query.setParameter("login", login);
        return (Admin) query.getSingleResult();
    }

}
