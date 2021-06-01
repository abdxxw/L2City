import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cinema extends Divertisement{

	public Cinema() {
		super("Cinema", (int)(Math.random()*30+20));
		try {
			img = ImageIO.read(new File("cin.png"));
    	}
    	catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public Color getColor() {
		return new Color(204,153,255);
	}

}
