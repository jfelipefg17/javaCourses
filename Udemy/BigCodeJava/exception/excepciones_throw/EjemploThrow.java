
public class EjemploThrow {
	public static void main(String[] args) {
		String hola = "hola";
		
		hola.charAt(100);
		
		new Integer("x");
	}
	
	private void yoNoSe() throws MiException {
		yoLanzoUnaExcepcion();
	}

	private void yoLanzoUnaExcepcion() throws MiException {
		throw new MiException("No funciono");
	}

}
