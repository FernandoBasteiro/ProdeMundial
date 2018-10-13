import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Persistencia {
	private static Persistencia instancia;
	private Connection conexion;
	
	private Persistencia() {
		try{
			String usr = "ProdeAdmin";
			String pwd = "password";
			String url = "jdbc:sqlserver://bd.basteiro.com.ar";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conexion = DriverManager.getConnection(url, usr, pwd);			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Persistencia getInstancia(){
		if (instancia == null) {
			instancia = new Persistencia();
		}
		return instancia;
	}
	
	public ArrayList<Partido> cargarPartidos(){
		Statement statement;
		try {
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Partidos");
			ArrayList<Partido> partidos = new ArrayList<Partido>();
			while (rs.next()) {
				Equipo equipoLocal = Prode.getInstancia().buscarEquipo(rs.getInt("idEquipoL"));
				Equipo equipoVisitante = Prode.getInstancia().buscarEquipo(rs.getInt("idEquipoV"));
				ArrayList<Jugada> jugadas = this.cargarJugadas(rs.getInt("id"));
				int idPartido = rs.getInt("id");
				char resultado = rs.getString("resultado").charAt(0);
				int fecha = rs.getInt("fecha");
				Partido p = new Partido(idPartido, fecha, equipoLocal, equipoVisitante, jugadas, resultado);
				partidos.add(p);
			}
			if (partidos.size() > 0) {
				return partidos;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Jugador> cargarJugadores() {
		Statement statement;
		try {
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Jugadores");
			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			while (rs.next()) {
				Jugador j = new Jugador(rs.getInt("id"), rs.getString("nombre"));
				jugadores.add(j);
			}
			if (jugadores.size() > 0) {
				return jugadores;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Equipo> cargarEquipos() {
		Statement statement;
		try {
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Equipos");
			ArrayList<Equipo> equipos = new ArrayList<Equipo>();
			while (rs.next()) {
				Equipo e = new Equipo(rs.getInt("id"), rs.getString("nombre"));
				equipos.add(e);
			}
			if (equipos.size() > 0) {
				return equipos;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Jugada> cargarJugadas(int idPartido) {
		Statement statement;
		try {
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Jugadas WHERE idPartido = " + idPartido);
			ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
			while (rs.next()) {
				Jugada j = new Jugada(rs.getInt("id"), Prode.getInstancia().buscarJugador(rs.getInt("idJugador")), rs.getString("jugada").charAt(0), rs.getInt("golesL"), rs.getInt("golesV"));
				jugadas.add(j);
			}
			if (jugadas.size() > 0) {
				return jugadas;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void guardarResultado (int idPartido, char resultado, int golesL, int golesV) {
		Statement statement;
		try {
			statement = conexion.createStatement();
			String query = "UPDATE Partidos SET resultado = '" + resultado + "', golesL = " + golesL + ", golesV = " + golesV + " WHERE id = " + idPartido;
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void guardarPronostico(int idPartido, int idJugador, char pronostico, int golesL, int golesV) {
		Statement statement;
		try {
			statement = conexion.createStatement();
			String query = "EXEC insertarPronostico " + idPartido + ", " + idJugador + ", " + pronostico + ", " + golesL + ", " + golesV;
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public ArrayList<TablaPos> cargarPosiciones(int fecha) {
		Statement statement;
		try {
			String query;
			if (fecha == 0) {
				query = "EXEC tablaFecha";
			}
			else {
				query = "EXEC tablaFecha " + fecha;
			}
			statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery(query);
			ArrayList<TablaPos> tabla = new ArrayList<TablaPos>();
			while (rs.next()) {
				TablaPos tp = new TablaPos(rs.getInt("id"), rs.getString("nombre"), rs.getInt("Puntaje"));
				tabla.add(tp);
			}
			if (tabla.size() > 0) {
				return tabla;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void crearJugador(String nombre){
		Statement statement;
		String query = "INSERT INTO Jugadores (nombre) VALUES ('" + nombre + "')";
		try {
			statement = conexion.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}