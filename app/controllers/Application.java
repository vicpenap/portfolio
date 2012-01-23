package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import models.Project;
import models.contents.Twitter;

import org.xml.sax.SAXException;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	List<Project> projects = Project.find("order by weight asc, createdAt desc").fetch();
        render(projects);
    }

    public static void tweets() throws SAXException, IOException, ParseException {
    	List<Twitter> tweets = Twitter.getLastTweets(1, 3);
		
    	render(tweets);
    }
}