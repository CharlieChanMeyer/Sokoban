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