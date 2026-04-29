public class ClienteFrecuente extends Cliente implements Constantes {
    private int tarjetaFrec;
	public ClienteFrecuente(String nom, String nif, String tarjeta, int tarFrec){
		super(nom, nif, tarjeta);
		tarjetaFrec = tarFrec;
	}
	public void setTarjetaFrec(int nuevaTarjeta){
		tarjetaFrec = nuevaTarjeta;
	}
	public int getTarjetaFrec(){
		return tarjetaFrec;
	}
	public double calcularDescuento(){
		double descuento = super.calcularDescuento();
		descuento += DESCUENTO_CLIENTE_FRECUENTE;
		return descuento;
	}
	public String toString(){
		return "Cliente Frecuente: " + nombre + ", NIF: " + nif + ", Tarjeta: " + tarjeta + ", Tarjeta Frecuente: " + tarjetaFrec;
	}
}
