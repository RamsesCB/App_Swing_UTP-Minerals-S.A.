package Modelo;

public class TipoDoc {

    private String nombre; // "DNI", "CE", "PASAPORTE"

    public TipoDoc(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
