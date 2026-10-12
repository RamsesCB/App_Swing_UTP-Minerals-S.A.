public class Geologo extends Trabajador {
    private final String especialidad;
    private final String numColegiatura;

    public Geologo(String nombre, String paterno, String materno, TipoDocumento doc, String numero, String especialidad, String colegiatura) {
        super(nombre, paterno, materno, doc, numero);
        this.especialidad = especialidad;
        this.numColegiatura = colegiatura;
    }

    @Override
    public String trabajar() { return "Geología: " + especialidad; }

    public String analizarMuestras() { return "Geólogo " + getNombre() + " analiza muestras (" + especialidad + ")"; }

    public String getEspecialidad() { return especialidad; }
    public String getNumColegiatura() { return numColegiatura; }
}
