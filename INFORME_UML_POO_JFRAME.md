# Informe técnico de concordancia UML, POO y JFrame
**Proyecto:** UTP Minerals S.A.  
**Fecha:** 11 de octubre de 2026  
**Proyecto NetBeans:** `/home/ramsescb/NetBeansProjects/AppSwingUTP-Minerals-S.A`  
**Modelo Astah:** `Diagrama_Casos_Uso_Mineria.asta`  
**Ventana ejecutable:** `src/VentanaPrincipal.java`  
**Java:** JDK 21, Swing, NetBeans Ant, sin librerías externas.

## 1. Objetivo y criterio de corrección

Se mantuvieron los conceptos originales: zona, campaña, actividad minera, trabajador, especializaciones, estado y tipo de documento. Se corrigió el código esqueleto para hacer funcionales sus métodos y ajustarlo al diagrama de clases. El modelo de casos de uso conservó sus cuatro vistas, 15 casos funcionales y seis relaciones `<<include>>`. Se añadió `VentanaPrincipal` al modelo de clases como clase de frontera `«boundary»`, puesto que ahora existe un JFrame real.

La corrección esencial fue eliminar `Trabajador extends ActividadExploracion` del código. **Una persona NO es una actividad.** `Trabajador` y `ActividadExploracion` son clases abstractas independientes. Las actividades tienen referencias al responsable y a la zona; la campaña mantiene su lista de actividades.

**Alcance real:** aplicación académica con registros solo en RAM. No implementa base de datos, login, control de permisos ni respaldo. El proyecto no inventa estos procesos porque los casos de uso actuales no los exigen. Las tarifas de costo son cifras de ejemplo, no cotizaciones reales.

## 2. Correspondencia entre todas las clases y sus casos de uso

| Clase / elemento | Tipo UML / Java | Función en el sistema | Casos relacionados |
|---|---|---|---|
| `Zona` | Clase | Registra código, ubicación, nombre y área. Permite consultar y modificar. | CU01, CU03 |
| `CampañaExploracion` | Clase | Contiene actividades; calcula costo total, consulta y modifica la campaña. | CU02, CU03, CU10, CU12 |
| `Trabajador` | Clase abstracta | Datos de identificación comunes y método `trabajar()`. | CU04, CU13 |
| `Topografo` | Hereda de `Trabajador` | Utiliza equipo topográfico y realiza levantamientos. | CU06, CU09, CU11 |
| `Geologo` | Hereda de `Trabajador` | Analiza muestras, realiza mapeos y muestreos. | CU07, CU08, CU09, CU11 |
| `TecnicoCampo` | Hereda de `Trabajador` | Opera equipos de perforación y registra trabajo de campo. | CU05, CU09, CU11 |
| `GerenteExploraciones` | Hereda de `Trabajador` | Consulta campañas, supervisa avances, lee informes y emite una decisión orientativa. | CU02, CU03, CU04, CU10, CU12, CU15 |
| `TipoDocumento` | Enumeración Java | Limita el documento a DNI, CE y Pasaporte. | CU13 |
| `ActividadExploracion` | Clase abstracta | Registra fecha, código, costo, zona, responsable, estado y resultado; define cálculo e informe. | CU03, CU04, CU09, CU11, CU14, CU15 |
| `PerforacionDiamantina` | Hereda de `ActividadExploracion` | Profundidad, diámetro, testigos, costo e informe. | CU05, CU09, CU11 |
| `EstudioGeofisico` | Hereda de `ActividadExploracion` | Tipo, mediciones, anomalías, costo e informe. | CU06, CU09, CU11 |
| `MapeoGeologico` | Hereda de `ActividadExploracion` | Unidades, estructuras, características, costo e informe. | CU07, CU09, CU11 |
| `MuestreoGeoquimico` | Hereda de `ActividadExploracion` | Cantidad, tipo y análisis de muestras, costo e informe. | CU08, CU09, CU11 |
| `EstadoActividad` | Enumeración Java | PLANIFICADA, EN_PROCESO, FINALIZADA, CANCELADA. | CU14 |
| `VentanaPrincipal` | Clase `«boundary»` que extiende `JFrame` | Vista que conecta formularios Swing con todas las clases del dominio. | CU01 a CU15 como interfaz |

