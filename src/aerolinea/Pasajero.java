
package aerolinea;

public class Pasajero extends Personas {

    private String numAsiento;
    private int valorPasaje;

    public Pasajero(int id, String nombres, int edad, Vuelos vuelo, String numAsiento, int valorPasaje) {
    super(id, nombres, edad, vuelo, "Sin Categoría"); // Pasamos un estado inicial al padre 
    this.numAsiento = numAsiento; 
    this.valorPasaje = valorPasaje; 
    calcularCategoria(); 
    }

    public int getValorPasaje() {
        return valorPasaje;
    }
    
    @Override
    public String mostrar(){   
        return "PASAJERO -> ID: " + getId() + 
               " | Nombre: " + getNombres() + 
               " | Edad: " + getEdad() + " años" +
               " | Asiento: " + numAsiento + 
               " | Costo Pasaje: $" + valorPasaje + 
               " | Categoría: " + getCategoria() + 
               " | Vuelo: " + getVuelo().getVuelo() + " con destino a " + getVuelo().getDestino();
    }


    @Override
    public void calcularCategoria() {
    if (this.valorPasaje >= 1000) {
            this.categoria = "Ejecutiva (VIP)";
        } else {
            this.categoria = "Turista Estandar";
        }
    }

    public String getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(String numAsiento) {
        this.numAsiento = numAsiento;
    }
    
    
    
}
