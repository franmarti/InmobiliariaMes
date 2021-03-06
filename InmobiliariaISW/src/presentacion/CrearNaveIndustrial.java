package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import excepciones.LogicaExcepcion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Asesor;
import logica.Cliente;
import logica.Controlador;
import logica.NaveIndustrial;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;

public class CrearNaveIndustrial extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCodigo;
	private JTextField textFieldDireccion;
	private JTextField textFieldLocalidad;
	private JTextField textFieldFecha;
	private JTextField textFieldSuperficie;
	private JTextField textFieldNumPuertas;
	private static Controlador control;
	private JTextField textCalifica;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAlquiler;
	private final JRadioButton rdbtnVenta;
	private JButton okButtonCrear;
	private final JComboBox<Asesor> comboBoxAsesor;
	private final JComboBox<Cliente> comboBoxPropietario;

	/**
	 * Launch the application.
	 */
	public static void main(String... args) {
		try {
			CrearNaveIndustrial dialog = new CrearNaveIndustrial(control);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean selected(){
		if(rdbtnAlquiler.isSelected() || rdbtnVenta.isSelected()) return true;
		return false;
		
	}
	
	public void selectVenta(){
		rdbtnAlquiler.setSelected(false);
		rdbtnVenta.setSelected(true);
	}
	
	public void selectAlquiler(){
		rdbtnVenta.setSelected(false);
		rdbtnAlquiler.setSelected(true);
		
	}
	
	public void botonOk(){
		okButtonCrear.doClick();
	}
	
	public void crearAsesorCliente(Asesor asesor, Cliente cliente){
		try {
			control.crearAsesor(asesor);
			control.crearCliente(cliente);
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBoxAsesor.addItem(asesor);
		comboBoxPropietario.addItem(cliente);
		
	}
	
	public void setCampos(NaveIndustrial nave){
		
		textFieldCodigo.setText(nave.getCodId());
		textFieldDireccion.setText(nave.getCalle());
		textFieldLocalidad.setText(nave.getLocalidad());
		textFieldFecha.setText(nave.getFechaAlta());
		textFieldSuperficie.setText(nave.getSuperficieTotal());
		textFieldNumPuertas.setText(nave.getNumPuertas());
		textCalifica.setText(nave.getCalificacion());		
		
		selectVenta();
	}
	
	//Inicializacion de los objetos necesarios de la capa de negocio
	private void initDominio() {
		try{
			control = Controlador.dameControlador();
		}catch (Exception e){
			Logger log = Logger.getLogger(CrearNaveIndustrial.class.getName());
        	if (log.isLoggable(Level.FINE))
        		log.fine(e.getMessage());
		}
	}

	/**
	 * Create the dialog.
	 * @param control2 
	 */
	public CrearNaveIndustrial(Controlador control1) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle("Crear NaveIndustrial");
		//Inicializamos el controlador para poder trabajar entre presentacion,l�gica y persistencia.
		initDominio();

		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPanel.add(lblNewLabel);

		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(84, 9, 86, 17);
		contentPanel.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);

		JLabel direccionLabel = new JLabel("Direcci\u00F3n:");
		direccionLabel.setBounds(10, 35, 65, 14);
		contentPanel.add(direccionLabel);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(84, 33, 340, 17);
		contentPanel.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

		JLabel localidadLabel = new JLabel("Localidad:");
		localidadLabel.setBounds(10, 58, 65, 14);
		contentPanel.add(localidadLabel);

		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setBounds(84, 56, 340, 17);
		contentPanel.add(textFieldLocalidad);
		textFieldLocalidad.setColumns(10);

		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setBounds(10, 86, 46, 14);
		contentPanel.add(fechaLabel);

		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(104, 84, 86, 17);
		contentPanel.add(textFieldFecha);
		textFieldFecha.setColumns(10);

		JLabel superficieLabel = new JLabel("Superficie:");
		superficieLabel.setBounds(10, 111, 65, 14);
		contentPanel.add(superficieLabel);

		JLabel lblNmero = new JLabel("Num.Puertas:");
		lblNmero.setBounds(10, 136, 84, 14);
		contentPanel.add(lblNmero);

		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(10, 182, 84, 14);
		contentPanel.add(lblPropietario);

		JLabel lblAsesor = new JLabel("Asesor:");
		lblAsesor.setBounds(10, 204, 46, 14);
		contentPanel.add(lblAsesor);

		textFieldSuperficie = new JTextField();
		textFieldSuperficie.setBounds(104, 109, 86, 17);
		contentPanel.add(textFieldSuperficie);
		textFieldSuperficie.setColumns(10);

		textFieldNumPuertas = new JTextField();
		textFieldNumPuertas.setBounds(104, 134, 86, 17);
		contentPanel.add(textFieldNumPuertas);
		textFieldNumPuertas.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(287, 121, 125, 80);
		contentPanel.add(panel);
		panel.setLayout(null);
		
				rdbtnAlquiler = new JRadioButton("Alquiler");
				buttonGroup.add(rdbtnAlquiler);
				rdbtnAlquiler.setBounds(6, 50, 109, 23);
				panel.add(rdbtnAlquiler);
				
						rdbtnVenta = new JRadioButton("Venta");
						buttonGroup.add(rdbtnVenta);
						rdbtnVenta.setBounds(6, 24, 109, 23);
						panel.add(rdbtnVenta);

		comboBoxPropietario = new JComboBox<Cliente>();
		comboBoxPropietario.setBounds(104, 203, 163, 17);
		try {
			java.util.List<Cliente> clientes= control.encontrarCliente();

			for(int i=0;i<clientes.size();i++){
				comboBoxPropietario.addItem(clientes.get(i));

			}
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		



		contentPanel.add(comboBoxPropietario);

		comboBoxAsesor = new JComboBox<Asesor>();
		comboBoxAsesor.setBounds(104, 181, 163, 17);
		try {
			java.util.List<Asesor> asesores= control.encontrarAsesores();

			for(int i=0;i<asesores.size();i++){
				comboBoxAsesor.addItem(asesores.get(i));

			}
		} catch (LogicaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		


		contentPanel.add(comboBoxAsesor);

		JLabel calificacionLabel = new JLabel("Calificacion:");
		calificacionLabel.setBounds(10, 161, 84, 14);
		contentPanel.add(calificacionLabel);

		textCalifica = new JTextField();
		textCalifica.setBounds(104, 160, 86, 16);
		contentPanel.add(textCalifica);
		textCalifica.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButtonCrear = new JButton("Crear");  //Boton crear
				okButtonCrear.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent actionListener) { //Asociamos el ActionEvent al boton crear.

						//Creamos la instancia de la clase Piso.
						String va = "";
						
						if (rdbtnVenta.isSelected()) va = "V";
						else if (rdbtnAlquiler.isSelected()) va="A";
						else {
							if(getContentPane().isVisible())
								JOptionPane.showMessageDialog(null, "Debe seleccionar si es una venta o un alquiler","NAVEINDUSTRIAL",
										JOptionPane.DEFAULT_OPTION);
						}
							
						if(!va.equals("")){
							NaveIndustrial pi = new NaveIndustrial(textFieldCodigo.getText(),
								textFieldDireccion.getText(),
								textFieldLocalidad.getText(),
								textFieldFecha.getText(),
								textFieldSuperficie.getText(),
								va,
								textFieldNumPuertas.getText(),
								textCalifica.getText(),
								(Asesor)comboBoxAsesor.getSelectedItem(),
								(Cliente)comboBoxPropietario.getSelectedItem());

						//	pi.setAsesor((Asesor)comboBoxAsesor.getSelectedItem());
						//	pi.setCliente((Cliente)comboBoxPropietario.getSelectedItem());
						try{
							
							//Antes de crearse la NaveIndustrial pregunta si estamos seguros o no.
							Object[] textoOpciones = {"Si Quiero", "Ahora no"};
							int opcion = 0;
							if(getContentPane().isVisible()){
								opcion = JOptionPane.showOptionDialog(null,"� Desea realizar la operacion ahora ?",
										"Mensaje de confirmacion",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null, //utilizar el icono predeterminado
										textoOpciones,
										textoOpciones[0]); //boton predeterminado
							}
							//Si el boton elegido es "Si quiero" (posicion 0 del array) lo crea.
							if(opcion==0) {
							control.crearNaveIndustrial(pi);
							
							if(getContentPane().isVisible())
								JOptionPane.showMessageDialog(null, "Nave Industrial Creada Correctamente","NAVEINDUSTRIAL",
									JOptionPane.DEFAULT_OPTION);
							}
							

						} catch(Exception e){
							if(getContentPane().isVisible())
								JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR AL CREAR",
									JOptionPane.ERROR_MESSAGE);
						}
						}
						
						
						
					}

				});

				okButtonCrear.setActionCommand("OK");
				buttonPane.add(okButtonCrear);
				getRootPane().setDefaultButton(okButtonCrear);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Se quita la pantalla.
						setVisible(false);

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}//Fin de la clase.
