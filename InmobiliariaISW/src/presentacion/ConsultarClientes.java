package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import logica.Cliente;
import logica.Controlador;
import logica.Inmueble;

import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JLabel;


public class ConsultarClientes extends JDialog {


	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private static Controlador control;
	private JTable tableCliente;
	int filas=0;
	private JTextField textFieldCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String... args) {
		try {
			ConsultarClientes dialog = new ConsultarClientes( control);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Inicializaci�n de los objetos necesarios de la capa de negocio
	private void initDominio() {
		try{
			control = Controlador.dameControlador();
		}catch (Exception e){
			Logger log = Logger.getLogger(ConsultarClientes.class.getName());
        	if (log.isLoggable(Level.FINE))
        		log.fine(e.getMessage());
		}
	}


	/**
	 * Create the dialog.
	 */
	public ConsultarClientes(Controlador control1) {
		setBackground(new Color(255, 255, 255));

		//M�todo que inicializa el controlador.
		initDominio();

		setBounds(100, 100, 900, 522);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Consultar Clientes por Inmueble");
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setLocation(10, 78);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


			scrollPane.setSize(864,362);


			contentPanel.add(scrollPane);
			{
				tableCliente = new JTable(new DefaultTableModel());
				tableCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
				scrollPane.setViewportView(tableCliente);
			}
		}
		{
			textFieldCliente = new JTextField();
			textFieldCliente.setBounds(191, 30, 127, 20);
			contentPanel.add(textFieldCliente);
			textFieldCliente.setColumns(10);
		}
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//Modelo por defecto que a�adiremos a nuestra tabla.
					DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
					
					if (tableCliente.getColumnCount()>0) {
		                model.setColumnCount(0);
		                model.setRowCount(0);
		            }
					
					cargaClientes();
				}
			});
			btnBuscar.setBounds(328, 29, 89, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JLabel lblNewLabel = new JLabel("Introduce el Nif del cliente:");
			lblNewLabel.setBounds(10, 33, 154, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblTablaDeLos = new JLabel("Consultar Clientes");
			lblTablaDeLos.setBounds(598, 11, 132, 14);
			contentPanel.add(lblTablaDeLos);
		}
		{
			JButton btnNewButton = new JButton("Clientes");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConsultarTodosClientes cur = new ConsultarTodosClientes(control);
					cur.setModal(true);
					cur.cargaTodosClientes();
					cur.setVisible(true);
				}
			});
			btnNewButton.setBounds(598, 29, 89, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Cierra la ventana de muestra Clientes.
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	//Se invoca desde fuera de �sta clase.
	public void cargaClientes() {
		try{
			ArrayList<Inmueble> listaInmuebles = (ArrayList<Inmueble>) control.encontrarInmuebles();

			//Cliente que usaremos para buscar sus Inmuebles.
			String cli = textFieldCliente.getText();

			Cliente cliente = control.encontrarClientePorCod(cli);


			//Modelo por defecto que a�adiremos a nuestra tabla.
			DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();

			//A�adimos las columnas a la tabla.
			model.addColumn("DNI");
			model.addColumn("Nombre");
			model.addColumn("Apellidos");
			model.addColumn("ID-Inmueble");
			model.addColumn("Direccion");
			model.addColumn("Localidad");
			model.addColumn("Fecha de Alta");
			model.addColumn("Superficie");
			model.addColumn("Venta/Alquiler");


			//Incluye Inmuebles por Cliente.
			for(int i=0; i<listaInmuebles.size(); i++) {
				
					if(listaInmuebles.get(i).getCliente().getNif().trim().equals(cliente.getNif())) {
					
						Object nuevo[]= {cliente.getNif(), cliente.getNombre(), cliente.getApellidos(), listaInmuebles.get(i).getCodId(),
								listaInmuebles.get(i).getCalle(), listaInmuebles.get(i).getLocalidad(), listaInmuebles.get(i).getFechaAlta(),
								listaInmuebles.get(i).getSuperficieTotal(), listaInmuebles.get(i).getVentaAlquiler(),
						};

						model.addRow(nuevo);

					}
				
			}



		}catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR AL MOSTRAR",JOptionPane.ERROR_MESSAGE);
		}
	}
}//Fin de la clase ConsultarAsesores.

