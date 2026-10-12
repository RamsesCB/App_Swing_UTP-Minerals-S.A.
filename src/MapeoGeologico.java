import java.util.Date;

public class MapeoGeologico extends ActividadExploracion {
    private final String unidadesGeologicas;
    private final String estructurasGeologicas;
    private final String caracteristicas;

    public MapeoGeologico(String codigo, Date fecha, Trabajador responsable, Zona zona, double costo, String unidades, String estructuras, String caracteristicas) {
        super(codigo, fecha, responsable, zona, costo);
        this.unidadesGeologicas = unidades;
        this.estructurasGeologicas = estructuras;
        this.caracteristicas = caracteristicas;
    }

    @Override
    public double calcularCosto() { return getCosto() + 95; }

    @Override
    public String generarInforme() {
        return mostrarInformacion() + "\nUnidades: " + unidadesGeologicas + " | Estructuras: " + estructurasGeologicas
            + " | Características: " + caracteristicas;
    }
}
