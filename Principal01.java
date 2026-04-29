import java.io.*;
import java.util.*;

public class Principal01 implements Constantes {

	public static Scanner consola = new Scanner(System.in);
	public static void main (String[]args){ 
		Agencia agencia = new Agencia("Agencia");
		OperadorWeb operadorWeb = new OperadorWeb(BENEFICIO_OPWEB, "Operador Web");
		EmpresaColaboradora empresaColaboradora = new EmpresaColaboradora("Servicios Turísticos Globales S.L.",15.0,5.0);
		try{
    		leerClientes ("Clientes.txt", agencia);
			leerViajes ("Viajes.txt",agencia);
			gestionarAgencia(agencia, empresaColaboradora, operadorWeb);
		}catch (IOException e){
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	// Método que lee los clientes del fichero de texto
	public static void leerClientes(String cadena, Agencia agencia) throws IOException {
		File f = new File(cadena);
		Scanner nombre_f = new Scanner(f);

		String nombre, dni, tarjeta;
		boolean frecuente;
		int tarjetaFrecuente;
				 
		while (nombre_f.hasNext()) {
			nombre = nombre_f.next();
			dni = nombre_f.next();
			tarjeta = nombre_f.next();	
			frecuente = nombre_f.nextBoolean();
			
			if (frecuente) { 
				tarjetaFrecuente = nombre_f.nextInt();
				ClienteFrecuente clienteFrecuente= new ClienteFrecuente(nombre, dni, tarjeta, tarjetaFrecuente);
				agencia.addCliente(clienteFrecuente);
			} else {
				Cliente cliente = new Cliente(nombre,dni,tarjeta);
				agencia.addCliente(cliente);
			}
		}
	}
	
	// Método que lee los viajes del fichero de texto
	public static void leerViajes(String cadena, Agencia agencia) throws IOException {
		File f = new File(cadena);
		Scanner nombre_f = new Scanner(f);

		char tipoViaje;
		String destino, fecha;
		boolean comida, entrada, guia, hotel, paquete;
		double precio;
		int plazas, tipo, noches;
				 
		while (nombre_f.hasNext()) {
			tipoViaje = nombre_f.next().charAt(0);

			destino = nombre_f.next();
			fecha = nombre_f.next();
			precio = nombre_f.nextDouble();	
			plazas = nombre_f.nextInt();
			tipo = nombre_f.nextInt();

			if (tipoViaje =='e') { 
				comida = nombre_f.nextBoolean();
				entrada = nombre_f.nextBoolean();
				guia = nombre_f.nextBoolean();
				Excursion excursion= new Excursion(destino, fecha, precio, plazas, tipo, comida, entrada, guia);
				agencia.addViaje(excursion);
			} else {
				noches = nombre_f.nextInt();
				hotel = nombre_f.nextBoolean();
				paquete = nombre_f.nextBoolean();
				ViajeVariosDias viajeVariosDias = new ViajeVariosDias(destino, fecha, precio, plazas, tipo, noches, hotel, paquete);
				agencia.addViaje(viajeVariosDias);
			}
		}
	} 
	public static int menu(){
		System.out.println("------MENÚ------");
		System.out.println("1) Mostrar la información de todos los viajes");
		System.out.println("2) Mostrar viajes culturales de menos de 4 días y con precio menor a 300 euros");
		System.out.println("3) Mostrar las ganancias del operador web");
		System.out.println("4) Mostrar las ganancias de la empresa colaboradora");
		System.out.println("5) Calcular el precio de un viaje para un cliente");
		System.out.println("6) Reservar un viaje para un cliente");
		System.out.println("7) Mostrar las reservas de un cliente");
		System.out.println("8) Mostrar los viajes con guía reservados por un cliente frecuente");
		System.out.println("9) Salir");
		int opcion=0;
		boolean salir = false;
		System.out.print("Introduce una opción (1-9): ");
		do{
			try{
			opcion = consola.nextInt();
			salir = true;
			}catch(InputMismatchException e){
				consola.next();
				System.out.println("Error: Entrada no válida. Por favor, introduce un número entre 1 y 9. ");
				salir = false;
			}
		}while(!salir);
		return opcion;
	}
	public static void gestionarAgencia(Agencia agencia, EmpresaColaboradora empresaColaboradora, OperadorWeb opWeb){
		int opcion;
		do {
			opcion=menu();
			if (opcion < 1 || opcion > 9){
				System.out.println("Opción no válida. Por favor, introduce un número entre 1 y 9: ");
			}
			switch (opcion) {
				case 1:
					mostrarInformacionViajes(agencia);
				break;
				case 2:
					mostrarViajesMenos4Dias(agencia);
				break;
				case 3:
					gananciasOperadorWebEx(agencia, opWeb);
				break;
				case 4:
					gananciasEmpresaColaboradora(agencia, empresaColaboradora);
				break;
				case 5:
					precioViajes(agencia);
				break;
				case 6:
					try {
						reservarViaje(agencia);
					} catch (LimiteReservasException e) {
						System.out.println(e.getMessage());
					}
				break;
				case 7:
					mostrarReservas(agencia);
				break;
				case 8:
					mostrarViajesConGuiaClienteFrecuente(agencia);
				break;
				case 9:
					System.out.println ("Gracias por utilizar el programa. ¡Hasta pronto!");
				}
		} while (opcion != 9);
	}
	public static void mostrarInformacionViajes(Agencia agencia){
		System.out.println(agencia.mostrarInfoViajes());
	}
	public static void mostrarViajesMenos4Dias(Agencia agencia){
		System.out.println(agencia.mostrarViajesMenos4Dias());
	}
	public static void gananciasOperadorWebEx(Agencia agencia, OperadorWeb opWeb){
		System.out.println(agencia.calcularGananciasExcursiones(opWeb));
	}
	public static void gananciasEmpresaColaboradora(Agencia agencia, EmpresaColaboradora empresaColaboradora){
		System.out.println(agencia.calcularGananciasEmpresaColaboradora(empresaColaboradora));
	}
	public static void precioViajes(Agencia agencia){
		double coste=0;
		System.out.println("Introduce el destino del viaje: ");
		String destino = consola.next();
		int posDestino = agencia.posViaje(destino);
		if(posDestino == -1){
			System.out.println("Viaje no encontrado.");
		}else{
			System.out.println("Introduce el DNI del cliente: ");
			String nif = consola.next();
			int posicionCliente = agencia.posCliente(nif);
			if(posicionCliente == -1){
				System.out.println("Cliente no encontrado.");
			}else{
				coste = agencia.calcularCosteReserva(posDestino, posicionCliente);
				System.out.println("El coste de la reserva es: " + coste + ". El precio original del viaje es: " + agencia.getPrecioViaje(posDestino)+". El descuento en porcentaje aplicado es: " + agencia.getDescuentoCliente(posicionCliente) + "%.");
			}
		}
	}
	public static void reservarViaje(Agencia agencia) throws LimiteReservasException{
		System.out.println("Introduce el DNI del cliente: ");
		String nif = consola.next();
		int posicionCliente = agencia.posCliente(nif);
		if(posicionCliente == -1){
			System.out.println("Cliente no encontrado. Se va a crear un nuevo cliente.");
			System.out.println("Introduce el nombre del cliente: ");
			String nombre = consola.next();
			System.out.println("Introduce el número de tarjeta del cliente: ");
			String tarjeta = consola.next();
			System.out.println("¿Es un cliente frecuente? (Introduzca S para confirmar. Introducir otra cosa contará como no frecuente): ");
			String respuesta = consola.next();
			if(respuesta.equalsIgnoreCase("S")){
				System.out.println("Introduce el número de tarjeta frecuente: ");
				int tarjetaFrecuente = consola.nextInt();
				ClienteFrecuente clienteFrecuente = new ClienteFrecuente(nombre, nif, tarjeta, tarjetaFrecuente);
				agencia.addCliente(clienteFrecuente);
			}else{
				Cliente cliente = new Cliente(nombre, nif, tarjeta);
				agencia.addCliente(cliente);
			}
			posicionCliente = agencia.posCliente(nif);
		}
		System.out.println("Introduce el destino del viaje: ");
		String destino = consola.next();
		int posDestino = agencia.posViaje(destino);
		if(posDestino == -1){
			System.out.println("Viaje no encontrado.");
		}else{
			if(agencia.estaReservado(posDestino, posicionCliente)){
				System.out.println("El viaje ya está reservado.");
			}else{
				agencia.reservarViaje(posDestino, posicionCliente);
				System.out.println("Reserva realizada.");
			}
		}
	}
	public static void mostrarReservas(Agencia agencia){
		System.out.println("Introduce el DNI del cliente: ");
		String nif = consola.next();
		int posicionCliente = agencia.posCliente(nif);
		if(posicionCliente == -1){
			System.out.println("Cliente no encontrado.");
		}else{
			System.out.println(agencia.mostrarReservasCliente(posicionCliente));
		}
	}
	public static void mostrarViajesConGuiaClienteFrecuente(Agencia agencia){
		System.out.println("Introduce el DNI del cliente: ");
		String nif = consola.next();
		int posicionCliente = agencia.posCliente(nif);
		if(posicionCliente == -1 || !(agencia.esClienteFrecuente(posicionCliente))){
			System.out.println("Cliente no encontrado o no es frecuente.");
		}else{
			System.out.println(agencia.mostrarViajesConGuiaClienteFrecuente(posicionCliente));
		}
	}
}