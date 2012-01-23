package controllers.admin;

import play.Play;
import play.cache.Cache;
import controllers.Secure;

public class Security extends Secure.Security {

	static boolean authenticate(String username, String password) {
		// implement your validation code here
		boolean result = true;

		return result;
	}
}
