package com.sokoban;

/**
 * 
 */
public class Position {
    /**
     * 
     */
    private int x;

    /**
     * 
     */
    private int y;

    
    
    /**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

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