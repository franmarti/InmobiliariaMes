package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logica.Asesor;
import excepciones.DAOExcepcion;

public class AsesorDAOImp implements IAsesorDAO {
	protected ConnectionManager connManager;

	public AsesorDAOImp() throws DAOExcepcion {
		super();
		// TODO Auto-generated constructor stub
		try {
			connManager = new ConnectionManager("practicaFinal");
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}

	
	public void crearAsesor(Asesor a) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try {
			connManager.connect();
			
			connManager.updateDB("insert into ASESOR (CODIGO_EMPLEADO,NOMBRE) values ('"
							+ a.getCodigoEmp() + "','" + a.getNombre() + "')");

			connManager.close();
		} catch (Exception e) {
			throw new DAOExcepcion(e);
		}
	}

	
	public Asesor encontrarAsesorPorCod(String cod) throws DAOExcepcion {
		// TODO Auto-generated method stub
		try {
			connManager.connect();

			ResultSet rs = connManager
					.queryDB("select * from ASESOR where CODIGO_EMPLEADO= '"
							+ cod + "'");
			connManager.close();
			
			if (rs.next())
				return new Asesor(cod, rs.getString("NOMBRE"));
			else
				return null;

		} catch (SQLException e) {
			throw new DAOExcepcion(e);
		}
	}

	
	@Override
	public List<Asesor> encontrarAsesores() throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from ASESOR");
			connManager.close();

			List<Asesor> listaAsesores = new ArrayList<Asesor>();
			try {
				while (rs.next()) {
					Asesor p = encontrarAsesorPorCod(rs
							.getString("CODIGO_EMPLEADO"));
					listaAsesores.add(p);
				}
				return listaAsesores;
			} catch (Exception e) {
				throw new DAOExcepcion(e);
			}
		} catch (DAOExcepcion e) {
			throw e;
		}
	}
}// Fin de la clase.
