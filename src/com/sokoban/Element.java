package com.sokoban;

/**
 * 
 */
public class Element {
    private String type;

    /**
     * @param type
     */
    public Element(String type) {
        this.type = type;
    }

    /**
     * @param Direction 
     * @return
     */
    public Boolean bougerVers(Direction dir) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getType() {
        return this.type;
    }

}