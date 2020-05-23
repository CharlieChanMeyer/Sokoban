package astar;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		int [][]matrice1=      {
		{0,0,0,1,0},
		{1,0,0,0,0},
		{0,0,0,0,0},
		{1,1,0,1,0},
		{1,0,0,0,0}		};
		ArrayList<Position> chemin = Astar.aStar(matrice1,0,0,0,4);
		if (chemin == null) {
			System.out.println("il n'y a pas de solution");
		} else {
			System.out.println("Chemin à suivre :");
			for (int i=0; i<chemin.size(); i++) {
				System.out.print(chemin.get(i).getX());
				System.out.print(";");
				System.out.println(chemin.get(i).getY());
			}
		}
	}
}
