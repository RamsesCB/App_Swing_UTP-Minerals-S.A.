package Modelo;

import java.util.ArrayList;
import java.util.List;

public class CampañaExploracion {

    private String codigo;
    private String nombre;
    private String area;          // O puede ser Area area
    private String fechaInicio;   // En formato String para facilidad con Swing y CSV
    private String fechaFin;
    private double presupuesto;
    private List<ActividadExploracion> actividades;

    public CampañaExploracion(String codigo, String nombre, String area, String fechaInicio, String fechaFin, double presupuesto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.area = area;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuesto = presupuesto;
        this.actividades = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<ActividadExploracion> getActividades() {
        return actividades;
    }

    public void agregarActividad(ActividadExploracion actividad) {
        this.actividades.add(actividad);
    }

    public void eliminarActividad(ActividadExploracion actividad) {
        this.actividades.remove(actividad);
    }

    public double calcularCostoTotal() {
        return presupuesto;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
