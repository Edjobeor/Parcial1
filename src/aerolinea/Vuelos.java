
package aerolinea;

public class Vuelos {
    private String nombreAereolinea;
    private String vuelo;
    private String destino;

    public Vuelos(String nombreAereolinea, String vuelo, String destino) {
        this.nombreAereolinea = nombreAereolinea;
        this.vuelo = vuelo;
        this.destino = destino;
    }

    public String getNombreAereolinea() {
        return nombreAereolinea;
    }

    public void setNombreAereolinea(String nombreAereolinea) {
        this.nombreAereolinea = nombreAereolinea;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
