import java.awt.Color;

public class Route implements Item{
	private static int nbRoutes = 0;
	private int id;
	private TypeRoute t;
	
	public Route(TypeRoute t) {
		this.t=t;
		nbRoutes++;		
		id = nbRoutes;
	}

	public TypeRoute getTypeRoute() {
		return t;
	}
	public String getStringID() {
		return String.format ("%s%02d", "R"+t.toString().charAt(0), id);
	}

	public Color getColor() {
		return new Color(31,31,31);
	}

}
