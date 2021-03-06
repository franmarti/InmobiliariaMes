package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Asesor;
import logica.Cliente;
import logica.Inmueble;
import logica.Visita;
import excepciones.DAOExcepcion;

public class VisitaDAOImp implements IVisitaDAO {
	
	protected ConnectionManager connManager;
	private Asesor ase =null;
	private Cliente cli =null;
	private Inmueble inmu = null;
	
	//Constructor de Visita.
	public VisitaDAOImp() throws DAOExcepcion {
		super();
		// TODO Auto-generated constructor stub
		try{
		connManager= new ConnectionManager("practicaFinal");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion(e);
		}
	}
	

	@Override
	public Visita encontrarVisitaPorCod(String cod) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();


		ResultSet rs=connManager.queryDB("select FECHA, COD_ID, NIF_CLIENTE, CODIGO_EMPLEADO, COD_VISITA from VISITA where COD_VISITA= '"+cod+"'");
			connManager.close();
		
			
			if (rs.next())
			  return new Visita(rs.getString("COD_VISITA"), rs.getString("FECHA"), inmu = encontrarInmueblePorCodIn(rs.getString("COD_ID")), ase = encontrarAsesorPorCodIn(rs.getString("CODIGO_EMPLEADO")), cli = encontrarClientePorCodIn(rs.getString("NIF_CLIENTE")) );
			 else
				return null;
					
			}catch (SQLException e){
						throw new DAOExcepcion(e);
					}
	}

	//Usamos este m�todo dentro de Visita para encontrar un Asesor y poder crear una Visita al buscarlo en la base de datos.
		 public Asesor encontrarAsesorPorCodIn(String cod)throws DAOExcepcion{
				// TODO Auto-generated method stub
		  try{
			
			connManager.connect();

		ResultSet rs=connManager.queryDB("select * from ASESOR where CODIGO_EMPLEADO= '"+cod+"'");
			connManager.close();
			if (rs.next())
			  return new Asesor(cod, rs.getString("NOMBRE"));
			 else
				return null;
					
			}catch (SQLException e){
						throw new DAOExcepcion(e);
					}
				
			}
		//Usamos este metodo dentro de Visita para encontrar una Cliente y poder crear una Visita al buscarlo en la base de datos.
		 public Cliente encontrarClientePorCodIn(String cod)throws DAOExcepcion{
				// TODO Auto-generated method stub
		  try{
			connManager.connect();

		ResultSet rs=connManager.queryDB("select * from CLIENTE where NIF_CLIENTE= '"+cod+"'");
			connManager.close();
			if (rs.next())
			  return new Cliente(cod, rs.getString("NOMBRE"),rs.getString("APELLIDOS"));
			 else
				return null;
					
			}catch (SQLException e){
						throw new DAOExcepcion(e);
					}
				
			}
		 
		 //Usamo este metodo dentro de Visita para encontrar un Inmueble y poder crear la Visita.
		 public Inmueble encontrarInmueblePorCodIn(String cod) throws DAOExcepcion{
			 try{
				 connManager.connect();
				 
				 ResultSet rs=connManager.queryDB("select * from INMUEBLE where COD_ID= '"+cod+"'");
				 connManager.close();
				 if(rs.next())
					 return new Inmueble(cod,rs.getString("CALLE"),rs.getString("LOCALIDAD"), rs.getString("FECHA_ALTA"),rs.getString("SUPERFICIE_TOTAL"),rs.getString("VENTA_ALQUILER"), ase = encontrarAsesorPorCodIn(rs.getString("CODIGO_EMPLEADO")), cli = encontrarClientePorCodIn(rs.getString("NIF_CLIENTE")) );
				 else
					 return null;
			 }catch (SQLException e){
				 throw new DAOExcepcion(e);
			 }
		 }
	
	@Override
	public void crearVisita(Visita i) throws DAOExcepcion {
		// TODO Auto-generated method stub
		
		try {
			connManager.connect();
			
			connManager.updateDB("insert into VISITA (FECHA, COD_ID, NIF_CLIENTE, CODIGO_EMPLEADO, COD_VISITA) values ('"+i.getFecha()+"','"+i.getInm().getCodId()+"','"+i.getCliente().getNif()+"','"+i.getAsesor().getCodigoEmp()+"','"+i.getCod()+"')");
			
			connManager.close();
		} catch (Exception e){
			throw new DAOExcepcion(e);
		}
	}

	@Override
	public List<Visita> encontrarVisitas() throws DAOExcepcion {
		// TODO Auto-generated method stub
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from VISITA");						
			connManager.close();

			List<Visita> listaVisitas=new ArrayList<Visita>();
			try{				
				while (rs.next()){
					Visita p = encontrarVisitaPorCod(rs.getString("COD_VISITA"));	 
					listaVisitas.add(p);
				}
				return listaVisitas;
			}catch (Exception e){
				throw new DAOExcepcion(e);
			}
		}catch (DAOExcepcion e){
			throw e;
		}	
	}


}
