# App Swing UTP Minerals S.A.

Sistema de escritorio desarrollado en **Java Swing** para la gestión integral de operaciones geológicas y exploraciones mineras de la empresa **UTP Minerals S.A.**

---

## 📌 Guía de Arquitectura y Modelado UML (Astah)

El archivo de modelado principal del proyecto se encuentra en:
👉 `Diagrama_Casos_Uso_Mineria.asta`

Este archivo contiene la especificación completa del sistema, sincronizada entre el **Diagrama de Casos de Uso** y el **Diagrama de Clases del Dominio (`pkg`)**.

---

## 🏗️ 1. Modelo de Dominio de Clases (`pkg`)

El modelo de clases del paquete `pkg` estructura la lógica del negocio minero en las siguientes entidades:

### 1.1 Jerarquía de Personal (`Trabajador`)
- **`Trabajador` (Clase Abstracta)**: Base de todo el personal operativo y administrativo.
  - Atributos: `nombre`, `apellidoPaterno`, `apellidoMaterno`, `tipoDocumento: TipoDocumento`, `numDocumento: String`.
  - Métodos: `trabajar(): void`, `mostrarDatos(): void`.
  - Subclases especializadas:
    - **`Topografo`**: Atributos `equipoTopografico`, `precisionEquipo`. Métodos: `realizarLevantamiento()`.
    - **`Geologo`**: Atributos `especialidad`, `numColegiatura`. Métodos: `analizarMuestras()`.
    - **`TecnicoCampo`**: Atributos `experiencia`, `areaTecnica`. Métodos: `operarEquipo()`.
    - **`GerenteExploraciones`**: Atributos `areaResponsable`, `nivelAcceso`. Métodos: `consultarCampaña()`, `consultarAvanceActividades()`, `consultarResultadoInforme()`, `supervisarCampaña()`, `tomarDecision()`.

### 1.2 Jerarquía de Operaciones de Campo (`ActividadExploracion`)
- **`ActividadExploracion` (Clase Abstracta)**: Representa cualquier actividad técnica ejecutada en campo.
  - Atributos: `codigo`, `fecha: Date`, `estado: EstadoActividad`, `costo: double`.
  - Métodos base: `calcularCosto(): double`, `generarInforme(): void`, `registrarResultado(): void`, `cambiarEstado(): void`, `registrar(): void`, `consultar(): void`, `modificar(): void`, `mostrarInformacion(): void`.
  - Subclases especializadas:
    - **`PerforacionDiamantina`**: `profundidadMetros`, `diametroPerforacion`, `descripcionTestigos`.
    - **`EstudioGeofisico`**: `tipoMedicion`, `mediciones`, `anomalias`.
    - **`MapeoGeologico`**: `unidadesGeologicas`, `estructurasGeologicas`, `caracteristicas`.
    - **`MuestreoGeoquimico`**: `cantidadMuestras`, `tipoMuestra`, `resultadoAnalisis`.

### 1.3 Entidades de Gestión Territorial y Campañas
- **`Zona`**: Delimitación de concesión y territorio minero.
  - Atributos: `codigo`, `nombre`, `ubicacion`, `area: double`.
  - Métodos: `registrar()`, `consultar()`, `modificar()`.
  - Relación: Contiene `0..* ActividadExploracion`.
- **`CampañaExploracion`**: Campaña temporal que agrupa actividades de exploración.
  - Atributos: `codigo`, `nombre`, `fechaInicio: Date`, `fechaFin: Date`, `estado: String`, `actividades: List<ActividadExploracion>`.
  - Métodos: `agregarActividad()`, `consultarActividades()`, `registrar()`, `consultar()`, `modificar()`.

### 1.4 Enumeraciones
- **`TipoDocumento`**: `DNI`, `CE`, `Pasaporte`.
- **`EstadoActividad`**: `PLANIFICADA`, `EN_PROCESO`, `FINALIZADA`, `CANCELADA`.

---

## 🎯 2. Adaptación de Casos de Uso

Los casos de uso fueron reorganizados en **3 paquetes temáticos** para guardar estricta correspondencia con los métodos y responsabilidades de las clases del dominio:

### Paquete 1: Planificación y Gestión Territorial
Agrupa la preparación de concesiones territoriales y la formulación de proyectos.
- **`CU01: Gestionar Zonas y Concesiones`**: Mapeado a la clase `Zona` (`registrar()`, `consultar()`, `modificar()`). Actor: *Topógrafo*.
- **`CU02: Gestionar Campaña de Exploración`**: Mapeado a la clase `CampañaExploracion` (`registrar()`, `modificar()`, `agregarActividad()`). Actor: *Gerente de Exploraciones*.
- **`CU03: Planificar Actividades de Exploración`**: Mapeado a `ActividadExploracion` (`registrar()`, `cambiarEstado()`). Actores: *Gerente de Exploraciones*, *Geólogo*. Incluye (`<<include>>`) a `CU02`.
- **`CU04: Asignar Trabajadores a Actividades`**: Mapeado a la asociación `Trabajador` ↔ `ActividadExploracion`. Actor: *Gerente de Exploraciones*. Incluye (`<<include>>`) a `CU03`.

