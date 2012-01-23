package controllers;

import play.modules.pdf.RenderPDFTemplate;
import play.mvc.Controller;
import static play.modules.pdf.PDF.*;

public class CV extends Controller {

	public static void index() {
		render();
	}
	
	public static void VictorPena_CV() {
		renderPDF();
	}
}
