package modele;

public class Intervalle {

	private double borneInf;
	private double borneSup;
	
	public Intervalle(double borneInf, double borneSup) {
		super();
		this.borneInf = borneInf;
		this.borneSup = borneSup;
	}
	
	public Intervalle(String strIntervalle){
		super();
		int indiceVirgule = strIntervalle.indexOf(",");
		this.borneInf = Double.parseDouble(strIntervalle.substring(0, indiceVirgule));
		this.borneSup = Double.parseDouble(strIntervalle.substring(indiceVirgule+1));
	}

	public double getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(double borneInf) {
		this.borneInf = borneInf;
	}

	public double getBorneSup() {
		return borneSup;
	}

	public void setBorneSup(double borneSup) {
		this.borneSup = borneSup;
	}
	
	public boolean contains(double valeur){
		return (valeur>=borneInf && valeur<=borneSup);
	}
	
}
