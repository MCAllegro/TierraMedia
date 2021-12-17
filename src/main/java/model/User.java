package model;

import java.util.HashMap;
import java.util.Map;

import persistence.ItinerarioDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class User {

	private Integer id;
	private String username, password;
	private Boolean admin;
	private Integer coins;
	private Double time;
	private HashMap<String, String> errors;
	private ItinerarioDAO itiDao = DAOFactory.getItinerarioDao();
	private Itinerario itinerario = new Itinerario();

	public User(Integer id, String username, String password, Integer coins, Double time, Boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.time = time;
		this.admin = admin;
	}

	public void addToItinerary(Attraction attraction) {
		this.coins -= attraction.getCost();
		this.time -= attraction.getDuration();
		// TODO agregar a su lista
	}
	
	public boolean canAffordPromo(Promocion promo) {
		return promo.getCost() <= this.coins;
	}

	public boolean canAttendPromo(Promocion promo) {
		return promo.getDuration() <= this.time;
	}

	public boolean canAfford(Attraction attraction) {
		return attraction.getCost() <= this.coins;
	}

	public boolean canAttend(Attraction attraction) {
		return attraction.getDuration() <= this.time;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Boolean getAdmin() {
		return admin;
	}

	public Integer getCoins() {
		return coins;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Double getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public boolean comproPromoAnteriormente(int promoId) {
		if (itiDao.checkearComprasPrevias(this.id)) {
			String promos = itiDao.trearPromoID(this.id);
			if (promos != null) {
				String[] spliteoPromos = promos.split(",");
				boolean salirFor = true;
				for (String id : spliteoPromos) {
					if (promoId == Integer.parseInt(id)) {
						salirFor = false;
					}
				}
				return salirFor;
				// termina for
			} else { // promo == null
				return true;
			}
		} // no compras previas
		return true;
	}

	public boolean comproAtracAnteriormente(int atracId) {
		if (itiDao.checkearComprasPrevias(this.getId())) {
			String atrac = itiDao.trearAtracID(this.getId());
			if (atrac != null) {
				String[] spliteoAtracs = atrac.split(",");
				boolean salirFor = true;
				for(String id : spliteoAtracs) {
					if(atracId == Integer.parseInt(id)) {
						salirFor = false;
					}
				}
				return salirFor; //termina for
			}else { // promo == null
				return true;
			}
		}// no compras previas
		return true;
	}
	
	public boolean comproPromo(int atracId) {
		return itinerario.comproPromo(this.getId(), atracId);
	}
	
	public boolean comproAtrac(int promoId) {
		return itinerario.comproAtrac(this.getId(), promoId);
	}
	public boolean comprasAnteriores() {
		return itiDao.checkearComprasPrevias(this.getId());
	}

	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (coins < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (time < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin + "]";
	}
	
	public void restarDineroTiempo(int coins, double time) {
		this.coins -= coins;
		this.time -= time;
	}

}
