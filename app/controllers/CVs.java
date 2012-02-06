package controllers;

import static play.modules.pdf.PDF.renderPDF;

import java.util.Date;
import java.util.List;

import models.CV;
import play.mvc.Controller;

public class CVs extends Controller {

	public static void index() {
		CV cv = CV.find("order by weight asc, updatedAt desc").first();
		render(cv);
	}
	
	public static void VictorPena_CV() {
		CV cv = CV.find("order by weight asc, updatedAt desc").first();
		renderPDF(cv);
	}
}
