package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class Attraction implements Ofertable {

	private Integer id;
	private String name;
	private Integer cost;
	private Double duration;
	private Integer capacity;
	private boolean esOfrecible = true;
	
	private Map<String, String> errors;
	
	public Attraction(String name, Integer cost, Double duration, Integer capacity) {
		super();
		this.name = name;
		this.cost = cost;
		this.duration = duration;
		this.capacity = capacity;
	}
	
	public Attraction(Integer id, String name, Integer cost, Double duration, Integer capacity) {
		this(name, cost, duration, capacity);
		this.id = id;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (cost <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duration <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (duration > 60) {
			errors.put("duration", "Excede el tiempo máximo");
		}
		if (capacity <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	public boolean esOfrecible() {
		return esOfrecible;
	}
	public boolean esPromocion() {
		return false;
	}
	
	public void reservar() {
		this.esOfrecible = false;
		this.capacity--;
	}
	
	public void afirmarOfrecible() {
		this.esOfrecible = true;
	}
	
	public List<Attraction> getListaInterna() {
		// solo Promocion usa este metodo
		return null;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", cost=" + cost + ", duration=" + duration + ", capacity="
				+ capacity + "]";
	}

	public boolean canHost() {
		return this.capacity > 0;
	}

	public void host(int i) {
		this.capacity -= i;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attraction other = (Attraction) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}