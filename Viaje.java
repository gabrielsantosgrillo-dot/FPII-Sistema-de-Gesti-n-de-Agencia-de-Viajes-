public abstract class Viaje {
    protected String destino,fecha;
    protected int plazas, tipo;
    protected double precio;
    public Viaje(String dest, String fec,int plaz, int tip, double prec){
        destino = dest;
        fecha = fec;
        plazas = plaz;
        tipo= tip;
        precio = prec;
    }
    public void setDestino(String nuevoDestino){
        destino= nuevoDestino;
    }
    public String getDestino (){
        return destino;
    }
    public void setFecha(String nuevaFecha){
        fecha = nuevaFecha;
    }
    public String getFecha(){
        return fecha;
    }
    public void setPrecio(double nuevoPrecio){
        precio = nuevoPrecio;
    }
    public double getPrecio(){
        return precio;
    }
    public void setPlazas(int nuevasPlazas){
        plazas= nuevasPlazas;
    }
    public int getPlazas(){
        return plazas;
    } 
    public void setTipo(int nuevoTipo){
        tipo = nuevoTipo;
    }
    public int getTipo(){
        return tipo;
    }
    public abstract boolean hayGuia();
    public abstract boolean viajeMenor4Dias();
    public boolean precioMenor300(){
        boolean resultado;
        if (precio<300){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }
    public String toString(){
        String tipoString;
        if(tipo == 0){
            tipoString = "Cultural";
        }else if(tipo == 1){
            tipoString = "Playa y Ocio";
        }else{
            tipoString = "Naturaleza y Aventura";
        }
        return destino + " el " + fecha + ", Precio: " + precio + ", Plazas: " + plazas + ", Tipo: " + tipoString;
	}
}
