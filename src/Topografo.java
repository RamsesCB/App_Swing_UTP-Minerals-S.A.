public class Topografo extends Trabajador {
    private final String equipoTopografico;
    private final double precisionEquipo;

    public Topografo(String nombre, String paterno, String materno, TipoDocumento doc, String numero, String equipo, double precision) {
        super(nombre, paterno, materno, doc, numero);
        this.equipoTopografico = equipo;
        this.precisionEquipo = precision;
    }

    @Override
    public String trabajar() { return "Topografía con " + equipoTopografico; }

    public String realizarLevantamiento() { return "Topógrafo " + getNombre() + " realiza levantamiento con " + equipoTopografico; }

    public String getEquipoTopografico() { return equipoTopografico; }
    public double getPrecisionEquipo() { return precisionEquipo; }
}
