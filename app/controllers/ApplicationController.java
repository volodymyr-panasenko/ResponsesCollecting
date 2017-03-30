package controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dao.impl.AdminDaoImpl;
import dao.impl.FieldDaoImpl;
import models.*;
import util.*;
import play.data.FormFactory;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.Context;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * at sign in and logout.
 */
public class ApplicationController extends Controller {

    @Inject
    private FormFactory formFactory;

    private AdminDaoImpl adminDao = AdminDaoImpl.getInstance();
    private FieldDaoImpl fieldDao = FieldDaoImpl.getInstance();

    /**
     * Administrator authentication.
     *
     * @return redirect on the Fields page after successful authentication
     * or redirect on the homepage with error message if authentication fails.
     */
    @Transactional
    public Result signIn() {
        Admin adminReq = formFactory.form(Admin.class).bindFromRequest().get();
        if (adminReq.getLogin().isEmpty() || adminReq.getPassword().isEmpty()) {
            session().clear();
            return ok(collecting.render("Sign in failed", fieldDao.getAll()));
        }
        Admin adminDb;
        try {
            adminDb = adminDao.getByLogin(adminReq.getLogin());
        } catch (NoResultException ex) {
            session().clear();
            return ok(collecting.render("Sign in failed", fieldDao.getAll()));
        }
        String password = PasswordEncryption.encrypt(adminReq.getPassword());
        if (password.equals(adminDb.getPassword())) {
            ctx().session().put("right", "admin");
            return redirect("fields");
        } else {
            session().clear();
            return ok(collecting.render("Sign in failed", fieldDao.getAll()));
        }
    }

    /**
     * Administrator log out.
     *
     * @return redirect on the homepage.
     */
    public Result logout() {
        session().clear();
        return redirect("/");
    }

}
