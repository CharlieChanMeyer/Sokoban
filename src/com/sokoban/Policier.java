package com.sokoban;

/**
 * 
 */
public class Policier extends Mobile {
    /**
     * 
     */
    public Direction regard;

    /**
     * @param Configuration 
     * @param Position
     */
    public Policier(Position pos) {
        super("Policier",pos);
    }

    /**
     * @param Direction 
     * @return
     */
    public boolean verifVision(Direction dir) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public Direction getRegard() {
        return this.regard;
    }

	@Override
	public Boolean bougerVers(Direction dir,Configuration conf) {
		Position newPos = this.position.add(dir);
		this.regard=dir;
		if (conf.estVide(newPos)) {
			this.position=newPos;
			return (true);
		} else {
			return (false);
		}
	}
}