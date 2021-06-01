import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Divertisement implements Item,AImage,ADeponses,APrix{
	private static int nbDiver = 0;
	private int id;
	private String type;
	private int tauxSatisfaction;
	protected Image img;
	
	public Divertisement(String type,int tauxSatisfaction) {
		this.type=type;
		nbDiver++;		
		id = nbDiver;
		this.tauxSatisfaction = tauxSatisfaction;
	}

	public int getTauxSatisfaction() {
		return tauxSatisfaction;
	}
	
	public int getPrix() {
		return 10000*tauxSatisfaction;
	}

	public int getDeponses() {
		return 5*tauxSatisfaction;
	}
	
	
	public void dessinerImage(Graphics g,VilleJPanel v,int i,int j, int tailleCase) {

		g.drawImage(img,j*tailleCase,(i+1)*tailleCase,tailleCase,tailleCase, v);
	}
	
	public String getStringID() {
		return String.format ("%s%02d ", "D"+type.charAt(0), id);
	}

	public String toString() { 
		return type +" No " + id +" prix="+getPrix()+" pims";
	}
	public Color getColor() {
		return Color.cyan;
	}

}
