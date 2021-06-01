
public abstract class Habitation extends Batiment{
	

	private char classeEnergie;
	
	public Habitation(String type, int surface) {
		super(type, surface);
		classeEnergie = (char)(Math.random()*('G'-'A'+1)+'A');
	}

	
	public int getPrix() {
		return surface*250*(10+'G'-classeEnergie);
	}

	public int getLoyer() {
		return (int) (0.05*getPrix());
	}
	public abstract int getNbHabitants();


	
	public String toString() {
		return super.toString()+" classe "+ classeEnergie +" nbHab="+getNbHabitants();
	}
	
	
	
	
}
