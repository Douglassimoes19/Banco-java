package classesutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbutil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/seu_banco";
	private static final String USUARIO = "root";
	private static final String SENHA = "senha";

	public static Connection conectar() throws SQLException {
	   return DriverManager.getConnection(URL, USUARIO, SENHA);
	}
	

}
