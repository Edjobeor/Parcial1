
package implementacion;

import aerolinea.*;
import data.Datos;
import java.util.ArrayList;

public class Principal implements Datos {
    private ArrayList<Vuelos> viajes;
    private ArrayList<Personas> viajeros;
    
    public Principal() {
        this.viajes = new ArrayList<Vuelos>();
        this.viajeros = new ArrayList<Personas>();
    }
    
    public void cargarDatos() {
        viajes.clear();
        viajeros.clear(); 

        for (String lineaVuelo : datos_vuelos) { 
            String[] tokens = lineaVuelo.split(";");
            String aerolinea = tokens[0]; 
            String numeroVuelo = tokens[1]; 
            String destino = tokens[2]; 
            
            Vuelos v = new Vuelos(aerolinea, numeroVuelo, destino);
            viajes.add(v);
        }

        for (String lineaPersona : datos_persona) { 
            String[] tokens = lineaPersona.split(";");
            int tipo = Integer.parseInt(tokens[0]);  
            int id = Integer.parseInt(tokens[1]);    
            String nombre = tokens[2];              
            int edad = Integer.parseInt(tokens[3]);  
            String numVuelo = tokens[4];              
            
            Vuelos vueloAsignado = buscarVuelo(numVuelo);
            
            if (tipo == 1) { // Pasajero
                String numAsiento = tokens[5];
                int valorPasaje = Integer.parseInt(tokens[6]);
                viajeros.add(new Pasajero(id, nombre, edad, vueloAsignado, numAsiento, valorPasaje));
            } 
            else if (tipo == 2) { // Azafata
                double altura = Double.parseDouble(tokens[5]);
                int idiomas = Integer.parseInt(tokens[6]); 
                viajeros.add(new Azafata(id, nombre, edad, vueloAsignado, altura, idiomas));
            } 
            else if (tipo == 3) { // Piloto
                int horas = Integer.parseInt(tokens[5]);
                viajeros.add(new Piloto(id, nombre, edad, vueloAsignado, horas)); 
            }
        }
    }

    private Vuelos buscarVuelo(String numeroVuelo) {
        for (Vuelos v : viajes) {
            if (v.getVuelo().equalsIgnoreCase(numeroVuelo)) {
                return v;
            }
        }
        return null;
    }

    public String mostrarCategoria() {
        if (viajeros.isEmpty()) return "Debe cargar los datos del sistema primero.";
        StringBuilder sb = new StringBuilder();
        for (Personas p : viajeros) {
            sb.append(p.mostrar()).append("\n");
        }
        return sb.toString();
    }

    public String destinoFavorito() {
        if (viajes.isEmpty()) return "Debe cargar los datos del sistema primero.";
        String destinoGanador = "";
        int maxPasajeros = -1;
        int recaudacionGanador = 0;

        for (Vuelos v : viajes) {
            String destinoActual = v.getDestino();
            int contadorPasajeros = 0;
            int acumuladorPasajes = 0;

            for (Personas p : viajeros) {
                if (p instanceof Pasajero && p.getVuelo().getDestino().equalsIgnoreCase(destinoActual)) {
                    Pasajero pas = (Pasajero) p;
                    contadorPasajeros++;
                    acumuladorPasajes += pas.getValorPasaje();
                }
            }

            if (contadorPasajeros > maxPasajeros) {
                maxPasajeros = contadorPasajeros;
                destinoGanador = destinoActual;
                recaudacionGanador = acumuladorPasajes;
            }
        }

        return "Destino con mayor afluencia comercial: " + destinoGanador + "\n" +
               "Cantidad total de pasajeros movilizados: " + maxPasajeros + " pasajeros.\n" +
               "Monto total recaudado por concepto de boletaje: $" + recaudacionGanador;
    }

    public String mejorPiloto() {
        if (viajeros.isEmpty()) return "Debe cargar los datos del sistema primero.";
        Piloto lider = null;
        int maxHoras = -1;

        for (Personas p : viajeros) {
            if (p instanceof Piloto) {
                Piloto pi = (Piloto) p;
                if (pi.getHoras() > maxHoras) {
                    maxHoras = pi.getHoras();
                    lider = pi;
                }
            }
        }

        if (lider != null) {
            return "Piloto con mayor experiencia de vuelo acumulada:\n" +
                   " Nombre: " + lider.getNombres() + "\n" +
                   " Horas de Vuelo Registradas: " + maxHoras + " horas operacionales.\n" +
                   " Rango Asignado: " + lider.getCategoria();
        }
        return "No se encontraron pilotos en los registros del aeropuerto.";
    }
    
    public ArrayList<Vuelos> getViajes() {
        return viajes;
    }

    public ArrayList<Personas> getViajeros() {
        return viajeros;
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Vista.VentanaAeropuerto().setVisible(true);
        });
    }
}
