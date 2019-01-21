package controllers;

import play.mvc.*;
import models.User;
import java.util.List;

public class HiController extends Controller {

    public Result hi(){
        List<User> users = User.find.all();
        System.out.println("==================== : " + users);
        return ok("Hi World!");
    }

}
