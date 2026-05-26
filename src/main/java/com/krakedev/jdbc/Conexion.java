package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {
	private static final Logger log = LoggerFactory.getLogger(Conexion.class);
	private static final String URL = "jdbc:postgresql://localhost:5432/apijdbc";
	// private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Dressbreak123";

	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Conexion Exitosa");
			return con;

		} catch (SQLException e) {
			log.error("Error en la conexion: " + e.getMessage());
			throw new RuntimeException("No se pudo conectar", e);
		}
	}
}
