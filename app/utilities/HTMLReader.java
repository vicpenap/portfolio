package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLReader {
	private HTMLReader() {
	
	}
	
	public static String getContentsOfURL(String URL) throws IOException {
		final URL url = new URL(URL);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"ISO-8859-1"));

		String line;
		String text = "";

		while ((line = reader.readLine()) != null) {
			text += line;
		}

		reader.close();
	
	return text;
	}
	
}
