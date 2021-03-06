package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import logica.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InmobiliariaApp {

	private JFrame frame;
	private Controlador control;


	/**
	 * Launch the application.
	 */
	public static void main(String... args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InmobiliariaApp window = new InmobiliariaApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InmobiliariaApp() {
		initialize();

		initDominio();

	}

	//Inicializaci�n de los objetos necesarios de la capa de negocio
	private void initDominio() {
		try{
			control = Controlador.dameControlador();
		}catch (Exception e){
			Logger log = Logger.getLogger(InmobiliariaApp.class.getName());
        	if (log.isLoggable(Level.FINE))
        		log.fine(e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Titulo del Jframe.
		frame.setTitle("INMOBILIARIA");

		JMenuBar menuPrin = new JMenuBar();
		frame.setJMenuBar(menuPrin);

		JMenu mnInmuebles = new JMenu("Inmuebles");
		menuPrin.add(mnInmuebles);

		JMenuItem mntmCrearPiso = new JMenuItem("Crear piso");
		mntmCrearPiso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearPisoJDialog cur  = new CrearPisoJDialog(control);
				cur.setModal(true);
				cur.setVisible(true);
			}
		});
		mnInmuebles.add(mntmCrearPiso);

		JMenuItem mntmCrearNaveIndustrial = new JMenuItem("Crear nave industrial");
		mntmCrearNaveIndustrial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearNaveIndustrial cur = new CrearNaveIndustrial(control);
				cur.setModal(true);
				cur.setVisible(true);
			}
		});
		mnInmuebles.add(mntmCrearNaveIndustrial);

		JMenuItem mntmConsultarInmueble = new JMenuItem("Consultar Pisos");
		mntmConsultarInmueble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ConsultaPisosJDialog cur  = new ConsultaPisosJDialog(control);
				cur.setModal(true);
				cur.cargaInmuebles();
				cur.setVisible(true);

			}

		});
		mnInmuebles.add(mntmConsultarInmueble);

		JMenuItem mntmNaveIndustrial = new JMenuItem("Consultar NavesIndustriales");
		mntmNaveIndustrial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarNaveIndustrial cur = new ConsultarNaveIndustrial(control);
				cur.setModal(true);
				cur.cargaNaves();
				cur.setVisible(true);
			}
		});
		mnInmuebles.add(mntmNaveIndustrial);

		JMenuItem mntmConsultarInmuebles = new JMenuItem("Consultar Inmuebles no v/a ");
		mntmConsultarInmuebles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsultarInmueblesNo cur = new ConsultarInmueblesNo(control);
				cur.setModal(true);
				cur.cargaPisos();
				cur.cargaNavesIndustriales();
				cur.setVisible(true);
			}
		});
		mnInmuebles.add(mntmConsultarInmuebles);

		JSeparator separator = new JSeparator();
		mnInmuebles.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cierra todo el programa.
				System.exit(0);
			}
		});
		mnInmuebles.add(mntmSalir);

		JMenu mnAsesor = new JMenu("Asesores");
		menuPrin.add(mnAsesor);

		JMenuItem mntmCrearAsesor = new JMenuItem("Crear Asesor");
		mntmCrearAsesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Event handler para ir a la creaci�n de asesores.
				CrearAsesor cur1  = new CrearAsesor(control);
				cur1.setModal(true);
				cur1.setVisible(true);

			}
		});
		mnAsesor.add(mntmCrearAsesor);

		JMenuItem mntmConsultarAsesor = new JMenuItem("Consultar Asesor por Inmueble");
		mntmConsultarAsesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarAsesores cur = new ConsultarAsesores(control);
				cur.setModal(true);
				cur.setVisible(true);

			}
		});
		mnAsesor.add(mntmConsultarAsesor);

		JMenuItem mntmNewMenuItem = new JMenuItem("Consultar Asesores");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarTodosAsesores cur = new ConsultarTodosAsesores(control);
				cur.setModal(true);
				cur.cargaTodosAsesores();
				cur.setVisible(true);
			}
		});
		mnAsesor.add(mntmNewMenuItem);

		JMenuItem mntmSalir2 = new JMenuItem("Salir");
		mntmSalir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cierra el programa.
				System.exit(0);
			}
		});

		JSeparator separador = new JSeparator();
		mnAsesor.add(separador);
		mnAsesor.add(mntmSalir2);

		JMenu mnNewMenu = new JMenu("Cliente");
		menuPrin.add(mnNewMenu);

		JMenuItem mntmCrearCliente = new JMenuItem("Crear Cliente");
		mntmCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Event handler para ir a la creaci�n de asesores.
				CrearCliente cur2  = new CrearCliente(control);
				cur2.setModal(true);
				cur2.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCrearCliente);

		JMenuItem mntmConsultarCliente = new JMenuItem("Consultar Cliente por Inmueble");
		mntmConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarClientes cur = new ConsultarClientes(control);
				cur.setModal(true);
				cur.setVisible(true);
			}
		});
		mnNewMenu.add(mntmConsultarCliente);

		JMenuItem clientesMenuItem = new JMenuItem("Consultar Clientes");
		clientesMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		clientesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarTodosClientes cur = new ConsultarTodosClientes(control);
				cur.setModal(true);
				cur.cargaTodosClientes();
				cur.setVisible(true);
			}
		});
		mnNewMenu.add(clientesMenuItem);

		JSeparator separador2 = new JSeparator();
		mnNewMenu.add(separador2);

		JMenuItem mntmSalir3 = new JMenuItem("Salir");
		mntmSalir3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Salir del programa
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmSalir3);
		
		JMenu mnVisitas = new JMenu("Visitas");
		menuPrin.add(mnVisitas);
		
				JMenuItem mntmCrearVisita = new JMenuItem("Crear Visita");
				mnVisitas.add(mntmCrearVisita);
				
						JMenuItem consultarVisitasMenuItem = new JMenuItem("Consultar Visitas/Ofertas");
						mnVisitas.add(consultarVisitasMenuItem);
						
						JSeparator separador3 = new JSeparator();
						mnVisitas.add(separador3);
						
						JMenuItem salirMenuItem = new JMenuItem("Salir");
						salirMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								//Salir del programa
								System.exit(0);
							}
						});
						mnVisitas.add(salirMenuItem);
						consultarVisitasMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								ConsultarVisitaOferta cur = new ConsultarVisitaOferta(control);
								cur.setModal(true);
								//cur.cargaInmueblesVisita();
								//cur.cargaInmueblesOferta();
								cur.setVisible(true);

							}
						});
				mntmCrearVisita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Event handler para ir a la creaci�n de visitas.
						NuevaVisita cur1  = new NuevaVisita(control);
						cur1.setModal(true);
						cur1.setVisible(true);

					}
				});
		
		JMenu mnOfertas = new JMenu("Ofertas");
		menuPrin.add(mnOfertas);
		
				JMenuItem mntmCrearoferta = new JMenuItem("CrearOferta");
				mnOfertas.add(mntmCrearoferta);
				
				JMenuItem menuItem = new JMenuItem("Consultar Visitas/Ofertas");
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConsultarVisitaOferta cur = new ConsultarVisitaOferta(control);
						cur.setModal(true);
						//cur.cargaInmueblesVisita();
						//cur.cargaInmueblesOferta();
						cur.setVisible(true);
					}
				});
				mnOfertas.add(menuItem);
				
				JSeparator separador4 = new JSeparator();
				mnOfertas.add(separador4);
				
				JMenuItem salirMenuItem2 = new JMenuItem("Salir");
				salirMenuItem2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Salir del programa
						System.exit(0);
					}
				});
				mnOfertas.add(salirMenuItem2);
				mntmCrearoferta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						//Event handler para ir a la creaci�n de ofertas.
						NuevaOferta cur1  = new NuevaOferta(control);
						cur1.setModal(true);
						cur1.setVisible(true);
					}
				});
		
		JMenu mnTransaccion = new JMenu("Transacciones");
		menuPrin.add(mnTransaccion);
		
				JMenuItem mntmCrearVentaalquiler = new JMenuItem("Crear Venta/Alquiler");
				mnTransaccion.add(mntmCrearVentaalquiler);
				
				JSeparator separador5 = new JSeparator();
				mnTransaccion.add(separador5);
				
				JMenuItem salirMenuItem3 = new JMenuItem("Salir");
				salirMenuItem3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				mnTransaccion.add(salirMenuItem3);
				mntmCrearVentaalquiler.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Event handler para nuevaTransaccion.
						NuevaTransaccion cur = new NuevaTransaccion(control);
						cur.setModal(true);
						cur.setVisible(true);
					}
				});
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Creado Por");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Event handler para ir a Creado Por.
				CreadoPor cur2  = new CreadoPor();
				cur2.setModal(true);
				cur2.setVisible(true);
			}
		});
		btnNewButton.setBounds(161, 207, 123, 23);
		frame.getContentPane().add(btnNewButton);
	}
}//Fin de Inmobiliaria.
