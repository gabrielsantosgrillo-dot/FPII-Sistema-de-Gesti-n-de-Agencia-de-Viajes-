public class OperadorWeb {
    private double porcentajeBeneficio;
	private String nombre;
	
	public OperadorWeb(double porcBenef, String nom){
		porcentajeBeneficio = porcBenef;
		nombre = nom;
	}
	public void setPorcentaje(double nuevoPorcentaje){
		porcentajeBeneficio = nuevoPorcentaje;
	}
	public double getPorcentaje(){
		return porcentajeBeneficio;
	}
	public void setNombre(String nuevoNombre){
		nombre = nuevoNombre;
	}
	public String getNombre(){
		return nombre;
	}
	public double calcularGanancias(double dinero){

		return dinero *getPorcentaje();
	}
	public String toString(){
		return "OperadorWeb: " + nombre + ", Porcentaje de beneficio: " + porcentajeBeneficio;
	}
}
