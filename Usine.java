import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Usine extends Batiment implements AEtage{

	public Usine() {
		super("Usine", (int)(Math.random()*301+50));
		try {
			img = ImageIO.read(new File("factory.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public int getPrix() {
		return surface*2000;
	}

	public int getLoyer() {
		return (int)(0.05*getPrix());
	}

	public int getNbEtages() {
		return 3;
	}

	
	public Color getColor() {
		return Color.RED;
	}


	
	
}
