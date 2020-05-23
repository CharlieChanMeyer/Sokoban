package com.sokoban;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public Configuration(int numNiv) throws IOException{
    	BufferedReader lecteur = null; //Lecteur du fichier
    	String ligne; //Variable d'une ligne			
        Integer cmpLigne = 0; //Compteur de ligne lu
        
        try {
			this.niveau = new Niveau(numNiv);
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.diamants = new ArrayList<Diamant>();
        this.policiers = new ArrayList<Policier>();
        
        //lecture des diamants et policiers
        try {
        	//Essaye de lire le fichier du niveau
			lecteur = new BufferedReader(new FileReader("src/ressources/niveaux/"+numNiv+".txt"));
		} catch (FileNotFoundException e) {
			//Si erreur, affiche une Erreur d'ouverture
			System.out.println("Erreur d'ouverture");
			e.printStackTrace();
		}
      //Tant qu'il y a une ligne a lire
        while ((ligne = lecteur.readLine()) != null) {
        	switch (cmpLigne) {
        	//S'il sagit de la premiere ligne
        	case 0:
        		//Incremente le nombre de ligne lu
        		cmpLigne++;
        		break;
        	//S'il sagit de la seconde ligne
        	case 1:
        		//Increment le nombre de ligne lu
        		cmpLigne++;
        		break;
        	//S'il sagit de la troisieme ligne (ligne de la position de depart du perso)
        	case 2:
        		//séparer la ligne en deux
        		String position[] = ligne.split(",");
        		//Stock la position x du joueur
        		int x = Integer.parseInt(position[0]);
        		//Stock la position y du joueur
        		int y = Integer.parseInt(position[1]);
        		this.joueur = new Joueur(this, new Position(x, y) , 3);
        		//Incremente le nombre de ligne lu
        		cmpLigne++;
        		break;
        	//Dans tous les autres cas
        	default:
        		//Pour chaque caractere de la ligne
        		for (int j=0;j<ligne.length();j++) {
        			//Stock le caractere
        			char verif = ligne.charAt(j);
        			//S'il sagit d'un 1, creer un mur
        			if (Character.getNumericValue(verif) == 3) {
        				this.getDiamants().add(new Diamant(this, new Position(j, cmpLigne-3)));
        			//Sinon, s'il sagit d'un 0,2,3 ou 4, cree une case
        			} else if (Character.getNumericValue(verif) == 4){
        				this.getPoliciers().add(new Policier(this, new Position(j, cmpLigne-3)));
        			}
        		}
        		//Incremente le nombre de ligne lu
        		cmpLigne++;
        	}
        }
        //Ferme le fichier
        lecteur.close();
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
        return(valRetour);
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
    	//on suppose que l'on a pas pu bouger le joueur
    	valRetour = false;
    	
        Position newPos = this.getJoueur().getPosition().add(direction);
		//si la nouvelle case ne contient rien et n'est pas un mur
		if (this.estVide(newPos)) {
			valRetour = this.getJoueur().setPosition(newPos);
		} else {
			//si la nouvelle case contient un diamant
			if (this.get(newPos).getType().equals(Type.DIAMANT)) {
				//on défini la nouvelle position du diamant
				Position newPosDiams = newPos.add(direction);
				if(this.get(newPos).getType().equals(Type.CASE)) {
					//on bouge le diamant et le joueur
					valRetour = (this.get(newPos).setPosition(newPosDiams) && this.getJoueur().setPosition(newPos));
				}
			}
		}
		//on retourne la valeur de retour
		return(valRetour);

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