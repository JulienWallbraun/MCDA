package main;

import modele.Alternative;
import modele.Global;
import modele.Intervalle;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbCriteres = 5;
		int nbAlternatives = 2;
		int nbSources = 5;
//		GenerateurIntervalle gen = new GenerateurIntervalle();
//		gen.creatIntervalle(5);
		double[][] matriceInteractionsPoids = {{0.3,  0.0,  0.05, 0.1,  0.0},
											   {0.0,  0.2,  0.05, 0.1,  0.0},
											   {0.05, 0.05, 0.1,  0,  	0.05},
											   {0.1,  0.1,  0.0,  0.2,  0.1},
											   {0.0,  0.0,  0.05, 0.1,  0.2}};

		
		Alternative a1c1 = new Alternative();
//		Intervalle[] test = {gen.getListIntervalle().get(0), gen.getListIntervalle().get(1),gen.getListIntervalle().get(2),gen.getListIntervalle().get(3),gen.getListIntervalle().get(4)};
		Intervalle[] t11= {new Intervalle(6.0, 9.0), new Intervalle(6.0, 9.0), new Intervalle(5.0, 9.0), new Intervalle(4.0, 8.0), new Intervalle(4.0, 7.0)};
		a1c1.setTabIntervalle(t11);
		
		Alternative a2c1 = new Alternative();
		Intervalle[] t21 = {new Intervalle(10.0, 13.0), new Intervalle(12.0, 15.0), new Intervalle(12.0, 15.0), new Intervalle(12.0, 15.0), new Intervalle(9.0, 13.0)};
		a2c1.setTabIntervalle(t21);
		
		Alternative a1c2 = new Alternative();
		Intervalle[] t12 = {new Intervalle(6.0, 9.0), new Intervalle(7.0, 10.0), new Intervalle(6.0, 9.0), new Intervalle(6.0, 9.0), new Intervalle(7.0, 9.0)};
		a1c2.setTabIntervalle(t12);
		
		Alternative a2c2 = new Alternative();
		Intervalle[] t22 = {new Intervalle(13.0, 16.0), new Intervalle(13.0, 16.0), new Intervalle(15.0, 17.0), new Intervalle(15.0, 16.0), new Intervalle(14.0, 16.0)};
		a2c2.setTabIntervalle(t22);
		
		Alternative a1c3 = new Alternative();	
		Intervalle[] t13 = {new Intervalle(6.0, 9.0), new Intervalle(6.0, 9.0), new Intervalle(6.0, 9.0), new Intervalle(4.0, 7.0), new Intervalle(4.0, 7.0)};
		a1c3.setTabIntervalle(t13);
		
		Alternative a2c3 = new Alternative();
		Intervalle[] t23 = {new Intervalle(15.0, 17.0), new Intervalle(15.0, 17.0), new Intervalle(15.0, 17.0), new Intervalle(15.0, 17.0), new Intervalle(15.0, 17.0)};
		a2c3.setTabIntervalle(t23);
		
		Alternative a1c4 = new Alternative();	
		Intervalle[] t14 = {new Intervalle(9.0, 10.0), new Intervalle(9.0, 10.0), new Intervalle(9.0, 10.0), new Intervalle(8.0, 11.0), new Intervalle(8.0, 11.0)};
		a1c4.setTabIntervalle(t14);
		
		Alternative a2c4 = new Alternative();
		Intervalle[] t24 = {new Intervalle(8.0, 11.0), new Intervalle(8.0, 11.0), new Intervalle(8.0, 11.0), new Intervalle(7.0, 10.0), new Intervalle(7.0, 10.0)};
		a2c4.setTabIntervalle(t24);
		
		Alternative a1c5 = new Alternative();		
		Intervalle[] t15 = {new Intervalle(4.0, 7.0), new Intervalle(4.0, 7.0), new Intervalle(6.0, 7.0), new Intervalle(4.0, 7.0), new Intervalle(6.0, 7.0)};
		a1c5.setTabIntervalle(t15);
		
		Alternative a2c5 = new Alternative();
		Intervalle[] t25 = {new Intervalle(15.0, 17.0), new Intervalle(15.0, 17.0), new Intervalle(13.0, 16.0), new Intervalle(13.0, 16.0), new Intervalle(13.0, 16.0)};
		a2c5.setTabIntervalle(t25);
		
		Alternative[][] alternatives = {{a1c1, a2c1},{a1c2,a2c2},{a1c3, a2c3},{a1c4, a2c4},{a1c5, a2c5}};
		
		Global global = new Global(nbCriteres, nbAlternatives, nbSources, alternatives, matriceInteractionsPoids);
		
		//résultats pour l'alternative "evacuate" de l'énoncé
		System.out.println("résultats pour l'alternative \"evacuate\" de l'énoncé");
		System.out.println(global.toutAfficher(0));
		
		System.out.println("\n\n\n");
		
		//résultats pour l'alternative "sheltering" de l'énoncé
		System.out.println("résultats pour l'alternative \"sheltering\" de l'énoncé");
		System.out.println(global.toutAfficher(1));
		
		
		/*
		 * exemple du prof avec intersections pour vérification (seulement 1 alternative et 2 sources)
		 */
		int nbAlternativesBis = 1;
		int nbSourcesBis = 2;
		
		Alternative a3c1 = new Alternative();		
		Intervalle[] t31 = {new Intervalle(4, 10), new Intervalle(5, 5.5)};
		a3c1.setTabIntervalle(t31);
		
		Alternative a3c2 = new Alternative();		
		Intervalle[] t32 = {new Intervalle(6, 7.5), new Intervalle(6.5, 7)};
		a3c2.setTabIntervalle(t32);
		
		Alternative a3c3 = new Alternative();		
		Intervalle[] t33 = {new Intervalle(5.5, 9), new Intervalle(8, 8.5)};
		a3c3.setTabIntervalle(t33);
		
		Alternative a3c4 = new Alternative();		
		Intervalle[] t34 = {new Intervalle(10.5, 13), new Intervalle(11, 11.5)};
		a3c4.setTabIntervalle(t34);
		
		Alternative a3c5 = new Alternative();		
		Intervalle[] t35 = {new Intervalle(12, 14), new Intervalle(12.5, 13.5)};
		a3c5.setTabIntervalle(t35);
		
		Alternative[][] alternativesBis = {{a3c1},{a3c2},{a3c3},{a3c4},{a3c5}};
		Global globalBis = new Global(nbCriteres, nbAlternativesBis, nbSourcesBis, alternativesBis, matriceInteractionsPoids);
		System.out.println("\n\n\nexemple bis du prof :");

		System.out.println(globalBis.toutAfficher(0));
		
	}

}
