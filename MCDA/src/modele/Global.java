package modele;

import java.util.ArrayList;


public class Global {
	
	private static Global instance;
	
	private int nbCriteres;
	private int nbAlternatives;
	private int nbSources;
	private Alternative[][] alternatives = new Alternative[nbCriteres][nbAlternatives];
	private double[][] matriceInteractionsPoids = new double[nbSources][nbSources];
	
	private Global() {
		super();
	}
	
	public static Global getInstance(){
		if (instance==null) {
			instance = new Global();
		}
		return instance;
	}
	
	public Global(int nbCriteres, int nbAlternatives, int nbSources, Alternative[][] alternatives, double[][] matriceInteractionsPoids) {
		super();
		this.nbCriteres = nbCriteres;
		this.nbAlternatives = nbAlternatives;
		this.nbSources = nbSources;
		this.alternatives = alternatives;
		this.matriceInteractionsPoids = matriceInteractionsPoids;
	}
	public int getNbCriteres() {
		return nbCriteres;
	}
	public void setNbCriteres(int nbCriteres) {
		this.nbCriteres = nbCriteres;
	}
	public int getNbAlternatives() {
		return nbAlternatives;
	}
	public void setNbAlternatives(int nbAlternatives) {
		this.nbAlternatives = nbAlternatives;
	}
	public int getNbSources() {
		return nbSources;
	}
	public void setNbSources(int nbSources) {
		this.nbSources = nbSources;
	}
	public Alternative[][] getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(Alternative[][] altenatives) {
		this.alternatives = altenatives;
	}	
	public double[][] getMatriceInteractionsPoids() {
		return matriceInteractionsPoids;
	}
	public void setMatriceInteractionsPoids(double[][] matriceInteractionsPoids) {
		this.matriceInteractionsPoids = matriceInteractionsPoids;
	}

	public ArrayList<Double> calculDesAlphasMontant(int numAltenative) {
		ArrayList<Double> listAlpha= new ArrayList<Double>();
		for (int i = 0; i < nbCriteres; i++) {
			for (int j = i + 1; j < nbCriteres; j++) {
				double a1 = alternatives[i][numAltenative].getA();
				double a2 = alternatives[j][numAltenative].getA();
				double b1 = alternatives[i][numAltenative].getB();
				double b2 = alternatives[j][numAltenative].getB();
				if ((a1 < a2) && (b2 < b1) || (a1 > a2) && (b2 > b1)) {
					double X = (b2 * a1 - a2 * b1) / (b2 - a2 - b1 + a1);
					double alphaM = (X-a1)/(b1-a1);
					if (!listAlpha.contains(alphaM)){
						listAlpha.add(alphaM);						
					}
				}
			}

		}
		return listAlpha;

	}
	
	public ArrayList<Double> calculDesAlphasDescendant(int numAltenative) {
		ArrayList<Double> listAlpha= new ArrayList<Double>();
		for (int i = 0; i < nbCriteres; i++) {
			for (int j = i + 1; j < nbCriteres; j++) {
				double c1 = alternatives[i][numAltenative].getC();
				double c2 = alternatives[j][numAltenative].getC();
				double d1 = alternatives[i][numAltenative].getD();
				double d2 = alternatives[j][numAltenative].getD();
				if ((c1 < c2) && (d2 < d1) || (c1 > c2) && (d2 > d1)) {
					double Y = (d2 * c1 - d1 * c2) / (d2 - c2 - d1 + c1);
					double alphaD = (d1-Y)/(d1-c1);
					if (!listAlpha.contains(alphaD)){
						listAlpha.add(alphaD);						;
					}
				}
			}

		}
		return listAlpha;

	}
	
	public ArrayList<double[]> calculDesXMontants(int numAlternative, ArrayList<Double> listAlphas) {
		ArrayList<double[]> listX= new ArrayList<double[]>();
		for (double alpha : listAlphas){
			double[] tabXi = new double[nbCriteres];
			for (int k=0; k<nbCriteres; k++){
				double a = alternatives[k][numAlternative].getA();
				double b = alternatives[k][numAlternative].getB();
				double Xi = a + (b-a)*alpha;
				tabXi[k]=Xi;
			}
			listX.add(tabXi);
		}
		return listX;
	}
	
