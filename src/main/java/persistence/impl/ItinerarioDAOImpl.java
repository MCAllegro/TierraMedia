package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


import model.Ofertable;

import persistence.ItinerarioDAO;
import persistence.commons.ConnectionProvider;



public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public Ofertable find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ofertable> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Ofertable t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Ofertable t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Ofertable t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean insert(int visiId, String promoId, String atracId) {
		int insert = 0;
		String sql = "INSERT INTO Itinerario (usuario_id, promocion_id, atraccion_id) VALUES (?, ? , ?)";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, visiId);
			statement.setString(2, promoId);
			statement.setString(3, atracId);
			insert = statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return insert > 0;
	}
	
	public boolean updatePromoId(int visiId, String promoId) {
		int update = 0;
		String sql ="UPDATE itinerario SET promocion_id = ? WHERE Itinerario.usuario_id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, promoId);
			statement.setInt(2, visiId);
			update = statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return update > 0;
	}
	
	public boolean updateAtracId(int visiId, String atracId) {
		int update = 0;
		String sql ="UPDATE Itinerario SET atraccion_id = ? WHERE Itinerario.usuario_id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, atracId);
			statement.setInt(2, visiId);
			update = statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return update > 0;
	}
	
	@Override
	public boolean checkearComprasPrevias(int userId) {
		boolean contieneAlgo = false;
		String sql = "SELECT count(*) FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			if(result.getInt(1) > 0) {
				contieneAlgo = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contieneAlgo;
	}
	
	public String [] compradoresAnteriores() {
		String devolver = null;
		String sql = "SELECT group_concat(Itinerario.usuario_id) FROM Itinerario;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			devolver = result.getString(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		String [] spliteoUsuId = devolver.split(",");
		return spliteoUsuId;
	}
	
	@Override
	public String trearPromoID(int visiID) {
		String devolver = null;
		String sql = "SELECT Itinerario.promocion_id FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, visiID);
			ResultSet result = statement.executeQuery();
			devolver = result.getString(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return devolver;
	}

	@Override
	public String trearAtracID(int visiID) {
		String devolver = null;
		String sql = "SELECT Itinerario.atraccion_id FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, visiID);
			ResultSet result = statement.executeQuery();
			devolver = result.getString(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return devolver;
	}
	@Override
	public String traerItinerario(int visiID) {
		String devolver = null;
		String sql = "SELECT Itinerario.usuario_id, Itinerario.atraccion_id, Itinerario.promocion_id FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, visiID);
			ResultSet result = statement.executeQuery();
			devolver = result.getString(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return devolver;
	}

		
	}


