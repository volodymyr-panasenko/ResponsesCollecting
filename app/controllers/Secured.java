package controllers;

import play.mvc.*;
import play.mvc.Http.*;

/**
 * Authenticator Secured is used for access control to the administrator's pages.
 */
public class Secured extends Security.Authenticator {

    /**
     * Method is used to get value of session parameter of the current logged in user.
     */
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("right");
    }

    /**
     * Method redirect on the homepage if the user is not authorized.
     */
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect("/");
    }
}