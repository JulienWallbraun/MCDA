package modele;

import java.util.ArrayList;
import java.util.Random;

public class GenerateurIntervalle {

	ArrayList<Intervalle> listIntervalle = new ArrayList<Intervalle>();

	public GenerateurIntervalle(ArrayList<Intervalle> listIntervalle) {
		super();
		this.listIntervalle = listIntervalle;
	}

	public GenerateurIntervalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Intervalle> getListIntervalle() {
		return listIntervalle;
	}

	public void setListIntervalle(ArrayList<Intervalle> listIntervalle) {
		this.listIntervalle = listIntervalle;
	}

	public static int getRandomNumberBetween(int min, int max) {
		Random foo = new Random();
		int randomNumber = foo.nextInt(max - min) + min;
		if (randomNumber == min) {
			// Since the random number is between the min and max values, simply
			// add 1
			return min + 1;
		} else {
			return randomNumber;
		}

	}

	public ArrayList<Intervalle> creatIntervalle(int nbreIntervalle) {

		System.out.println("crétaion de " + nbreIntervalle + " intervalles : ");

		for (int i = 0; i < nbreIntervalle; i++) {

			int j = getRandomNumberBetween(0, 20);
			int k = getRandomNumberBetween(0, 20);

			listIntervalle.add(i, new Intervalle((double) Math.min(j, k), (double) Math.max(j, k)));
			System.out.println("[" + listIntervalle.get(i).getBorneInf()
					+ " , " + listIntervalle.get(i).getBorneSup() + "]");
		}

		System.out.println("le nombre d'intervalles est : " + nbreIntervalle);

		return listIntervalle;
	}
}
