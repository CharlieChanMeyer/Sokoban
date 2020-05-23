package astar;

import java.util.ArrayList;

public class Astar {
	
	public static ArrayList<Position> aStar(int[][] map,int xDepart,int yDepart,int xArrive,int yArrive){
		Matrice matrice = new Matrice(map);
		matrice.setDepart(new Position(xDepart,yDepart));
		matrice.setArrive(new Position(xArrive,yArrive));
		OpenList openList = new OpenList(matrice.getDepart());
		int meilleur =0 ;
		Point pointEtudie = openList.getListe().get(0);
		matrice.etudeVoisins(pointEtudie, openList);
		meilleur = openList.meilleur();
		pointEtudie = openList.getListe().get(meilleur);
		matrice.etudeVoisins(pointEtudie, openList);
		while((!pointEtudie.equal(matrice.getArrive())) && (!openList.getListe().isEmpty())) {
			meilleur = openList.meilleur();
			pointEtudie = openList.getListe().get(meilleur);
			matrice.etudeVoisins(pointEtudie, openList);
		}
		if ((openList.getListe().isEmpty()) && (!pointEtudie.equal(matrice.getArrive()))) {
			return null;
		} else {
			ArrayList<Position> chemin = pointEtudie.getParents();
			chemin.add(pointEtudie.getPos());
			return chemin;
		}
	}
	
}