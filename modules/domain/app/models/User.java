package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class User extends Model {

    @Id
    public Long id;

    public String name;

    public boolean done;

    public Date dueDate = new Date();

    public static final Finder<Long, User> find = new Finder<>(User.class);
}