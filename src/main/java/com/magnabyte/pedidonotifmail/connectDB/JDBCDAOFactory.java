package com.magnabyte.pedidonotifmail.connectDB;

import java.sql.Connection;

/**
 * @author Jose_Leon
 * 
 */
public class JDBCDAOFactory {
	public static Connection createConnection() throws Exception {
		Connection conn = null;
		try {
			// Se establece la conexion
			CxnJDBC cxn = new CxnJDBC();
			// Se hace la conexion usando la fuente de datos especificada
			conn = cxn.ConnectJDBC();
		} catch (Exception e) {
			System.err.println("Se ha producido un error en la BD: init");
			System.err.println(e.getMessage());
		}
		return conn;
	}

}
