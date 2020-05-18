package com.sokoban;

import java.util.*;

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
     * @param x 
     * @param y
     */
    public Niveau(int x, int y) {
        this.grille = new Immobile[x][y];
    }

    /**
     * @return
     */
    public int getX() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getY() {
        // TODO implement here
        return 0;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean addCible(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean addMur(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estCible(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @param Position 
     * @return
     */
    public boolean estVide(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @param Position 
     * @return
     */
    public Element get(Position pos) {
        // TODO implement here
        return null;
    }

}