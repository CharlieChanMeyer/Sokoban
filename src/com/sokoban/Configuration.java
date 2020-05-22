package com.sokoban;

import java.util.ArrayList;

/**
 * 
 */
public class Configuration {
	private ArrayList<Diamant> diamants;
	private ArrayList<Policier> policiers;
    private Joueur joueur;
    private Niveau niveau;

    /**
     * @param niv 
     * @param positionJoueur
     */
    public Configuration(Niveau niv, Position positionJoueur) {
        this.niveau = niv;
        this.diamants = new ArrayList<Diamant>();
        this.policiers = new ArrayList<Policier>();
        this.joueur = new Joueur(this, positionJoueur);
    }

    /**
     * @param config
     */
    public Configuration(Configuration config) {
    	this.diamants = config.getDiamants();
    	this.policiers = config.getPoliciers();
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
    		//gerer la position
    		//throw IllegalArgumentException;
    	}
        catch (IllegalArgumentException e) {
        	//une erreur est survenu, on retourne false
        	valRetour = false;
        }
        return valRetour;
    }
    
    /**
     * @param pos 
     * @return
     */
    public boolean addPolicier(Position pos) {
    	boolean valRetour; //la valeur de retour qui indique si tout c'est bien passé
    	//on suppose que tout se passera bien
    	valRetour = true;
    	
    	try {
    		this.getPoliciers().add(new Policier(this, pos));
    		//gerer la position
    		//throw IllegalArgumentException;
    	}
        catch (IllegalArgumentException e) {
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
     * @param pos Position a chercher 
     * @return l'element contenu a la position donnee
     */
    public Element get(Position pos) {
        //Pour chaque diamant, on verifie sa position
        for (Diamant diamant : this.getDiamants()) {
        	if (diamant.getPosition().equals(pos)) {
        		//Si la position se trouve a notre emplacement de verification, on le retourne
        		return diamant;
        	}
        }
        
        //Pour chaque diamant, on verifie sa position
        for (Policier policier : this.getPoliciers()) {
        	if (policier.getPosition().equals(pos)) {
        		//Si la position se trouve a notre emplacement de verification, on le retourne
        		return policier;
        	}
        }
        
        //Si le joueur se trouve a la position verifie, on le retourne
        if (this.getJoueur().getPosition().equals(pos)) {
        	return this.getJoueur();
        }
        
        //si aucun "mobile" n'est à la position, on renvoie l'imobille de la grille du niveau, donc "mur" ou "case"
        return this.getNiveau().getGrille()[pos.getX()][pos.getY()];
    }

    /**
     * @param pos Position de la case a verifier 
     * @return Vrai si la position contient un diamant et est une cible
     */
    public boolean estVide(Position pos) {
    	for (Diamant diamant : this.getDiamants()) {
        	if (diamant.getPosition().equals(pos)) {
        		//Si la position se trouve a notre emplacement de verification, on le retourne
        		return false;
        	}
        }
        return (this.getNiveau().getCibles().contains(pos) /**&& this.getDiamants().contains(pos)**/);
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
    	//on parcours tous les diamants
    	for(Diamant diamant : this.getDiamants()) {
    		//si un diament n'est pas sur une cible
    		if(!this.estCible(diamant.getPosition())){
    			//on retourne false
    			return false;
    		}
    	}
    	//si tous les diamant sont sur des cibles on retourne true
        return true;
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

	public ArrayList<Policier> getPoliciers() {
		return policiers;
	}

}