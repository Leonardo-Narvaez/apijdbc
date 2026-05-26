package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LoggerFactory.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		Videojuego juego = null;

		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO videojuegos(codigo, nombre, plataforma, precio, disponible, genero) VALUES(?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			juego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			int filas = ps.executeUpdate();

			log.info("Filas insertadas: " + filas);
		} catch (Exception e) {
			log.error("Error al insertar: " + e.getMessage());
			throw new RuntimeException("Error al insertar: " + e.getMessage());

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return juego;
	}
}
