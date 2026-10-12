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
- **src/VentanaPrincipal.java** + **src/VentanaPrincipal.form**: JFrame Form de NetBeans, cuatro pestañas editables desde **Design**.
- El diseño se mantiene en el GUI Builder; las clases del dominio se utilizan desde los manejadores de eventos.
- La clase visual no incluye imports de java.awt o javax.swing; los marcadores técnicos GEN-BEGIN y GEN-END son necesarios para NetBeans.
- **`INFORME_UML_POO_JFRAME.md`**: explicación detallada de cada clase, métodos, relaciones UML, casos de uso, `include`, `extend`, composición, dependencia, herencia, interfaces y ejemplos para la exposición.

Los costos son **simulaciones educativas**; el proyecto no implementa login, auditoría ni respaldo.


## Fedora con Niri / XWayland: corregir ventana blanca

En el escritorio Fedora Niri, Java Swing puede abrir una ventana completamente blanca cuando se ejecuta sin identificar el gestor de ventanas como no-reparenting. El problema también afecta a aplicaciones Swing mínimas y no significa que falten formularios.

Para iniciar esta aplicación **con la interfaz visible**, use desde un terminal:

```bash
./ejecutar-fedora.sh
```

El script define `_JAVA_AWT_WM_NONREPARENTING=1` antes de iniciar Java y desactiva las rutas gráficas que dieron problemas en la sesión comprobada. También puede ejecutar desde la terminal:

```bash
_JAVA_AWT_WM_NONREPARENTING=1 java -Dsun.java2d.xrender=false -Dsun.java2d.pmoffscreen=false -jar dist/UTPMinerals.jar
```

En NetBeans, si **Run Project** vuelve a mostrar una ventana blanca, inicie NetBeans desde una terminal con `_JAVA_AWT_WM_NONREPARENTING=1 netbeans`, o use directamente `./ejecutar-fedora.sh` después de compilar.

## Editar con diseñador de JFrame en NetBeans

Abra VentanaPrincipal.java desde Source Packages y pulse **Design** junto a **Source**. El diseñador carga VentanaPrincipal.form, permite seleccionar y modificar los componentes con el ratón, y mantiene initComponents automáticamente. No es necesario crear botones ni paneles por código manual para personalizar la interfaz.

Verificado en NetBeans 29: vista Design, compilación Ant correcta y prueba de operaciones sobre Zona, CampañaExploracion, Trabajador, ActividadExploracion y GerenteExploraciones.
