package com.sokoban;

import java.util.*;

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
    public Policier(Configuration conf, Position pos) {
        super("Policier",conf,pos);
        this.histo = new ArrayList<Direction>();
    }

    /**
     * @param Direction 
     * @return
     */
    public boolean verifVision(Direction dir) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public Direction getRegard() {
        // TODO implement here
        return null;
    }

}