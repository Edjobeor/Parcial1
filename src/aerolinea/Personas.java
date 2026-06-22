
package aerolinea;

public abstract class Personas {
    private int id;
    private String nombres;
    private int  edad;
    private Vuelos vuelo;
    protected String categoria;

    //Constructor
    public Personas(int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        this.id = id;
        this.nombres = nombres;
        this.edad = edad;
        this.vuelo = vuelo;
        this.categoria = categoria;
    }
    
    //Metodos Abstractos
    public abstract String mostrar();
    
    public abstract void calcularCategoria();

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Vuelos getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelos vuelo) {
        this.vuelo = vuelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}
