
public abstract class Commerce extends Batiment implements ADeponses{
	

	public static final double TAUX_TAXE = 0.001;
	private int taxe;
	
	public Commerce(String type, int surface) {
		super(type, surface);
		taxe=(int)(getPrix()*TAUX_TAXE);
	}
	public int getPrix() {
		return surface*1500;
	}

	public int getLoyer() {
		return (int)0.05*getPrix();
	}
	public abstract int getProfit();
	
	public int getTaxe() {
		return taxe;
	}
	

	public String toString() {
		return super.toString()+" Taxe= "+ taxe +" Profit="+getProfit()+" Deponses="+getDeponses();
	}
	
	

}
