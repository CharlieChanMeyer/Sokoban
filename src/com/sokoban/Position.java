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
        // TODO implement here
        return null;
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
    public boolean equals(Object o) {
        // TODO implement here
        return false;
    }

}