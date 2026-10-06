import java.util.Date;

public abstract class ActividadExploracion {

	private int codigo;

	private Date fecha;

	private Trabajador responsable;

	private char estado;

	private double costo;

	public void mostrarInformacion() {

	}

	public double calcularCosto() {
		return 0;
	}

	public void generarInforme() {

	}

}
