public class ViajeVariosDias extends Viaje {
    private int noches;
	private boolean hotel;
	private boolean paquete;
	public ViajeVariosDias(String dest, String fec, double prec, int plaz, int tip, int noch, boolean hot, boolean paq){
		super(dest, fec, plaz, tip, prec);
		noches = noch;
		hotel = hot;
		paquete = paq;
	}
	public void setNoches(int nNoches){
		noches = nNoches;
	}
	public int getNoches(){
		return noches;
	}
	public void setHotel(boolean hayHotel){
		hotel = hayHotel;
	}
	public boolean getHotel(){
		return hotel;
	}
	public void setPaquete(boolean hayPaquete){
		paquete = hayPaquete;
	}
	public boolean getPaquete(){
		return paquete;
	}
	public boolean hayGuia(){
		return true;
	}
	public boolean viajeMenor4Dias(){
		boolean resultado;
		if(noches+1<4){
			resultado = true;
		}else{
			resultado = false;
		}
		return resultado;
	}
	public String toString(){
		String hotelString = hotel ? "Sí" : "No";
		String paqueteString = paquete ? "Sí" : "No";
		return "Viaje de varios días a " + (super.toString()) + ", Noches: " + noches + ", Hotel: " + hotelString + ", Paquete: " + paqueteString;
	}
}
