import java.util.ArrayList;

public class Partido {
	private int id;
	private int fecha;
	private Equipo local;
	private Equipo visitante;
	private ArrayList<Jugada> jugadas;
	private char resultado;
	public Partido(int id, int fecha, Equipo local, Equipo visitante, ArrayList<Jugada> jugadas, char resultado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.local = local;
		this.visitante = visitante;
		this.jugadas = jugadas;
		this.resultado = resultado;
	}
	
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	public ArrayList<Jugada> getJugadas() {
		return jugadas;
	}
	public void setJugadas(ArrayList<Jugada> jugadas) {
		this.jugadas = jugadas;
	}
	public char getResultado() {
		return resultado;
	}
	public void setResultado(char resultado) {
		this.resultado = resultado;
	}	
}
