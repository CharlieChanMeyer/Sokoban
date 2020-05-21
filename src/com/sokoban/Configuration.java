package com.sokoban;

import java.util.*;

/**
 * 
 */
public class Configuration {
    private ArrayList<Diamant> diamants;
    private Joueur joueur;
    private Niveau niveau;

    /**
     * @param niv 
     * @param positionJoueur
     */
    public Configuration(Niveau niv, Position positionJoueur) {
        this.niveau = niv;
        this.diamants = new ArrayList<Diamant>();
        this.joueur = new Joueur(this, positionJoueur);
    }

    /**
     * @param config
     */
    public Configuration(Configuration config) {
    	this.diamants = config.getDiamants();
    	this.joueur = config.getJoueur();
    	this.niveau = config.getNiveau();
    }

    /**
     * @param pos 
     * @return
     */
    public boolean addDiamant(Position pos) {
    	boolean valRetour; //la valeur de retour qui indique si tout c'est bien passé
    	//on suppose que tout se passera bien
    	valRetour = true;
    	
    	try {
    		this.getDiamants().add(new Diamant(this, pos));
    	}
        catch (IndexOutOfBoundsException e) {
        	//une erreur est survenu, on retourne false
        	valRetour = false;
        }
        return valRetour;
    }

    /**
     * @return
     */
    public Integer getX() {
        // retourner la valeur X de la position du joueur
        return this.getJoueur().getPosition().getX();
    }

    /**
     * @return
     */
    public Integer getY() {
    	// retourner la valeur Y de la position du joueur
    	return this.getJoueur().getPosition().getY();
    }

    /**
     * @param Position 
     * @return
     */
    public Element get(Position pos) {
        // TODO implement here
        return null;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estVide(Position pos) {
    	return this.getNiveau().estVide(pos);
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estCible(Position pos) {
        return this.getNiveau().estCible(pos);
    }

    /**
     * @param Direction 
     * @return
     */
    public boolean bougerJoueurVers(Direction direction) {
    	boolean valRetour; //la valeur de retour qui indique si tout c'est bien passé
    	//on suppose que tout se passera bien
    	valRetour = true;
    	
    	Position pos = new Position(this.getJoueur().getPosition().getX()+direction.getDx(), this.getJoueur().getPosition().getY()+direction.getDy());
    	
    	try {
    		this.getJoueur().setPosition(pos);
    	}
        catch (IndexOutOfBoundsException e) {
        	//une erreur est survenu, on retourne false
        	valRetour = false;
        }
        return valRetour;
    }

    /**
     * @return
     */
    public boolean victoire() {
    	boolean gagne; //la valeur de retour qui dit si la partie est gagné
    	//on suppose que la partie est gagné
    	gagne = true;
    	for(Diamant diamant : this.getDiamants()) {
    		//if(diamant.getPosition().getX() == this.getNiveau().get)
    	}
        return gagne;
    }

	public ArrayList<Diamant> getDiamants() {
		return diamants;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Niveau getNiveau() {
		return niveau;
	}

}