La `VentanaPrincipal` es una clase de presentación, no una entidad minera; no se ha creado un nuevo caso de uso para «abrir ventana», pues eso no representa una nueva meta del actor.

## 3. Relaciones del diagrama de clases, explicadas

### 3.1 Generalización / herencia (flecha triangular blanca)

`Topografo —|> Trabajador`, `Geologo —|> Trabajador`, `TecnicoCampo —|> Trabajador` y `GerenteExploraciones —|> Trabajador`.

Estas cuatro clases **son un tipo de trabajador**, por eso usan `extends Trabajador`. Reutilizan nombre, apellidos y documento, e implementan `trabajar()` de manera diferente. Por ejemplo, `Geologo.trabajar()` indica su especialidad y `TecnicoCampo.trabajar()` su área técnica.

`PerforacionDiamantina —|> ActividadExploracion`, `EstudioGeofisico —|> ActividadExploracion`, `MapeoGeologico —|> ActividadExploracion` y `MuestreoGeoquimico —|> ActividadExploracion`.

Todas **son actividades** y comparten estado, fecha, responsable, costo base y resultado. Cada una sobreescribe `calcularCosto()` y `generarInforme()`. Una variable de tipo `ActividadExploracion` admite las cuatro subclases sin una cadena de cálculos específicos: **polimorfismo**.

**No debe existir `Trabajador extends ActividadExploracion`:** un trabajador realiza o responde por una actividad, pero no *es* una actividad. La relación apropiada es una **asociación**.

### 3.2 Asociaciones y multiplicidades

- **`CampañaExploracion (1) — (0..*) ActividadExploracion`:** una campaña agrupa cero o muchas actividades; una actividad registrada pertenece a una campaña. En Java, `CampañaExploracion` almacena `List<ActividadExploracion>`, mediante `agregarActividad()`, `eliminarActividad()` y `consultarActividades()`. Se protege la lista con una vista no modificable.
- **`Trabajador (1) — (0..*) ActividadExploracion`:** una actividad tiene un responsable, y un trabajador puede ser responsable de varias actividades. En Java se representa con `ActividadExploracion.responsable`. No significa que solo pueda trabajar una vez.
- **`Zona (1) — (0..*) ActividadExploracion`:** cada actividad se realiza en una zona existente; la zona puede contener múltiples actividades. En Java se representa con `ActividadExploracion.zona`.

Se corrigieron las multiplicidades del modelo Astah para que apunten a estos cardinales, manteniendo las asociaciones originales.

### 3.3 ¿Por qué asociación, agregación o composición?

**Asociación:** un objeto conoce o se relaciona con otro. Es el caso de `ActividadExploracion → Trabajador` y `ActividadExploracion → Zona`: no se destruye el trabajador ni la zona al eliminar la actividad.

**Agregación (rombo blanco):** un objeto reúne otros que pueden vivir por su cuenta. **Composición (rombo negro):** el objeto parte pertenece exclusivamente al todo y su ciclo de vida depende de él.

En este programa, la campaña almacena una lista y puede retirar actividades, pero el Java **no borra automáticamente** los objetos al eliminar una campaña. Por eso se ha conservado la asociación UML existente, sin fingir una composición fuerte. Si el diseño posteriormente exige que cada actividad se destruya con su campaña, se podría remodelar como composición, implementando y probando esa propiedad. Una `List` por sí sola no prueba que exista composición.

### 3.4 Dependencias (flecha discontinua)