	public ArrayList<double[]> calculDesXDescendants(int numAlternative, ArrayList<Double> listAlphas) {
		ArrayList<double[]> listX= new ArrayList<double[]>();
		for (double alpha : listAlphas){
			double[] tabXi = new double[nbCriteres];
			for (int k=0; k<nbCriteres; k++){
				double c = alternatives[k][numAlternative].getC();
				double d = alternatives[k][numAlternative].getD();
				double Xi = d - (d-c)*alpha;
				tabXi[k]=Xi;
			}
			listX.add(tabXi);
		}
		return listX;
	}

	public double choquet2Additive(int numAlternative, IndicateurDistributionAgregee indicateur){
		double result = 0.0;
		double sommeVi = 0.0;
		double sommeIij = 0.0;
		for (int i=0; i<nbCriteres; i++){
			sommeVi = sommeVi + matriceInteractionsPoids[i][i]*alternatives[i][numAlternative].getIndicateur(indicateur);
		}
		for (int i=0; i<nbCriteres-1; i++){
			for (int j=i+1; j<nbCriteres; j++){
				double terme = matriceInteractionsPoids[i][j]*(alternatives[i][numAlternative].getIndicateur(indicateur) - alternatives[j][numAlternative].getIndicateur(indicateur));
				if (terme>=0){
					sommeIij += terme;
				}
				else{
					sommeIij -= terme;
				}
			}
		}
		result = sommeVi - 0.5*sommeIij;
		return result;
	}
	
	public double choquet2AdditiveX(double[] tabXi){
		double result = 0.0;
		double sommeVi = 0.0;
		double sommeIij = 0.0;
		for (int i=0; i<tabXi.length; i++){
			sommeVi = sommeVi + matriceInteractionsPoids[i][i]*tabXi[i];
		}
		for (int i=0; i<nbCriteres-1; i++){
			for (int j=i+1; j<nbCriteres; j++){
				double terme = matriceInteractionsPoids[i][j]*(tabXi[i] - tabXi[j]);
				sommeIij = terme>=0 ? sommeIij+terme : sommeIij-terme;
			}
		}
		result = sommeVi - 0.5*sommeIij;
		return result;
	}
	
	public ArrayList<Point> choquetGlobalPourUneAlternative(int numAlternative){
		ArrayList<Point> choquetGlobalPourUneAlternative = new ArrayList<Point>();
		
		//ajout du point a obtenu par Choquet-2-additive
		choquetGlobalPourUneAlternative.add(new Point("a", choquet2Additive(numAlternative, IndicateurDistributionAgregee.a), 0.0));
		
		//ajout des X obtenus avec les intersections croissantes
		ArrayList<Double> listAlphasMontant = calculDesAlphasMontant(numAlternative);
		if (listAlphasMontant != null){
			ArrayList<double[]> listXiMontants = calculDesXMontants(numAlternative, listAlphasMontant);
			for (int i=0; i<listAlphasMontant.size(); i++){
				choquetGlobalPourUneAlternative.add(new Point("x", choquet2AdditiveX(listXiMontants.get(i)), listAlphasMontant.get(i)));
			}
		}
		//ajout du point b obtenu par Choquet-2-additive
		choquetGlobalPourUneAlternative.add(new Point("b", choquet2Additive(numAlternative, IndicateurDistributionAgregee.b), 1.0));
		
		//ajout du point c obtenu par Choquet-2-additive
		choquetGlobalPourUneAlternative.add(new Point("c", choquet2Additive(numAlternative, IndicateurDistributionAgregee.c), 1.0));
		
		//ajout des X obtenus avec les intersections décroissantes
		ArrayList<Double> listAlphasDescendants = calculDesAlphasDescendant(numAlternative);
		if (listAlphasDescendants != null){
			ArrayList<double[]> listXiDescendants = calculDesXDescendants(numAlternative, listAlphasDescendants);		
			for (int i=0; i<listAlphasDescendants.size(); i++){
				choquetGlobalPourUneAlternative.add(new Point("x", choquet2AdditiveX(listXiDescendants.get(i)), listAlphasDescendants.get(i)));
			}
		}
		
		//ajout du point d obtenu par Choquet-2-additive
		choquetGlobalPourUneAlternative.add(new Point("d", choquet2Additive(numAlternative, IndicateurDistributionAgregee.d), 0.0));
		
		
		return rangerParAbscisseCroissante(choquetGlobalPourUneAlternative);
	}
	
