package com.sokoban;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 */
public enum Direction {
	//Objets directement construits
	HAUT(0,-1),
	BAS(0,1),
	GAUCHE(-1,0),
	DROITE(1,0);
	
	private static final ArrayList<Direction> directions = new ArrayList<Direction>(Arrays.asList(HAUT,BAS,GAUCHE,DROITE)); 
	
	private int dx = 0;
	private int dy = 0;
	
	Direction(int dx,int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public String toString(){
	    return "Position [x=" + dx + ", y=" + dy + "]";
	}
	
	public ArrayList<Direction> getDirections(){
		return directions;
	}
}