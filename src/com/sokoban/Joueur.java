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
     * @return histo
     */
    public int setBalles() {
        return this.balles;
    }
    
//    /**
//     * 
//     */
//    public void tirer() {
//        Direction dir = this.getRegard();
//        Position pos = this.position;
//        Configuration config = this.config;
//        Element e = config.get(pos);
//        while (!e.getType().equals(Type.MUR) || (!e.getType().equals(Type.POLICIER))){
//        	pos.add(dir);
//        	e = config.get(pos);
//        }
//        if(e.getType().equals(Type.POLICIER)) {
//        	this.balles--;
//        	Case c = new Case();
//
//        }else{
//        	this.balles--;
//        }
//    }

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