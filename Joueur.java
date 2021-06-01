import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Joueur {
	private String pseudo;
	private VilleJPanel laVille;
	private int porteMonnaie;
	private boolean debutMois;
	private boolean deponsesPaye;
	private int nbActions;
	
	
	public Joueur(String pseudo,VilleJPanel v) {
		this.pseudo = pseudo;
		laVille = v.clone();
		porteMonnaie = 10000000;
		debutMois=true;
		nbActions=0;
		
		
		
	}

	public void acheterBatiment(Item b) {
		if(b == null)
			JOptionPane.showMessageDialog(null, "Achat non effectuer");
		else if(laVille.VillePleine())
			JOptionPane.showMessageDialog(null, "La ville est pleine");
		else if(porteMonnaie<((APrix)b).getPrix()){
			JOptionPane.showMessageDialog(null, "pims non suffisants");
		}else {
			porteMonnaie-=((APrix)b).getPrix();
			do{}while(!(laVille.ajouter(b)));
			nbActions++;
		}
	}

	public void vendreBatiment(String id) {
		int x =  laVille.supprimer(id);
		if(x == -1)
			System.out.println("Batiment non trouve");
		else
			porteMonnaie+= x/2;
		
	}
	
	public int relverLoyer() {
		return laVille.releverLoyer();
	}
	
	public int ajouterLoyerPorteMonnaie() {
		int x =this.relverLoyer();
		porteMonnaie+=x;
		return x;
		
	}

	public int AjouterProfit() {
		int x =laVille.CalculeProfit();
		porteMonnaie+=x;
		return x;
	}
	public int PayerDeponses() {
		int x =laVille.CaculeDeponse();
		if(x>porteMonnaie){
			JOptionPane.showMessageDialog(null, "pims non suffisants");
			return -1;
		}
			porteMonnaie-=x;
		return x;
	}
	public int PayerTaxes() {
		int x = laVille.CaculeTaxe();
		if(x>porteMonnaie){
			JOptionPane.showMessageDialog(null, "pims non suffisants");
			return -1;
		}
		porteMonnaie-=x;
		return x;
	}
	
	public int getPorteMonnaie() {
		return porteMonnaie;
	}

	public String getPseudo() {
		return pseudo;
	}

	public VilleJPanel getLaVille() {
		return laVille;
	}
	public void addBonus() {
		porteMonnaie+=laVille.compterHabitants()*5000;
		JOptionPane.showMessageDialog(null, "Vous avez recu "+laVille.compterHabitants()*50+" pims en bonus");
	}

	public void polutionUsines() {
		int x= laVille.compterUsinesHabitation()*5000;
		porteMonnaie-=x;
		JOptionPane.showMessageDialog(null, "Vous avez perdu "+x+" pims a cause de la polution");
	}
	
	public void Satisfaction() {
		int x= laVille.compterCentreDivertissementHabitation()*5000;
		porteMonnaie+=x;
		JOptionPane.showMessageDialog(null, "Vous avez recu "+x+" pims pour la satisfaction de vos habitant");
	}
	
	public int getNbActions() {
		return nbActions;
	}
	public String toString() {
		return "Bienvenu "+pseudo+" vous avez "+porteMonnaie+" pims  "+laVille.toString();
	}
	
	
	public void update(){
			if(nbActions == 10) {
				debutMois=true;
				nbActions=0;
				addBonus();
				polutionUsines();
				Satisfaction();
			
			}
	}
	
	public void Jouer() {

		laVille.getAcheter().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {
					/*Menu.afficherMenuAcheter();
					Scanner sc = new Scanner(System.in);
					int choixAcheter;
					try{
						choixAcheter= sc.nextInt();
					}catch(InputMismatchException ime){
						choixAcheter = -1;
					}
					
						
					}*/
				  	String[] choices = { "CentreCommercial", "Immeuble", "Maison", "Restaurent", "Usine", "Parc", "Cinema" };
				    String input = (String) JOptionPane.showInputDialog(null, "Acheter",
				        "Choisir un Item :", JOptionPane.QUESTION_MESSAGE, null, choices, choices[2]); 
				    int choixAcheter = Arrays.asList(choices).indexOf(input);
				    if(choixAcheter != -1){
				    	Item b = Generateur.getNewBatiement(choixAcheter+1);
						if(b == null){
							JOptionPane.showMessageDialog(null, "choix incorrect re-saisir s'il vous plait");
						}else {

							int reply = JOptionPane.showConfirmDialog(null, "Voulez vous acheter : "+b.toString(), "Confirmation",
									JOptionPane.YES_NO_OPTION);
					        if (reply == JOptionPane.YES_OPTION) {
					        	if(b instanceof APrix)
					        		acheterBatiment(b);
					        		
					        }
				    }
				    }

					laVille.getInfo().setText("PIMS : "+porteMonnaie+" / Action : "+nbActions+" / Habitants : "+laVille.compterHabitants());
					laVille.repaint();
					update();
			        
			  }

			});

		laVille.getVendre().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {
				  laVille.setSellMode(true);
					laVille.repaint();
					String idchoix = JOptionPane.showInputDialog(null, "Donnez le id du batiment :");
					APrix b2 = laVille.recherche(idchoix);
					if(b2 == null)
						JOptionPane.showMessageDialog(null, "Batiment non trouver !");
					else {
						nbActions++;
						int reply2 = JOptionPane.showConfirmDialog(null, "Voulez vous vendre ce batiment pour: "+b2.getPrix()/2+" pims"
								, "Confirmation",JOptionPane.YES_NO_OPTION);
				        if (reply2 == JOptionPane.YES_OPTION) {
							vendreBatiment(idchoix);
				        }
					}
					laVille.setSellMode(false);

					laVille.getInfo().setText("PIMS : "+porteMonnaie+" / Action : "+nbActions+" / Habitants : "+laVille.compterHabitants());
					laVille.repaint();
					update();
			  }

			});

		laVille.getReleverLoyer().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {

					if(debutMois) {
						int x = ajouterLoyerPorteMonnaie();
						debutMois = false;
						JOptionPane.showMessageDialog(null, "Vous avez recu "+x+" pims");
					}
					else
						JOptionPane.showMessageDialog(null, "Vous avez deja relever le loyer de ce mois");

					laVille.getInfo().setText("PIMS : "+porteMonnaie+" / Action : "+nbActions+" / Habitants : "+laVille.compterHabitants());
					laVille.repaint();
					update();
			  }

			});
		
		

		laVille.getPayerDeponses().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {

					if(deponsesPaye) {
						JOptionPane.showMessageDialog(null, "Vous avez deja paye les deponses, vous pouvez recupirer le profit");
					}else {
						int x =PayerDeponses();
						if( x != -1) {
							x+=PayerTaxes();
							if( x != -1){
								JOptionPane.showMessageDialog(null, "Vous avez paye "+x+" pims");
								deponsesPaye = true;
								nbActions++;
							}
						}
					}
					

					laVille.getInfo().setText("PIMS : "+porteMonnaie+" / Action : "+nbActions+" / Habitants : "+laVille.compterHabitants());
					laVille.repaint();
					update();
			  }

			});
		
		

		laVille.getReleverProfis().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {
				  if(deponsesPaye) {
						int x = AjouterProfit();
						JOptionPane.showMessageDialog(null, "Vous avez recu "+x+" pims");
						deponsesPaye = false;
						nbActions++;
					}else {
						JOptionPane.showMessageDialog(null, "Payer vos deponses et taxes d'abord");
					}

					laVille.getInfo().setText("PIMS : "+porteMonnaie+" / Action : "+nbActions+" / Habitants : "+laVille.compterHabitants());
					laVille.repaint();
					update();
			  }

			});
		
		

		laVille.getQuitter().addActionListener(new ActionListener() {

			  public void actionPerformed(ActionEvent event) {
					int option = JOptionPane.showConfirmDialog(
	                        null, 
	                        "Voulez-vous quitter le jeu?",
	                        "Quitter ", 
	                        JOptionPane.YES_NO_OPTION, 
	                        JOptionPane.QUESTION_MESSAGE);
	                if (option == JOptionPane.YES_OPTION)
	                        System.exit(0);
			  }

			});
		
		
		
		
		
	}

	
	

	

}
