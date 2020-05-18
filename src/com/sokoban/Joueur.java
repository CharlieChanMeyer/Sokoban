package com.sokoban;

import java.util.*;

/**
 * 
 */
public class Joueur extends Mobile {
    public ArrayList<Direction> histo;

    /**
     * @param Configuration 
     * @param Position
     */
    public Joueur(Configuration conf, Position pos) {
    	super("Joueur",conf,pos);
    }

    /**
     * @param Configuration 
     * @param Joueur
     */
    public Joueur(Configuration conf, Joueur joueur) {
        super("Joueur",conf,joueur);
    }

    /**
     * @return
     */
    public ArrayList<Direction> getHisto() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void tirer() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Direction getRegard() {
        // TODO implement here
        return null;
    }

}