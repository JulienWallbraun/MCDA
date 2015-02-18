package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import modele.Global;
import controleur.Controleur;

public class Vue_resultats {

	private Controleur controleur;
	
	private JFrame frmAfficherRsultats;
	private JSpinner spinnerAlternativeChoisie;
	private JButton btnAfficherResultats;
	private JButton btnRetourAccueil;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane;



//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Vue_resultats window = new Vue_resultats();
//					window.frmAfficherRsultats.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Vue_resultats() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//on lance le contrôleur
		controleur = new Controleur(this);
				
		frmAfficherRsultats = new JFrame();
		frmAfficherRsultats.setTitle("Afficher r\u00E9sultats");
		frmAfficherRsultats.setBounds(100, 100, 651, 620);
		frmAfficherRsultats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAfficherRsultats.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(83, 36, 403, 30);
		frmAfficherRsultats.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("alternative s\u00E9lectionn\u00E9e");
		panel.add(lblNewLabel);
		
		spinnerAlternativeChoisie = new JSpinner();
		spinnerAlternativeChoisie.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(Global.getInstance().getNbAlternatives()-1), new Integer(1)));
		panel.add(spinnerAlternativeChoisie);
		
		btnAfficherResultats = new JButton("afficher les r\u00E9sultats");
		panel.add(btnAfficherResultats);
		btnAfficherResultats.addActionListener(controleur);
		
//		textArea = new JTextArea();
//		textArea.setBounds(20, 77, 559, 453);
//		frmAfficherRsultats.getContentPane().add(textArea);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(34, 90, 569, 440);
		frmAfficherRsultats.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("S\u00E9lectionnez une alternative puis cliquez sur \"afficher\" pour afficher les r\u00E9sultats");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 569, 14);
		frmAfficherRsultats.getContentPane().add(lblNewLabel_1);
		
		btnRetourAccueil = new JButton("retour accueil");
		btnRetourAccueil.setBounds(441, 548, 138, 23);
		frmAfficherRsultats.getContentPane().add(btnRetourAccueil);
		btnRetourAccueil.addActionListener(controleur);
	}
	
	public JFrame getFrmAfficherRsultats() {
		return frmAfficherRsultats;
	}

	public void setFrmAfficherRsultats(JFrame frmAfficherRsultats) {
		this.frmAfficherRsultats = frmAfficherRsultats;
	}

	public JSpinner getSpinnerAlternativeChoisie() {
		return spinnerAlternativeChoisie;
	}

	public void setSpinnerAlternativeChoisie(JSpinner spinnerAlternativeChoisie) {
		this.spinnerAlternativeChoisie = spinnerAlternativeChoisie;
	}

	public JButton getBtnAfficherResultats() {
		return btnAfficherResultats;
	}

	public void setBtnAfficherResultats(JButton btnAfficherResultats) {
		this.btnAfficherResultats = btnAfficherResultats;
	}

	public JButton getBtnRetourAccueil() {
		return btnRetourAccueil;
	}

	public void setBtnRetourAccueil(JButton btnRetourAccueil) {
		this.btnRetourAccueil = btnRetourAccueil;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String string) {
		this.textArea.setText(string);
	}
}
