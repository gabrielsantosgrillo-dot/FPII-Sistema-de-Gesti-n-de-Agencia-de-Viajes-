public class Reserva {
    private String identificador;
	private Viaje viajeReservado;
	public Reserva(String ident,Viaje viajeReser){
		identificador = ident;
		viajeReservado = viajeReser;
	}
	public void setIdentificador(String nuevoIdent){
		identificador = nuevoIdent;
	}
	public String getIdentificador(){
		return identificador;
	}
	public Viaje getViajeReservado(){
		return viajeReservado;
	}
	public boolean viajeConGuia(){
		return viajeReservado.hayGuia();
	}
	public String getDestino(){
		return viajeReservado.getDestino();
	}
	public String toString (){
		return "El identificador de la reserva es: "+ identificador+" " +viajeReservado.toString();
	}
}
