package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Autor;
import model.Libro;
import services.GestionBiblioteca;

public class formularioBiblioteca {

	private JFrame GestorBiblioteca;
	private GestionBiblioteca gestionBiblioteca;
	private JTable tableLibros;
	private DefaultTableModel tableModel;
	private JPopupMenu popupMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				formularioBiblioteca window = new formularioBiblioteca();
				window.GestorBiblioteca.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public formularioBiblioteca() {
		gestionBiblioteca = new GestionBiblioteca();
		initialize();
		actualizarTabla();   
	}

	private void initialize() {
		GestorBiblioteca = new JFrame();
		GestorBiblioteca.setTitle("Gestor Biblioteca");
		GestorBiblioteca.setBounds(100, 100, 600, 500);
		GestorBiblioteca.setLocationRelativeTo(null);
		GestorBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		GestorBiblioteca.getContentPane().add(panel);

		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(27, 10, 70, 13);
		JTextField txtTitulo = new JTextField();
		txtTitulo.setBounds(27, 23, 251, 19);
		JLabel lblAutorNombre = new JLabel("Nombre del Autor:");
		lblAutorNombre.setBounds(304, 10, 157, 13);
		JTextField txtAutorNombre = new JTextField();
		txtAutorNombre.setBounds(304, 23, 263, 19);
		JLabel lblAutorApellido = new JLabel("Apellido del Autor:");
		lblAutorApellido.setBounds(27, 52, 140, 13);
		JTextField txtAutorApellido = new JTextField();
		txtAutorApellido.setBounds(27, 65, 251, 19);
		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setBounds(304, 52, 115, 13);
		JTextField txtGenero = new JTextField();
		txtGenero.setBounds(304, 65, 264, 19);
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(27, 94, 140, 13);
		JTextField txtDescripcion = new JTextField();
		txtDescripcion.setBounds(27, 107, 251, 19);

		JButton btnAgregar = new JButton("Agregar Lb");
		btnAgregar.setBounds(66, 147, 112, 21);
		JButton btnEditar = new JButton("Editar Lb");
		btnEditar.setBounds(188, 147, 97, 21);
		JButton btnEliminar = new JButton("Eliminar Lb");
		btnEliminar.setBounds(300, 147, 113, 21);
		JButton btnBuscar = new JButton("Buscar Lb");
		btnBuscar.setBounds(424, 147, 106, 21);
		panel.setLayout(null);

		panel.add(lblTitulo);
		panel.add(txtTitulo);
		panel.add(lblAutorNombre);
		panel.add(txtAutorNombre);
		panel.add(lblAutorApellido);
		panel.add(txtAutorApellido);
		panel.add(lblGenero);
		panel.add(txtGenero);
		panel.add(lblDescripcion);
		panel.add(txtDescripcion);
		panel.add(btnAgregar);
		panel.add(btnEditar);
		panel.add(btnEliminar);
		panel.add(btnBuscar);


		tableModel = new DefaultTableModel(new Object[]{"Título", "Autor", 
				"Género", "Descripción"}, 0);
		tableLibros = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(tableLibros);
		scrollPane.setBounds(0, 178, 586, 275);
		panel.add(scrollPane);

		popupMenu = new JPopupMenu();
		JMenuItem eliminarItem = new JMenuItem("Eliminar libro");
		popupMenu.add(eliminarItem);

		eliminarItem.addActionListener(e -> {
			int selectedRow = tableLibros.getSelectedRow();
			if (selectedRow >= 0) {
				String titulo = (String) tableModel.getValueAt(selectedRow, 0);
				if (gestionBiblioteca.eliminarLibro(titulo)) {
					actualizarTabla();
					JOptionPane.showMessageDialog(GestorBiblioteca, 
							"Libro eliminado exitosamente.");
				} else {
					JOptionPane.showMessageDialog(GestorBiblioteca, 
							"Error al eliminar el libro.");
				}
			}
		});

		tableLibros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger() && tableLibros.getSelectedRowCount() == 1) {
					int r = tableLibros.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tableLibros.getRowCount()) {
						tableLibros.setRowSelectionInterval(r, r);
					}
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		btnAgregar.addActionListener(e -> {
			Autor autor = new Autor(txtAutorNombre.getText(), txtAutorApellido.getText());
			Libro nuevoLibro = new Libro(txtTitulo.getText(), autor, txtGenero.getText(), 
					txtDescripcion.getText());
			gestionBiblioteca.agregarLibro(nuevoLibro);
			actualizarTabla();
			JOptionPane.showMessageDialog(GestorBiblioteca, "Libro agregado exitosamente.");

			txtTitulo.setText("");
			txtAutorNombre.setText("");
			txtAutorApellido.setText("");
			txtGenero.setText("");
			txtDescripcion.setText("");
		});

		btnEditar.addActionListener(e -> {
			Autor autor = new Autor(txtAutorNombre.getText(), txtAutorApellido.getText());
			Libro libroEditado = new Libro(txtTitulo.getText(), autor, txtGenero.getText(), 
					txtDescripcion.getText());
			if (gestionBiblioteca.editarLibro(txtTitulo.getText(), libroEditado)) {
				actualizarTabla();
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro editado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro no encontrado.");
			}
		});

		btnEliminar.addActionListener(e -> {
			if (gestionBiblioteca.eliminarLibro(txtTitulo.getText())) {
				actualizarTabla();
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro eliminado exitosamente.");
			} else {
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro no encontrado.");
			}
		});

		btnBuscar.addActionListener(e -> {
			Libro libro = gestionBiblioteca.buscarLibro(txtTitulo.getText());
			if (libro != null) {
				txtAutorNombre.setText(libro.getAutor().getNombre());
				txtAutorApellido.setText(libro.getAutor().getApellido());
				txtGenero.setText(libro.getGenero());
				txtDescripcion.setText(libro.getDescripcion());
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro encontrado.");
			} else {
				JOptionPane.showMessageDialog(GestorBiblioteca, "Libro no encontrado.");
			}
		});
	}

	private void actualizarTabla() {
		tableModel.setRowCount(0);

		for (Libro libro : gestionBiblioteca.getLibros()) {
			Object[] rowData = {libro.getTitulo(), libro.getAutor().getNombre() 
					+ " " + libro.getAutor().getApellido(),
					libro.getGenero(), libro.getDescripcion()};
			tableModel.addRow(rowData);
		}
	}
}
