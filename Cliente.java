public class Cliente implements Constantes {
    protected String nombre, nif, tarjeta;
    protected int nReservas;
    protected Reserva[] reservas;
    
    public Cliente(String nom, String nif, String tarjeta){
        nombre = nom;
        this.nif = nif;
        this.tarjeta = tarjeta;
        reservas = new Reserva[MAX_RESERVAS];
        nReservas=0;
    }
    
    public void setNombre(String nuevoNombre){
        nombre= nuevoNombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNif(String nuevoNif){
        nif = nuevoNif;
    }
    public String getNif(){
        return nif;
    }
    public void setTarjeta(String nuevaTarjeta){
        tarjeta= nuevaTarjeta;
    }
    public String getTrajeta(){
        return tarjeta;
    }
    public void addReserva(Reserva Reserva) throws LimiteReservasException{
        //Excepcion 4 aqui
        if(nReservas< MAX_RESERVAS){ 
            reservas[nReservas]=Reserva;
            nReservas++;
        }else throw new LimiteReservasException("El cliente ha alcanzado el límite de reservas permitidas.");
    }
    public int getNReservas(){
        return nReservas;
    }
    public double calcularDescuento(){
        double descuento = 0.0;
        if(nReservas>0){
            descuento = DESCUENTO_CLIENTE_REGULAR;
        }
        return descuento;
    }
    public String mostrarReserva(int indice){
        return reservas[indice].toString();
    }
    public boolean tieneGuia(int i){
        return reservas[i].viajeConGuia();
    }
    public boolean estaReservado(String destino){
        boolean estaReservado = false;
        for(int i=0; i<nReservas; i++){
            if(destino == reservas[i].getDestino()){
                estaReservado = true;
            }
        }
        return estaReservado;
    }
    public String toString(){
        return "Cliente: " + nombre + ", NIF: " + nif + ", Tarjeta: " + tarjeta;
    }
}
