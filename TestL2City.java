import java.awt.*;
import javax.swing.*;

public class TestL2City {

	private static final int TAILLE_CASE=40;
	private static final int NB_CASES=15; // min is 8
	
	public static void main(String[] args) throws InterruptedException{
		JFrame f = new JFrame(); // Creation fenetre graphique
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Creation de la ville(qui est aussi unpanneau)
		VilleJPanel villeJPanel= new VilleJPanel("L2City",NB_CASES,TAILLE_CASE);
		Joueur j = new Joueur("abdow",villeJPanel);
		f.setContentPane(j.getLaVille());//Ajout du panneau a la fenetre
		f.pack();	//Adaptation de la fenetre au panneau
		f.setVisible(true);//Affichage de la fenetre
		/*for(int i=0;i<10;i++){
		Thread.sleep(100);//Ralentil�affichage
		villeJPanel.ajouter(new Maison());
		villeJPanel.ajouter(new Usine());
		//...
		villeJPanel.repaint();//Redessine le graphique
		}
		Batiment b = Generateur.getNewBatiement();
		j.acheterBatiment(b);

		Thread.sleep(3000);
		j.vendreBatiment(b.getStringID());
		f.setContentPane(j.getLaVille());
		j.getLaVille().repaint();*/
		
		j.Jouer();
		//j.getLaVille().afficherVille();
		
		
	}
}
