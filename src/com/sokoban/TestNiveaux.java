package com.sokoban;

import java.io.IOException;

public class TestNiveaux {

	public static void main(String[] args) {
		try {
			Niveau test = new Niveau(1);
			test.affGrille();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
