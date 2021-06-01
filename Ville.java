
public class Ville {
	private String nom;
	private Batiment[][] terrin;
	
	
	
	public Ville(String nom,int n) {
		this.nom = nom;
		terrin = new Batiment[n][n];
	}
	
	
	public boolean ajouter(Batiment b) {
		int x = (int) (Math.random()*terrin.length);
		int y = (int) (Math.random()*terrin.length);
		
		if(terrin[x][y] != null)
			return false;
		else {
			terrin[x][y] = b;   
			return true;
		}

		
	}
	
	public void afficherVille() {
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if(terrin[i][j]== null)
					System.out.print("  .   ");
				else
					System.out.print(terrin[i][j].getStringID()+" ");
			}
					System.out.println();
		}
			
	}
	
	
	
	public int releverLoyer() {
		int s=0;
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if(terrin[i][j] != null)
					s+=terrin[i][j].getLoyer();
			}
		}
		return s;
	}
	
	public int compterHabitants() {
		int n=0;
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if((terrin[i][j] != null)&&(terrin[i][j] instanceof Habitation)){
					n+=((Habitation)terrin[i][j]).getNbHabitants();
				}
					
			}
		}
		return n;
	}
	public void afficherBatimentAEtage(int nbE){
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if((terrin[i][j] != null)&&(terrin[i][j] instanceof AEtage)){
					if(((AEtage) terrin[i][j]).getNbEtages() >= nbE)
						System.out.println(terrin[i][j].toString());
				}
					
			}
		}
	}

	public int CalculeProfit() {
		int s=0;
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if((terrin[i][j] != null)&&(terrin[i][j] instanceof Commerce))
					s+=((Commerce) terrin[i][j]).getProfit();
			}
		}
		return s;
	}
	public int CaculeDeponse() {
		int s=0;
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if((terrin[i][j] != null)&&(terrin[i][j] instanceof Commerce))
					s+=((Commerce) terrin[i][j]).getDeponses();
			}
		}
		return s;
	}
	public int CaculeTaxe() {
		int s=0;
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if((terrin[i][j] != null)&&(terrin[i][j] instanceof Commerce))
					s+=((Commerce) terrin[i][j]).getTaxe();
			}
		}
		return s;
	}
	
	public boolean VillePleine() {
		for(int i=0;i<terrin.length;i++) {
			for(int j=0;j<terrin[i].length;j++) {
				if(terrin[i][j] == null)
						return false;
			}
		}
		return true;
	}
	public String toString() {
		return "Ville " + nom + " nbHab=" + compterHabitants();
	}
	
	
	
	
}
