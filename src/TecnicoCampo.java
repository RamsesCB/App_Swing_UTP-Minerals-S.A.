public class TecnicoCampo extends Trabajador {
    private final int experiencia;
    private final String areaTecnica;

    public TecnicoCampo(String nombre, String paterno, String materno, TipoDocumento doc, String numero, int experiencia, String areaTecnica) {
        super(nombre, paterno, materno, doc, numero);
        this.experiencia = experiencia;
        this.areaTecnica = areaTecnica;
    }

    @Override
    public String trabajar() { return "Trabajo de campo: " + areaTecnica; }

    public String operarEquipo() { return "Técnico " + getNombre() + " opera equipo de " + areaTecnica; }

    public int getExperiencia() { return experiencia; }
    public String getAreaTecnica() { return areaTecnica; }
}
