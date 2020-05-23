package astar;

import java.util.ArrayList;
import java.lang.Math;

public class Point {
	
	private int valeur;
	private Position pos;
	private int cout;
	private int f;
	private int h;
	private ArrayList<Position> parents;
	
	public Point(int valeur,int x,int y) {
		this.valeur = valeur;
		this.pos = new Position(x,y);
		this.cout = -1;
		this.f = -1;
		this.h = -1;
		this.parents = new ArrayList<Position>();
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public int getCout() {
		return cout;
	}
	
	public int getF() {
		return f;
	}
	
	public int getH() {
		return h;
	}
	
	public ArrayList<Position> getParents() {
		return parents;
	}
	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public void setCout(int cout) {
		this.cout = cout;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public void heuristique(Point arrive) {
		this.h = (Math.abs(this.pos.getX()-arrive.getPos().getX())+Math.abs(this.pos.getY()-arrive.getPos().getY()));
	}
	
	public void cout(Point point) {
		this.cout = point.cout+1;
	}

	
	public void parents(Point point) {
		ArrayList<Position> newParents = new ArrayList<>(point.getParents());
		newParents.add(point.getPos());
		this.parents = newParents;
	}
	
	public void setParents(ArrayList<Position> parents) {
		this.parents = parents;
	}
	
	public boolean equal(Point point) {
		if(this.pos.equal(point.getPos())) {
			return true;
		} else {
			return false;
		}	
	}
}
