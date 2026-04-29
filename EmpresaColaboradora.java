public class EmpresaColaboradora {
// Creo las variables
    private String nombre;
    private double tarifaGuia;
    private double tarifaAdicional;

    //Creo el constructor para asignar las variables.
    public EmpresaColaboradora(String nom, double tarGuia, double tarAdicional){
        nombre = nom;
        tarifaGuia = tarGuia;
        tarifaAdicional = tarAdicional;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nuevoNombre){
        nombre = nuevoNombre;
    }

    public double getTarifaGuia(){
        return tarifaGuia;
    }

    public double getTarifaAdicional(){
        return tarifaAdicional;
    }

    public void setTarifaGuia(double nuevaTarifaGuia){
        tarifaGuia = nuevaTarifaGuia;
    }

    public void setTarifaAdicional(double nuevaTarifaAdicional){
        tarifaAdicional = nuevaTarifaAdicional;
    }
	public String toString(){
		return "Empresa Colaboradora: " + nombre + ", Tarifa Guía: " + tarifaGuia + ", Tarifa Adicional: " + tarifaAdicional;
	}
}
