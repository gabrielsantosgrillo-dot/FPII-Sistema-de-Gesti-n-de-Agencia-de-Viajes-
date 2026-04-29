public class Excursion extends Viaje {
    private boolean comida;
	private boolean entrada;
	private boolean guia;
	public Excursion(String dest, String fec, double prec, int plaz, int tip, boolean comi, boolean entr, boolean guia){
		super(dest, fec, plaz, tip, prec);
		comida = comi;
		entrada = entr;
		this.guia = guia;
	}
	public void setComida(boolean hayComida){
		comida = hayComida;
	}
	public boolean getComida(){
		return comida;
	}
	public void setEntrada(boolean hayEntrada){
		entrada = hayEntrada;
	}
	public boolean getEntrada(){
		return entrada;
	}
	public void setGuia(boolean hayGuia){
		guia = hayGuia;
	}
	public boolean hayGuia(){
		return guia;
	}
	public boolean viajeMenor4Dias(){
		return true;
	}
	public String toString(){
		String comidaString = comida ? "Sí" : "No";
		String entradaString = entrada ? "Sí" : "No";
		String guiaString = guia ? "Sí" : "No";
		return "Excursión a " + (super.toString()) + ", Comida: " + comidaString + ", Entrada: " + entradaString + ", Guía: " + guiaString;
	}
}
