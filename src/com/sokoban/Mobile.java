package com.sokoban;

/**
 * 
 */
public class Mobile extends Element {
    /**
     * 
     */
    public Configuration config;
    /**
     * 
     */
    public Position position;

    /**
     * @param Type 
     * @param Configuration 
     * @param Position
     */
    public Mobile(String type,Configuration conf,Position pos) {
    	super(type);
    	this.config = conf;
    	this.position = pos;
    }
    /**
     * @param Type 
     * @param Configuration 
     * @param Joueur
     */
    public Mobile(String type,Configuration conf,Joueur joueur) {
    	super(type);
    	this.config = conf;
    	this.position = joueur.position; //ICIIIIIIIIIIIIIIIIIIIIIIIII
    }

    /**
     * @param Position 
     * @return
     */
    public Boolean setPosition(Position pos) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Position getPosition() {
        // TODO implement here
        return null;
    }

}