### Paquete 2: Ejecución de Actividades de Exploración
Refleja la ejecución de las 4 subclases de `ActividadExploracion` y el registro de resultados.
- **`CU05: Ejecutar Perforación Diamantina`**: Mapeado a `PerforacionDiamantina` y `TecnicoCampo::operarEquipo()`. Actor: *Técnico de Campo*.
- **`CU06: Ejecutar Levantamiento y Geofísica`**: Mapeado a `EstudioGeofisico` y `Topografo::realizarLevantamiento()`. Actor: *Topógrafo*.
- **`CU07: Realizar Mapeo Geológico`**: Mapeado a `MapeoGeologico` y `Geologo::analizarMuestras()`. Actor: *Geólogo*.
- **`CU08: Registrar Muestreo Geoquímico y Ensayos`**: Mapeado a `MuestreoGeoquimico`. Actor: *Geólogo*.
- **`CU09: Registrar Resultados y Calcular Costos`**: Caso de uso base incluido (`<<include>>`) por CU05, CU06, CU07 y CU08. Mapeado a los métodos polimórficos `registrarResultado()` y `calcularCosto()`.

### Paquete 3: Supervisión, Informes y Control
Abarca el seguimiento gerencial, control de calidad automatizado y seguridad.
- **`CU10: Consultar Avance y Supervisar Campaña`**: Mapeado a `GerenteExploraciones::supervisarCampaña()` y `consultarAvanceActividades()`. Actor: *Gerente de Exploraciones*.
- **`CU11: Generar Informes Técnicos de Actividad`**: Extiende opcionalmente (`<<extend>>`) a CU10 tras avances. Mapeado a `ActividadExploracion::generarInforme()`. Actor: *Geólogo*.
- **`CU12: Tomar Decisiones de Exploración`**: Incluye (`<<include>>`) a CU10 para evaluar viabilidad. Mapeado a `GerenteExploraciones::tomarDecision()`. Actor: *Gerente de Exploraciones*.
- **`CU13: Autenticar Usuario y Gestionar Personal`**: Mapeado a la entidad `Trabajador` y su autenticación. Actor: *Administrador del Sistema*.
- **`CU14: Validación Automática de Estados y QA/QC`**: Mapeado a las reglas de transición del enum `EstadoActividad`. Actor: *Sistema*.
- **`CU15: Auditoría y Respaldo de Datos`**: Incluido (`<<include>>`) por CU13 para trazabilidad transaccional. Actor: *Administrador del Sistema*.

---

## 👥 3. Matriz de Actores del Sistema

| Actor | Tipo | Responsabilidad en el Sistema |
| :--- | :--- | :--- |
| **Topógrafo** | Humano / Operativo | Gestión de zonas/concesiones geográficas y levantamientos geofísicos. |
| **Geólogo** | Humano / Especialista | Planificación técnica, mapeos geológicos, muestreos geoquímicos e informes. |
| **Técnico de Campo** | Humano / Operativo | Operación de equipos de perforación diamantina y toma de datos de testigos. |
| **Gerente de Exploraciones** | Humano / Decisor | Creación de campañas, asignación de personal, supervisión general y decisiones. |
| **Administrador del Sistema**| Humano / Soporte | Gestión de cuentas de trabajadores, seguridad y auditoría de la plataforma. |
| **Sistema** | Automático / Backend | Validaciones automáticas de reglas QA/QC y transiciones de estados de actividades. |

---

## 📊 4. Diagramas Disponibles en Astah

Al abrir `Diagrama_Casos_Uso_Mineria.asta` en **Astah Professional**, revisar los siguientes diagramas:

1. **`0. Diagrama General de Casos de Uso`**: Vista integral con todos los actores, los 3 paquetes y relaciones organizadas ortogonalmente.
2. **`Diagrama - Paquete 1: Planificación y Territorial`**: Vista de detalle para preparación y zonificación.
3. **`Diagrama - Paquete 2: Ejecución de Actividades`**: Vista de detalle para actividades de campo y costos.
4. **`Diagrama - Paquete 3: Supervisión, Informes y Control`**: Vista de detalle para gerencia y auditoría.
5. **`Diagrama de Clases - Modelo del Dominio (pkg)`**: Vista completa de las 10 clases, 2 enumeraciones, atributos, métodos y asociaciones.

---

## 🚀 Requisitos para Ejecución

- **Java Development Kit (JDK)**: 8 o superior.
- **IDE Recomendado**: Apache NetBeans / IntelliJ IDEA.
- **Modelador UML**: Astah Professional (para visualizar o editar `.asta`).
