package Domain;

import java.awt.Image;

public class PiezaImagen {
	private Image pieza;
	public PiezaImagen() {
		this.pieza = null;
		
	}
	public PiezaImagen(Image img) {
		this.pieza = img;
		}
	public Image getPieza() {
		return pieza;
	}
	public void setPieza(Image pieza) {
		this.pieza = pieza;
	}
	
	
}
