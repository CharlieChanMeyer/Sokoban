package astar;

import java.util.ArrayList;

public class OpenList {
	
	private ArrayList<Point> liste;
	
	public OpenList(Point depart) {
		ArrayList<Point> openList = new ArrayList<Point>();
		openList.add(depart);
		this.liste = openList;
	}
	
	public ArrayList<Point> getListe() {
		return liste;
	}
	
	public void setListe(ArrayList<Point> liste) {
		this.liste = liste;
	}
	
	public void add(Point point) {
		if (this.liste.contains(point)) {
			if(this.liste.get(this.liste.indexOf(point)).getF() < point.getF()) {
				this.liste.get(this.liste.indexOf(point)).setParents(point.getParents());
			}
		} else { 
			this.liste.add(point);
		}
	}
	
	public int meilleur() {
		int i=0;
		for (int j=1; j<this.liste.size(); j++) {
			if (this.liste.get(i).getF()>this.liste.get(j).getF()) {
				i=j;
			}
		}
		return i;
	}
}