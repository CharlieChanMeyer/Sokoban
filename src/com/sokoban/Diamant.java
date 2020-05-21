package com.sokoban;

/**
 * 
 */
public class Diamant extends Mobile {
    /**
     * @param Configuration 
     * @param Position
     */
    public Diamant( Position pos) {
        super("Diamant",pos);
    }

	@Override
	public Boolean bougerVers(Direction dir,Configuration conf) {
		Position newPos = this.position.add(dir);
		if (conf.estVide(newPos)) {
			this.position=newPos;
			return (true);
		} else {
			return (false);
		}
	}
}