import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Maison extends Habitation{

	public Maison() {
		super("Maison", (int)(Math.random()*101+20));
		try {
			img = ImageIO.read(new File("house.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public int getNbHabitants() {
		
		return surface/20;
	}


	public Color getColor() {
		return new Color(255,204,153);
	}

	


}
