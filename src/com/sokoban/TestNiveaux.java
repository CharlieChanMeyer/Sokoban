package com.sokoban;

import java.io.IOException;

public class TestNiveaux {

	public static void main(String[] args) {
		try {
			Niveau test = new Niveau(1);
			int i;
			int j;
			Immobile[][] grille = test.getGrille();
			for(i=0;i<11;i++) {
				for(j=0;j<10;j++) {
					System.out.println("Test");
					if(grille[i][j].getType() == "Mur") {
						System.out.print("1");
					} else {
						System.out.print("0");
					}
				}
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
