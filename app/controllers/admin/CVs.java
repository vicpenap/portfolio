package controllers.admin;

import play.mvc.With;
import controllers.CRUD;
import controllers.Secure;

@With(Secure.class)
public class CVs extends CRUD {

}
