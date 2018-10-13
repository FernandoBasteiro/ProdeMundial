
public class Jugada {
	private int id;
	private Jugador jugador;
	private char resultado;
	private int golesL;
	private int golesV;

	public int getGolesL() {
		return golesL;
	}

	public void setGolesL(int golesL) {
		this.golesL = golesL;
	}

	public int getGolesV() {
		return golesV;
	}

	public void setGolesV(int golesV) {
		this.golesV = golesV;
	}

	public Jugada(int id, Jugador jugador, char resultado, int golesL, int golesV) {
		super();
		this.id = id;
		this.jugador = jugador;
		this.resultado = resultado;
		this.golesL = golesL;
		this.golesV = golesV;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public char getResultado() {
		return resultado;
	}

	public void setResultado(char resultado) {
		this.resultado = resultado;
	}
	
}

