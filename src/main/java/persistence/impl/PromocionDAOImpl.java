package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ofertable;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import persistence.AttractionDAO;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;
import persistence.commons.ConnectionProvider;


public class PromocionDAOImpl implements PromocionDAO {


	
	public List<Promocion> findAll() { //chquear este metodo con el otro findALl
		AttractionDAO atraccionDao = DAOFactory.getAttractionDAO();
		List<Promocion> promocionList = new ArrayList<Promocion>();
		promoPorcentual(promocionList, atraccionDao);
		promoAbsoluta(promocionList, atraccionDao);
		promoAxB(promocionList, atraccionDao);
		return promocionList;
	}

	private void promoPorcentual(List<Promocion> promocionList, AttractionDAO atraccionDao) {
		String sql = "SELECT Promocion.id, Promocion.name, Promocion.descuento, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Porcentual\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionPorcentual(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(4), atraccionDao.findAll()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAbsoluta(List<Promocion> promocionList, AttractionDAO atraccionDao) {
		String sql ="SELECT Promocion.id, Promocion.name, Promocion.costo, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"Absoluta\"\r\n"
				+ "GROUP BY Promocion.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAbsoluta(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), atraccionDao.findAll()));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void promoAxB(List<Promocion> promocionList, AttractionDAO atraccionDao) {
		String sql ="SELECT promo_AxB.id, promo_AxB.name, promo_AxB.atracciones, group_concat(promoGratuita_axb.atraccion_id) as atracciones_gratis\r\n"
				+ "FROM (\r\n"
				+ "SELECT Promocion.id, Promocion.name, group_concat(promocion_atraccion.atraccion_id) as atracciones\r\n"
				+ "FROM Promocion\r\n"
				+ "LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id\r\n"
				+ "WHERE Promocion.tipo = \"AxB\"\r\n"
				+ "GROUP by Promocion.id\r\n"
				+ ") as \"promo_AxB\"\r\n"
				+ "INNER JOIN promoGratuita_axb on promoGratuita_axb.promocion_id = promo_AxB.id\r\n"
				+ "GROUP By promo_AxB.id;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				promocionList.add(new PromocionAxB(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), atraccionDao.findAll()));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Promocion find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
