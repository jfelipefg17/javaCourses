package excepciones.novedades;

public class CapturaMultiple {

    private void lanzoDeTodo(boolean b) throws AException, BException {
	if (b) {
	    throw new AException();
	} else {
	    throw new BException();
	}
    }

    private void capturaSimple(boolean b) {
	try {
	    lanzoDeTodo(b);
	} catch (AException ne) {
	    hagoMuchasCosasParaResolver(ne);
	} catch (BException te) {
	    hagoMuchasCosasParaResolver(te);
	}
    }

    private void capturaMultiple(boolean b) {
	try {
	    lanzoDeTodo(b);
	} catch (AException | BException e) {
	    hagoMuchasCosasParaResolver(e);
	}
    }

    private void hagoMuchasCosasParaResolver(Exception e) {
	// En realidad no hago nada ;)
    }
}
