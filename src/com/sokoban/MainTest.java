package com.sokoban;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		Direction d1 = Direction.HAUT;
	    Direction d2 = Direction.BAS;
	      
	    System.out.println(d1.toString()+d2.toString());
	    
	    if (d1.equals(d2)) {
	    	System.out.println("Pareil");
	    }else {
	    	System.out.println("Différent");
	    }
	    
	    ArrayList<Direction> ds = d1.getDirections();
	    
	    for(int i = 0 ; i < ds.size(); i++)
	    	   System.out.println(ds.get(i).toString());
	    
	    Type t1 = Type.CASE;
	    Type t2 = Type.JOUEUR;
	    
	    System.out.println(t1.toString()+t2.toString());
	    if (t1.equals(t2)) {
	    	System.out.println("Pareil");
	    }else {
	    	System.out.println("Différent");
	    }
	}

}
