
package Vista;

import aerolinea.Pasajero;
import aerolinea.Vuelos;
import implementacion.Principal;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class VentanaAeropuerto extends JFrame {
    
    private JTextArea areaConsola;
    private JButton btnCargar, btnMostrar, btnDestino, btnPiloto, btnAgregarManual;
    
    private JTextField txtId, txtNombre, txtEdad, txtAsiento, txtPrecio;
    
    private Principal controlador;

    public VentanaAeropuerto() {
        controlador = new Principal();
        
        setTitle("Aeropuerto UNET - Modo Interactivo Completo");
        setSize(850, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperiorMaster = new JPanel(new BorderLayout(5, 5));
        
        btnCargar = new JButton("1. Cargar Base de Datos Automática");
        btnCargar.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperiorMaster.add(btnCargar, BorderLayout.NORTH);
        
        JPanel panelFormulario = new JPanel(new GridLayout(2, 6, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Pasajero Manualmente"));
        
        panelFormulario.add(new JLabel("ID / Cédula:", JLabel.RIGHT));
        txtId = new JTextField(10); panelFormulario.add(txtId);
        
        panelFormulario.add(new JLabel("Nombre:", JLabel.RIGHT));
        txtNombre = new JTextField(10); panelFormulario.add(txtNombre);
        
        panelFormulario.add(new JLabel("Edad:", JLabel.RIGHT));
        txtEdad = new JTextField(10); panelFormulario.add(txtEdad);
        
        panelFormulario.add(new JLabel("Asiento:", JLabel.RIGHT));
        txtAsiento = new JTextField(10); panelFormulario.add(txtAsiento);
        
        panelFormulario.add(new JLabel("Precio Pasaje:", JLabel.RIGHT));
        txtPrecio = new JTextField(10); panelFormulario.add(txtPrecio);
        
        btnAgregarManual = new JButton("Registrar");
        panelFormulario.add(btnAgregarManual);
        panelFormulario.add(new JLabel("")); // Celda vacía de relleno para cuadrar el GridLayout (2x6=12)
        
        panelSuperiorMaster.add(panelFormulario, BorderLayout.CENTER);
        add(panelSuperiorMaster, BorderLayout.NORTH);


        areaConsola = new JTextArea();
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaConsola.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaConsola);
        add(scrollPane, BorderLayout.CENTER);

  
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
                int id = Integer.parseInt(txtId.getText().trim());
                String nombre = txtNombre.getText().trim();
                int edad = Integer.parseInt(txtEdad.getText().trim());
                String asiento = txtAsiento.getText().trim();
                int precio = Integer.parseInt(txtPrecio.getText().trim());
                
                
                if(nombre.isEmpty() || asiento.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos de texto.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
               
                Vuelos vueloPorDefecto;
                if (!controlador.getViajes().isEmpty()) {
                    vueloPorDefecto = controlador.getViajes().get(0);
                } else {
                    vueloPorDefecto = new Vuelos("UNET Airlines", "UNET100", "San Cristóbal");
                    controlador.getViajes().add(vueloPorDefecto);
                }
                
                Pasajero nuevoPasajero = new Pasajero(id, nombre, edad, vueloPorDefecto, asiento, precio);
                
                controlador.getViajeros().add(nuevoPasajero);
                
                txtId.setText(""); txtNombre.setText(""); txtEdad.setText(""); txtAsiento.setText(""); txtPrecio.setText("");
                
                areaConsola.setText("¡Felicidades! Agregaste a '" + nombre + "' manualmente a la RAM del sistema.\n"
                                   + "Presiona el botón 'Ver Pasajeros y Tripulación' abajo para verlo en la lista.");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, llena los campos con números válidos (ID, Edad, Precio).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnMostrar.addActionListener(e -> areaConsola.setText(controlador.mostrarCategoria()));
        btnDestino.addActionListener(e -> areaConsola.setText(controlador.destinoFavorito()));
        btnPiloto.addActionListener(e -> areaConsola.setText(controlador.mejorPiloto()));
    }
}
