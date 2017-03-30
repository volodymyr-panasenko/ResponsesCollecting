package controllers;

import play.mvc.*;
import views.html.*;
import models.*;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's congratulation page.
 */
public class SuccessController extends Controller {

    public Result success() {
        return ok(success.render("Thank you for submitting your data!"));
    }

}
