public class GerenteExploraciones extends Trabajador {
    private final String areaResponsable;
    private final String nivelAcceso;

    public GerenteExploraciones(String nombre, String paterno, String materno, TipoDocumento doc, String numero, String area, String nivel) {
        super(nombre, paterno, materno, doc, numero);
        this.areaResponsable = area;
        this.nivelAcceso = nivel;
    }

    @Override
    public String trabajar() { return "Gestión de exploraciones: " + areaResponsable; }

    public String consultarCampaña(CampañaExploracion campaña) { return campaña.consultar(); }

    public String consultarAvanceActividades(CampañaExploracion campaña) {
        int terminadas = 0;
        for (ActividadExploracion a : campaña.consultarActividades()) {
            if (a.getEstado() == EstadoActividad.FINALIZADA) terminadas++;
        }
        return "Avance " + terminadas + "/" + campaña.consultarActividades().size() + " actividades finalizadas";
    }

    public String consultarResultadoInforme(ActividadExploracion actividad) { return actividad.generarInforme(); }

    public String supervisarCampaña(CampañaExploracion campaña) {
        return consultarCampaña(campaña) + "\n" + consultarAvanceActividades(campaña);
    }

    public String tomarDecision(CampañaExploracion campaña) {
        String resumen = supervisarCampaña(campaña);
        String resultados = campaña.consultarActividades().isEmpty() ? "Faltan actividades para evaluar." :
            campaña.consultarActividades().stream().allMatch(a -> a.getEstado() == EstadoActividad.FINALIZADA)
            ? "Actividades finalizadas. Evaluar técnicamente los resultados." : "Mantener seguimiento de las actividades pendientes.";
        StringBuilder informes = new StringBuilder();
        for (ActividadExploracion actividad : campaña.consultarActividades()) {
            informes.append("\nResultado consultado (CU15): ").append(consultarResultadoInforme(actividad));
        }
        return resumen + informes + "\nDecisión orientativa: " + resultados;
    }

    public String getAreaResponsable() { return areaResponsable; }
    public String getNivelAcceso() { return nivelAcceso; }
}
