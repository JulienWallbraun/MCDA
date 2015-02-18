package modele;

public class Point {
	
	private String name;
	private double abscisse;
	private double ordonnee;

	public Point(String name, double abscisse, double ordonnee) {
		super();
		this.name = name;
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAbscisse() {
		return abscisse;
	}
	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}
	public double getOrdonnee() {
		return ordonnee;
	}
	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}
	
	

}
