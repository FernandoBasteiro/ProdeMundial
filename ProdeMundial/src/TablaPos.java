
public class TablaPos {
	private int idJugador;
	private String nombre;
	private int puntos;
	public TablaPos(int idJugador, String nombre, int puntos) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.puntos = puntos;
	}
	public int getIdJugador() {
		return idJugador;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPuntos() {
		return puntos;
	}
	public void sumarPunto() {
		puntos++;
	}
	
}
