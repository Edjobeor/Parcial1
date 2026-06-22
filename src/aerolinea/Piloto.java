
package aerolinea;

public class Piloto extends Personas {
    
    private int horas;

    public Piloto(int id, String nombres, int edad, Vuelos vuelo, int horas) {
    super(id, nombres, edad, vuelo, "Sin Categoría");
    this.horas = horas; 
    calcularCategoria();
    }
 
    @Override
    public String mostrar() {
    return "PILOTO    -> ID: " + getId() + 
               " | Nombre: " + getNombres() + 
               " | Edad: " + getEdad() + " años" +
               " | Horas de Vuelo: " + horas + " hrs" + 
               " | Categoría: " + getCategoria() + 
               " | Vuelo asignado: " + getVuelo().getVuelo();
    }

    @Override
    public void calcularCategoria() {
    if (this.horas > 1500) {
            this.categoria = "Comandante Senior";
        } else {
            this.categoria = "Primer Oficial";
        }
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    
    
    
}
