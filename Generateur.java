
public class Generateur {
	
	public static Batiment getNewBatiement() {
		double rand = Math.random();
		
		if(rand <0.3)
			return new Maison();
		else if(rand<0.6)
			return new Immeuble();
		else
			return new Usine();
	}

	public static Item getNewBatiement(int choixAcheter) {

		switch (choixAcheter) {
		case 1 : return new CentreCommercial();
		case 2 : return new Immeuble();
		case 3 : return new Maison();
		case 4 : return new Restaurant();
		case 5 : return new Usine();
		case 6 : return new Parc();
		case 7 : return new Cinema();
		default : return null;
		}
	}
	
	
}
