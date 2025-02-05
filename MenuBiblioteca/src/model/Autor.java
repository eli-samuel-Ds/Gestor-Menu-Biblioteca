package model;

import java.util.ArrayList;
import java.util.List;

public class Autor {

    // Atributos
    private String nombre;
    private String apellido;
    private List<Libro> listaLibros;

    // Constructor
    public Autor(String nombreAutor, String apellidoAutor) {
        this.nombre = nombreAutor;
        this.apellido = apellidoAutor;
        this.listaLibros = new ArrayList<>();
    }

    // Métodos GET Y SET
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Autor)) return false;
        Autor otroAutor = (Autor) obj;
        return nombre.equalsIgnoreCase(otroAutor.nombre) &&
                apellido.equalsIgnoreCase(otroAutor.apellido);
    }
}
