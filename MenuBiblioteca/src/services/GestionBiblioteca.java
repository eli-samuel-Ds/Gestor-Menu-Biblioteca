package services;

import java.util.ArrayList;
import java.util.List;
import model.Autor;
import model.Libro;

public class GestionBiblioteca {

    // Atributos
    private List<Autor> listaAutoresCreados;
    private List<Libro> listaLibroCreados;

    // Constructor
    public GestionBiblioteca() {
        this.listaAutoresCreados = new ArrayList<>();
        this.listaLibroCreados = new ArrayList<>();
    }

    // Métodos
    public void agregarLibro(Libro nuevoLibro) {
        listaLibroCreados.add(nuevoLibro);
        Autor autorDelLibro = nuevoLibro.getAutor();

        if (!listaAutoresCreados.contains(autorDelLibro)) {
            listaAutoresCreados.add(autorDelLibro);
        }
    }

    public boolean editarLibro(String titulo, Libro libroEditado) {
        for (int i = 0; i < listaLibroCreados.size(); i++) {
            if (listaLibroCreados.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                listaLibroCreados.set(i, libroEditado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarLibro(String titulo) {
        return listaLibroCreados.removeIf(libro -> libro.getTitulo().equalsIgnoreCase(titulo));
    }

    public Libro buscarLibro(String titulo) {
        for (Libro libro : listaLibroCreados) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public List<Libro> getLibros() {
        return listaLibroCreados;
    }
}
