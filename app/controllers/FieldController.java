package controllers;

import dao.impl.FieldDaoImpl;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's Fields page.
 */
public class FieldController extends Controller {

    private FieldDaoImpl dao = FieldDaoImpl.getInstance();

    @Security.Authenticated(Secured.class)
    @Transactional
    public Result fields() {
        return ok(fields.render(dao.getAll()));
    }

    /**
     * Deletion field from database by the field label.
     */
    @Transactional
    public Result deleteField() {
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        String label = values.get("lbl")[0];
        dao.deleteByLabel(label);
        return ok("Field was deleted");
    }

}
