package model;

public class Libro {

	// Atributos
	private String titulo;
	private Autor autor;
	private String genero;
	private String descripcion;

	// Constructor
	public Libro(String tituloLibro, Autor autorLibro, 
			String generoLibro, String descripcionLibro) {
		this.titulo = tituloLibro;
		this.autor = autorLibro;
		this.genero = generoLibro;
		this.descripcion = descripcionLibro;
	}

	// Métodos GET Y SET
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
