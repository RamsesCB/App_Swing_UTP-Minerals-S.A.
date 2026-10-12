import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {
    private final List<Zona> zonas = new ArrayList<>();
    private final List<CampañaExploracion> campañas = new ArrayList<>();
    private final List<Trabajador> trabajadores = new ArrayList<>();
    private final JComboBox<Zona> cboZona = new JComboBox<>();
    private final JComboBox<CampañaExploracion> cboCampaña = new JComboBox<>();
    private final JComboBox<CampañaExploracion> cboSeguimiento = new JComboBox<>();
    private final JComboBox<Trabajador> cboTrabajador = new JComboBox<>();
    private final JComboBox<Trabajador> cboPersonal = new JComboBox<>();
    private final JComboBox<GerenteExploraciones> cboGerente = new JComboBox<>();
    private final JComboBox<ActividadExploracion> cboActividad = new JComboBox<>();
    private final JComboBox<ActividadExploracion> cboEstadoActividad = new JComboBox<>();
    private final JComboBox<ActividadExploracion> cboInforme = new JComboBox<>();
    private final JTextArea salida = new JTextArea(7, 72);

    public VentanaPrincipal() {
        super("UTP Minerals S.A. - Gestión de exploraciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(970, 760);
        setLocationRelativeTo(null);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("1. Zonas y campañas", panelPlanificacion());
        tabs.addTab("2. Personal", panelPersonal());
        tabs.addTab("3. Actividades", panelActividades());
        tabs.addTab("4. Supervisión", panelSupervision());
        salida.setEditable(false);
        salida.setLineWrap(true);
        salida.setWrapStyleWord(true);
        JScrollPane consola = new JScrollPane(salida);
        consola.setBorder(BorderFactory.createTitledBorder("Resultado de la operación"));
        consola.setPreferredSize(new Dimension(800, 180));
        add(tabs, BorderLayout.CENTER);
        add(consola, BorderLayout.SOUTH);
        mostrar("Cree una zona, una campaña y un trabajador para comenzar.");
    }

    private JTextField campo(JPanel panel, String etiqueta, String ejemplo) {
        panel.add(new JLabel(etiqueta));
        JTextField texto = new JTextField(ejemplo);
        panel.add(texto);
        return texto;
    }

    private <T> void selector(JPanel panel, String etiqueta, JComboBox<T> combo) {
        panel.add(new JLabel(etiqueta));
        panel.add(combo);
    }

    private void boton(JPanel panel, String etiqueta, Runnable accion) {
        panel.add(new JLabel(""));
        JButton boton = new JButton(etiqueta);
        boton.addActionListener(e -> {
            try {
                accion.run();
            } catch (IllegalArgumentException | IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Revisar datos", JOptionPane.WARNING_MESSAGE);
            }
        });
        panel.add(boton);
    }

    private JPanel formulario() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 12, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        return panel;
    }

    private void mostrar(String texto) {
        salida.setText(texto);
        salida.setCaretPosition(0);
    }

    private String texto(JTextField campo) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) throw new IllegalArgumentException("Complete todos los datos obligatorios");
        return valor;
    }

    private double numero(JTextField campo) {
        try {
            return Double.parseDouble(texto(campo));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Se necesita un número válido: " + campo.getText());
        }
    }

    private int entero(JTextField campo) {
        try {
            return Integer.parseInt(texto(campo));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Se necesita un número entero válido: " + campo.getText());
        }
    }

    private <T> T elegido(JComboBox<T> combo, String mensaje) {
        T valor = combo.getSelectedIndex() < 0 ? null : combo.getItemAt(combo.getSelectedIndex());
        if (valor == null) throw new IllegalArgumentException("Debe seleccionar " + mensaje);
        return valor;
    }

    private JPanel panelPlanificacion() {
        JPanel panel = formulario();
        JTextField zCodigo = campo(panel, "CU01 - Código de zona", "Z01");
        JTextField zNombre = campo(panel, "Nombre de zona", "Zona Norte");
        JTextField zUbicacion = campo(panel, "Ubicación", "Áncash");
        JTextField zArea = campo(panel, "Área (km²)", "4.5");
        boton(panel, "Registrar zona", () -> {
            String codigo = texto(zCodigo);
            for (Zona z : zonas) if (z.getCodigo().equalsIgnoreCase(codigo)) throw new IllegalArgumentException("Código de zona repetido");
            Zona zona = new Zona(codigo, texto(zNombre), texto(zUbicacion), numero(zArea));
            zona.registrar();
            zonas.add(zona);
            cboZona.addItem(zona);
            mostrar(zona.consultar());
        });
        boton(panel, "Modificar zona seleccionada", () -> {
            Zona zona = elegido(cboZona, "zona");
            zona.modificar(texto(zNombre), texto(zUbicacion), numero(zArea));
            mostrar("CU01: " + zona.consultar());
        });
        JTextField cCodigo = campo(panel, "CU02 - Código de campaña", "C01");
        JTextField cNombre = campo(panel, "Nombre de campaña", "Exploración inicial");
        JTextField cDias = campo(panel, "Duración estimada (días)", "30");
        boton(panel, "Registrar campaña", () -> {
            String codigo = texto(cCodigo);
            for (CampañaExploracion c : campañas) if (c.getCodigo().equalsIgnoreCase(codigo)) throw new IllegalArgumentException("Código de campaña repetido");
            int dias = entero(cDias);
            if (dias <= 0) throw new IllegalArgumentException("La duración debe ser positiva");
            Date inicio = new Date();
            CampañaExploracion campaña = new CampañaExploracion(codigo, texto(cNombre), inicio, new Date(inicio.getTime() + dias * 86400000L));
            campaña.registrar();
            campañas.add(campaña);
            cboCampaña.addItem(campaña);
            cboSeguimiento.addItem(campaña);
            mostrar(campaña.consultar());
        });
        boton(panel, "Modificar campaña seleccionada", () -> {
            CampañaExploracion campaña = elegido(cboCampaña, "campaña");
            int dias = entero(cDias);
            if (dias <= 0) throw new IllegalArgumentException("La duración debe ser positiva");
            campaña.modificar(texto(cNombre), new Date(System.currentTimeMillis() + dias * 86400000L), "EN_PROCESO");
            mostrar("CU02: " + campaña.consultar());
        });
        boton(panel, "Consultar zonas y campañas", () -> {
            StringBuilder texto = new StringBuilder("ZONAS\n");
            for (Zona z : zonas) texto.append(z.consultar()).append("\n");
            texto.append("\nCAMPAÑAS\n");
            for (CampañaExploracion c : campañas) texto.append(c.consultar()).append("\n");
            mostrar(texto.toString());
        });
        return panel;
    }

    private JPanel panelPersonal() {
        JPanel panel = formulario();
        JComboBox<String> rol = new JComboBox<>(new String[]{"Geologo", "Topografo", "TecnicoCampo", "GerenteExploraciones"});
        selector(panel, "Clase Trabajador / subclase", rol);
        JTextField nombre = campo(panel, "Nombre", "Ana");
        JTextField paterno = campo(panel, "Apellido paterno", "Pérez");
        JTextField materno = campo(panel, "Apellido materno", "Díaz");
        JComboBox<TipoDocumento> documento = new JComboBox<>(TipoDocumento.values());
        selector(panel, "Enumeración TipoDocumento", documento);
        JTextField numeroDoc = campo(panel, "Número de documento", "12345678");
        JLabel info1 = new JLabel("Especialidad / Equipo / Área");
        panel.add(info1);
        JTextField dato1 = new JTextField("Geología");
        panel.add(dato1);
        JLabel info2 = new JLabel("Colegiatura / Precisión / Experiencia / Acceso");
        panel.add(info2);
        JTextField dato2 = new JTextField("10");
        panel.add(dato2);
        rol.addActionListener(e -> {
            String seleccion = (String) rol.getSelectedItem();
            if ("Geologo".equals(seleccion)) { info1.setText("Especialidad"); info2.setText("Número de colegiatura"); }
            if ("Topografo".equals(seleccion)) { info1.setText("Equipo topográfico"); info2.setText("Precisión"); }
            if ("TecnicoCampo".equals(seleccion)) { info1.setText("Área técnica"); info2.setText("Años de experiencia"); }
            if ("GerenteExploraciones".equals(seleccion)) { info1.setText("Área responsable"); info2.setText("Nivel de acceso"); }
        });
        boton(panel, "Registrar trabajador", () -> {
            String n = texto(nombre), ap = texto(paterno), am = texto(materno), doc = texto(numeroDoc);
            TipoDocumento tipoDoc = elegido(documento, "tipo de documento");
            for (Trabajador t : trabajadores) if (t.getNumDocumento().equals(doc) && t.getTipoDocumento() == tipoDoc)
                throw new IllegalArgumentException("Documento ya registrado");
            Trabajador trabajador;
            switch ((String) rol.getSelectedItem()) {
                case "Geologo":
                    trabajador = new Geologo(n, ap, am, tipoDoc, doc, texto(dato1), texto(dato2));
                    break;
                case "Topografo":
                    trabajador = new Topografo(n, ap, am, tipoDoc, doc, texto(dato1), numero(dato2));
                    break;
                case "TecnicoCampo":
                    trabajador = new TecnicoCampo(n, ap, am, tipoDoc, doc, entero(dato2), texto(dato1));
                    break;
                default:
                    GerenteExploraciones gerente = new GerenteExploraciones(n, ap, am, tipoDoc, doc, texto(dato1), texto(dato2));
                    trabajador = gerente;
                    cboGerente.addItem(gerente);
            }
            trabajadores.add(trabajador);
            cboTrabajador.addItem(trabajador);
            cboPersonal.addItem(trabajador);
            mostrar(trabajador.mostrarDatos() + "\n" + trabajador.trabajar());
        });
        selector(panel, "CU13 - Trabajador registrado", cboPersonal);
        boton(panel, "Consultar datos de trabajador", () -> mostrar(elegido(cboPersonal, "trabajador").mostrarDatos()));
        return panel;
    }

    private JPanel panelActividades() {
        JPanel panel = formulario();
        selector(panel, "CU03 - Campaña", cboCampaña);
        selector(panel, "Zona asignada", cboZona);
        selector(panel, "CU04 - Responsable", cboTrabajador);
        JComboBox<String> tipo = new JComboBox<>(new String[]{"PerforacionDiamantina", "EstudioGeofisico", "MapeoGeologico", "MuestreoGeoquimico"});
        selector(panel, "CU05-CU08 - Tipo de actividad", tipo);
        JTextField codigo = campo(panel, "Código de actividad", "A01");
        JTextField costo = campo(panel, "Costo base de ejemplo (S/)", "100");
        JLabel l1 = new JLabel("Profundidad (m)");
        JLabel l2 = new JLabel("Diámetro (mm)");
        JLabel l3 = new JLabel("Descripción de testigos");
        panel.add(l1);
        JTextField a = new JTextField("12");
        panel.add(a);
        panel.add(l2);
        JTextField b = new JTextField("50");
        panel.add(b);
        panel.add(l3);
        JTextField c = new JTextField("Testigos de roca");
        panel.add(c);
        tipo.addActionListener(e -> {
            switch ((String) tipo.getSelectedItem()) {
                case "PerforacionDiamantina":
                    l1.setText("Profundidad (m)"); l2.setText("Diámetro (mm)"); l3.setText("Descripción de testigos"); break;
                case "EstudioGeofisico":
                    l1.setText("Tipo de medición"); l2.setText("Mediciones"); l3.setText("Anomalías"); break;
                case "MapeoGeologico":
                    l1.setText("Unidades geológicas"); l2.setText("Estructuras geológicas"); l3.setText("Características"); break;
                default:
                    l1.setText("Cantidad de muestras"); l2.setText("Tipo de muestra"); l3.setText("Resultado de análisis");
            }
        });
        boton(panel, "Registrar y asignar actividad", () -> {
            CampañaExploracion campaña = elegido(cboCampaña, "campaña");
            Zona zona = elegido(cboZona, "zona");
            Trabajador trabajador = elegido(cboTrabajador, "trabajador");
            String id = texto(codigo);
            for (CampañaExploracion actual : campañas) for (ActividadExploracion actividad : actual.consultarActividades())
                if (actividad.getCodigo().equalsIgnoreCase(id)) throw new IllegalArgumentException("Código de actividad repetido");
            double base = numero(costo);
            String clase = (String) tipo.getSelectedItem();
            if ("PerforacionDiamantina".equals(clase) && !(trabajador instanceof TecnicoCampo)
                || "EstudioGeofisico".equals(clase) && !(trabajador instanceof Topografo)
                || ("MapeoGeologico".equals(clase) || "MuestreoGeoquimico".equals(clase)) && !(trabajador instanceof Geologo))
                throw new IllegalArgumentException("Seleccione un profesional apropiado a la actividad");
            ActividadExploracion actividad;
            switch (clase) {
                case "PerforacionDiamantina":
                    actividad = new PerforacionDiamantina(id, new Date(), trabajador, zona, base, numero(a), numero(b), texto(c));
                    break;
                case "EstudioGeofisico":
                    actividad = new EstudioGeofisico(id, new Date(), trabajador, zona, base, texto(a), texto(b), texto(c));
                    break;
                case "MapeoGeologico":
                    actividad = new MapeoGeologico(id, new Date(), trabajador, zona, base, texto(a), texto(b), texto(c));
                    break;
                default:
                    actividad = new MuestreoGeoquimico(id, new Date(), trabajador, zona, base, entero(a), texto(b), texto(c));
            }
            actividad.registrar();
            campaña.agregarActividad(actividad);
            cboActividad.addItem(actividad);
            cboEstadoActividad.addItem(actividad);
            cboInforme.addItem(actividad);
            mostrar("CU03/CU04: Actividad registrada y responsable asignado.\n" + actividad.consultar());
        });
        selector(panel, "CU09 - Actividad a concluir", cboActividad);
        JTextField resultado = campo(panel, "Resultado registrado", "Trabajo realizado");
        boton(panel, "Registrar resultado y calcular costo", () -> {
            ActividadExploracion actividad = elegido(cboActividad, "actividad");
            actividad.registrarResultado(texto(resultado));
            String accion = actividad.getResponsable().trabajar();
            if (actividad.getResponsable() instanceof TecnicoCampo) accion = ((TecnicoCampo) actividad.getResponsable()).operarEquipo();
            if (actividad.getResponsable() instanceof Topografo) accion = ((Topografo) actividad.getResponsable()).realizarLevantamiento();
            if (actividad.getResponsable() instanceof Geologo) accion = ((Geologo) actividad.getResponsable()).analizarMuestras();
            mostrar(accion + "\nCU09: " + actividad.generarInforme());
        });
        return panel;
    }

    private JPanel panelSupervision() {
        JPanel panel = formulario();
        selector(panel, "CU10/CU12 - Campaña", cboSeguimiento);
        selector(panel, "GerenteExploraciones", cboGerente);
        boton(panel, "Supervisar campaña (CU10)", () -> {
            GerenteExploraciones g = elegido(cboGerente, "gerente");
            mostrar(g.supervisarCampaña(elegido(cboSeguimiento, "campaña")));
        });
        boton(panel, "Tomar decisión (CU12)", () -> {
            GerenteExploraciones g = elegido(cboGerente, "gerente");
            mostrar(g.tomarDecision(elegido(cboSeguimiento, "campaña")));
        });
        selector(panel, "CU11/CU15 - Actividad", cboInforme);
        boton(panel, "Generar y consultar informe", () -> {
            ActividadExploracion act = elegido(cboInforme, "actividad");
            GerenteExploraciones gerente = (GerenteExploraciones) cboGerente.getSelectedItem();
            mostrar(gerente == null ? act.generarInforme() : gerente.consultarResultadoInforme(act));
        });
        selector(panel, "CU14 - Actividad", cboEstadoActividad);
        JComboBox<EstadoActividad> estado = new JComboBox<>(EstadoActividad.values());
        selector(panel, "Enumeración EstadoActividad", estado);
        boton(panel, "Cambiar estado (CU14)", () -> {
            ActividadExploracion actividad = elegido(cboEstadoActividad, "actividad");
            actividad.cambiarEstado(elegido(estado, "estado"));
            mostrar(actividad.mostrarInformacion());
        });
        boton(panel, "Ver resumen de clases y costos", () -> {
            StringBuilder resumen = new StringBuilder("CampañaExploracion y clases ActividadExploracion\n");
            for (CampañaExploracion camp : campañas) {
                resumen.append(camp.consultar()).append("\n");
                for (ActividadExploracion act : camp.consultarActividades()) resumen.append("   ").append(act.mostrarInformacion()).append("\n");
            }
            mostrar(resumen.toString());
        });
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
