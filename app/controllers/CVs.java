package controllers;

import java.util.Date;
import java.util.List;

import models.CV;
import play.mvc.Controller;

public class CVs extends Controller {

	public static void index() {
		CV cv = CV.find("order by weight asc, updatedAt desc").first();
		render(cv);
	}
	
	public static void printCV() {
		CV cv = CV.find("order by weight asc, updatedAt desc").first();
		render(cv);
	}
}
