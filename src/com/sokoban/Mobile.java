package com.sokoban;

/**
 * 
 */
public abstract class Mobile extends Element {
    /**
     * 
     */
    public Position position;

    /**
     * @param Type 
     * @param Configuration 
     * @param Position
     */
    public Mobile(String type,Position pos) {
    	super(type);
    	this.position = pos;
    }
    
    /**
     * @param Position 
     * @return
     */
    public void setPosition(Position pos) {
        this.position=pos;
    }

    /**
     * @return
     */
    public Position getPosition() {
        return this.position;
    }
    
    /**
     * @param Direction 
     * @return
     */
    public abstract Boolean bougerVers(Direction dir,Configuration conf);

}