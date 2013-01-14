package controllers;

import java.util.List;
import models.Contact;
import play.Logger;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.*;



public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void results() {
        List<Contact> contacts = Contact.findAll();
        render(contacts);
    }

    public static void addContact(@Required @MaxSize(100) String name,
            @Required @Email String email,
            @Required @MaxSize(100) String major,
            @Required @MaxSize(100) String gradyear) {
        if (Validation.hasErrors()) {
            Logger.error("validation failed adding a contact");
            Validation.keep();
            params.flash();
            index();
        }
        Contact c = new Contact();
        c.name = name;
        c.major = major;
        c.gradyear = gradyear;
        c.email = email;

        if (!c.validateAndSave()) {
            Logger.error("validation failed: %s", c);
            Validation.keep();
            params.flash();
            index();
        }

        Logger.info("contact added");
        flash.success("Thank you");
        index();

    }

}