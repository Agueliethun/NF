package puzzle.model;

public class Position {
	public int x;
	public int y;

	public Position() {
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position copy() {
		return new Position(x, y);
	}

	@Override
	public int hashCode() {
		int result = x;
    	result = 31 * result + y;
    	return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			return hashCode() == other.hashCode();
		}

		return false;
	}

	public void offset(Position b) {
		this.x += b.x;
		this.y += b.y;
	}

	@Override
	public String toString() {
		return "x: " + x + ", y: " + y;
	}
}