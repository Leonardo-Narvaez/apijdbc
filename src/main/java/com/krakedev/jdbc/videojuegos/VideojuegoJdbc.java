package com.krakedev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LoggerFactory.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		Videojuego juego = null;

		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO videojuegos(codigo, nombre, plataforma, precio, disponible, genero) VALUES(?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

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

	public static List<Videojuego> listar() {
		List<Videojuego> juegos = new ArrayList<>();
		Connection con = null;

		try {
			con = Conexion.getConnection();
			String sql = "SELECT * FROM videojuegos";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego juego = new Videojuego(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
						rs.getBoolean(5), rs.getString(6));
				juegos.add(juego);

			}

		} catch (Exception e) {
			log.error("Error al listar");
			throw new RuntimeException("Error al listar: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return juegos;
	}

	public static Videojuego buscarPorCodigo(String codigo) {
		Connection con = null;
		Videojuego juego = null;

		try {
			con = Conexion.getConnection();
			String sql = "SELECT * FROM videojuegos WHERE codigo =?";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				juego = new Videojuego(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
						rs.getBoolean(5), rs.getString(6));
			}
		} catch (Exception e) {
			log.error("Error al buscar");
			throw new RuntimeException("Error al buscar: " + e.getMessage());
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
