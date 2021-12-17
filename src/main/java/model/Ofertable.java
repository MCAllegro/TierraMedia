package model;

import java.util.List;

public interface Ofertable {
		public String getName();
	
		public Integer getId();
		
		public List<Attraction> getListaInterna();// solo promocion usa este metodo
		
		public Integer getCost();
		
		public double getDuration();
		
		public boolean canHost();
		
		public boolean esOfrecible();
		
		public void reservar();
		
		public boolean esPromocion();
		
		public void afirmarOfrecible();// solo promocion usa este metodo
	}
