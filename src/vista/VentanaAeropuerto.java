
package Vista;

import aerolinea.Pasajero;
import aerolinea.Vuelos;
import implementacion.Principal;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class VentanaAeropuerto extends JFrame {
    
    private JTextArea areaConsola;
    private JButton btnCargar, btnMostrar, btnDestino, btnPiloto, btnAgregarManual;
    
    // Cajas de texto para que TÚ ingreses datos manualmente
    private JTextField txtId, txtNombre, txtEdad, txtAsiento, txtPrecio;
    
    private Principal controlador;

    public VentanaAeropuerto() {
        controlador = new Principal();
        
        setTitle("Aeropuerto UNET - Modo Interactivo Completo");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. PANEL SUPERIOR: Carga automática + FORMULARIO MANUAL
        JPanel panelSuperiorMaster = new JPanel(new GridLayout(2, 1, 5, 5));
        
        btnCargar = new JButton("1. Cargar Base de Datos Automática (Examen)");
        btnCargar.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperiorMaster.add(btnCargar);
        
        // Formulario para meter "cositas" de Pasajeros
        JPanel panelFormulario = new JPanel(new GridLayout(2, 6, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Pasajero Manualmente"));
        
        panelFormulario.add(new JLabel("ID / Cédula:"));
        txtId = new JTextField(); panelFormulario.add(txtId);
        
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(); panelFormulario.add(txtNombre);
        
        panelFormulario.add(new JLabel("Edad:"));
        txtEdad = new JTextField(); panelFormulario.add(txtEdad);
        
        panelFormulario.add(new JLabel("Asiento:"));
        txtAsiento = new JTextField(); panelFormulario.add(txtAsiento);
        
        panelFormulario.add(new JLabel("Precio Pasaje:"));
        txtPrecio = new JTextField(); panelFormulario.add(txtPrecio);
        
        btnAgregarManual = new JButton("Registrar");
        panelFormulario.add(btnAgregarManual);
        
        panelSuperiorMaster.add(panelFormulario);
        add(panelSuperiorMaster, BorderLayout.NORTH);

        // 2. MONITOR CENTRAL
        areaConsola = new JTextArea();
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaConsola.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaConsola);
        add(scrollPane, BorderLayout.CENTER);

        // 3. PANEL INFERIOR (Consultas)
        JPanel panelInferior = new JPanel(new GridLayout(1, 3, 10, 10));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        btnMostrar = new JButton("Ver Pasajeros y Tripulación");
        btnDestino = new JButton("Destino Favorito");
        btnPiloto = new JButton("Mejor Piloto");
        panelInferior.add(btnMostrar); panelInferior.add(btnDestino); panelInferior.add(btnPiloto);
        add(panelInferior, BorderLayout.SOUTH);

        configurarControlDeEventos();
    }

    private void configurarControlDeEventos() {

        btnCargar.addActionListener(e -> {
            controlador.cargarDatos();
            areaConsola.setText("[SISTEMA INICIALIZADO] Datos del examen cargados en la RAM.");
        });

        btnAgregarManual.addActionListener(e -> {
            try {
                // 1. Capturamos lo que tú escribiste en las cajitas de la pantalla
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                String asiento = txtAsiento.getText();
                int precio = Integer.parseInt(txtPrecio.getText());
                
                // Asignamos un vuelo por defecto (ej. el primero de la lista)
                Vuelos vueloPorDefecto = new Vuelos("UNET Airlines", "UNET100", "San Cristóbal");
                
                // 2. Creamos el nuevo objeto Pasajero en caliente con tus datos
                Pasajero nuevoPasajero = new Pasajero(id, nombre, edad, vueloPorDefecto, asiento, precio);
                
                // 3. ¡Lo metemos al ArrayList usando .add() mediante un nuevo método que agregaremos!
                controlador.getViajeros().add(nuevoPasajero);
                
                // 4. Limpiamos las cajitas para que metas otro
                txtId.setText(""); txtNombre.setText(""); txtEdad.setText(""); txtAsiento.setText(""); txtPrecio.setText("");
                
                areaConsola.setText("¡Felicidades! Agregaste a '" + nombre + "' manualmente a la RAM del sistema.\n"
                                   + "Presiona el botón 'Ver Pasajeros y Tripulación' abajo para verlo en la lista.");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, llena los campos con números válidos (ID, Edad, Precio).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botones analíticos habituales
        btnMostrar.addActionListener(e -> areaConsola.setText(controlador.mostrarCategoria()));
        btnDestino.addActionListener(e -> areaConsola.setText(controlador.destinoFavorito()));
        btnPiloto.addActionListener(e -> areaConsola.setText(controlador.mejorPiloto()));
    }
}
