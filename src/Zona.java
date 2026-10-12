public class Zona {
    private final String codigo;
    private String nombre;
    private String ubicacion;
    private double area;
    private boolean registrada;

    public Zona(String codigo, String nombre, String ubicacion, double area) {
        if (area <= 0) throw new IllegalArgumentException("El área debe ser mayor que cero");
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.area = area;
    }

    public void registrar() { registrada = true; }

    public String consultar() {
        return "Zona " + codigo + " - " + nombre + " | Ubicación: " + ubicacion + " | Área: " + area + " km²";
    }

    public void modificar(String nombre, String ubicacion, double area) {
        if (area <= 0) throw new IllegalArgumentException("El área debe ser mayor que cero");
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.area = area;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public boolean isRegistrada() { return registrada; }
    @Override
    public String toString() { return codigo + " - " + nombre; }
}
