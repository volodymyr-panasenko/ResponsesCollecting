package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dao.impl.FieldDaoImpl;
import models.*;
import play.mvc.Security;
import util.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's homepage (Responses collecting).
 */
public class HomeController extends Controller {
	
	@Inject
    private FormFactory formFactory;
    private FieldDaoImpl dao = FieldDaoImpl.getInstance();

    @Transactional
    public Result collecting() {
        return ok(collecting.render("", dao.getAll()));
    }
    
    /**
     * Saving new response into database.
     */
	@Transactional
	public Result saveResponse() {
		List<Field> list = dao.getAll();
		for (Field field : list) {
			if (field.getActive()) {
				if (field.getType().toString().equals("Check box")) {
					String[] values = Controller.request().body().asFormUrlEncoded().get(field.getLabel());
					for (String value : values) {
						Response response = new Response(value);
						field.getResponses().add(response);
					}
				} else {
					DynamicForm requestData = formFactory.form().bindFromRequest();
					String value = requestData.get(field.getLabel());
					Response response = new Response(value);
					field.getResponses().add(response);
				}
				dao.save(field);
			}
		}
		return ok("Response was saved");
	}

}
