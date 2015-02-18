package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controleur.Controleur;

public class Vue_selectionNbParametres{
	
	private Controleur controleur;

	private JFrame frmSelectionNbParametres;
	private JPanel panelNbAlternatives;
	private JLabel labelNbAlternatives;
	private JSpinner spinnerNbAlternatives;
	private JPanel panelNbCriteres;
	private JLabel labelNbCriteres;
	private JSpinner spinnerNbCriteres;
	private JPanel panelNbSources;
	private JLabel labelNbSources;
	private JSpinner spinnerNbSources;
	private JButton btnValider;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue_selectionNbParametres window = new Vue_selectionNbParametres();
					window.frmSelectionNbParametres.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vue_selectionNbParametres() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//on lance le contrôleur
		controleur = new Controleur(this);
		
		frmSelectionNbParametres = new JFrame();
		frmSelectionNbParametres.setTitle("S\u00E9lection du nombre de param\u00E8tres");
		frmSelectionNbParametres.setBounds(100, 100, 369, 322);
		frmSelectionNbParametres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSelectionNbParametres.getContentPane().setLayout(null);
		
		panelNbAlternatives = new JPanel();
		panelNbAlternatives.setBounds(10, 88, 333, 30);
		frmSelectionNbParametres.getContentPane().add(panelNbAlternatives);
		
		labelNbAlternatives = new JLabel("nombre d'alternatives :");
		panelNbAlternatives.add(labelNbAlternatives);
		
		spinnerNbAlternatives = new JSpinner();
		spinnerNbAlternatives.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panelNbAlternatives.add(spinnerNbAlternatives);
		
		panelNbCriteres = new JPanel();
		panelNbCriteres.setBounds(10, 140, 333, 30);
		frmSelectionNbParametres.getContentPane().add(panelNbCriteres);
		
		labelNbCriteres = new JLabel("nombre de crit\u00E8res :");
		panelNbCriteres.add(labelNbCriteres);
		
		spinnerNbCriteres = new JSpinner();
		spinnerNbCriteres.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panelNbCriteres.add(spinnerNbCriteres);
		
		panelNbSources = new JPanel();
		panelNbSources.setBounds(10, 192, 333, 30);
		frmSelectionNbParametres.getContentPane().add(panelNbSources);
		
		labelNbSources = new JLabel("nombre de sources :");
		panelNbSources.add(labelNbSources);
		
		spinnerNbSources = new JSpinner();
		spinnerNbSources.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panelNbSources.add(spinnerNbSources);
		
		btnValider = new JButton("Valider les param\u00E8tres rentr\u00E9s");
		btnValider.setBounds(10, 250, 333, 23);
		frmSelectionNbParametres.getContentPane().add(btnValider);
		
		JLabel lblEnonce = new JLabel("s\u00E9lectionnez les valeurs des param\u00E8tres suivants");
		lblEnonce.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnonce.setForeground(Color.BLUE);
		lblEnonce.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblEnonce.setBounds(10, 11, 333, 47);
		frmSelectionNbParametres.getContentPane().add(lblEnonce);
		
		JLabel lblEnonce2 = new JLabel("puis validez");
		lblEnonce2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnonce2.setForeground(Color.BLUE);
		lblEnonce2.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblEnonce2.setBounds(10, 44, 333, 23);
		frmSelectionNbParametres.getContentPane().add(lblEnonce2);
		btnValider.addActionListener(controleur);		
		
	}
	

	public JFrame getFrmSelectionNbParametres() {
		return frmSelectionNbParametres;
	}

	public void setFrmSelectionNbParametres(JFrame frmSelectionNbParametres) {
		this.frmSelectionNbParametres = frmSelectionNbParametres;
	}

	public JSpinner getSpinnerNbAlternatives() {
		return spinnerNbAlternatives;
	}

	public void setSpinnerNbAlternatives(JSpinner spinnerNbAlternatives) {
		this.spinnerNbAlternatives = spinnerNbAlternatives;
	}

	public JSpinner getSpinnerNbCriteres() {
		return spinnerNbCriteres;
	}

	public void setSpinnerNbCriteres(JSpinner spinnerNbCriteres) {
		this.spinnerNbCriteres = spinnerNbCriteres;
	}

	public JSpinner getSpinnerNbSources() {
		return spinnerNbSources;
	}

	public void setSpinnerNbSources(JSpinner spinnerNbSources) {
		this.spinnerNbSources = spinnerNbSources;
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}
}
