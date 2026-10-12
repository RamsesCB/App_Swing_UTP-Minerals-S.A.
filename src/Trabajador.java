public abstract class Trabajador {
    private final String nombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final TipoDocumento tipoDocumento;
    private final String numDocumento;

    protected Trabajador(String nombre, String apellidoPaterno, String apellidoMaterno, TipoDocumento tipoDocumento, String numDocumento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
    }

    public abstract String trabajar();

    public String mostrarDatos() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno + " | " + tipoDocumento + ": " + numDocumento + " | " + getClass().getSimpleName();
    }

    public String getNombre() { return nombre; }
    public String getNumDocumento() { return numDocumento; }
    public TipoDocumento getTipoDocumento() { return tipoDocumento; }

    @Override
    public String toString() { return nombre + " " + apellidoPaterno + " (" + getClass().getSimpleName() + ")"; }
}
