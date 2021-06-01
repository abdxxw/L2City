import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CentreCommercial extends Commerce implements AEtage{


	public static final int MAX_MAGASINS = 40;
	private int nbMagasins;
	
	public CentreCommercial() {
		super("CentreCommercial", (int)(Math.random()*450+100));
		nbMagasins = (int)(Math.random()*(MAX_MAGASINS+1));
		try {
			img = ImageIO.read(new File("mall.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	


	public int getNbMagasins() {
		return nbMagasins;
	}

	public int getProfit() {
		return nbMagasins*(int)(Math.random()*2001+3000);
	}

	public int getDeponses() {
		return nbMagasins*(int)(Math.random()*2500+1000);
	}

	public int getNbEtages() {
		return nbMagasins/10;
	}

	public Color getColor() {
		return new Color(0,255,255);
	}
	




}
