package excepciones.novedades;

public class ReThrow {

    private static void ejemploRethrowAntiguo(boolean b) throws Exception {
	try {
	    if (b) {
		throw new AException();
	    } else {
		throw new BException();
	    }
	} catch (Exception e) {
	    hagoCosas();
	    throw e;
	}
    }

    private static void ejemploRethrow(boolean b) throws AException, BException {
	try {
	    if (b) {
		throw new AException();
	    } else {
		throw new BException();
	    }
	} catch (Exception e) {
	    hagoCosas();
	    throw e;
	}
    }

    private static void llamadaRethrow(boolean b) {
	try {
	    ejemploRethrow(b);
	} catch (AException | BException e) {
	    hagoOtrasCosas();
	}
    }

    private static void hagoCosas() {
	// En realidad no hago nada ;)
    }

    private static void hagoOtrasCosas() {
	// En realidad no hago nada ;)
    }

}
