import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentanaPrincipal extends javax.swing.JFrame {
    private final List<Zona> zonas = new ArrayList<>();
    private final List<CampañaExploracion> campañas = new ArrayList<>();
    private final List<Trabajador> trabajadores = new ArrayList<>();

    public VentanaPrincipal() {
        initComponents();
        prepararDatos();
        mostrar("Cree una zona, una campaña y un trabajador para comenzar.");
    }

    private void prepararDatos() {
        cboRol.setModel(new javax.swing.DefaultComboBoxModel(
            new String[]{"Geologo", "Topografo", "TecnicoCampo", "GerenteExploraciones"}));
        cboTipoActividad.setModel(new javax.swing.DefaultComboBoxModel(
            new String[]{"PerforacionDiamantina", "EstudioGeofisico", "MapeoGeologico", "MuestreoGeoquimico"}));
        cboDoc.setModel(new javax.swing.DefaultComboBoxModel(TipoDocumento.values()));
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(EstadoActividad.values()));
        actualizarRol();
        actualizarTipo();
        pestanas.setSelectedIndex(0);
    }

    private void ejecutarSeguro(Runnable accion) {
        try {
            accion.run();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Revisar datos",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrar(String texto) {
        salida.setText(texto);
        salida.setCaretPosition(0);
    }

    private String texto(javax.swing.JTextField campo) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) throw new IllegalArgumentException("Complete los datos obligatorios");
        return valor;
    }

    private double numero(javax.swing.JTextField campo) {
        try { return Double.parseDouble(texto(campo)); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Número inválido: " + campo.getText()); }
    }

    private int entero(javax.swing.JTextField campo) {
        try { return Integer.parseInt(texto(campo)); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("Entero inválido: " + campo.getText()); }
    }

    private <T> T elegido(javax.swing.JComboBox combo, Class<T> tipo, String nombre) {
        Object v = combo.getSelectedItem();
        if (v == null || !tipo.isInstance(v)) throw new IllegalArgumentException("Seleccione " + nombre);
        return tipo.cast(v);
    }

    private void actualizarRol() {
        String v = (String) cboRol.getSelectedItem();
        if ("Geologo".equals(v)) {
            lblTxtDatoUno.setText("Especialidad");
            lblTxtDatoDos.setText("Número de colegiatura");
        } else if ("Topografo".equals(v)) {
            lblTxtDatoUno.setText("Equipo topográfico");
            lblTxtDatoDos.setText("Precisión");
        } else if ("TecnicoCampo".equals(v)) {
            lblTxtDatoUno.setText("Área técnica");
            lblTxtDatoDos.setText("Años de experiencia");
        } else {
            lblTxtDatoUno.setText("Área responsable");
            lblTxtDatoDos.setText("Nivel de acceso");
        }
    }

    private void actualizarTipo() {
        String v = (String) cboTipoActividad.getSelectedItem();
        if ("PerforacionDiamantina".equals(v)) {
            lblTxtDatoA.setText("Profundidad (m)");
            lblTxtDatoB.setText("Diámetro (mm)");
            lblTxtDatoC.setText("Descripción de testigos");
        } else if ("EstudioGeofisico".equals(v)) {
            lblTxtDatoA.setText("Tipo de medición");
            lblTxtDatoB.setText("Mediciones");
            lblTxtDatoC.setText("Anomalías");
        } else if ("MapeoGeologico".equals(v)) {
            lblTxtDatoA.setText("Unidades geológicas");
            lblTxtDatoB.setText("Estructuras geológicas");
            lblTxtDatoC.setText("Características");
        } else {
            lblTxtDatoA.setText("Cantidad de muestras");
            lblTxtDatoB.setText("Tipo de muestra");
            lblTxtDatoC.setText("Resultado de análisis");
        }
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {
        pestanas = new javax.swing.JTabbedPane();
        barraSalida = new javax.swing.JScrollPane();
        salida = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UTP Minerals S.A. - Gestión de exploraciones");
        getContentPane().setLayout(new java.awt.BorderLayout());
        panelZonas = new javax.swing.JPanel();
        panelZonas.setLayout(new java.awt.GridLayout(0, 2, 9, 6));
        lblTxtZonaCodigo = new javax.swing.JLabel();
        lblTxtZonaCodigo.setText("CU01 - Código de zona");
        panelZonas.add(lblTxtZonaCodigo);
        txtZonaCodigo = new javax.swing.JTextField();
        txtZonaCodigo.setText("Z01");
        panelZonas.add(txtZonaCodigo);
        lblTxtZonaNombre = new javax.swing.JLabel();
        lblTxtZonaNombre.setText("Nombre de zona");
        panelZonas.add(lblTxtZonaNombre);
        txtZonaNombre = new javax.swing.JTextField();
        txtZonaNombre.setText("Zona Norte");
        panelZonas.add(txtZonaNombre);
        lblTxtZonaUbicacion = new javax.swing.JLabel();
        lblTxtZonaUbicacion.setText("Ubicación");
        panelZonas.add(lblTxtZonaUbicacion);
        txtZonaUbicacion = new javax.swing.JTextField();
        txtZonaUbicacion.setText("Áncash");
        panelZonas.add(txtZonaUbicacion);
        lblTxtZonaArea = new javax.swing.JLabel();
        lblTxtZonaArea.setText("Área (km²)");
        panelZonas.add(lblTxtZonaArea);
        txtZonaArea = new javax.swing.JTextField();
        txtZonaArea.setText("4.5");
        panelZonas.add(txtZonaArea);
        lblCboZonaEditar = new javax.swing.JLabel();
        lblCboZonaEditar.setText("Zona a modificar");
        panelZonas.add(lblCboZonaEditar);
        cboZonaEditar = new javax.swing.JComboBox();
        panelZonas.add(cboZonaEditar);
        lblBtnRegistrarZona = new javax.swing.JLabel();
        lblBtnRegistrarZona.setText("");
        panelZonas.add(lblBtnRegistrarZona);
        btnRegistrarZona = new javax.swing.JButton();
        btnRegistrarZona.setText("Registrar zona");
        btnRegistrarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarZonaActionPerformed(evt);
            }
        });
        panelZonas.add(btnRegistrarZona);
        lblBtnModificarZona = new javax.swing.JLabel();
        lblBtnModificarZona.setText("");
        panelZonas.add(lblBtnModificarZona);
        btnModificarZona = new javax.swing.JButton();
        btnModificarZona.setText("Modificar zona seleccionada");
        btnModificarZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarZonaActionPerformed(evt);
            }
        });
        panelZonas.add(btnModificarZona);
        lblTxtCampCodigo = new javax.swing.JLabel();
        lblTxtCampCodigo.setText("CU02 - Código de campaña");
        panelZonas.add(lblTxtCampCodigo);
        txtCampCodigo = new javax.swing.JTextField();
        txtCampCodigo.setText("C01");
        panelZonas.add(txtCampCodigo);
        lblTxtCampNombre = new javax.swing.JLabel();
        lblTxtCampNombre.setText("Nombre de campaña");
        panelZonas.add(lblTxtCampNombre);
        txtCampNombre = new javax.swing.JTextField();
        txtCampNombre.setText("Exploración inicial");
        panelZonas.add(txtCampNombre);
        lblTxtCampDias = new javax.swing.JLabel();
        lblTxtCampDias.setText("Duración estimada (días)");
        panelZonas.add(lblTxtCampDias);
        txtCampDias = new javax.swing.JTextField();
        txtCampDias.setText("30");
        panelZonas.add(txtCampDias);
        lblCboCampEditar = new javax.swing.JLabel();
        lblCboCampEditar.setText("Campaña a modificar");
        panelZonas.add(lblCboCampEditar);
        cboCampEditar = new javax.swing.JComboBox();
        panelZonas.add(cboCampEditar);
        lblBtnRegistrarCamp = new javax.swing.JLabel();
        lblBtnRegistrarCamp.setText("");
        panelZonas.add(lblBtnRegistrarCamp);
        btnRegistrarCamp = new javax.swing.JButton();
        btnRegistrarCamp.setText("Registrar campaña");
        btnRegistrarCamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCampActionPerformed(evt);
            }
        });
        panelZonas.add(btnRegistrarCamp);
        lblBtnModificarCamp = new javax.swing.JLabel();
        lblBtnModificarCamp.setText("");
        panelZonas.add(lblBtnModificarCamp);
        btnModificarCamp = new javax.swing.JButton();
        btnModificarCamp.setText("Modificar campaña");
        btnModificarCamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCampActionPerformed(evt);
            }
        });
        panelZonas.add(btnModificarCamp);
        lblBtnConsultarZonas = new javax.swing.JLabel();
        lblBtnConsultarZonas.setText("");
        panelZonas.add(lblBtnConsultarZonas);
        btnConsultarZonas = new javax.swing.JButton();
        btnConsultarZonas.setText("Consultar zonas y campañas");
        btnConsultarZonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarZonasActionPerformed(evt);
            }
        });
        panelZonas.add(btnConsultarZonas);
        pestanas.addTab("1. Zonas y campañas", panelZonas);
        panelPersonal = new javax.swing.JPanel();
        panelPersonal.setLayout(new java.awt.GridLayout(0, 2, 9, 6));
        lblCboRol = new javax.swing.JLabel();
        lblCboRol.setText("Clase de trabajador");
        panelPersonal.add(lblCboRol);
        cboRol = new javax.swing.JComboBox();
        cboRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRolActionPerformed(evt);
            }
        });
        panelPersonal.add(cboRol);
        lblTxtNombre = new javax.swing.JLabel();
        lblTxtNombre.setText("Nombre");
        panelPersonal.add(lblTxtNombre);
        txtNombre = new javax.swing.JTextField();
        txtNombre.setText("Ana");
        panelPersonal.add(txtNombre);
        lblTxtPaterno = new javax.swing.JLabel();
        lblTxtPaterno.setText("Apellido paterno");
        panelPersonal.add(lblTxtPaterno);
        txtPaterno = new javax.swing.JTextField();
        txtPaterno.setText("Pérez");
        panelPersonal.add(txtPaterno);
        lblTxtMaterno = new javax.swing.JLabel();
        lblTxtMaterno.setText("Apellido materno");
        panelPersonal.add(lblTxtMaterno);
        txtMaterno = new javax.swing.JTextField();
        txtMaterno.setText("Díaz");
        panelPersonal.add(txtMaterno);
        lblCboDoc = new javax.swing.JLabel();
        lblCboDoc.setText("TipoDocumento (enum)");
        panelPersonal.add(lblCboDoc);
        cboDoc = new javax.swing.JComboBox();
        panelPersonal.add(cboDoc);
        lblTxtNumeroDoc = new javax.swing.JLabel();
        lblTxtNumeroDoc.setText("Número de documento");
        panelPersonal.add(lblTxtNumeroDoc);
        txtNumeroDoc = new javax.swing.JTextField();
        txtNumeroDoc.setText("12345678");
        panelPersonal.add(txtNumeroDoc);
        lblTxtDatoUno = new javax.swing.JLabel();
        lblTxtDatoUno.setText("Especialidad / equipo / área");
        panelPersonal.add(lblTxtDatoUno);
        txtDatoUno = new javax.swing.JTextField();
        txtDatoUno.setText("Geología");
        panelPersonal.add(txtDatoUno);
        lblTxtDatoDos = new javax.swing.JLabel();
        lblTxtDatoDos.setText("Colegiatura / precisión / experiencia");
        panelPersonal.add(lblTxtDatoDos);
        txtDatoDos = new javax.swing.JTextField();
        txtDatoDos.setText("10");
        panelPersonal.add(txtDatoDos);
        lblBtnRegistrarTrab = new javax.swing.JLabel();
        lblBtnRegistrarTrab.setText("");
        panelPersonal.add(lblBtnRegistrarTrab);
        btnRegistrarTrab = new javax.swing.JButton();
        btnRegistrarTrab.setText("Registrar trabajador");
        btnRegistrarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTrabActionPerformed(evt);
            }
        });
        panelPersonal.add(btnRegistrarTrab);
        lblCboPersonal = new javax.swing.JLabel();
        lblCboPersonal.setText("CU13 - Trabajador registrado");
        panelPersonal.add(lblCboPersonal);
        cboPersonal = new javax.swing.JComboBox();
        panelPersonal.add(cboPersonal);
        lblBtnConsultarTrab = new javax.swing.JLabel();
        lblBtnConsultarTrab.setText("");
        panelPersonal.add(lblBtnConsultarTrab);
        btnConsultarTrab = new javax.swing.JButton();
        btnConsultarTrab.setText("Consultar datos del trabajador");
        btnConsultarTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTrabActionPerformed(evt);
            }
        });
        panelPersonal.add(btnConsultarTrab);
        pestanas.addTab("2. Personal", panelPersonal);
        panelActividades = new javax.swing.JPanel();
        panelActividades.setLayout(new java.awt.GridLayout(0, 2, 9, 6));
        lblCboCampaña = new javax.swing.JLabel();
        lblCboCampaña.setText("CU03 - Campaña");
        panelActividades.add(lblCboCampaña);
        cboCampaña = new javax.swing.JComboBox();
        panelActividades.add(cboCampaña);
        lblCboZona = new javax.swing.JLabel();
        lblCboZona.setText("Zona asignada");
        panelActividades.add(lblCboZona);
        cboZona = new javax.swing.JComboBox();
        panelActividades.add(cboZona);
        lblCboTrabajador = new javax.swing.JLabel();
        lblCboTrabajador.setText("CU04 - Responsable");
        panelActividades.add(lblCboTrabajador);
        cboTrabajador = new javax.swing.JComboBox();
        panelActividades.add(cboTrabajador);
        lblCboTipoActividad = new javax.swing.JLabel();
        lblCboTipoActividad.setText("CU05-CU08 - Tipo de actividad");
        panelActividades.add(lblCboTipoActividad);
        cboTipoActividad = new javax.swing.JComboBox();
        cboTipoActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoActividadActionPerformed(evt);
            }
        });
        panelActividades.add(cboTipoActividad);
        lblTxtActCodigo = new javax.swing.JLabel();
        lblTxtActCodigo.setText("Código de actividad");
        panelActividades.add(lblTxtActCodigo);
        txtActCodigo = new javax.swing.JTextField();
        txtActCodigo.setText("A01");
        panelActividades.add(txtActCodigo);
        lblTxtActCosto = new javax.swing.JLabel();
        lblTxtActCosto.setText("Costo base de ejemplo (S/)");
        panelActividades.add(lblTxtActCosto);
        txtActCosto = new javax.swing.JTextField();
        txtActCosto.setText("100");
        panelActividades.add(txtActCosto);
        lblTxtDatoA = new javax.swing.JLabel();
        lblTxtDatoA.setText("Profundidad (m)");
        panelActividades.add(lblTxtDatoA);
        txtDatoA = new javax.swing.JTextField();
        txtDatoA.setText("12");
        panelActividades.add(txtDatoA);
        lblTxtDatoB = new javax.swing.JLabel();
        lblTxtDatoB.setText("Diámetro (mm)");
        panelActividades.add(lblTxtDatoB);
        txtDatoB = new javax.swing.JTextField();
        txtDatoB.setText("50");
        panelActividades.add(txtDatoB);
        lblTxtDatoC = new javax.swing.JLabel();
        lblTxtDatoC.setText("Descripción de testigos");
        panelActividades.add(lblTxtDatoC);
        txtDatoC = new javax.swing.JTextField();
        txtDatoC.setText("Testigos de roca");
        panelActividades.add(txtDatoC);
        lblBtnRegistrarAct = new javax.swing.JLabel();
        lblBtnRegistrarAct.setText("");
        panelActividades.add(lblBtnRegistrarAct);
        btnRegistrarAct = new javax.swing.JButton();
        btnRegistrarAct.setText("Registrar y asignar actividad");
        btnRegistrarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActActionPerformed(evt);
            }
        });
        panelActividades.add(btnRegistrarAct);
        lblCboActividad = new javax.swing.JLabel();
        lblCboActividad.setText("CU09 - Actividad a concluir");
        panelActividades.add(lblCboActividad);
        cboActividad = new javax.swing.JComboBox();
        panelActividades.add(cboActividad);
        lblTxtResultado = new javax.swing.JLabel();
        lblTxtResultado.setText("Resultado registrado");
        panelActividades.add(lblTxtResultado);
        txtResultado = new javax.swing.JTextField();
        txtResultado.setText("Trabajo realizado");
        panelActividades.add(txtResultado);
        lblBtnRegistrarResultado = new javax.swing.JLabel();
        lblBtnRegistrarResultado.setText("");
        panelActividades.add(lblBtnRegistrarResultado);
        btnRegistrarResultado = new javax.swing.JButton();
        btnRegistrarResultado.setText("Registrar resultado y calcular costo");
        btnRegistrarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarResultadoActionPerformed(evt);
            }
        });
        panelActividades.add(btnRegistrarResultado);
        pestanas.addTab("3. Actividades", panelActividades);
        panelSupervision = new javax.swing.JPanel();
        panelSupervision.setLayout(new java.awt.GridLayout(0, 2, 9, 6));
        lblCboSeguimiento = new javax.swing.JLabel();
        lblCboSeguimiento.setText("CU10/CU12 - Campaña");
        panelSupervision.add(lblCboSeguimiento);
        cboSeguimiento = new javax.swing.JComboBox();
        panelSupervision.add(cboSeguimiento);
        lblCboGerente = new javax.swing.JLabel();
        lblCboGerente.setText("GerenteExploraciones");
        panelSupervision.add(lblCboGerente);
        cboGerente = new javax.swing.JComboBox();
        panelSupervision.add(cboGerente);
        lblBtnSupervisar = new javax.swing.JLabel();
        lblBtnSupervisar.setText("");
        panelSupervision.add(lblBtnSupervisar);
        btnSupervisar = new javax.swing.JButton();
        btnSupervisar.setText("Supervisar campaña (CU10)");
        btnSupervisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupervisarActionPerformed(evt);
            }
        });
        panelSupervision.add(btnSupervisar);
        lblBtnDecision = new javax.swing.JLabel();
        lblBtnDecision.setText("");
        panelSupervision.add(lblBtnDecision);
        btnDecision = new javax.swing.JButton();
        btnDecision.setText("Tomar decisión (CU12)");
        btnDecision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecisionActionPerformed(evt);
            }
        });
        panelSupervision.add(btnDecision);
        lblCboInforme = new javax.swing.JLabel();
        lblCboInforme.setText("CU11/CU15 - Actividad");
        panelSupervision.add(lblCboInforme);
        cboInforme = new javax.swing.JComboBox();
        panelSupervision.add(cboInforme);
        lblBtnInforme = new javax.swing.JLabel();
        lblBtnInforme.setText("");
        panelSupervision.add(lblBtnInforme);
        btnInforme = new javax.swing.JButton();
        btnInforme.setText("Generar y consultar informe");
        btnInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeActionPerformed(evt);
            }
        });
        panelSupervision.add(btnInforme);
        lblCboEstadoActividad = new javax.swing.JLabel();
        lblCboEstadoActividad.setText("CU14 - Actividad");
        panelSupervision.add(lblCboEstadoActividad);
        cboEstadoActividad = new javax.swing.JComboBox();
        panelSupervision.add(cboEstadoActividad);
        lblCboEstado = new javax.swing.JLabel();
        lblCboEstado.setText("EstadoActividad (enum)");
        panelSupervision.add(lblCboEstado);
        cboEstado = new javax.swing.JComboBox();
        panelSupervision.add(cboEstado);
        lblBtnCambiarEstado = new javax.swing.JLabel();
        lblBtnCambiarEstado.setText("");
        panelSupervision.add(lblBtnCambiarEstado);
        btnCambiarEstado = new javax.swing.JButton();
        btnCambiarEstado.setText("Cambiar estado (CU14)");
        btnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoActionPerformed(evt);
            }
        });
        panelSupervision.add(btnCambiarEstado);
        lblBtnResumen = new javax.swing.JLabel();
        lblBtnResumen.setText("");
        panelSupervision.add(lblBtnResumen);
        btnResumen = new javax.swing.JButton();
        btnResumen.setText("Ver resumen de clases y costos");
        btnResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumenActionPerformed(evt);
            }
        });
        panelSupervision.add(btnResumen);
        pestanas.addTab("4. Supervisión", panelSupervision);
        getContentPane().add(pestanas, java.awt.BorderLayout.CENTER);
        barraSalida.setViewportView(salida);
        salida.setEditable(false);
        salida.setLineWrap(true);
        salida.setWrapStyleWord(true);
        barraSalida.setPreferredSize(new java.awt.Dimension(1020, 145));
        getContentPane().add(barraSalida, java.awt.BorderLayout.SOUTH);
        setSize(new java.awt.Dimension(1050, 820));
        setLocationRelativeTo(null);
    }
    //GEN-END:initComponents


    private void registrarZona() {
        String codigo = texto(txtZonaCodigo);
        for (Zona z : zonas) if (z.getCodigo().equalsIgnoreCase(codigo))
            throw new IllegalArgumentException("Código de zona repetido");
        Zona z = new Zona(codigo, texto(txtZonaNombre), texto(txtZonaUbicacion), numero(txtZonaArea));
        z.registrar();
        zonas.add(z);
        cboZona.addItem(z);
        cboZonaEditar.addItem(z);
        mostrar(z.consultar());
    }

    private void modificarZona() {
        Zona z = elegido(cboZonaEditar, Zona.class, "zona");
        z.modificar(texto(txtZonaNombre), texto(txtZonaUbicacion), numero(txtZonaArea));
        mostrar(z.consultar());
    }

    private void registrarCampaña() {
        String codigo = texto(txtCampCodigo);
        for (CampañaExploracion c : campañas) if (c.getCodigo().equalsIgnoreCase(codigo))
            throw new IllegalArgumentException("Código de campaña repetido");
        int dias = entero(txtCampDias);
        if (dias <= 0) throw new IllegalArgumentException("La duración debe ser positiva");
        Date inicio = new Date();
        CampañaExploracion camp = new CampañaExploracion(codigo, texto(txtCampNombre),
            inicio, new Date(inicio.getTime() + dias * 86400000L));
        camp.registrar();
        campañas.add(camp);
        cboCampEditar.addItem(camp);
        cboCampaña.addItem(camp);
        cboSeguimiento.addItem(camp);
        mostrar(camp.consultar());
    }

    private void modificarCampaña() {
        CampañaExploracion camp = elegido(cboCampEditar, CampañaExploracion.class, "campaña");
        int dias = entero(txtCampDias);
        if (dias <= 0) throw new IllegalArgumentException("La duración debe ser positiva");
        camp.modificar(texto(txtCampNombre), new Date(System.currentTimeMillis() + dias * 86400000L), "EN_PROCESO");
        mostrar(camp.consultar());
    }

    private void consultarZonas() {
        StringBuilder b = new StringBuilder("ZONAS\n");
        for (Zona z : zonas) b.append(z.consultar()).append("\n");
        b.append("\nCAMPAÑAS\n");
        for (CampañaExploracion c : campañas) b.append(c.consultar()).append("\n");
        mostrar(b.toString());
    }

    private void registrarTrabajador() {
        String n = texto(txtNombre), ap = texto(txtPaterno), am = texto(txtMaterno), num = texto(txtNumeroDoc);
        TipoDocumento doc = elegido(cboDoc, TipoDocumento.class, "TipoDocumento");
        for (Trabajador t : trabajadores) if (t.getNumDocumento().equals(num) && t.getTipoDocumento() == doc)
            throw new IllegalArgumentException("Documento repetido");
        String tipo = elegido(cboRol, String.class, "tipo de trabajador");
        Trabajador t;
        switch (tipo) {
            case "Geologo":
                t = new Geologo(n, ap, am, doc, num, texto(txtDatoUno), texto(txtDatoDos));
                break;
            case "Topografo":
                t = new Topografo(n, ap, am, doc, num, texto(txtDatoUno), numero(txtDatoDos));
                break;
            case "TecnicoCampo":
                t = new TecnicoCampo(n, ap, am, doc, num, entero(txtDatoDos), texto(txtDatoUno));
                break;
            default:
                GerenteExploraciones g = new GerenteExploraciones(n, ap, am, doc, num,
                    texto(txtDatoUno), texto(txtDatoDos));
                t = g;
                cboGerente.addItem(g);
        }
        trabajadores.add(t);
        cboTrabajador.addItem(t);
        cboPersonal.addItem(t);
        mostrar(t.mostrarDatos() + "\n" + t.trabajar());
    }

    private void registrarActividad() {
        CampañaExploracion camp = elegido(cboCampaña, CampañaExploracion.class, "campaña");
        Zona zona = elegido(cboZona, Zona.class, "zona");
        Trabajador resp = elegido(cboTrabajador, Trabajador.class, "responsable");
        String id = texto(txtActCodigo);
        for (CampañaExploracion c : campañas) for (ActividadExploracion a : c.consultarActividades())
            if (a.getCodigo().equalsIgnoreCase(id)) throw new IllegalArgumentException("Código de actividad repetido");
        double costo = numero(txtActCosto);
        String clase = elegido(cboTipoActividad, String.class, "tipo de actividad");
        if ("PerforacionDiamantina".equals(clase) && !(resp instanceof TecnicoCampo)
            || "EstudioGeofisico".equals(clase) && !(resp instanceof Topografo)
            || ("MapeoGeologico".equals(clase) || "MuestreoGeoquimico".equals(clase)) && !(resp instanceof Geologo))
            throw new IllegalArgumentException("Elija el profesional indicado para esta actividad");
        ActividadExploracion a;
        switch (clase) {
            case "PerforacionDiamantina":
                a = new PerforacionDiamantina(id, new Date(), resp, zona, costo,
                    numero(txtDatoA), numero(txtDatoB), texto(txtDatoC));
                break;
            case "EstudioGeofisico":
                a = new EstudioGeofisico(id, new Date(), resp, zona, costo,
                    texto(txtDatoA), texto(txtDatoB), texto(txtDatoC));
                break;
            case "MapeoGeologico":
                a = new MapeoGeologico(id, new Date(), resp, zona, costo,
                    texto(txtDatoA), texto(txtDatoB), texto(txtDatoC));
                break;
            default:
                a = new MuestreoGeoquimico(id, new Date(), resp, zona, costo,
                    entero(txtDatoA), texto(txtDatoB), texto(txtDatoC));
        }
        a.registrar();
        camp.agregarActividad(a);
        cboActividad.addItem(a);
        cboEstadoActividad.addItem(a);
        cboInforme.addItem(a);
        mostrar("CU03/CU04: Actividad y responsable registrados.\n" + a.consultar());
    }

    private void registrarResultado() {
        ActividadExploracion a = elegido(cboActividad, ActividadExploracion.class, "actividad");
        a.registrarResultado(texto(txtResultado));
        String accion = a.getResponsable().trabajar();
        if (a.getResponsable() instanceof TecnicoCampo)
            accion = ((TecnicoCampo) a.getResponsable()).operarEquipo();
        if (a.getResponsable() instanceof Topografo)
            accion = ((Topografo) a.getResponsable()).realizarLevantamiento();
        if (a.getResponsable() instanceof Geologo)
            accion = ((Geologo) a.getResponsable()).analizarMuestras();
        mostrar(accion + "\nCU09: " + a.generarInforme());
    }

    private void supervisar() {
        GerenteExploraciones g = elegido(cboGerente, GerenteExploraciones.class, "gerente");
        mostrar(g.supervisarCampaña(elegido(cboSeguimiento, CampañaExploracion.class, "campaña")));
    }

    private void decidir() {
        GerenteExploraciones g = elegido(cboGerente, GerenteExploraciones.class, "gerente");
        mostrar(g.tomarDecision(elegido(cboSeguimiento, CampañaExploracion.class, "campaña")));
    }

    private void informe() {
        ActividadExploracion a = elegido(cboInforme, ActividadExploracion.class, "actividad");
        Object g = cboGerente.getSelectedItem();
        mostrar(g instanceof GerenteExploraciones ?
            ((GerenteExploraciones) g).consultarResultadoInforme(a) : a.generarInforme());
    }

    private void cambiarEstado() {
        ActividadExploracion a = elegido(cboEstadoActividad, ActividadExploracion.class, "actividad");
        a.cambiarEstado(elegido(cboEstado, EstadoActividad.class, "estado"));
        mostrar(a.mostrarInformacion());
    }

    private void resumen() {
        StringBuilder b = new StringBuilder("Campañas y clases ActividadExploracion\n");
        for (CampañaExploracion camp : campañas) {
            b.append(camp.consultar()).append("\n");
            for (ActividadExploracion a : camp.consultarActividades())
                b.append("   ").append(a.mostrarInformacion()).append("\n");
        }
        mostrar(b.toString());
    }

    private void btnRegistrarZonaActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::registrarZona);
    }

    private void btnModificarZonaActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::modificarZona);
    }

    private void btnRegistrarCampActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::registrarCampaña);
    }

    private void btnModificarCampActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::modificarCampaña);
    }

    private void btnConsultarZonasActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::consultarZonas);
    }

    private void btnRegistrarTrabActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::registrarTrabajador);
    }

    private void btnConsultarTrabActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(() -> mostrar(elegido(cboPersonal, Trabajador.class, "trabajador").mostrarDatos()));
    }

    private void btnRegistrarActActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::registrarActividad);
    }

    private void btnRegistrarResultadoActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::registrarResultado);
    }

    private void btnSupervisarActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::supervisar);
    }

    private void btnDecisionActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::decidir);
    }

    private void btnInformeActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::informe);
    }

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::cambiarEstado);
    }

    private void btnResumenActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarSeguro(this::resumen);
    }

    private void cboRolActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarRol();
    }

    private void cboTipoActividadActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarTipo();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnConsultarTrab;
    private javax.swing.JButton btnConsultarZonas;
    private javax.swing.JButton btnDecision;
    private javax.swing.JButton btnInforme;
    private javax.swing.JButton btnModificarCamp;
    private javax.swing.JButton btnModificarZona;
    private javax.swing.JButton btnRegistrarAct;
    private javax.swing.JButton btnRegistrarCamp;
    private javax.swing.JButton btnRegistrarResultado;
    private javax.swing.JButton btnRegistrarTrab;
    private javax.swing.JButton btnRegistrarZona;
    private javax.swing.JButton btnResumen;
    private javax.swing.JButton btnSupervisar;
    private javax.swing.JComboBox cboActividad;
    private javax.swing.JComboBox cboCampEditar;
    private javax.swing.JComboBox cboCampaña;
    private javax.swing.JComboBox cboDoc;
    private javax.swing.JComboBox cboEstado;
    private javax.swing.JComboBox cboEstadoActividad;
    private javax.swing.JComboBox cboGerente;
    private javax.swing.JComboBox cboInforme;
    private javax.swing.JComboBox cboPersonal;
    private javax.swing.JComboBox cboRol;
    private javax.swing.JComboBox cboSeguimiento;
    private javax.swing.JComboBox cboTipoActividad;
    private javax.swing.JComboBox cboTrabajador;
    private javax.swing.JComboBox cboZona;
    private javax.swing.JComboBox cboZonaEditar;
    private javax.swing.JLabel lblBtnCambiarEstado;
    private javax.swing.JLabel lblBtnConsultarTrab;
    private javax.swing.JLabel lblBtnConsultarZonas;
    private javax.swing.JLabel lblBtnDecision;
    private javax.swing.JLabel lblBtnInforme;
    private javax.swing.JLabel lblBtnModificarCamp;
    private javax.swing.JLabel lblBtnModificarZona;
    private javax.swing.JLabel lblBtnRegistrarAct;
    private javax.swing.JLabel lblBtnRegistrarCamp;
    private javax.swing.JLabel lblBtnRegistrarResultado;
    private javax.swing.JLabel lblBtnRegistrarTrab;
    private javax.swing.JLabel lblBtnRegistrarZona;
    private javax.swing.JLabel lblBtnResumen;
    private javax.swing.JLabel lblBtnSupervisar;
    private javax.swing.JLabel lblCboActividad;
    private javax.swing.JLabel lblCboCampEditar;
    private javax.swing.JLabel lblCboCampaña;
    private javax.swing.JLabel lblCboDoc;
    private javax.swing.JLabel lblCboEstado;
    private javax.swing.JLabel lblCboEstadoActividad;
    private javax.swing.JLabel lblCboGerente;
    private javax.swing.JLabel lblCboInforme;
    private javax.swing.JLabel lblCboPersonal;
    private javax.swing.JLabel lblCboRol;
    private javax.swing.JLabel lblCboSeguimiento;
    private javax.swing.JLabel lblCboTipoActividad;
    private javax.swing.JLabel lblCboTrabajador;
    private javax.swing.JLabel lblCboZona;
    private javax.swing.JLabel lblCboZonaEditar;
    private javax.swing.JLabel lblTxtActCodigo;
    private javax.swing.JLabel lblTxtActCosto;
    private javax.swing.JLabel lblTxtCampCodigo;
    private javax.swing.JLabel lblTxtCampDias;
    private javax.swing.JLabel lblTxtCampNombre;
    private javax.swing.JLabel lblTxtDatoA;
    private javax.swing.JLabel lblTxtDatoB;
    private javax.swing.JLabel lblTxtDatoC;
    private javax.swing.JLabel lblTxtDatoDos;
    private javax.swing.JLabel lblTxtDatoUno;
    private javax.swing.JLabel lblTxtMaterno;
    private javax.swing.JLabel lblTxtNombre;
    private javax.swing.JLabel lblTxtNumeroDoc;
    private javax.swing.JLabel lblTxtPaterno;
    private javax.swing.JLabel lblTxtResultado;
    private javax.swing.JLabel lblTxtZonaArea;
    private javax.swing.JLabel lblTxtZonaCodigo;
    private javax.swing.JLabel lblTxtZonaNombre;
    private javax.swing.JLabel lblTxtZonaUbicacion;
    private javax.swing.JPanel panelActividades;
    private javax.swing.JPanel panelPersonal;
    private javax.swing.JPanel panelSupervision;
    private javax.swing.JPanel panelZonas;
    private javax.swing.JScrollPane barraSalida;
    private javax.swing.JTabbedPane pestanas;
    private javax.swing.JTextArea salida;
    private javax.swing.JTextField txtActCodigo;
    private javax.swing.JTextField txtActCosto;
    private javax.swing.JTextField txtCampCodigo;
    private javax.swing.JTextField txtCampDias;
    private javax.swing.JTextField txtCampNombre;
    private javax.swing.JTextField txtDatoA;
    private javax.swing.JTextField txtDatoB;
    private javax.swing.JTextField txtDatoC;
    private javax.swing.JTextField txtDatoDos;
    private javax.swing.JTextField txtDatoUno;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroDoc;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtResultado;
    private javax.swing.JTextField txtZonaArea;
    private javax.swing.JTextField txtZonaCodigo;
    private javax.swing.JTextField txtZonaNombre;
    private javax.swing.JTextField txtZonaUbicacion;
    //GEN-END:variables
}
