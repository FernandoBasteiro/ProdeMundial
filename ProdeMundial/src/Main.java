import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		//Prode.getInstancia().cargarDatos();
		Main dis = new Main();
		dis.clearScreen();
		dis.mostrarMenu();
	}
	
	public void clearScreen(){
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void mostrarMenu(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Opciones");
		System.out.println("1 - Ver posiciones");
		System.out.println("2 - Ver pronosticos");
		System.out.println("3 - Cargar resultado");
		System.out.println("4 - Cargar pronostico");
		System.out.println("5 - Actualizar");
		System.out.println("6 - Crear Jugador");
//		System.out.println("7 - Proximamente");
		System.out.println("0 - Salir");
		System.out.print("Opcion: ");
		try {
			char s = (char)reader.read();
			switch (s) {
			case '1': {
				this.verPosiciones();
				break;
			}
			case '2': {
				this.verPronosticos();
				break;
			}
			case '3': {
				this.cargarResultado();
				break;
			}
			case '4': {
				this.cargarPronostico();
				break;
			}
			case '5': {
				this.actualizar();
				break;
			}
			case '6': {
				this.crearJugador();
				break;
			}
			case '0': {
				System.exit(0);
				break;
			}
			}
			this.mostrarMenu();
		}
		catch(Exception e) {
			System.out.print(e.getStackTrace());
		}
	}
	
	public void actualizar() {
		Prode.getInstancia().cargarDatos();
	}
	public void verPosiciones(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fecha (1, 2, 3 o 0 para total): ");
		try {
			int s = reader.read() - 48;
			/*
			 ArrayList<TablaPos> tabla = Prode.getInstancia().getPosiciones(s);
			int pos = 0;
			*/ this.clearScreen(); /*
			for (TablaPos tp : tabla) {
				System.out.println(++pos + ") " + tp.getNombre() + " - " + tp.getPuntos());
			} */
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void cargarResultado() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fecha? (Numero entre 1 y 7): ");
		try {
			int s = reader.read() - 48;
			ArrayList<Partido> partidosFecha = Prode.getInstancia().getPartidosFecha(s);
			System.out.println("Partidos fecha " + s + ": ");
			int i = 0;
			for (Partido p : partidosFecha) {
				System.out.println(++i + " - " + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre());
			}
			System.out.print("Seleccione partido: ");
			int s2 = Integer.valueOf(reader2.readLine());
			System.out.print("Goles " + partidosFecha.get(s2 - 1).getLocal().getNombre() + ": ");
			int gl = Integer.valueOf(reader3.readLine());
			System.out.print("Goles " + partidosFecha.get(s2 - 1).getVisitante().getNombre() + ": ");
			int gv = Integer.valueOf(reader4.readLine());
			char pronostico;
			if (gl > gv) {
				pronostico = "L".charAt(0);
			}
			else if (gl == gv) {
				pronostico = "E".charAt(0);
			}
			else {
				pronostico = "V".charAt(0);
			}
			Prode.getInstancia().setResultado(partidosFecha.get(s2 - 1).getId(), pronostico, gl, gv);
			this.clearScreen();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void cargarPronostico() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Jugador> jugadores = Prode.getInstancia().getJugadores();
		System.out.println("Jugadores:");
		int jugNum = 0;
		for (Jugador j : jugadores) {
			System.out.println(++jugNum + " - " + j.getNombre());
		}
		System.out.print("Jugador: ");
		try {
			int s = Integer.valueOf(reader.readLine());
			System.out.print("Fecha? (Numero de 1 a 7): ");
			int s2 = Integer.valueOf(reader2.readLine());
			ArrayList<Partido> partidosFecha = Prode.getInstancia().getPartidosFecha(s2);
			System.out.println("Pronostico de " + jugadores.get(s - 1).getNombre() + " para la fecha " + s2 + ":");
			for(Partido p : partidosFecha) {
				BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
				System.out.println(p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre() + ": ");
				System.out.print("Goles " + p.getLocal().getNombre() + ":");
				int gl = Integer.valueOf(reader3.readLine());
				System.out.print("Goles " + p.getVisitante().getNombre() + ":");
				int gv = Integer.valueOf(reader4.readLine());
				char pronostico;
				if (gl > gv) {
					pronostico = "L".charAt(0);
				}
				else if (gl == gv) {
					pronostico = "E".charAt(0);
				}
				else {
					pronostico = "V".charAt(0);
				}
				Prode.getInstancia().setPronostico(p.getId(), jugadores.get(s - 1).getId(), pronostico, gl, gv);
			}
			this.clearScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void crearJugador() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nombre jugador nuevo: ");
		try {
			String nombre = reader.readLine();
			Prode.getInstancia().crearJugador(nombre);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void verPronosticos() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Fecha? (Numero entre 1 y 7): ");
		try {
			int s = reader.read() - 48;
			ArrayList<Partido> partidosFecha = Prode.getInstancia().getPartidosFecha(s);
			System.out.println("Partidos fecha " + s + ": ");
			int i = 0;
			for (Partido p : partidosFecha) {
				System.out.println(++i + " - " + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre());
			}
			System.out.print("Seleccione partido: ");
			int s2 = Integer.valueOf(reader2.readLine());
			ArrayList<Jugada> jugadas = Prode.getInstancia().getPronosticosPartido(partidosFecha.get(s2 - 1).getId());
			this.clearScreen();
System.out.println("Pronosticos para el partido: " + partidosFecha.get(s2 - 1).getLocal().getNombre() + " vs " + partidosFecha.get(s2 - 1).getVisitante().getNombre());
			for (Jugada j : jugadas ) {
				System.out.print(j.getJugador().getNombre() + " - ");
				switch (j.getResultado()) {
				case 'L':
					System.out.println(partidosFecha.get(s2 - 1).getLocal().getNombre() + " (" + j.getGolesL() + "-" + j.getGolesV() + ")" );
					break;
				case 'V':
					System.out.println(partidosFecha.get(s2 - 1).getVisitante().getNombre() + " (" + j.getGolesV() + "-" + j.getGolesL() + ")" );
					break;
				case 'E':
					System.out.println("Empate (" + j.getGolesL() + "-" + j.getGolesV() + ")" );
					break;
				}
			}
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
