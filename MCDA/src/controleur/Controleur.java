package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Alternative;
import modele.Global;
import modele.Intervalle;
import vue.Vue_evaluationAlternatives;
import vue.Vue_matriceInteractionsPoids;
import vue.Vue_resultats;
import vue.Vue_selectionNbParametres;

public class Controleur implements ActionListener{

	Vue_selectionNbParametres vue_selectionNbParametres;
	Vue_evaluationAlternatives vue_evaluationAlternatives;
	Vue_matriceInteractionsPoids vue_matriceInteractionsPoids;
	Vue_resultats vue_resultats;

	public Controleur(Vue_selectionNbParametres vue_selectionNbParametres) {
		this.vue_selectionNbParametres = vue_selectionNbParametres;
	}

	public Controleur(Vue_evaluationAlternatives vue_evaluationAlternatives){
		this.vue_evaluationAlternatives = vue_evaluationAlternatives;	
	}

	public Controleur(Vue_matriceInteractionsPoids vue_matriceInteractionsPoids){
		this.vue_matriceInteractionsPoids = vue_matriceInteractionsPoids;	
	}

	public Controleur(Vue_resultats vue_resultats){
		this.vue_resultats = vue_resultats;	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(vue_selectionNbParametres!=null){
			if (source==vue_selectionNbParametres.getBtnValider()){
				//MAJ nbAlternatives, nbCriteres, nbSources de l'instance
				Global.getInstance().setNbAlternatives((int) vue_selectionNbParametres.getSpinnerNbAlternatives().getValue());
				Global.getInstance().setNbCriteres((int) vue_selectionNbParametres.getSpinnerNbCriteres().getValue());
				Global.getInstance().setNbSources((int) vue_selectionNbParametres.getSpinnerNbSources().getValue());

				//changement de vue
				vue_selectionNbParametres.getFrmSelectionNbParametres().setVisible(false);
				vue_evaluationAlternatives = new Vue_evaluationAlternatives();
				vue_evaluationAlternatives.getFrmFournirUnevaluation().setVisible(true);

				//test
				//			System.out.println(Global.getInstance().getNbAlternatives());
				//			System.out.println(Global.getInstance().getNbCriteres());
				//			System.out.println(Global.getInstance().getNbSources());
			}
		}

		if(vue_evaluationAlternatives!=null){
			if(source==vue_evaluationAlternatives.getBtnValider()){
				//MAJ alternatives de l'instance
				int nbAlternatives = Global.getInstance().getNbAlternatives();
				int nbCriteres = Global.getInstance().getNbCriteres();
				int nbSources = Global.getInstance().getNbSources();
				Alternative[][] alternatives = new Alternative[nbCriteres][nbAlternatives];
				for (int numAlternative=0; numAlternative<nbAlternatives; numAlternative++){
					for (int numCritere=0; numCritere<nbCriteres; numCritere++){
						//on récupère l'intervalle, ce qui nous donne l'alternative pour 1 critère
						Intervalle[] tabIntervalles = new Intervalle[nbSources];
						for (int numSource=0; numSource<nbSources; numSource++){
							//on récupère l'intervalle ici
							String strIntervalle = vue_evaluationAlternatives.getTableauEvaluationAlternatives()[numSource][numCritere*nbAlternatives+numAlternative].getText();
							//						System.out.println("intervalle (a"+numAlternative+"c"+numCritere+"s"+numSource+") = "+strIntervalle);
							Intervalle intervalle = new Intervalle(strIntervalle);
							tabIntervalles[numSource] = intervalle;
						}
						Alternative alternative = new Alternative();
						alternative.setTabIntervalle(tabIntervalles);
						//on ajoute l'alternative pour 1 critère au tableau de l'ensemble des alternatives
						alternatives[numCritere][numAlternative] = alternative;
					}
				}			
				Global.getInstance().setAlternatives(alternatives);

				//changement de vue
				vue_evaluationAlternatives.getFrmFournirUnevaluation().setVisible(false);
				vue_matriceInteractionsPoids = new Vue_matriceInteractionsPoids();
				vue_matriceInteractionsPoids.getFrmMatriceInteractionsPoids().setVisible(true);

				//test
				//			for (int numCritere=0; numCritere<Global.getInstance().getAlternatives().length; numCritere++){
				//				for (int numAlternative=0; numAlternative<Global.getInstance().getAlternatives()[numCritere].length; numAlternative++){
				//					Alternative alt = Global.getInstance().getAlternatives()[numCritere][numAlternative];
				//					for (int numSource=0; numSource<alt.getTabIntervalle().length; numSource++){
				//						System.out.println("c"+numCritere+"a"+numAlternative+"s"+numSource+" = ["+Global.getInstance().getAlternatives()[numCritere][numAlternative].getTabIntervalle()[numSource].getBorneInf()+","+Global.getInstance().getAlternatives()[numCritere][numAlternative].getTabIntervalle()[numSource].getBorneSup()+"]");
				//					}
				//				}
				//			}
				//			System.out.println("valider vue éval alternatives!");
			}
			else if (source==vue_evaluationAlternatives.getBtnRetourAccueil()){
				vue_evaluationAlternatives.getFrmFournirUnevaluation().setVisible(false);
				vue_selectionNbParametres = new Vue_selectionNbParametres();
				vue_selectionNbParametres.getFrmSelectionNbParametres().setVisible(true);				
			}
		}

		if(vue_matriceInteractionsPoids!=null){
			if (source==vue_matriceInteractionsPoids.getBtnValider()){
				//MAJ matrice des intéractions et des poids de l'instance
				int nbCriteres = Global.getInstance().getNbCriteres();
				double[][] matriceInteractionsPoids = new double[nbCriteres][nbCriteres];
				for (int i=0; i<matriceInteractionsPoids.length; i++){
					for (int j=i; j<matriceInteractionsPoids.length; j++){
						double val = Double.parseDouble(vue_matriceInteractionsPoids.getMatriceInteractionsPoids()[i][j].getText());
						matriceInteractionsPoids[i][j] = val;
						matriceInteractionsPoids[j][i] = val;
					}
				}			
				Global.getInstance().setMatriceInteractionsPoids(matriceInteractionsPoids);

				//changement de vue
				vue_matriceInteractionsPoids.getFrmMatriceInteractionsPoids().setVisible(false);
				vue_resultats = new Vue_resultats();
				vue_resultats.getFrmAfficherRsultats().setVisible(true);

				//test
				//			for (int i=0; i<Global.getInstance().getMatriceInteractionsPoids().length; i++){
				//				for (int j=0; j<Global.getInstance().getMatriceInteractionsPoids().length; j++){
				//					System.out.println("MIP("+i+","+j+") = "+Global.getInstance().getMatriceInteractionsPoids()[i][j]);
				//				}
				//			}			
				//			System.out.println("valider vue mat intér poids!");
			}

			else if (source==vue_matriceInteractionsPoids.getBtnRetourAccueil()){
				vue_matriceInteractionsPoids.getFrmMatriceInteractionsPoids().setVisible(false);
				vue_selectionNbParametres = new Vue_selectionNbParametres();
				vue_selectionNbParametres.getFrmSelectionNbParametres().setVisible(true);		
			}
		}
		
		if (vue_resultats!=null){
			if (source==vue_resultats.getBtnAfficherResultats()){
				//affichage des résultats obtenus pour l'alternative sélectionnée
				int alternativeSelectionnee = (int) vue_resultats.getSpinnerAlternativeChoisie().getValue();
				vue_resultats.setTextArea(Global.getInstance().toutAfficher(alternativeSelectionnee));
			}
			
			else if (source==vue_resultats.getBtnRetourAccueil()){
				vue_resultats.getFrmAfficherRsultats().setVisible(false);
				vue_selectionNbParametres = new Vue_selectionNbParametres();
				vue_selectionNbParametres.getFrmSelectionNbParametres().setVisible(true);
			}
		}


	}

}