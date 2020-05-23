package com.sokoban;

import java.io.IOException;
import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) throws IOException {
//		Direction d1 = Direction.HAUT;
//	    Direction d2 = Direction.BAS;
//	      
//	    System.out.println(d1.toString()+d2.toString());
//	    
//	    if (d1.equals(d2)) {
//	    	System.out.println("Pareil");
//	    }else {
//	    	System.out.println("Différent");
//	    }
//	    
//	    ArrayList<Direction> ds = d1.getDirections();
//	    
//	    for(int i = 0 ; i < ds.size(); i++)
//	    	   System.out.println(ds.get(i).toString());
//	    
//	    Type t1 = Type.CASE;
//	    Type t2 = Type.JOUEUR;
//	    
//	    System.out.println(t1.toString()+t2.toString());
//	    if (t1.equals(t2)) {
//	    	System.out.println("Pareil");
//	    }else {
//	    	System.out.println("Différent");
//	    }
	    
	    Configuration config = new Configuration(1);
	    
//	    System.out.println("Diamants:");
//	    for(int i = 0 ; i < config.getDiamants().size(); i++)
//	    System.out.println(config.getDiamants().get(i).getPosition().toString());
//	    
//	    System.out.println("Policiers:");
//	    for(int i = 0 ; i < config.getPoliciers().size(); i++)
//	    System.out.println(config.getPoliciers().get(i).getPosition().toString());
	    
	    System.out.println("Joueur:");
	    System.out.println(config.getJoueur().getPosition().toString());
	    
	    if(config.bougerJoueurVers(Direction.DROITE)) {
	    	System.out.println("Bougé:");
		    System.out.println(config.getJoueur().getPosition().toString());
	    }else {
	    	System.out.println("Pas bougé:");
		    System.out.println(config.getJoueur().getPosition().toString());
	    }
//	    System.out.println("Diamants:");
//	    for(int i = 0 ; i < config.getDiamants().size(); i++)
//	    System.out.println(config.getDiamants().get(i).getPosition().toString());
	}

}
