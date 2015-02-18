package modele;

public class Alternative {
	
	private Intervalle[] tabIntervalles;

	public Intervalle[] getTabIntervalle() {
		return tabIntervalles;
	}

	public void setTabIntervalle(Intervalle[] tabIntervalle) {
		this.tabIntervalles = tabIntervalle;
	}
	
	public double getIndicateur(IndicateurDistributionAgregee i){
		double result = 0.0;
		if (i == IndicateurDistributionAgregee.a) result = getA();
		if (i == IndicateurDistributionAgregee.b) result = getB();
		if (i == IndicateurDistributionAgregee.c) result = getC();
		if (i == IndicateurDistributionAgregee.d) result = getD();
		return result;
	}
	
	public double getA(){
		double a = 20.0;
		for (Intervalle intervalle : tabIntervalles){
			if (intervalle.getBorneInf()<a) a=intervalle.getBorneInf();
		}
		return a;
	}

	public double getB(){
		double b = 20.0;
		for (Intervalle intervalle : tabIntervalles){
			if (intervalle.getBorneInf()<b && containsForAll(intervalle.getBorneInf())) b=intervalle.getBorneInf();
		}
		return b;
	}
	
	public double getC(){
		double c = 0.0;
		for (Intervalle intervalle : tabIntervalles){
			if (intervalle.getBorneSup()>c && containsForAll(intervalle.getBorneSup())) c=intervalle.getBorneSup();
		}
		return c;
	}
	
	public double getD(){
		double d = 0.0;
		for (Intervalle intervalle : tabIntervalles){
			if (intervalle.getBorneSup()>d) d=intervalle.getBorneSup();
		}
		return d;
	}
	
	public boolean containsForAll(double valeur){
		boolean result = true;
		int i = 0;
		while (i<tabIntervalles.length && result==true){
			if (!tabIntervalles[i].contains(valeur)) result=false; i=i+1;
		}
		return result;
	}

}