`VentanaPrincipal` depende de clases de dominio como `CampañaExploracion`, `ActividadExploracion`, `Zona`, `Trabajador` y sus especializaciones. **Usa** sus constructores, métodos y referencias, pero un JFrame no es una zona, una campaña ni un trabajador. Astah incorpora dependencias de la clase de frontera a campaña y actividad para ejemplificar el vínculo sin saturar el dibujo; las demás se verifican directamente en Java.

La dependencia no equivale a herencia. **Una herencia utiliza `extends` porque expresa «es un»; una dependencia expresa «utiliza».**

### 3.5 Clases abstractas, interfaz y enumeración

- `Trabajador` es **abstracta** porque no se registra «un trabajador genérico» sin especialidad. Cada subclase implementa `trabajar()`.
- `ActividadExploracion` es **abstracta** porque no se conoce el cálculo ni los detalles del informe sin elegir el tipo concreto.
- `TipoDocumento` y `EstadoActividad` son **enum**, no clases de atributos numéricos. Reducen errores por cadenas de texto inventadas.
- **No se creó `interface` Java:** el dominio no necesita todavía un contrato compartido entre jerarquías distintas. Añadir una interfaz solo para exhibir `implements` complicaría el diseño sin mejorar el caso de uso. `JFrame` sí forma parte de la interfaz gráfica, pero **interfaz de usuario no significa `interface` de Java**.
- La clase `VentanaPrincipal extends JFrame` hereda de la biblioteca Swing porque **es una ventana**. Esto no convierte `JFrame` en una clase de negocio.

## 4. Trazabilidad: explicación de los 15 casos de uso

| Caso | Objetivo del actor | Clase y operación que lo respaldan | Dónde se ve |
|---|---|---|---|
| **CU01** | Gestionar zonas | `Zona.registrar/consultar/modificar` | Pestaña Zonas y campañas |
| **CU02** | Gestionar campañas | `CampañaExploracion.registrar/consultar/modificar` | Pestaña Zonas y campañas |
| **CU03** | Planificar actividades | `ActividadExploracion.registrar`, `CampañaExploracion.agregarActividad` | Pestaña Actividades |
| **CU04** | Asignar trabajadores | `ActividadExploracion.responsable` y referencia a `Trabajador` | Selector de responsable |
| **CU05** | Ejecutar perforación | `PerforacionDiamantina`, `TecnicoCampo.operarEquipo` | Selector Perforación |
| **CU06** | Ejecutar levantamiento y geofísica | `EstudioGeofisico`, `Topografo.realizarLevantamiento` | Selector Geofísica |
| **CU07** | Realizar mapeo geológico | `MapeoGeologico`, `Geologo.trabajar` | Selector Mapeo |
| **CU08** | Registrar muestreo geoquímico | `MuestreoGeoquimico`, `Geologo.analizarMuestras` | Selector Muestreo |
| **CU09** | Registrar resultados y calcular costos | `ActividadExploracion.registrarResultado/calcularCosto` y sobrescrituras | Botón Registrar resultado |
| **CU10** | Consultar avance y supervisar | `GerenteExploraciones.supervisarCampaña/consultarAvanceActividades` | Supervisión |
| **CU11** | Generar informe | `ActividadExploracion.generarInforme` (implementación de cada subtipo) | Supervisión |
| **CU12** | Tomar decisiones | `GerenteExploraciones.tomarDecision` | Supervisión |
| **CU13** | Consultar datos de trabajadores | `Trabajador.mostrarDatos`, `TipoDocumento` | Personal |
| **CU14** | Cambiar estado de actividad | `ActividadExploracion.cambiarEstado`, `EstadoActividad` | Supervisión |
| **CU15** | Consultar resultados e informes | `GerenteExploraciones.consultarResultadoInforme`, `ActividadExploracion.consultar` | Supervisión |

**Importante:** «ejecutar» actividades (CU05-CU08) significa registrar en el software resultados de actividades de campo. La app no realiza perforaciones físicas ni mediciones de sensores.

## 5. Por qué `<<include>>`, `<<extend>>` y las asociaciones de actores

