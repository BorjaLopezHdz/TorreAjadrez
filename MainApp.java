package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
private static Torre torre;
	public static void main(String[] args) {
		int opcionElegir=0;
		do {
			mostrarMenu();
			opcionElegir=elegirOpcion();
			ejecutarOpcion(opcionElegir);
			System.out.println("===========Esta es tu torre================");
			mostrarTorre();	
		}while(opcionElegir!=5);
	}
	private static void mostrarTorre() {
		try {
			System.out.println(torre.toString());
		}catch(NullPointerException e) {
			System.out.println("ERROR: La torre aún no está creada.");
		}
	}
	private static void mostrarMenu() {
		System.out.println("1. Crear una torre por defecto.");
		System.out.println("2. Crear una torre por defecto con el color a elegir.");
		System.out.println("3. Crear una torre por con el color y posición a elegir.");
		System.out.println("4. Mover la torre.");
		System.out.println("5. Salir");
	}
	private static int elegirOpcion() {
		int opcion;
		do {
			System.out.println("Elija una opcion a ejecutar.");
			opcion = Entrada.entero();
		}while(opcion<1 || opcion>5);
		return opcion;
	}
	private static Color elegirColor() {
		Color color = null;
		int opcionesColor=0;
		do {
			System.out.println("===============");
			System.out.println("1. Blancas");
			System.out.println("2. Negras");
			System.out.println("================");
			System.out.println("Escoja un color:");
			opcionesColor=Entrada.entero();
		}while(opcionesColor<1 || opcionesColor>2);
		switch (opcionesColor){
		case 1:
			color=Color.BLANCO;
			break;
		case 2:
			color=Color.NEGRO;
			break;
		}
		return color;
	}
	private static char elegirColumnaInicial() {
		char columna;
		do {
		System.out.println("==============================");
		System.out.println("Escoja la columna inicial con la cual quieres empezar");
		System.out.println("Escoja entre la columna 'a' o 'h':");
		System.out.println("===============================");
		 columna= Character.toLowerCase(Entrada.caracter());
		}while(columna!='a' && columna!='h');
		return columna;
	}
	private static void mostrarMenuDirecciones() {
		System.out.println("==========================");
		System.out.println("Escoja entre los siguientes movimientos:");
		System.out.println("1. Mover hacia delante");
		System.out.println("2. Mover hacia atras");
		System.out.println("3. Mover hacia la izquierda");
		System.out.println("4. Mover hacia la derecha");
		System.out.println("5. Enroque largo");
		System.out.println("6. Enroque corto");
		System.out.println("====================");
	}
	private static Direccion elegirDireccion() {
		Direccion direccion = null;
		int elegirMovimiento=0;
		do {
			System.out.println("==============================");
			System.out.println("Escoja una de las opciones anteriores:");
			System.out.println("==================================");
			elegirMovimiento=Entrada.entero();
		}while(elegirMovimiento<1 || elegirMovimiento>6);
		switch(elegirMovimiento) {
		case 1:
			direccion=Direccion.ARRIBA;
			break;
		case 2:
			direccion=Direccion.ABAJO;
			break;
		case 3:
			direccion=Direccion.IZQUIERDA;
			break;
		case 4:
			direccion=Direccion.DERECHA;
			break;
		case 5:
			direccion=Direccion.ENROQUE_LARGO;
			break;
		case 6:
			direccion=Direccion.ENROQUE_CORTO;
			break;
		}
		return direccion;
	}
	private static void crearTorreDefecto() {
		torre = new Torre();
	}
	private static void crearTorreColor() {
		torre= new Torre(elegirColor());
	}
	private static void crearTorreColorColumna() {
		torre = new Torre(elegirColor(), elegirColumnaInicial());
	}
	private static void mover(){
		Direccion direccion = null;
		int pasos;
		if (torre == null) {
			try {
				System.out.println(torre.toString());
			}catch(NullPointerException e){
				System.out.println("ERROR: La torre aún no está creada.");
			}
		}else {
		mostrarMenuDirecciones();
		direccion=elegirDireccion();
			if(direccion.equals(Direccion.ENROQUE_CORTO) || direccion.equals(Direccion.ENROQUE_LARGO)) {
				try {
					torre.enrocar(direccion);
				}catch(OperationNotSupportedException e){
					System.out.println(e.getMessage());
				}
			}else {
				System.out.println("================");
				System.out.println("Introduzca los pasos que quieres dar:");
				pasos=Entrada.entero();
				try {
					torre.mover(direccion, pasos);
				}catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			crearTorreDefecto();
			break;
		case 2:
			crearTorreColor();
			break;
		case 3:
			crearTorreColorColumna();
			break;
		case 4:
			mover();
			break;
		case 5:
			System.out.println("Gracias por jugar, ¡Vuelva pronto!");
			 System.exit(0);
			break;
		}
	}
}