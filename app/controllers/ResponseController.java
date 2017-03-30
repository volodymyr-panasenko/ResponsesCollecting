package controllers;

import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;
import models.*;

import java.util.List;
import java.util.Map;

import dao.impl.FieldDaoImpl;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's responses page.
 */
public class ResponseController extends Controller {
	
	private FieldDaoImpl dao = FieldDaoImpl.getInstance();

    @Security.Authenticated(Secured.class)
    @Transactional
    public Result responses() {
        return ok(responses.render(dao.getAll()));
    }

}
