package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.Global;
import controleur.Controleur;

public class Vue_evaluationAlternatives {
	
	private Controleur controleur;

	private JFrame frmFournirUnevaluation;
	private JButton btnValider;
	private JButton btnRetourAccueil;
	private JTextField[][] tableauEvaluationAlternatives;
	private JLabel lblExemple;
	private JLabel lblRentrezLesIntervalles;
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Vue_evaluationAlternatives window = new Vue_evaluationAlternatives();
//					window.frmFournirUnevaluation.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Vue_evaluationAlternatives() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controleur = new Controleur(this);
		frmFournirUnevaluation = new JFrame();
		frmFournirUnevaluation.setTitle("Fournir une \u00E9valuation des alternatives");
		frmFournirUnevaluation.setBounds(100, 100, 450, 327);
		frmFournirUnevaluation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFournirUnevaluation.getContentPane().setLayout(null);
		frmFournirUnevaluation.getContentPane().setLayout(null);
		
		btnValider = new JButton("valider l'\u00E9valuation des alternatives");
		btnValider.setBounds(10, 244, 414, 34);
		frmFournirUnevaluation.getContentPane().add(btnValider);
		btnValider.addActionListener(controleur);
		
		btnRetourAccueil = new JButton("retour accueil");
		btnRetourAccueil.setBounds(238, 33, 186, 23);
		frmFournirUnevaluation.getContentPane().add(btnRetourAccueil);
		btnRetourAccueil.addActionListener(controleur);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 68, 414, 165);
		frmFournirUnevaluation.getContentPane().add(panel);
		
		//à enlever après
//		Global.getInstance().setNbAlternatives(2);
//		Global.getInstance().setNbCriteres(2);
//		Global.getInstance().setNbSources(2);
		
		int nblignes = Global.getInstance().getNbSources();
		int nbcolonnes = Global.getInstance().getNbAlternatives() * Global.getInstance().getNbCriteres();
		
		tableauEvaluationAlternatives = new JTextField[nblignes][nbcolonnes];
		panel.setLayout(new GridLayout(nblignes + 1, nbcolonnes + 1, 1, 1));
		
		lblExemple = new JLabel("ex : \"5.5,9\" pour l'intervalle [5,5 ; 9]");
		lblExemple.setForeground(Color.RED);
		lblExemple.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblExemple.setBounds(10, 32, 414, 22);
		frmFournirUnevaluation.getContentPane().add(lblExemple);
		
		lblRentrezLesIntervalles = new JLabel("Rentrez les intervalles pour chaque triplet alternative/crit\u00E8re/source");
		lblRentrezLesIntervalles.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentrezLesIntervalles.setForeground(Color.BLUE);
		lblRentrezLesIntervalles.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblRentrezLesIntervalles.setBounds(10, 0, 414, 34);
		frmFournirUnevaluation.getContentPane().add(lblRentrezLesIntervalles);
		
		for (int i=0; i<nblignes+1; i++){
			for (int j=0; j<nbcolonnes+1; j++){
				int numAlternative = (j-1) % Global.getInstance().getNbAlternatives();
				int numCritere = (j-1) / Global.getInstance().getNbAlternatives();
				//on remplit les noms des colonnes
				if (i==0){
					if (j==0){
						panel.add(new JLabel());
					}
					else{
						panel.add(new JLabel("c"+numCritere+"a"+numAlternative));
					}
				}
				//on remplit les noms des lignes
				else if (j==0){
					panel.add(new JLabel("s"+Integer.toString(i-1)));
				}
				//on remplit le tableau des évaluations d'alternatives
				else{
					tableauEvaluationAlternatives[i-1][j-1] = new JTextField();
					panel.add(tableauEvaluationAlternatives[i-1][j-1]);	
				}
			}
		}		
		
	}

	public JFrame getFrmFournirUnevaluation() {
		return frmFournirUnevaluation;
	}

	public void setFrmFournirUnevaluation(JFrame frmFournirUnevaluation) {
		this.frmFournirUnevaluation = frmFournirUnevaluation;
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}

	public JButton getBtnRetourAccueil() {
		return btnRetourAccueil;
	}

	public void setBtnRetourAccueil(JButton btnRetourAccueil) {
		this.btnRetourAccueil = btnRetourAccueil;
	}

	public JTextField[][] getTableauEvaluationAlternatives() {
		return tableauEvaluationAlternatives;
	}

	public void setTableauEvaluationAlternatives(
			JTextField[][] tableauEvaluationAlternatives) {
		this.tableauEvaluationAlternatives = tableauEvaluationAlternatives;
	}
}
