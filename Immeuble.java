import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Immeuble extends Habitation implements AEtage{



	public static final int MAX_ETAGES = 10;
	private int nbEtages;

	public int getNbEtages() {

		return nbEtages;
	}

	public Immeuble() {
		super("Immeuble", 100);
		nbEtages = (int)(Math.random()*(MAX_ETAGES+1));
		try {
			img = ImageIO.read(new File("imm.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public int getNbHabitants() {
		return (nbEtages*surface)/30;
	}

	public Color getColor() {
		return Color.ORANGE;
	}
	
	
	
}
