import java.util.Date;

public class MuestreoGeoquimico extends ActividadExploracion {
    private final int cantidadMuestras;
    private final String tipoMuestra;
    private final String resultadoAnalisis;

    public MuestreoGeoquimico(String codigo, Date fecha, Trabajador responsable, Zona zona, double costo, int cantidad, String tipo, String analisis) {
        super(codigo, fecha, responsable, zona, costo);
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        this.cantidadMuestras = cantidad;
        this.tipoMuestra = tipo;
        this.resultadoAnalisis = analisis;
    }

    @Override
    public double calcularCosto() { return getCosto() + cantidadMuestras * 12; }

    @Override
    public String generarInforme() {
        return mostrarInformacion() + "\nMuestras: " + cantidadMuestras + " | Tipo: " + tipoMuestra
            + " | Análisis: " + resultadoAnalisis;
    }
}
