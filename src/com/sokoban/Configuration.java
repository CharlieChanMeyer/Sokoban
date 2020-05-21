package com.sokoban;

import java.util.*;

/**
 * 
 */
public class Configuration {
    public ArrayList<Diamant> diamants;
    public Joueur joueur;
    public Niveau niveau;
    public ArrayList<Policier> listePolicier;

    /**
     * @param Niveau 
     * @param positionJoueur
     */
    public Configuration(Niveau niv, Position positionJoueur) {
        // TODO implement here
    }

    /**
     * @param Configuration
     */
    public Configuration(Configuration config) {
        // TODO implement here
    }

    /**
     * @param Position 
     * @return
     */
    public boolean addDiamant(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public ArrayList<Policier> getListePolicier() {
        return this.listePolicier;
    }
    
    /**
     * @return
     */
    public Integer getX() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Integer getY() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Niveau getNiveau() {
        // TODO implement here
        return null;
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
     * @return
     */
    public Joueur getJoueur() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Diamant> getDiamants() {
        // TODO implement here
        return null;
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
    public boolean estCible(Position pos) {
        // TODO implement here
        return false;
    }

    /**
     * @param Direction 
     * @return
     */
    public boolean bougerJoueurVers(Direction direction) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public boolean victoire() {
        // TODO implement here
        return false;
    }

}