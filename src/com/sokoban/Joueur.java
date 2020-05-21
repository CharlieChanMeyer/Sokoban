package com.sokoban;


/**
 * 
 */
public class Joueur extends Mobile {
	public int nbDeplacement;
	public Direction regard;

    /**
     * @param Configuration 
     * @param Position
     * debut de la partie et reprise de la sauvegarde
     */
    public Joueur(Position pos) {
    	super("Joueur",pos);
    }

    /**
     * @param Configuration 
     * @param Joueur
     * 
     */
    
    /**
     * @return ArrayList<direction>
     */
    public int getNbDeplacement() {
        return (this.nbDeplacement);
    }
        
    /**
     * 
     */
    public void tirer(Configuration conf) {
    	Niveau carte = conf.getNiveau();
        Direction regard = this.regard;
        Position pos = this.position;
        pos=pos.add(regard);
        while (carte.get(pos).getType()=="Case") {
        	pos=pos.add(regard);
        }
        if (carte.get(pos).getType()=="Policier") {
        	int i=0;
        	while (!pos.equals(conf.getListePolicier().get(i).getPosition())) {
        		i++;
        	}
        	conf.getListePolicier().remove(i);
        }
    }

	@Override
	public Boolean bougerVers(Direction dir,Configuration conf) {
		Position newPos = this.position.add(dir);
		this.regard=dir;
		if (conf.estVide(newPos)) {
			this.position=newPos;
			this.nbDeplacement++;
			return (true);
		} else {
			if (conf.getNiveau().get(newPos).getType()=="Diamand") {
				int i=0;
				while (!newPos.equals(conf.getDiamants().get(i).getPosition())) {
					i++;
				}
				Boolean deplace = conf.getDiamants().get(i).bougerVers(dir,conf);
				if (deplace) {
					this.position = newPos;
					this.nbDeplacement++;
					return (true);
				} else {
					return (false);
				}
			} else {
				return (false);
			}
		}
	}
	
}