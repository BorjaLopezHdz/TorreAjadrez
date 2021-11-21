package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;

public class Torre {
	
	private Color color;
	private Posicion posicion;
	
	public Torre() {
		posicion = new Posicion(8,'h');
		setColor(Color.NEGRO);
	}
	public Torre (Color color) {
		if(color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		if (color.equals(Color.NEGRO)) {
		posicion = new Posicion(8,'h');
		setColor(Color.NEGRO);
		}
		if (color.equals(Color.BLANCO)) {
		posicion = new Posicion(1,'h');
		setColor(Color.BLANCO);
		}
	}
	public Torre (Color color, char columna) {
		if(color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		if (color.equals(Color.NEGRO)){
			posicion = new Posicion(8, columna);
			setColor(Color.NEGRO);
		}
		if (color.equals(Color.BLANCO)) {
			posicion = new Posicion(1, columna);
			setColor(Color.BLANCO);
		}
	}
	public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
		if (direccion == null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		if ( pasos <= 0) {
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		}
		switch (direccion) {
		case ARRIBA:
			if (color.equals(Color.BLANCO)) {
				try {
					posicion = new Posicion(posicion.getFila()+pasos,posicion.getColumna());
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
			}else if(color.equals(Color.NEGRO)) {
				try {
					posicion = new Posicion(posicion.getFila()-pasos,posicion.getColumna());
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
			}
			break;
		case ABAJO:
			if (color.equals(Color.BLANCO)) {
				try {
					posicion = new Posicion(posicion.getFila()-pasos, posicion.getColumna());
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
			}else if (color.equals(Color.NEGRO)) {
				try {
					posicion = new Posicion(posicion.getFila()+pasos, posicion.getColumna());
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}	
			}
			break;
		case IZQUIERDA:
			if (color.equals(Color.BLANCO)) {
				try {
					posicion=new Posicion(posicion.getFila(),(char)(posicion.getColumna()-pasos));
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
			}else if (color.equals(Color.NEGRO)) {
				try {
					posicion=new Posicion(posicion.getFila(),(char)(posicion.getColumna()+pasos));
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				
			}
			break;
		case DERECHA:
			if (color.equals(Color.BLANCO)) {
				try {
					posicion=new Posicion(posicion.getFila(),(char)(posicion.getColumna()+pasos));
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
			}else if (color.equals(Color.NEGRO)) {
				try {
					posicion=new Posicion(posicion.getFila(),(char)(posicion.getColumna()-pasos));
					setPosicion(this.posicion);
				}catch(IllegalArgumentException e) {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
				}
				
			}
			break;
		default:
			break;
		}	
	}
	public void enrocar(Direccion direccion) throws OperationNotSupportedException {
		if (direccion==null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		switch(direccion) {
		case ENROQUE_LARGO:
			if(color.equals(Color.NEGRO) & posicion.getColumna()=='a' & posicion.getColumna()!='h') {
				posicion = new Posicion(posicion.getFila(),'d');
				setPosicion(this.posicion);
			}else if (color.equals(Color.BLANCO) & posicion.getColumna()=='a' & posicion.getColumna()!='h') {
				posicion = new Posicion(posicion.getFila(),'d');
				setPosicion(this.posicion);
			}else {
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}
			break;
		case ENROQUE_CORTO:
			if(color.equals(Color.NEGRO) & posicion.getColumna()=='h' & posicion.getColumna()!='a') {
				posicion = new Posicion(posicion.getFila(),'f');
				setPosicion(this.posicion);
			}else if (color.equals(Color.BLANCO) & posicion.getColumna()=='h' & posicion.getColumna()!='a') {
				posicion = new Posicion(posicion.getFila(),'f');
				setPosicion(this.posicion);
			}else {
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}
			break;
		default:
			break;
		}
	}
	public Color getColor() {
		return color;
	}
	private void setColor(Color color) {
		if (color == null || color.equals(" ")) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		if(!color.equals(Color.BLANCO)  && !color.equals(Color.NEGRO)) {
			throw new IllegalArgumentException("El color no es el esperado.");
		}
		this.color = color;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	private void setPosicion(Posicion posicion) {
		if (posicion==null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		}
		this.posicion = new Posicion(posicion);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torre other = (Torre) obj;
		if (color != other.color)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + posicion + ","+ " color=" + color + "";
	}
}
