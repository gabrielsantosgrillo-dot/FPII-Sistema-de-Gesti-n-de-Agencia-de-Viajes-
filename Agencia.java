public class Agencia implements Constantes {
    private String nombre;
    private int nClientes;
    private int nViajes;
    private Viaje[] viajes;
    private Cliente[] clientes;
    public Agencia(String nombre){
        this.nombre=nombre;
        viajes = new Viaje[MAX_VIAJES];
        clientes = new Cliente[MAX_CLIENTES];
        nClientes=0;
        nViajes=0;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nuevoNombre){
        nombre=nuevoNombre;
    }
    public void addViaje(Viaje Viaje){
        if(nViajes < MAX_VIAJES){ 
            viajes[nViajes]=Viaje;
            nViajes++;
        }
    }
    public void addCliente(Cliente Cliente){
        if (nClientes < MAX_CLIENTES){ 
            clientes[nClientes]=Cliente;
            nClientes++;
        }
    }
    public int posCliente(String nif){
        boolean encontrado=false;
        int pos=0;
        while(!encontrado && pos<nClientes){
            if(clientes[pos].getNif().equalsIgnoreCase(nif)){
                encontrado=true;
            }
            if(!encontrado){
                pos++;
            }
        }
        if(!encontrado){
            pos=-1;
        }
        return pos;
    }
    public int posViaje(String destino){
        boolean encontrado=false;
        int pos=0;
        while(!encontrado && pos<nViajes){
            if(viajes[pos].getDestino().equalsIgnoreCase(destino)){
                encontrado=true;
            }
            if(!encontrado){
                pos++;
            }
        }
        if(!encontrado){
            pos=-1;
        }
        return pos;
    }
    public double costeOperadorWeb(OperadorWeb opWeb){
        double coste=0;
        for (int i =0; i<nViajes; i++){
            coste += viajes[i].getPrecio() * opWeb.getPorcentaje();
        }
        return coste;
    }
    public double costeEmpresaColaboradora(EmpresaColaboradora emp){
        double coste = 0;
        for(int i =0; i<nViajes; i++){
            if(viajes[i] instanceof Excursion && viajes[i].hayGuia()){
                coste += viajes[i].getPrecio()* emp.getTarifaGuia(); 
            }
            else if (viajes[i] instanceof ViajeVariosDias ){
                coste+= viajes[i].getPrecio()*( emp.getTarifaGuia()+emp.getTarifaAdicional()); 
            }
            else{
                 coste+= 0;
            }
        }
        return coste;
    }
    public int getNClientes(){
        return nClientes;
    }
    public int getNViajes(){
        return nViajes;
    }
    public int getNReservas(){
        int totalReservas = 0;
        for (int i = 0; i < nClientes; i++) {
            totalReservas += clientes[i].getNReservas();
        }
        return totalReservas;
    }
    // Primera consulta: Mostrar la información de todos los viajes.
    public String mostrarInfoViajes(){
        String info="";
        for(int i=0; i<nViajes; i++){
            info += viajes[i].toString()+"\n";
        }
        return info;
    }
    // Segunda consulta: Mostrar la información de viajes menores 4 días y precio < 300.
    public String mostrarViajesMenos4Dias(){
        String info="";
        for (int i=0; i<nViajes; i++){
            if(viajes[i].getTipo() == 0){
                if(viajes[i].viajeMenor4Dias()){
                    if(viajes[i].precioMenor300()){
                            info += viajes[i].toString() + "\n";
                    }
                }
            }
        }
        return info;
	}
    // Tercera consulta: Calcular las ganancias del operador web si se reservaran todas las plazas de las excursiones.
    public double calcularGananciasExcursiones ( OperadorWeb opWeb){
        double ganancias= 0;
        for(int i = 0; i<nViajes; i++){
            if(viajes[i] instanceof Excursion){
                for (int j=0; j< viajes[i].getPlazas(); j++){
                    ganancias += opWeb.calcularGanancias(viajes[i].getPrecio());
                }
            }
        }
        return ganancias;
    }
    // Cuarta consulta: Calcular las ganancias de la empresa colaboradora.
    public double calcularGananciasEmpresaColaboradora (EmpresaColaboradora emp){
        double ganancias = 0;
        for (int i = 0; i<nViajes; i++){
            if(viajes[i].hayGuia()){
                ganancias += emp.getTarifaGuia();
            }
            if(viajes[i] instanceof ViajeVariosDias){
                if  (viajes[i].viajeMenor4Dias()){
                    ganancias+= TARIFA_BASE;
                }else {
                    ganancias+= TARIFA_BASE + (((ViajeVariosDias) viajes[i]).getNoches()-2)*TARIFA_NOCHES;
                }
            }
        }
        return ganancias;
    }
    //Quinta consulta: Consultar cuánto costaría reservar una plaza para un viaje determinado y si tiene descuento para clientes.
    public double calcularCosteReserva(int destino, int cliente){
        double coste=viajes[destino].getPrecio();
        if(destino < nViajes && cliente < nClientes && clientes[cliente].calcularDescuento() > 0){
            coste = viajes[destino].getPrecio() * (clientes[cliente].calcularDescuento());
        }
        return coste;
    }
    public double getPrecioViaje(int destino){
        return viajes[destino].getPrecio();
    }
    public double getDescuentoCliente(int cliente){
        return clientes[cliente].calcularDescuento()*100;
    }
    //Sexta consulta: Reservar una plaza para un viaje.
    public void reservarViaje(int destino, int cliente) throws LimiteReservasException{
        if(destino < nViajes && cliente < nClientes){
            clientes[cliente].addReserva(new Reserva("Reserva "+(clientes[cliente].getNReservas()+1), viajes[destino]));
        }
    }
    public boolean estaReservado(int destino, int cliente){
        boolean resultado = false;
        if(destino < nViajes && cliente < nClientes){
            resultado = clientes[cliente].estaReservado(viajes[destino].getDestino());
        }
        return resultado;
    }
    //Séptima consulta: Mostrar las reservas de un cliente.
    public String mostrarReservasCliente(int posicion){
        String info = "";
        if(clientes[posicion].getNReservas() > 0){
            for(int i=0; i<clientes[posicion].getNReservas(); i++){
            info += clientes[posicion].mostrarReserva(i) + "\n";
            }
        }else{
            info = "El cliente no tiene reservas.";
        }
        return info;
    }
    //Octava consulta: Mostrar la información de todos los viajes que incluyen guía local que han sido reservados por un cliente frecuente.
    public String mostrarViajesConGuiaClienteFrecuente(int cliente){
        String resultado = "";
        for(int i=0; i<clientes[cliente].getNReservas(); i++){
            if(clientes[cliente] instanceof ClienteFrecuente){
                if(clientes[cliente].tieneGuia(i)){
                    resultado += clientes[cliente].mostrarReserva(i) + "\n";
                }
            }
        }
        if(resultado.equals("")){
            resultado = "El cliente frecuente no tiene reservas con guía local.";
        }
        return resultado;
    }
    public boolean esClienteFrecuente(int cliente){
        boolean resultado = false;
        if(clientes[cliente] instanceof ClienteFrecuente){
            resultado = true;
        }
        return resultado;
    }

}