Un `<<include>>` indica que, al completar el caso base, se realiza **obligatoriamente** el comportamiento del caso incluido. La flecha apunta **desde el caso principal al incluido**.

Se mantienen exactamente seis:
1. **CU05 → CU09:** para registrar la perforación se registran los resultados y costo.
2. **CU06 → CU09:** para registrar la geofísica se registran los resultados y costo.
3. **CU07 → CU09:** para registrar el mapeo se registran los resultados y costo.
4. **CU08 → CU09:** para registrar el muestreo se registran los resultados y costo.
5. **CU12 → CU10:** una decisión utiliza la consulta de avance de campaña.
6. **CU12 → CU15:** una decisión utiliza la consulta de resultados e informes.

El CU12 demuestra las dos consultas porque `tomarDecision()` llama a `supervisarCampaña()` y depende de la información registrada en las actividades. En un sistema real, la política de decisión sería más elaborada.

Un `<<extend>>` significa que un comportamiento **opcional, bajo una condición**, amplía un caso base. **Este modelo no contiene `extend`** porque no se ha definido un flujo opcional verificable. Por ejemplo, «generar informe» CU11 es un objetivo separado; no se debe dibujar como `extend` de supervisar solo porque esté relacionado con ello. Una precondición (zona existente) tampoco es automáticamente un `include`.

**Asociación actor-caso de uso:** indica que una persona participa en un objetivo; no significa herencia Java, ni composición UML ni una llamada de método específica.

Las cuatro personas actoras del diagrama son **Topógrafo**, **Geólogo**, **Técnico de Campo** y **Gerente de Exploraciones**. Su responsabilidad funcional se vincula con las clases `Topografo`, `Geologo`, `TecnicoCampo` y `GerenteExploraciones`, pero un actor UML es un papel externo, no la instancia de la clase.

## 6. Diseño JFrame muy sencillo: cuatro pestañas

**1. Zonas y campañas:** campos cortos para registrar zona y campaña, modificar las seleccionadas y consultar registros. Utiliza `Zona` y `CampañaExploracion`.

**2. Personal:** alta de trabajador por uno de cuatro tipos, elección del `enum TipoDocumento` y consulta del trabajador creado. Los campos cambian su etiqueta según la especialidad. Utiliza `Trabajador` y sus cuatro subclases, demostrando **herencia**.

**3. Actividades:** seleccionar campaña, zona, responsable y uno de los cuatro tipos de actividad. Los tres datos técnicos se reutilizan por subtipo. El botón crea un objeto de la subclase, lo agrega a la campaña y lo muestra en listas. El botón CU09 registra resultado y recalcula usando **polimorfismo**.

**4. Supervisión:** seleccionar gerente y campaña, consultar avances, emitir decisión orientativa, generar informe y cambiar estado mediante `EstadoActividad`. Es una demostración de las asociaciones, consultas y enumeraciones.

### Reglas y validación
- No se permiten códigos repetidos para zonas, campañas y actividades.
- Debe indicarse zona, campaña y responsable antes de registrar una actividad.
- Los valores numéricos se convierten con validación.
- El técnico ejecuta perforación, el topógrafo estudio geofísico y el geólogo mapeo/muestreo.
- Una actividad finalizada o cancelada no vuelve a cambiar estado en esta versión.
- No se simula persistencia ni autenticación.

### Costos ilustrativos

| Actividad | Fórmula de demostración en Java |
|---|---|
| Perforación | `costoBase + profundidadMetros * 30` |
| Geofísica | `costoBase + 120` |
| Mapeo | `costoBase + 95` |
| Muestreo | `costoBase + cantidadMuestras * 12` |

Los importes son ejemplos para demostrar `calcularCosto()`; no representan tarifas industriales. `CampañaExploracion.calcularCostoTotal()` suma los costos de todas las actividades sin importar el subtipo.

## 7. Cómo ejecutar con Apache NetBeans 29

