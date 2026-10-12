import java.util.Date;

public class EstudioGeofisico extends ActividadExploracion {
    private final String tipoMedicion;
    private final String mediciones;
    private final String anomalias;

    public EstudioGeofisico(String codigo, Date fecha, Trabajador responsable, Zona zona, double costo, String tipo, String mediciones, String anomalias) {
        super(codigo, fecha, responsable, zona, costo);
        this.tipoMedicion = tipo;
        this.mediciones = mediciones;
        this.anomalias = anomalias;
    }

    @Override
    public double calcularCosto() { return getCosto() + 120; }

    @Override
    public String generarInforme() {
        return mostrarInformacion() + "\nTipo: " + tipoMedicion + " | Mediciones: " + mediciones + " | Anomalías: " + anomalias;
    }
}