	public double calculMoyenneInf(int numAlternative){
		double moyenneInf = 0;
		ArrayList<Point> listePointsTriee = choquetGlobalPourUneAlternative(numAlternative);
		//on détermine l'ordonnée du point dont l'abscisse vaut E*inf
		double ordonneePointDAbscisseEinf=0;
		int i=0;
		double abscissePointA = listePointsTriee.get(0).getAbscisse();
		int j=1;
		while (!listePointsTriee.get(i).getName().equals("b")){
			double abscisse1 = listePointsTriee.get(i).getAbscisse();
			double ordonnee1 = listePointsTriee.get(i).getOrdonnee();
			double abscisse2 = listePointsTriee.get(i+1).getAbscisse();
			double ordonnee2 = listePointsTriee.get(i+1).getOrdonnee();
			ordonneePointDAbscisseEinf += 0.5*(abscisse2-abscisse1)*(ordonnee1+ordonnee2);
			i++;
		}
		double abscissePointB = listePointsTriee.get(i).getAbscisse();
		//cas pente infinie
		if (abscissePointA==abscissePointB){
			moyenneInf = abscissePointA;
		}
		//cas normal
		else{
			ordonneePointDAbscisseEinf = ordonneePointDAbscisseEinf/(abscissePointB-abscissePointA);
			//on détermine l'abscisse du point d'ordonnée "ordonneePointDAbscisseEinf"
			while (listePointsTriee.get(j).getOrdonnee() < ordonneePointDAbscisseEinf){
				j++;
			}
			moyenneInf = trouverAbscissePointAPartirOrdonneeEt2PointsDroite(ordonneePointDAbscisseEinf, listePointsTriee.get(j-1), listePointsTriee.get(j));
		}
		return moyenneInf;
	}
	
	public double calculMoyenneSup(int numAlternative){
		double moyenneSup = 0;
		ArrayList<Point> listePointsTriee = choquetGlobalPourUneAlternative(numAlternative);
		//on détermine l'ordonnée du point dont l'abscisse vaut E*sup
		double ordonneePointDAbscisseEsup=0;
		int i=0;
		while (!listePointsTriee.get(i).getName().equals("c"))i++;
		double abscissePointC = listePointsTriee.get(i).getAbscisse();
		int j=i;
		while (!listePointsTriee.get(i).getName().equals("d")){
			double abscisse1 = listePointsTriee.get(i).getAbscisse();
			double ordonnee1 = listePointsTriee.get(i).getOrdonnee();
			double abscisse2 = listePointsTriee.get(i+1).getAbscisse();
			double ordonnee2 = listePointsTriee.get(i+1).getOrdonnee();
			ordonneePointDAbscisseEsup += 0.5*(abscisse2-abscisse1)*(ordonnee1+ordonnee2);
			i++;
		}
		double abscissePointD = listePointsTriee.get(i).getAbscisse();
		//cas pente infinie
		if (abscissePointC==abscissePointD){
			moyenneSup = abscissePointC;
		}
		//cas normal
		else{
			ordonneePointDAbscisseEsup = ordonneePointDAbscisseEsup/(abscissePointD-abscissePointC);
			//on détermine l'abscisse du point d'ordonnée "ordonneePointDAbscisseEsup"
			while (listePointsTriee.get(j).getOrdonnee() > ordonneePointDAbscisseEsup){
				j++;
			}
			moyenneSup = trouverAbscissePointAPartirOrdonneeEt2PointsDroite(ordonneePointDAbscisseEsup, listePointsTriee.get(j-1), listePointsTriee.get(j));
		}
		return moyenneSup;
	}
	
	public double indicateurPosition(int numAlternative){
		return 0.5*(calculMoyenneInf(numAlternative)+calculMoyenneSup(numAlternative));
	}
	
	public double indicateurImprecisionMoyenne(int numAlternative){
		return calculMoyenneSup(numAlternative)-calculMoyenneInf(numAlternative);
	}
	
