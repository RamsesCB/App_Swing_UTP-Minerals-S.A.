package Modelo;


public class Geologo extends Trabajador {

	private String especialidad;

	private String numColegiatura;

    public Geologo(String nombre, String apellidoPaterno, String apellidoMaterno, TipoDoc tipoDocumento, int numDocumento) {
        super(nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numDocumento);
    }

	public void trabajar() {

	}

	public void analizarMuestras() {

	}

}
