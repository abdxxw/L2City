import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VilleJPanel extends JPanel{

	private String nom;
	private Item[][] terrain;
	private int tailleCase;
	private Image lock;
	private boolean sellMode;
	private JLabel info;
	private JLabel menu;
	private JButton acheter;
	private JButton vendre;
	private JButton releverLoyer;
	private JButton payerDeponses;
	private JButton releverProfis;
	private JButton quitter;
	private ArrayList <JLabel> idItems;
	
	public VilleJPanel(String nom,int n, int tailleCase) {
		
		setPreferredSize(new Dimension(tailleCase*n+200,tailleCase*(n+1)));
		setLayout(null);
		this.nom = nom;
		terrain = new Item[n][n];
		this.tailleCase =tailleCase;
		sellMode=false;
		String temp = "PIMS : 1000000 / Action : 0 / Habitants : 0";
		info = new JLabel(temp);
		idItems = new ArrayList <JLabel>();
		menu = new JLabel("Menu :");
		acheter = new JButton("Acheter");
		vendre = new JButton("Vendre");
		releverLoyer = new JButton("Relever Loyer");
		payerDeponses = new JButton("Payer Depenses");
		releverProfis = new JButton("Relever Profits");
		quitter = new JButton("Quitter");
		add(menu);
		add(acheter);
		add(vendre);
		add(releverLoyer);
		add(payerDeponses);
		add(releverProfis);
		add(quitter);
		try {
			lock = ImageIO.read(new File("lock.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
		
		for(int i=0;i<terrain.length;i++) {
			terrain[i][n/4] = new Route(TypeRoute.VERTICALE);
			terrain[i][n/2] = new Route(TypeRoute.VERTICALE);
			terrain[i][3*n/4] = new Route(TypeRoute.VERTICALE);
			
		}
		for(int j=0;j<terrain[0].length;j++) {
			terrain[n/4][j] = new Route(TypeRoute.HORIZONTALE);
			terrain[n/2][j] = new Route(TypeRoute.HORIZONTALE);
			terrain[3*n/4][j] = new Route(TypeRoute.HORIZONTALE);
		}

		terrain[n/4][n/4] = new Route(TypeRoute.INTERSECTION);
		terrain[n/2][n/4] = new Route(TypeRoute.INTERSECTION);
		terrain[3*n/4][n/4] = new Route(TypeRoute.INTERSECTION);
		terrain[n/4][n/2] = new Route(TypeRoute.INTERSECTION);
		terrain[n/2][n/2] = new Route(TypeRoute.INTERSECTION);
		terrain[3*n/4][n/2] = new Route(TypeRoute.INTERSECTION);
		terrain[n/4][3*n/4] = new Route(TypeRoute.INTERSECTION);
		terrain[n/2][3*n/4] = new Route(TypeRoute.INTERSECTION);
		terrain[3*n/4][3*n/4] = new Route(TypeRoute.INTERSECTION);
				
	}
	
	
	public JButton getAcheter() {
		return acheter;
	}

	public JButton getVendre() {
		return vendre;
	}

	public JButton getReleverLoyer() {
		return releverLoyer;
	}

	public JButton getPayerDeponses() {
		return payerDeponses;
	}

	public JButton getReleverProfis() {
		return releverProfis;
	}

	public JButton getQuitter() {
		return quitter;
	}

	public JLabel getInfo(){
		return info;
	}
	public void setSellMode(boolean b){
		sellMode = b;
	}
	public boolean ajouter(Item b) {
		int x = (int) (Math.random()*terrain.length);
		int y = (int) (Math.random()*terrain.length);
		
		if(terrain[x][y] != null)
			return false;
		else {
			terrain[x][y] = b;   
			return true;
		}

		
	}	
	public int supprimer(String id) {
		
		
		for(int i=0;i<terrain.length;i++) 
			for(int j=0;j<terrain[i].length;j++) 
				if((terrain[i][j] != null)&&(terrain[i][j].getStringID().replaceAll("\\s","").equals(id))) {
					int x=-1;
					if(terrain[i][j] instanceof APrix)
						x = ((APrix)terrain[i][j]).getPrix();
					terrain[i][j]=null;
					return x;
				}
		return -1;
		
		

		
	}
	public APrix recherche(String id) {
		
		
		for(int i=0;i<terrain.length;i++) 
			for(int j=0;j<terrain[i].length;j++) 
				if((terrain[i][j] != null)&&(terrain[i][j].getStringID().replaceAll("\\s","").equals(id))&&(terrain[i][j] instanceof APrix)) {
					return((APrix)terrain[i][j]);
				}
		return null;
		
		

		
	}
	
	public void afficherVille() {
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if(terrain[i][j]== null)
					System.out.print("  .   ");
				else
					System.out.print(terrain[i][j].getStringID()+" ");
			}
					System.out.println();
		}
			
	}
	
	
	
	public int releverLoyer() {
		int s=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Batiment))
					s+=((Batiment) terrain[i][j]).getLoyer();
			}
		}
		return s;
	}
	
	public int compterHabitants() {
		int n=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Habitation)){
					n+=((Habitation)terrain[i][j]).getNbHabitants();
				}
					
			}
		}
		return n;
	}
	public void afficherBatimentAEtage(int nbE){
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof AEtage)){
					if(((AEtage) terrain[i][j]).getNbEtages() >= nbE)
						System.out.println(terrain[i][j].toString());
				}
					
			}
		}
	}

	public int CalculeProfit() {
		int s=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Commerce))
					s+=((Commerce) terrain[i][j]).getProfit();
			}
		}
		return s;
	}
	public int CaculeDeponse() {
		int s=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof ADeponses))
					s+=((ADeponses) terrain[i][j]).getDeponses();
			}
		}
		return s;
	}
	public int CaculeTaxe() {
		int s=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Commerce))
					s+=((Commerce) terrain[i][j]).getTaxe();
			}
		}

		return s;
	}
	
	public boolean VillePleine() {
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if(terrain[i][j] == null)
						return false;
			}
		}
		return true;
	}
	
	public int rayonHabitation(int x,int y) {
		int n=0;
		for(int i=x-2;i<=x+2;i++) {
			for(int j=y-2;j<=y+2;j++) {
				try{		
					if((terrain[i][j] != null)&&(terrain[i][j] instanceof Habitation)){
						n++;
					}
				}catch(ArrayIndexOutOfBoundsException aiob){
					continue;
				}
			}
		}
		return n;
	
	}
	public int compterUsinesHabitation() {
		int n=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Usine)){
					n+=rayonHabitation(i,j);
				}
					
			}
		}
		return n;
	}
	

	public int compterCentreDivertissementHabitation(){
		int n=0;
		for(int i=0;i<terrain.length;i++) {
			for(int j=0;j<terrain[i].length;j++) {
				if((terrain[i][j] != null)&&(terrain[i][j] instanceof Divertisement)){
					n+=rayonHabitation(i,j);
				}
					
			}
		}
		return n;
	}
	
	public String toString() {
		return "Ville " + nom + " nbHab=" + compterHabitants();
	}
	
	public VilleJPanel clone() {
		return new VilleJPanel(nom,terrain.length,tailleCase);
	}
	
	public void paintComponent (Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4.0F));
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		info.setBounds(getWidth()/2-150,-30,350,100);
		add(info);
		menu.setFont(new Font("", Font.PLAIN, 30));
		menu.setBounds(getWidth()-150,-30,150,100);
		acheter.setBounds(getWidth()-180,50,150,30);
		vendre.setBounds(getWidth()-180,90,150,30);
		releverLoyer.setBounds(getWidth()-180,130,150,30);
		payerDeponses.setBounds(getWidth()-180,170,150,30);
		releverProfis.setBounds(getWidth()-180,210,150,30);
		quitter.setBounds(getWidth()-180,250,150,30);
        
		for(JLabel l : idItems)
			remove(l);
		
		for(int i=0;i<terrain.length;i++){
			for(int j=0;j<terrain[i].length;j++){
				if(terrain[i][j] != null){
					g.setColor(terrain[i][j].getColor());
					g.fillRect(j*tailleCase,(i+1)*tailleCase,tailleCase,tailleCase);
					if(terrain[i][j] instanceof Route) {
						g2.setColor(Color.WHITE);
						switch (((Route) terrain[i][j]).getTypeRoute()) {
						case VERTICALE: g2.drawLine((j*tailleCase)+tailleCase/2, ((i+1)*tailleCase)+tailleCase/3, 
								((j+1)*tailleCase)-tailleCase/2, ((i+2)*tailleCase)-tailleCase/2);
						
							break;
						case INTERSECTION: g2.drawRect((j*tailleCase)+tailleCase/6, ((i+1)*tailleCase)+tailleCase/6, 
								tailleCase-tailleCase/3, tailleCase-tailleCase/3);
						
							break;
						case HORIZONTALE: g2.drawLine((j*tailleCase)+tailleCase/3, ((i+1)*tailleCase)+tailleCase/2, 
								((j+1)*tailleCase)-tailleCase/3, ((i+2)*tailleCase)-tailleCase/2);
							break;
						default:
							break;
						}
					}
					
					if(terrain[i][j] instanceof AImage) {
						if(!sellMode)
							((AImage)terrain[i][j]).dessinerImage(g, this, i, j, tailleCase);
						else{
							JLabel temp = new JLabel(terrain[i][j].getStringID());
							idItems.add(temp);
							temp.setFont(new Font("", Font.PLAIN, tailleCase/3));
							temp.setBounds(j*tailleCase+5,(i+1)*tailleCase,tailleCase,tailleCase);
							add(temp);
							
						}
					}

				}else {
					g.setColor(Color.WHITE);
					g.fillRect(j*tailleCase,(i+1)*tailleCase,tailleCase,tailleCase);
			    	g.drawImage(lock,j*tailleCase,(i+1)*tailleCase,tailleCase,tailleCase, this);
				}
			}
		}
	}


}
