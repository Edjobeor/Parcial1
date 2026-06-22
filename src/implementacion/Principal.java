
package implementacion;

import aerolinea.Azafata;
import aerolinea.Pasajero;
import aerolinea.Personas;
import aerolinea.Piloto;
import aerolinea.Vuelos;
import data.Datos;
import java.util.ArrayList;

public class Principal implements Datos {
    private ArrayList<Vuelos> viajes;
    private ArrayList<Personas> viajeros;

    //Constructor
    public Principal(ArrayList<Vuelos> viajes, ArrayList<Personas> viajeros) {
        this.viajes = viajes;
        this.viajeros = viajeros;
    }
    
    public void cargarDatos() {
        // Limpiar listas para evitar duplicación si se presiona el botón dos veces
        viajes.clear();
        viajeros.clear();

        // 1. PROCESAR Y CARGAR LOS VUELOS
        for (String lineaVuelo : datos_vuelos) {
            String[] tokens = lineaVuelo.split(";");
            String aerolinea = tokens[0];
            String numeroVuelo = tokens[1];
            String destino = tokens[2];
            
            Vuelos v = new Vuelos(aerolinea, numeroVuelo, destino);
            viajes.add(v);
        }

        // 2. PROCESAR Y CARGAR LOS VIAJEROS (PASAJEROS, AZAFATAS Y PILOTOS)
        for (String lineaPersona : datos_persona) {
            String[] tokens = lineaPersona.split(";");
            int tipo = Integer.parseInt(tokens[1]); // El primer token identifica el rol
            int id = Integer.parseInt(tokens[1]);
            String nombre = tokens[2];
            int edad = Integer.parseInt(tokens[3]);
            String numVuelo = tokens[4];

            // Buscar el objeto Vuelos correspondiente en base a su código identificador
            Vuelos vueloAsignado = buscarVuelo(numVuelo);

            // Determinar la subclase exacta a instanciar usando condicionales por tipo
            if (tipo == 1) { // Es Pasajero
                // Extraemos los datos específicos del Pasajero usando los índices del split
                String numAsiento = tokens[5];                  // Índice 5: Asiento (ej: "12A")
                int valorPasaje = Integer.parseInt(tokens[6]);  // Índice 6: Precio del pasaje (ej: 1200)
                
                // Agregamos el objeto a la lista polimórfica
                viajeros.add(new Pasajero(id, nombre, edad, vueloAsignado, numAsiento, valorPasaje));
            } 
            else if (tipo == 2) { // Es Azafata
                // Extraemos los datos específicos de la Azafata
                double altura = Double.parseDouble(tokens[5]);  // Índice 5: Altura (ej: 1.68)
                int idiomas = Integer.parseInt(tokens[6]);      // Índice 6: Cantidad de idiomas (ej: 4)
                
                viajeros.add(new Azafata(id, nombre, edad, vueloAsignado, altura, idiomas));
            } 
            else if (tipo == 3) { // Es Piloto
                // Extraemos el dato específico del Piloto
                int horas = Integer.parseInt(tokens[5]);        // Índice 5: Horas operacionales (ej: 2200)
                
                viajeros.add(new Piloto(id, nombre, edad, vueloAsignado, horas));
            }
        }
    }

    /**
     * Método auxiliar de búsqueda directa para enlazar los códigos de vuelo con sus objetos.
     */
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
            sb.append(p.mostrar()).append("\n"); // Invoca el comportamiento personalizado en caliente
        }
        return sb.toString();
    }

    public String destinoFavorito() {
        if (viajes.isEmpty()) return "Debe cargar los datos del sistema primero.";

        String destinoGanador = "";
        int maxPasajeros = -1;
        int recaudacionGanador = 0;

        // Evaluar cada destino registrado de forma individual
        for (Vuelos v : viajes) {
            String destinoActual = v.getDestino();
            int contadorPasajeros = 0;
            int acumuladorPasajes = 0;

            // Recorrer los viajeros buscando coincidencias con el destino evaluado
            for (Personas p : viajeros) {
                if (p instanceof Pasajero && p.getVuelo().getDestino().equalsIgnoreCase(destinoActual)) {
                    Pasajero pas = (Pasajero) p;
                    contadorPasajeros++;
                    acumuladorPasajes += pas.getValorPasaje();
                }
            }

            // Validar si el destino analizado supera al máximo anterior conocido
            if (contadorPasajeros > maxPasajeros) {
                maxPasajeros = contadorPasajeros;
                destinoGanador = destinoActual;
                recaudacionGanador = acumuladorPasajes;
            }
        }

        return " Destino con mayor afluencia comercial: " + destinoGanador + "\n" +
               " Cantidad total de pasajeros movilizados: " + maxPasajeros + " pasajeros.\n" +
               " Monto total recaudado por concepto de boletaje: $" + recaudacionGanador;
    }

    public String mejorPiloto() {
        if (viajeros.isEmpty()) return "Debe cargar los datos del sistema primero.";

        Piloto lider = null;
        int maxHoras = -1;

        for (Personas p : viajeros) {
            if (p instanceof Piloto) { // Filtro de seguridad RTTI
                Piloto pi = (Piloto) p; // Downcasting seguro
                if (pi.getHoras() > maxHoras) {
                    maxHoras = pi.getHoras();
                    lider = pi;
                }
            }
        }

        if (lider != null) {
            return "️ Piloto con mayor experiencia de vuelo acumulada:\n" +
                   " Nombre: " + lider.getNombres() + "\n" +
                   " Horas de Vuelo Registradas: " + maxHoras + " horas operacionales.\n" +
                   " Rango Asignado: " + lider.getCategoria();
        }
        return "No se encontraron pilotos en los registros del aeropuerto.";
    }
    
    
    
    //Getters y Setters

    public ArrayList<Vuelos> getViajes() {
        return viajes;
    }

    public void setViajes(ArrayList<Vuelos> viajes) {
        this.viajes = viajes;
    }

    public ArrayList<Personas> getViajeros() {
        return viajeros;
    }

    public void setViajeros(ArrayList<Personas> viajeros) {
        this.viajeros = viajeros;
    }
    
    
    
    
}
