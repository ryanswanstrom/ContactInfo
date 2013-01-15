package models;

import java.util.Date;
import javax.persistence.Entity;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Contact extends Model {

    @Required
    @MaxSize(50)
    public String name;

    @Required
    @Email
    public String email;

    @Required
    @MaxSize(50)
    public String major;

    @Required
    @MaxSize(20)
    public String gradyear;

    public Date created;
    public String display;

    public Contact() {
        this.created = new Date();
        this.display = "Y";
    }


}
