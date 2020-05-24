package com.sokoban;

import java.util.ArrayList;

/**
 * 
 */
public class Joueur extends Mobile {
	
    private ArrayList<Direction> histo;
    private int balles;
    
    /**
     * @param Configuration 
     * @param Position
     */
    public Joueur(Configuration conf, Position position, int balles) {
    	super(Type.JOUEUR,conf,position);
    	this.balles = balles;
    	this.histo = new ArrayList<Direction>();
    }

    /**
     * @param Configuration 
     * @param Joueur
     */
    public Joueur(Configuration conf, Joueur joueur) {
    	super(Type.JOUEUR,conf,joueur.getPosition());
    	this.histo = joueur.getHisto();
    	this.balles = joueur.balles;
    }

    /**
     * @return histo
     */
    public ArrayList<Direction> getHisto() {
        return this.histo;
    }
    
    /**
     * @return histo
     */
    public int getBalles() {
        return this.balles;
    }
    
    /**
     * @param nbBalles Nombres de balle du joueur
     */
    public void setBalles(int nbBalles) {
        this.balles = nbBalles;
    }
    
    /**
     * 
     */
    public void tirer(Direction dir) {
    	if (this.balles > 0) {
            Position pos = this.getPosition();
            Configuration config = this.getConfig();
            Element e = config.get(pos);
            while (!e.getType().equals(Type.MUR) && (!e.getType().equals(Type.POLICIER)) && (!e.getType().equals(Type.DIAMANT))){
            	pos = pos.add(dir);
            	e = config.get(pos);
            }
            if(e.getType().equals(Type.POLICIER)) {
            	config.removePolicier(pos);
            }
            this.balles--;
    	}
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
    
    public void addHisto(Direction dir) {
    	this.histo.add(dir);
    }
    

}