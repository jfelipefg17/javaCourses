
public class EjemploTryCatch {
	public static void main(String[] args) {
		
	}
	
	private void sinMentiras(boolean esCierto) throws MiException {
		if (!esCierto) {
			throw new MiException("Es mentira!");
		}
	}
	
	private void denunciarMentiras() {
		System.out.println("Alguien ha intentado contar una mentira");
	}
	
	private void yoFunciono(boolean algo) {
		try {
			sinMentiras(algo);
		} catch (MiException e) {
			denunciarMentiras();
		}
	}

}
