package com.sokoban;
/**
 * 
 */
public class Direction {

    private static final Direction GAUCHE = new Direction(-1,0);
    private static final Direction DROITE = new Direction(1,0);
    private static final Direction HAUT = new Direction(0,1);
    private static final Direction BAS = new Direction(0,-1);
    private int dx;
    private int dy;

    /**
     * @param dx 
     * @param dy
     */
    public Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the gauche
	 */
	public static Direction getGauche() {
		return GAUCHE;
	}

	/**
	 * @return the droite
	 */
	public static Direction getDroite() {
		return DROITE;
	}

	/**
	 * @return the haut
	 */
	public static Direction getHaut() {
		return HAUT;
	}

	/**
	 * @return the bas
	 */
	public static Direction getBas() {
		return BAS;
	}

}