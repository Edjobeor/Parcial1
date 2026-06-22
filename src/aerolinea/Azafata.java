
package aerolinea;

public class Azafata extends Personas {
    
    private double altura;
    private int idiomas;

    public Azafata(double altura, int idiomas, int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        super(id, nombres, edad, vuelo, categoria);
        this.altura = altura;
        this.idiomas = idiomas;
         calcularCategoria();
    }

    

    @Override
    public String mostrar() {
    return "AZAFATA   -> ID: " + getId() + 
               " | Nombre: " + getNombres() + 
               " | Edad: " + getEdad() + " años" +
               " | Altura: " + altura + "m" + 
               " | Idiomas: " + idiomas + 
               " | Categoría: " + getCategoria() + 
               " | Vuelo asignado: " + getVuelo().getVuelo();
    }

    @Override
    public void calcularCategoria() {
    if (this.idiomas > 3) {
            this.categoria = "Tripulante Internacional";
        } else {
            this.categoria = "Tripulante Nacional";
        }
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(int idiomas) {
        this.idiomas = idiomas;
    }
    

}
