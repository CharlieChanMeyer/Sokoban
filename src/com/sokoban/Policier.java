package com.sokoban;

import java.util.ArrayList;

import astar.Astar;
import astar.Matrice;

/**
 * 
 */
public class Policier extends Mobile {
    /**
     * 
     */
    public ArrayList<Direction> histo;
    public boolean etatAlerte;

    /**
     * @param Configuration 
     * @param Position
     */
    public Policier(Configuration conf, Position position) {
        super(Type.POLICIER,conf,position);
        this.histo = new ArrayList<Direction>();
        this.etatAlerte = false;
    }

    /**
     * @return
     */
    public Direction getRegard() {
    	Direction res = Direction.DROITE;
        if (!this.histo.isEmpty()) {
        	res = this.histo.get(this.histo.size()-1);
        }
        return res;
    }
    
    public Direction deplacementPolicier() {
    	//si le policier n'est pas en alerte, on regarde s'il le deviens ce tour
    	if (!this.etatAlerte) {
    		int xPolicier=this.getPosition().getX();
    		int yPolicier=this.getPosition().getY();
    		int xJoueur=this.getConfig().getJoueur().getPosition().getX();
    		int yJoueur=this.getConfig().getJoueur().getPosition().getY();
    		//si le joueur se trouve à 3 cases du policier, il entre en alerte
    		if (Math.abs(xPolicier-xJoueur)+Math.abs(yPolicier-yJoueur) <= 3) {
    			etatAlerte = true;
    		}
    	}
    	//si le policier est en alerte, il poursuit le joueur
    	if (this.etatAlerte) {
    		// ajout des mobiles sur la map pour l'application de A*
    		Matrice newMap=this.getConfig().getNiveau().getMatrice().ajoutDesMobiles(this.getConfig());
    		//ajout du point de depart
    		newMap.setDepart(this.getPosition());
    		//ajout du point d'arrive
    		newMap.setArrive(this.getConfig().getJoueur().getPosition());
    		//application du code A*
    		Direction direction = Astar.aStar(newMap);
    		//renvoie de la direction à suivre
    		return direction;
    	//le policier n'est pas en alerte
    	} else {
    		// le policier ce deplace en fonction de la derniere direction
    		Direction direction = histo.get(histo.size()-1);
    		//si le policier peut se deplacer dans cette direction, il le fait
    		if (this.bougerVers(direction)) {
    			//renvoie de la direction à suivre
    			return (direction);
    		//sinon il fait demi-tour
    		} else {
    			// inversion de la direction
    			direction.setDx(-1*direction.getDx());
    			direction.setDy(-1*direction.getDy());
    			//renvoie la direction à suivre
    			return (direction);
    		}
    	}
    }
}