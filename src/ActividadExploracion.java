import java.util.Date;

public abstract class ActividadExploracion {
    private final String codigo;
    private final Date fecha;
    private Trabajador responsable;
    private Zona zona;
    private EstadoActividad estado = EstadoActividad.PLANIFICADA;
    private final double costo;
    private String resultado = "";
    private boolean registrada;

    protected ActividadExploracion(String codigo, Date fecha, Trabajador responsable, Zona zona, double costo) {
        if (costo < 0) throw new IllegalArgumentException("El costo no puede ser negativo");
        if (responsable == null || zona == null) throw new IllegalArgumentException("Indique responsable y zona");
        this.codigo = codigo;
        this.fecha = new Date(fecha.getTime());
        this.responsable = responsable;
        this.zona = zona;
        this.costo = costo;
    }

    public abstract double calcularCosto();
    public abstract String generarInforme();

    public void registrar() { registrada = true; }
    public String consultar() { return mostrarInformacion(); }
    public void modificar(String resultado) { this.resultado = resultado; }
    public void registrarResultado(String resultado) {
        if (estado == EstadoActividad.CANCELADA) throw new IllegalStateException("Actividad cancelada");
        this.resultado = resultado;
        estado = EstadoActividad.FINALIZADA;
    }

    public void cambiarEstado(EstadoActividad nuevo) {
        if (nuevo == null) throw new IllegalArgumentException("Estado obligatorio");
        if (estado == EstadoActividad.FINALIZADA || estado == EstadoActividad.CANCELADA)
            throw new IllegalStateException("El estado terminal no puede cambiar");
        estado = nuevo;
    }

    public String mostrarInformacion() {
        return getClass().getSimpleName() + " | Código: " + codigo + " | Zona: " + zona.getNombre()
            + " | Responsable: " + responsable.getNombre() + " | Estado: " + estado
            + " | Costo: S/ " + String.format("%.2f", calcularCosto()) + " | Resultado: " + resultado;
    }

    public String getCodigo() { return codigo; }
    public Date getFecha() { return new Date(fecha.getTime()); }
    public Trabajador getResponsable() { return responsable; }
    public void setResponsable(Trabajador responsable) { this.responsable = java.util.Objects.requireNonNull(responsable); }
    public Zona getZona() { return zona; }
    public EstadoActividad getEstado() { return estado; }
    public double getCosto() { return costo; }
    public String getResultado() { return resultado; }
    public boolean isRegistrada() { return registrada; }
    @Override
    public String toString() { return codigo + " - " + getClass().getSimpleName(); }
}
