package br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {
	private DatabaseConnection() {
	}
	
	private static final String RESOURCE = "java:/comp/env/jdbc/calmind";

	public static Connection getConnection() throws SQLException {
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(RESOURCE);
			return dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}