package astar;


public class Matrice {

	private Point[][] matrice;
	private Point depart;
	private Point arrive;
	
	public Point[][] getMatrice() {
		return matrice;
	}

	
	public Matrice(int[][] m){
		int maxX = m.length;
		int maxY = m[0].length;
		Point[][] matrice = new Point[maxX][maxY];
		for(int x=0;x<maxX;x++) {
			for(int y=0;y<maxY;y++) {
				matrice[x][y] = new Point(m[x][y],x,y);
			}
		}
		this.matrice = matrice;
	}
			
	public void afficher() {
		int maxX = this.matrice.length;
		int maxY = 6;
		for (int x=0;x<maxX;x++) {
			for (int y=0;y<maxY;y++) {
				if (this.matrice[x][y].getValeur() == 0) {
					System.out.print(" ");
				} else if (this.matrice[x][y].getValeur() == 1){
					System.out.print("x");
				} else if (this.matrice[x][y].getValeur() == 2){
					System.out.print("D");
				} else if (this.matrice[x][y].getValeur() == -1){
					System.out.print("A");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	public Point getDepart() {
		return depart;
	}
	
	public void setDepart(Position pos) {
		this.depart = this.matrice[pos.getX()][pos.getY()];
	}
	
	public Point getArrive() {
		return arrive;
	}
	
	public void setArrive(Position pos) {
		this.arrive = this.matrice[pos.getX()][pos.getY()];
	}
	
	public void etudeVoisins(Point point,OpenList openList){
		int x = point.getPos().getX();
		int y = point.getPos().getY();
		Point voisin = null;
		if (x != 0) {
			if (this.matrice[x-1][y].getValeur() == 0) {
				Point p = point;
				voisin=this.matrice[x-1][y];
				voisin.heuristique(this.arrive);
				voisin.cout(p);
				voisin.parents(p);
				voisin.setF(voisin.getCout()+voisin.getH());
				openList.add(voisin);
			}
		}
		if (x != this.matrice.length-1) {
			if (this.matrice[x+1][y].getValeur() == 0) {
				Point p = point;
				voisin=this.matrice[x+1][y];
				voisin.heuristique(this.arrive);
				voisin.cout(p);
				voisin.parents(p);
				voisin.setF(voisin.getCout()+voisin.getH());
				openList.add(voisin);
			}
		}
		if (y != 0) {
			if (this.matrice[x][y-1].getValeur() == 0) {
				Point p = point;
				voisin=this.matrice[x][y-1];
				voisin.heuristique(this.arrive);
				voisin.cout(p);
				voisin.parents(p);
				voisin.setF(voisin.getCout()+voisin.getH());
				openList.add(voisin);
			}
		
		}
		if (y != this.matrice[0].length-1) {
			if (this.matrice[x][y+1].getValeur() == 0) {
				Point p = point;
				voisin=this.matrice[x][y+1];
				voisin.heuristique(this.arrive);
				voisin.cout(p);
				voisin.parents(p);
				voisin.setF(voisin.getCout()+voisin.getH());
				openList.add(voisin);
			}
		}
		point.setValeur(2);
		openList.getListe().remove(point);
	}
}