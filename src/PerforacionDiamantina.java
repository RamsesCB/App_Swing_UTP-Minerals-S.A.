import java.util.Date;

public class PerforacionDiamantina extends ActividadExploracion {
    private final double profundidadMetros;
    private final double diametroPerforacion;
    private final String descripcionTestigos;

    public PerforacionDiamantina(String codigo, Date fecha, Trabajador responsable, Zona zona, double costo, double profundidad, double diametro, String testigos) {
        super(codigo, fecha, responsable, zona, costo);
        if (profundidad <= 0 || diametro <= 0) throw new IllegalArgumentException("Profundidad y diámetro deben ser positivos");
        this.profundidadMetros = profundidad;
        this.diametroPerforacion = diametro;
        this.descripcionTestigos = testigos;
    }

    @Override
    public double calcularCosto() { return getCosto() + profundidadMetros * 30; }

    @Override
    public String generarInforme() {
        return mostrarInformacion() + "\nProfundidad: " + profundidadMetros + " m | Diámetro: " + diametroPerforacion
            + " mm | Testigos: " + descripcionTestigos;
    }
}
