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

public class Vue_matriceInteractionsPoids {

	private Controleur controleur;
	
	private JFrame frmMatriceInteractionsPoids;
	private JButton btnValider;
	private JButton btnRetourAccueil;
	private JTextField[][] matriceInteractionsPoids;
	private JLabel lblEnonce;

	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Vue_matriceInteractionsPoids window = new Vue_matriceInteractionsPoids();
//					window.frmMatriceInteractionsPoids.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Vue_matriceInteractionsPoids() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controleur = new Controleur(this);
		frmMatriceInteractionsPoids = new JFrame();
		frmMatriceInteractionsPoids.setTitle("Sp\u00E9cifier la matrice des interactions et des poids");
		frmMatriceInteractionsPoids.setBounds(100, 100, 450, 330);
		frmMatriceInteractionsPoids.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMatriceInteractionsPoids.getContentPane().setLayout(null);
		
		btnValider = new JButton("valider la matrice des interactions et des poids");
		btnValider.setBounds(10, 258, 414, 23);
		frmMatriceInteractionsPoids.getContentPane().add(btnValider);
		btnValider.addActionListener(controleur);
		
		btnRetourAccueil = new JButton("retour accueil");
		btnRetourAccueil.setBounds(234, 33, 190, 23);
		frmMatriceInteractionsPoids.getContentPane().add(btnRetourAccueil);
		btnRetourAccueil.addActionListener(controleur);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 67, 414, 180);
		frmMatriceInteractionsPoids.getContentPane().add(panel);
		
		//à enlever après
//		Global.getInstance().setNbCriteres(5);

		int nbCriteres = Global.getInstance().getNbCriteres();
		
		matriceInteractionsPoids = new JTextField[nbCriteres][nbCriteres];
		panel.setLayout(new GridLayout(nbCriteres + 1, nbCriteres + 1, 1, 1));
		
		lblEnonce = new JLabel("Remplissez la matrice des interactions et poids puis validez");
		lblEnonce.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnonce.setForeground(Color.BLUE);
		lblEnonce.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblEnonce.setBounds(10, 0, 414, 28);
		frmMatriceInteractionsPoids.getContentPane().add(lblEnonce);

		for (int i=0; i<nbCriteres+1; i++){
			for (int j=0; j<nbCriteres+1; j++){
				//on remplit les noms des colonnes
				if (i==0){
					if (j==0){
						panel.add(new JLabel());
					}
					else{
						panel.add(new JLabel("c"+Integer.toString(j)));
					}
				}
				//on remplit les noms des lignes
				else if (j==0){
					panel.add(new JLabel("c"+Integer.toString(i)));
				}
				//on remplit la matrice des poids critères (uniquement la moitié car symétrique)
				else{
					if (j>=i){
						matriceInteractionsPoids[i-1][j-1] = new JTextField();
						panel.add(matriceInteractionsPoids[i-1][j-1]);	
					}
					else{
						panel.add(new JLabel());
					}
				}
			}
		}
	}

	public JFrame getFrmMatriceInteractionsPoids() {
		return frmMatriceInteractionsPoids;
	}

	public void setFrmMatriceInteractionsPoids(JFrame frmMatriceInteractionsPoids) {
		this.frmMatriceInteractionsPoids = frmMatriceInteractionsPoids;
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

	public JTextField[][] getMatriceInteractionsPoids() {
		return matriceInteractionsPoids;
	}

	public void setMatriceInteractionsPoids(JTextField[][] matriceInteractionsPoids) {
		this.matriceInteractionsPoids = matriceInteractionsPoids;
	}
}
