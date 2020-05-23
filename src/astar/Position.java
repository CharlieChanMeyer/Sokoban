package astar;

public class Position {
	private int x;
	private int y;
	
	public Position(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Boolean equal(Position pos) {
		if((this.x == pos.getX()) && (this.y == pos.getY())) {
			return true;
		} else {
			return false;
		}
	}
}

