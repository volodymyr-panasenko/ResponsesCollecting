package controllers;

import dao.impl.FieldDaoImpl;
import models.Field;
import models.FieldType;
import models.Option;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's Create/Edit Field page.
 */
public class EditController extends Controller {

    @Inject
    private FormFactory formFactory;
    private FieldDaoImpl dao = FieldDaoImpl.getInstance();

    @Security.Authenticated(Secured.class)
    public Result editing() {
        return ok(editing.render("Create/Edit Field"));
    }

    /**
     * Getting values of the form elements.
     */

    public String getLabel() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        return requestData.get("label");
    }

    public FieldType getType() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        return FieldType.toEnum(requestData.get("type"));
    }

    public boolean isRequired() {
        String[] required = Controller.request().body().
                asFormUrlEncoded().get("isRequired");
        return required != null;
    }

    public boolean isActive() {
        String[] active = Controller.request().body().
                asFormUrlEncoded().get("isActive");
        return active != null;
    }

    public Collection<Option> getOptions() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Collection<Option> optionsCollection = new ArrayList<>();
        String[] optionNames = requestData.get("optionsArea").split("\n");
        for (String optionName : optionNames) {
            optionsCollection.add(new Option(optionName));
        }
        return optionsCollection;
    }

    public Collection<Option> getSliderOptions() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        Collection<Option> optionsCollection = new ArrayList<>();
        String minValue = requestData.get("min");
        String maxValue = requestData.get("max");
        optionsCollection.add(new Option(minValue));
        optionsCollection.add(new Option(maxValue));
        return optionsCollection;
    }

    /**
     * Adding new field into database.
     *
     * @return redirect on the Field page.
     */
    @Transactional
    public Result addField() {
        Field field = new Field();
        field.setLabel(getLabel());
        field.setType(getType());
        field.setRequired(isRequired());
        field.setActive(isActive());
        if (field.getType().equals(FieldType.RADIO_BUTTON) ||
                field.getType().equals(FieldType.CHECK_BOX) ||
                field.getType().equals(FieldType.COMBO_BOX)) {
            field.getOptions().addAll(getOptions());
        } else if (field.getType().equals(FieldType.SLIDER)) {
            field.getOptions().addAll(getSliderOptions());
        }
        dao.save(field);
        return ok("Field was added");
    }

//    @Transactional
//    public Result editField() {
//        Map<String, String[]> values = request().body().asFormUrlEncoded();
//        String label = values.get("lbl")[0];
//        Field field = dao.getByLabel(label);
//        return ok(editing.render("Create/Edit Field", field));
//    }

}
