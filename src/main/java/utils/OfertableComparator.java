package utils;

import java.util.Comparator;

import model.Ofertable;

public class OfertableComparator implements Comparator<Ofertable> {

	public int compare(Ofertable o1, Ofertable o2) {
		if (o1.esPromocion() && o2.esPromocion()) {
			if (Integer.compare(o1.getCost(), o2.getCost()) == 0) {
				return -Double.compare(o1.getDuration(), o2.getDuration());
			} else {
				return -Integer.compare(o1.getCost(), o2.getCost());
			}
		}
		if (!o1.esPromocion() && !o2.esPromocion()) {
			if (Integer.compare(o1.getCost(), o2.getCost()) == 0) {
				return -Double.compare(o1.getDuration(), o2.getDuration());
			} else {
				return -Integer.compare(o1.getCost(), o2.getCost());
			}
		} else {
			return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
		}
	}

}
