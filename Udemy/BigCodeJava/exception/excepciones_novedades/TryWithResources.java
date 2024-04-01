package excepciones.novedades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TryWithResources {

    private void tryALaAntigua() {
	BufferedReader reader = null;
	try {
	    URL url = new URL("http://url.de.test/");
	    reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    String line = reader.readLine();
	} catch (IOException exception) {
	    hagoMuchasCosasParaResolver(exception);
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	    }
	}
    }

    private void tryWithResources() {
	try (BufferedReader reader = new BufferedReader(
		new InputStreamReader(new URL("http://url.de.test/").openStream()))) {
	    String line = reader.readLine();
	} catch (IOException exception) {
	    hagoMuchasCosasParaResolver(exception);
	}
    }

    private void hagoMuchasCosasParaResolver(Exception e) {
	// En realidad no hago nada ;)
    }
}