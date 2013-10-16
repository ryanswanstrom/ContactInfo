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
        Contact.deleteAll();
        List<Contact> contacts = Contact.find("display = ? order by created", "Y").fetch();
        render(contacts);
    }

    public static void addContact(@Required @MaxSize(50) String name,
            @Required @Email String email,
            @Required @MaxSize(50) String major,
            @Required @MaxSize(20) String gradyear) {
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
        flash.success("%s, thank you for your contact info", c.name);
        index();

    }

    public static void removeContact(Long id) {
        Contact c = Contact.findById(id);
        c.display = "N";
        c.save();
        results();
    }

}