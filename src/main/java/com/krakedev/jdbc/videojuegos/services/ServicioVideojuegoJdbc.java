package com.krakedev.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {

	public Videojuego crear(Videojuego juego) {
		return VideojuegoJdbc.insertar(juego.getCodigo(), juego.getNombre(), juego.getPlataforma(), juego.getPrecio(),
				juego.isDisponible(), juego.getGenero());
	}

	public List<Videojuego> listar() {
		return VideojuegoJdbc.listar();
	}

	public Videojuego buscarPorCodigo(String codigo) {
		return VideojuegoJdbc.buscarPorCodigo(codigo);
	}

	public Videojuego actualizar(String codigo, Videojuego juego) {
		return VideojuegoJdbc.actualizar(codigo, juego.getNombre(), juego.getPlataforma(), juego.getPrecio(),
				juego.isDisponible(), juego.getGenero());
	}

	public boolean eliminar(String codigo) {
		return VideojuegoJdbc.eliminar(codigo);
	}
}
