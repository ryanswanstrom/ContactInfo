package models;

import javax.persistence.Entity;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Contact extends Model {

    @Required
    @MaxSize(100)
    public String name;

    @Required
    @Email
    public String email;

    @Required
    @MaxSize(100)
    public String major;

    @Required
    @MaxSize(100)
    public String gradyear;


}
