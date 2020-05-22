package com.sokoban;

import java.io.File;
import java.io.IOException;

public class TestNiveaux {

	public static void main(String[] args) {
		try {
			Niveau test = new Niveau(1);
			System.out.println();
			System.out.println("Test Affichage");
			test.affGrille();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
