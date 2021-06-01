import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Restaurant extends Commerce{
	
	private int nbTables;
	public Restaurant() {
		super("Restaurant", (int)(Math.random()*61+20));
		nbTables = (int)(Math.random()*21+8);
		try {
			img = ImageIO.read(new File("food.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public int getNbTables() {
		return nbTables;
	}

	public int getProfit() {
		return nbTables*(int)(Math.random()*1800+800);
	}
	

	public int getDeponses() {
		return nbTables*(int)(Math.random()*600+400);
	}
	
	public Color getColor() {
		return new Color(0,191,255);
	}
}
