import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Parc extends Divertisement{

	public Parc() {
		super("Parc", (int)(Math.random()*45+30));
		try {
			img = ImageIO.read(new File("parc.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public Color getColor() {
		return new Color(124,252,0);
	}


}
