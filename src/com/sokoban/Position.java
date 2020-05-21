package com.sokoban;

import java.util.*;

/**
 * 
 */
public class Position {
    /**
     * 
     */
    public int x;

    /**
     * 
     */
    public int y;

    /**
     * @param x 
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }

    /**
     * @param Position
     */
    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    /**
     * @param Direction 
     * @return
     */
    public Position add(Direction dir) {
        int x=this.x+dir.getDx();
        int y=this.y+dir.getDx();
        Position pos = new Position(x,y);
        return(pos);
    }

    /**
     * @param Direction 
     * @return
     */
    public Position sub(Direction dir) {
        // TODO implement here
        return null;
    }

    /**
     * @param Object 
     * @return
     */
    public boolean equals(Mobile m) {
        if ((m.getPosition().getX()==this.x) && (m.getPosition().getY()==this.y)) {
        	return true;
        } else {
        	return false;
        }
    }

}