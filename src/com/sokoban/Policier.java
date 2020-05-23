package com.sokoban;

import java.util.ArrayList;

/**
 * 
 */
public class Policier extends Mobile {
    /**
     * 
     */
    public ArrayList<Direction> histo;

    /**
     * @param Configuration 
     * @param Position
     */
    public Policier(Configuration conf, Position position) {
        super(Type.POLICIER,conf,position);
        this.histo = new ArrayList<Direction>();
    }
    
//		A CODER AILLEURS A MON AVIS
//    /**
//     * @param Direction 
//     * @return
//     */
//    public boolean verifVision() {
//    	Boolean res = false;
//        Configuration conf = this.config;
//        Direction dir = getRegard();
//        
//        Position pos = this.position.add(dir);
//        
//        return false;
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

}