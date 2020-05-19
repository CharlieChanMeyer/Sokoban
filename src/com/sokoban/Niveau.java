package com.sokoban;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 */
public class Niveau {
    /**
     * 
     */
    private Immobile[][] grille;
    /**
     * 
     */
    private ArrayList<Position> cibles;

    /**
     * @param x nb de ligne de la grille
     * @param y nb de colonne de la grille
     */
    public Niveau(int x, int y) {
        this.grille = new Immobile[x][y];
    }
    /**
     * @param n numero du niveau a charger
     * @throws IOException 
     */
    public Niveau(int n) throws IOException {
        BufferedReader lecteur = null;
        String ligne;
        Integer cmpLigne = 0;
        this.cibles = new ArrayList<Position>();
        int x = 0;
        int y = 0;
        int i = 0;
        try {
			lecteur = new BufferedReader(new FileReader("src/ressources/niveaux/"+n+".txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Erreur d'ouverture");
			e.printStackTrace();
		}
        while ((ligne = lecteur.readLine()) != null) {
        	switch (cmpLigne) {
        	case 0:
        		x = Integer.parseInt(ligne);
        		cmpLigne++;
        		break;
        	case 1:
        		y = Integer.parseInt(ligne);
        		this.grille = new Immobile[x][y];
        		cmpLigne++;
        		break;
        	case 2:
        		cmpLigne++;
        		break;
        	default:
        		for (int j=0;j<ligne.length();j++) {
        			char verif = ligne.charAt(j);
        			if (Character.getNumericValue(verif) == 1) {
        				this.grille[i][j] = new Mur();
        			} else if ((Character.getNumericValue(verif) == 0) || (Character.getNumericValue(verif) == 2) || (Character.getNumericValue(verif) == 3) || (Character.getNumericValue(verif) == 4)){
        				this.grille[i][j] = new Case();
        				if (Character.getNumericValue(verif) == 2) {
        					this.cibles.add(new Position(i,j));
        				}
        			}
        		}
        		i++;
        		cmpLigne++;
        	}
        }
        lecteur.close();
    }
    
    /**
	 * @return the grid
	 */
	public Immobile[][] getGrille() {
		return grille;
	}
	/**
     * @return length of the grid
     */
    public int getX() {
        return this.grille.length;
    }

    /**
     * @return width of the grid
     */
    public int getY() {
        return this.grille[0].length;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estCible(Position pos) {
        return (this.cibles.contains(pos));
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estVide(Position pos) {
        return (this.cibles.contains(pos) /**&& Configuration.this.diamants.contains(pos)**/);
    }

    /**
     * @param Position 
     * @return
     */
    public Element get(Position pos) {
        // TODO implement here
        return null;
    }
    //Affiche la grille de jeu avec les murs et les cases
    public void affGrille() {
    	int x = this.getX();
    	int y = this.getY();
    	int i;
    	int j;
    	System.out.println("X "+x+" | Y "+y);
    	for(Position cible : this.cibles) {
    		System.out.println("Cible :"+cible.getX()+"|"+cible.getY());
    	}
    	for(i=0;i<x;i++) {
    		for(j=0;j<y;j++) {
    			System.out.print(this.grille[i][j].getType());
    		}
    		System.out.println("");
    	}
    }

}