import java.awt.Graphics;
import java.awt.Image;

public abstract class Batiment implements Item,AImage,APrix{
	private static int nbBatiments = 0;
	private int id;
	private String type;
	protected int surface;
	protected Image img;
	
	public Batiment(String type, int surface) {
		nbBatiments++;		
		id = nbBatiments;
		this.type = type;
		this.surface = surface;
	}

	public static int getNbBatiments() {
		return nbBatiments;
	}

	public int getSurface() {
		return surface;
	}
	public void dessinerImage(Graphics g,VilleJPanel v,int i,int j, int tailleCase) {

    	g.drawImage(img,j*tailleCase,(i+1)*tailleCase,tailleCase,tailleCase, v);
	}
	public abstract int getPrix();
	public abstract int getLoyer();

	
	public String toString() {
		return type +" No " + id + " " + surface + "m2 prix="+getPrix()+" pims";
	}
	
	public String getStringID( ) {
		return String.format (" %c%02d ", type.charAt(0), id);
	}
	
	
	
	
	
	
	
	
}
