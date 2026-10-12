import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CampañaExploracion {
    private final String codigo;
    private String nombre;
    private final Date fechaInicio;
    private Date fechaFin;
    private String estado = "PLANIFICADA";
    private final List<ActividadExploracion> actividades = new ArrayList<>();
    private boolean registrada;

    public CampañaExploracion(String codigo, String nombre, Date fechaInicio, Date fechaFin) {
        if (fechaFin.before(fechaInicio)) throw new IllegalArgumentException("Fecha final anterior a fecha inicial");
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaInicio = new Date(fechaInicio.getTime());
        this.fechaFin = new Date(fechaFin.getTime());
    }

    public void agregarActividad(ActividadExploracion actividad) {
        if (actividad == null || actividades.contains(actividad)) throw new IllegalArgumentException("Actividad inválida o repetida");
        actividades.add(actividad);
    }

    public void eliminarActividad(ActividadExploracion actividad) { actividades.remove(actividad); }
    public List<ActividadExploracion> consultarActividades() { return Collections.unmodifiableList(actividades); }

    public double calcularCostoTotal() {
        double total = 0;
        for (ActividadExploracion actividad : actividades) total += actividad.calcularCosto();
        return total;
    }

    public void registrar() { registrada = true; }

    public String consultar() {
        return "Campaña " + codigo + " - " + nombre + " | Estado: " + estado +
            " | Actividades: " + actividades.size() + " | Costo estimado: S/ " + String.format("%.2f", calcularCostoTotal());
    }

    public void modificar(String nombre, Date fechaFin, String estado) {
        if (fechaFin.before(fechaInicio)) throw new IllegalArgumentException("Fecha final anterior a fecha inicial");
        this.nombre = nombre;
        this.fechaFin = new Date(fechaFin.getTime());
        this.estado = estado;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public boolean isRegistrada() { return registrada; }
    @Override
    public String toString() { return codigo + " - " + nombre; }
}
