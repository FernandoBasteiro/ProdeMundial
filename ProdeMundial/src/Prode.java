import java.util.ArrayList;

public class Prode {
	static private Prode instancia;
	private ArrayList<Equipo> equipos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Partido> partidos;
	
	static public Prode getInstancia(){
		if (instancia == null){
			instancia = new Prode();
		}
		return instancia;
	}
	
	public void cargarDatos(){
		equipos = Persistencia.getInstancia().cargarEquipos();
		jugadores = Persistencia.getInstancia().cargarJugadores();
		partidos = Persistencia.getInstancia().cargarPartidos();
	}
	
	public Equipo buscarEquipo (int idEquipo){
		for (Equipo e : equipos) {
			if (e.getId() == idEquipo) {
				return e;
			}
		}
//		Equipo e = Persistencia.getInstancia().cargarEquipo(idEquipo);
//		if (e != null) {
//			equipos.add(e);
//		}
//		return e;
		return null;
	}
	
	public Jugador buscarJugador (int idJugador){
		for (Jugador j : jugadores) {
			if (j.getId() == idJugador) {
				return j;
			}
		}
//		Jugador j = Persistencia.getInstancia().cargarJugador(idJugador);
//		if (j != null) {
//			jugadores.add(j);
//		}
//		return j;
		return null;
	}
	
	public ArrayList<Partido> getPartidosFecha (int fecha){
		ArrayList<Partido> partiditos = new ArrayList<Partido>();
		for (Partido p : partidos) {
			if (p.getFecha() == fecha) {
				partiditos.add(p);
			}
		}
		return partiditos;
	}
	
	public ArrayList<TablaPos> getPosiciones(int fecha) {
		return Persistencia.getInstancia().cargarPosiciones(fecha);
	}
	
	public void setResultado (int idPartido, char resultado, int gl, int gv) {
		for (Partido p : partidos) {
			if (p.getId() == idPartido) {
				p.setResultado(resultado);
				Persistencia.getInstancia().guardarResultado(idPartido, resultado, gl, gv);
			}
		}
	}
	public void setPronostico (int idPartido, int idJugador, char pronostico, int golesL, int golesV) {
		Persistencia.getInstancia().guardarPronostico(idPartido, idJugador, pronostico, golesL, golesV);
	}
	
	public ArrayList<Jugador> getJugadores(){
		return this.jugadores;
	}
	
	public void crearJugador(String nombre) {
		Persistencia.getInstancia().crearJugador(nombre);
		this.cargarDatos();
	}

	public ArrayList<Jugada> getPronosticosPartido(int idPartido) {
		for (Partido p : partidos) {
			if (p.getId() == idPartido) {
				return p.getJugadas();
			}
		}
		return null;
	}
}
