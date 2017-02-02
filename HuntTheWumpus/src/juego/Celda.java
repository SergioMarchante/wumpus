package juego;

public class Celda {

	private String peligro;
	private boolean visible;
	private boolean estaCazador;

	public Celda() {
		peligro = " ";
	}

	public String getPeligro() {
		return peligro;
	}

	public void setPeligro(String peligro) {
		this.peligro = peligro;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean getEstaCazador() {
		return estaCazador;
	}
		
	public void setEstaCazador(boolean estaCazador) {
		this.estaCazador = estaCazador;
	}

	
}
