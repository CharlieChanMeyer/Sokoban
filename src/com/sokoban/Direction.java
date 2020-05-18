package com.sokoban;
/**
 * 
 */
public class Direction {

    public static final Direction GAUCHE = new Direction(-1,0);
    public static final Direction DROITE = new Direction(1,0);
    public static final Direction HAUT = new Direction(0,-1);
    public static final Direction BAS = new Direction(0,1);
    public int dx;
    public int dy;

    /**
     * @param dx 
     * @param dy
     */
    public Direction(int dx, int dy) {
        // TODO implement here
    }

}