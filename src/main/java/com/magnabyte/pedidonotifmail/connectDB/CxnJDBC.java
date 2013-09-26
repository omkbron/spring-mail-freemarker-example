package com.magnabyte.pedidonotifmail.connectDB;

import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.*;

/**
 * @author Jose_Leon
 * 
 */
public class CxnJDBC implements JDBCConnectionParameter {

	/**
	 * False si es transaccional
	 */
	public boolean autoCommit = true;
	DataSource dataSource;
	Connection con;

	private String error = "";

	/**
	 * Constructor
	 * 
	 **/
	public CxnJDBC() {
	}

	/*********************** [ Connect ] ********************************/
	/**
	 * Realiza la conexion con la Base de datos
	 */
	public Connection ConnectJDBC() {
		try {
			Class.forName(DRIVERDB);
		} catch (ClassNotFoundException e) {
			System.out.println("Primer try");
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(URLDB + SERVERHOST + PORTDB + SID + INFORMIXSERVER + " " + USERDB + " " + PWDDB);
			con = DriverManager.getConnection(URLDB + SERVERHOST + PORTDB + SID + INFORMIXSERVER, USERDB, PWDDB);
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("ERROR: no se ha podido cargar el controlador JDBC de Informix.");
			e.printStackTrace();
		}
		
		if (con != null) {
			return con;
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the error.
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            The error to set.
	 */
	public void setError(String error) {
		this.error = error;
	}
}
