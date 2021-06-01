
public class TestJeu {

	public static void main(String[] args) {
		System.out.println(Batiment.getNbBatiments());

		System.out.println(new Maison().toString());
		System.out.println(new Maison().toString());
		System.out.println(new Maison().toString());
		System.out.println(new CentreCommercial().toString());
		
		Ville v = new Ville("AbdowVG", 10);
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(Generateur.getNewBatiement());
		v.ajouter(new Restaurant());
		v.ajouter(new CentreCommercial());
		
		v.afficherVille();
	}

}
