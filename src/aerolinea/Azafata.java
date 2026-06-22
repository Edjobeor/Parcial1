
package aerolinea;

public class Azafata extends Personas {
    
    private double altura;
    private int idiomas;

    public Azafata(int id, String nombres, int edad, Vuelos vuelo, double altura, int idiomas) {
    super(id, nombres, edad, vuelo, "Sin Categoría");
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