	/*
	 * méthodes utilitaires
	 */
	public ArrayList<Point> rangerParAbscisseCroissante(ArrayList<Point> listePoints){
		ArrayList<Point> listePointsTrieeParAbscisseCroissante = listePoints;
		for (int i=listePoints.size()-1; i>=0; i--){
			for (int j=0; j<i; j++){
				if (listePointsTrieeParAbscisseCroissante.get(j).getAbscisse() > listePointsTrieeParAbscisseCroissante.get(j+1).getAbscisse()){
					Point pointAuxiliaire = listePointsTrieeParAbscisseCroissante.get(j);
					listePointsTrieeParAbscisseCroissante.set(j, listePointsTrieeParAbscisseCroissante.get(j+1));
					listePointsTrieeParAbscisseCroissante.set(j+1, pointAuxiliaire);					
				}
			}
		}
		return listePointsTrieeParAbscisseCroissante;
	}
	
	//retourne, à partir d'une ordonnée et des coordonnées de 2 points, l'abscisse du point correspondant à l'ordonnée et appartenant à la droite
	public double trouverAbscissePointAPartirOrdonneeEt2PointsDroite(double ordonnee, Point point1, Point point2){
		double x1 = point1.getAbscisse();
		double x2 = point2.getAbscisse();
		double y1 = point1.getOrdonnee();
		double y2 = point2.getOrdonnee();
		return x2+(ordonnee-y2)*(x2-x1)/(y2-y1);
	}
	
	/*
	 * méthodes d'affichage
	 */
	public String afficherIndicateursPourUneAlternative(int numAlternative){
		String affichage = "";
		for (int j=0; j<nbCriteres; j++){
			affichage += "indicateurs du critère "+j+" avec l'alternative "+numAlternative+" :\n";
			affichage += "a = "+alternatives[j][numAlternative].getA()+"\n";
			affichage += "b = "+alternatives[j][numAlternative].getB()+"\n";
			affichage += "c = "+alternatives[j][numAlternative].getC()+"\n";
			affichage += "d = "+alternatives[j][numAlternative].getD()+"\n";
		}
		return affichage;
	}
	
	public String afficherIndicateursChoquets(){
		String affichage = "";
		for (int i=0; i<nbAlternatives; i++){
			affichage += "indicateurs de Choquet de l'alternative "+(i+1)+" :\n";
			affichage += "C(a) = "+choquet2Additive(i, IndicateurDistributionAgregee.a)+"\n";
			affichage += "C(b) = "+choquet2Additive(i, IndicateurDistributionAgregee.b)+"\n";
			affichage += "C(c) = "+choquet2Additive(i, IndicateurDistributionAgregee.c)+"\n";
			affichage += "C(d) = "+choquet2Additive(i, IndicateurDistributionAgregee.d)+"\n";
		}
		return affichage;
	}
	
	public String afficherPointsChoquetAdditifPourUneAlternative(int numAlternative){
		String affichage = "";
		affichage += "liste des points obtenus par Choquet 2-additive pour l'alternative "+numAlternative+" :\n";
		for (Point point : choquetGlobalPourUneAlternative(numAlternative)){
			affichage += "point : type "+point.getName()+" abscisse : "+point.getAbscisse()+" ordonnée "+point.getOrdonnee()+"\n";
		}
		affichage += "\n";
		return affichage;
	}
	
	public String afficherMoyennesInfEtSup(int numAlternative){
		String affichage = "";
		affichage += "calcul des moyennes inférieure et supérieure de l'alternative "+numAlternative+" :\n";
		affichage += "E*inf : "+calculMoyenneInf(numAlternative)+"\n";
		affichage += "E*sup : "+calculMoyenneSup(numAlternative)+"\n";
		return affichage;
	}
	
	public String afficherIndicateursPositionEtImprecisionMoyenne(int numAlternative){
		String affichage = "";
		affichage += "calcul des indicateurs de position et d'imprécision moyenne de l'alternative "+numAlternative+" :\n";
		affichage += "MD(pi) = "+indicateurPosition(numAlternative)+"\n";
		affichage += "Delta(pi) = "+indicateurImprecisionMoyenne(numAlternative)+"\n";
		return affichage;
	}
	
	public String toutAfficher(int numAlternative){
		String affichage = "";
		affichage += afficherIndicateursPourUneAlternative(numAlternative)+"\n";
		affichage += afficherPointsChoquetAdditifPourUneAlternative(numAlternative)+"\n";
		affichage += afficherMoyennesInfEtSup(numAlternative)+"\n";
		affichage += afficherIndicateursPositionEtImprecisionMoyenne(numAlternative)+"\n";
		return affichage;
	}
	
}
