# UTP Minerals S.A. — Sistema de exploración minera (POO / Swing)

Proyecto educativo en **Java 21**, **Swing/JFrame** y **Ant**, compatible con **Apache NetBeans 29**.

## Abrir y ejecutar

1. Abra esta carpeta desde **NetBeans → File → Open Project**.
2. Ejecute **Clean and Build**.
3. Pulse **Run Project**; se inicia `VentanaPrincipal`.
4. Registre una zona, una campaña y personal. A continuación registre las actividades y consulte sus resultados desde Supervisión.

No utiliza dependencias externas, Maven ni una base de datos. Los registros existen solamente durante la ejecución.

## Diagrama Astah

El archivo `Diagrama_Casos_Uso_Mineria.asta` incluye las vistas general y detalladas de **15 casos de uso**, el diagrama de clases actualizado, **dos enumeraciones** y la clase de presentación `VentanaPrincipal` con estereotipo `«boundary»`.

## Código y documentación

- `src/Trabajador.java` y las cuatro subclases: personal minero y gerencia.
- `src/ActividadExploracion.java` y sus cuatro subclases: operaciones de campo, informes y costos.
- `src/Zona.java` y `src/CampañaExploracion.java`: gestión territorial y campañas.
- `src/TipoDocumento.java` y `src/EstadoActividad.java`: enumeraciones.
- `src/VentanaPrincipal.java`: JFrame sencillo de cuatro pestañas, sin comentarios.
- **`INFORME_UML_POO_JFRAME.md`**: explicación detallada de cada clase, métodos, relaciones UML, casos de uso, `include`, `extend`, composición, dependencia, herencia, interfaces y ejemplos para la exposición.

Los costos son **simulaciones educativas**; el proyecto no implementa login, auditoría ni respaldo.