1. Abrir NetBeans y elegir **File → Open Project**.
2. Seleccionar `/home/ramsescb/NetBeansProjects/AppSwingUTP-Minerals-S.A`.
3. Revisar `Source Packages` / paquete predeterminado. No se requieren librerías externas.
4. Seleccionar **Clean and Build**; debe generar `dist/UTPMinerals.jar`.
5. Pulsar **Run Project**. `nbproject/project.properties` ya establece `main.class=VentanaPrincipal`.
6. Crear zona, campaña, trabajador, actividad y luego registrar resultado y abrir Supervisión.

El proyecto continúa siendo **Ant / NetBeans**, no se migró a Maven. La interfaz se convirtió en un **JFrame Form nativo de NetBeans GUI Builder/Matisse**. Los archivos src/VentanaPrincipal.java y src/VentanaPrincipal.form se mantienen juntos: se modifica lo visual en Design y la lógica POO permanece en los eventos. Continúa compilando como Swing estándar.

## 8. Verificación reproducible y límites

- Compilación NetBeans/Ant: **BUILD SUCCESSFUL** con Java 21.
- Prueba automatizada de lógica (clases del dominio): **OK** con cuatro trabajadores, cuatro actividades, asociación a zona y campaña, `enum`, estados, informes, herencia, costo total y supervisor. Costo total de prueba: **S/ 975**.
- Modelo `.asta`: editado y validado con API oficial de Astah; se conservaron las cuatro vistas de casos de uso y se actualizó el diagrama de clases con `VentanaPrincipal`, métodos y cardinalidades.
- Las pruebas anteriores se ejecutaron sobre lógica Java; las validaciones interactivas visuales completas de cada botón no están automatizadas.
- No hay base de datos, autenticación, registro de auditoría, roles técnicos reales ni informes PDF; están fuera del modelo actual.
- No hay comentarios explicativos en el código Java. Se conservan únicamente los cuatro marcadores técnicos obligatorios del GUI Builder (GEN-BEGIN / GEN-END), necesarios para que NetBeans regenere y proteja initComponents y las variables visuales. La clase no contiene imports Swing ni AWT. Este Markdown contiene la explicación para la exposición.

## 9. Guion corto para exposición

«Primero creamos `Zona` y `CampañaExploracion`; luego registramos un `Trabajador` especializado. El formulario crea una de las cuatro subclases de `ActividadExploracion` y la asocia a una campaña, zona y responsable. Gracias a la herencia no duplicamos código, y gracias al polimorfismo calculamos costos y generamos informes con un solo tipo base. `EstadoActividad` y `TipoDocumento` son enumeraciones que limitan opciones válidas. Finalmente el `GerenteExploraciones` consulta el avance y los informes para tomar una decisión orientativa. Las relaciones `include` muestran subprocesos obligatorios entre casos de uso, mientras que las flechas de herencia y las asociaciones del diagrama de clases explican la estructura del código; son tipos de UML distintos y no deben confundirse».

## 10. JFrame Form visual y editable en NetBeans

La ventana principal ahora cuenta con dos archivos: **src/VentanaPrincipal.java** y **src/VentanaPrincipal.form**. El archivo .form guarda los componentes, propiedades, eventos y el diseño de las cuatro pestañas. Las operaciones POO están implementadas fuera del bloque autogenerado initComponents.

Para modificarlo, abra VentanaPrincipal.java en NetBeans 29 y elija la pestaña **Design**. Allí puede editar las etiquetas, botones, combos, campos y pestañas visualmente. No edite el bloque generado por NetBeans; haga modificaciones a la interfaz en Design y mantenga la lógica fuera de initComponents.

Se verificó que el GUI Builder de NetBeans 29 reconoce el formulario y lo muestra en Design. También se verificó su compilación con Ant, su ejecución y el funcionamiento automatizado de registros, resultados, informes y supervisión. Las opciones de costos son de demostración.
