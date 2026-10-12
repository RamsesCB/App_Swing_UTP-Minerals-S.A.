package Modelo;




public abstract class Trabajador extends ActividadExploracion {

	private String nombre;

	private String apellidoPaterno;

	private String apellidoMaterno;

	private TipoDoc tipoDocumento;

	private int numDocumento;

	public void trabajar() {

	}

	public Trabajador(String nombre, String apellidoPaterno, String apellidoMaterno, TipoDoc tipoDocumento,
			int numDocumento) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
	}

	public String obtenerDatosFormato() {
		return "--- DATOS DEL TRABAJADOR ---" + System.lineSeparator()
				+ "Nombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno + System.lineSeparator()
				+ "Documento: " + numDocumento + System.lineSeparator();
	}

	public void mostrarDatos() {
		System.out.println(obtenerDatosFormato());
	}

	public void exportarTxt(String rutaArchivo) {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(rutaArchivo, true))) {
			pw.println(obtenerDatosFormato());
			System.out.println("Datos exportados exitosamente a: " + rutaArchivo);
		} catch (java.io.IOException e) {
			System.err.println("Error al exportar a archivo TXT: " + e.getMessage());
		}
	}

}
