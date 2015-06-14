package org.duchessfr.minesweeper;

import java.util.Objects;

public class Coordinate {

	private final int x;
	private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj))
			return false;

		if (!(obj instanceof Coordinate))
			return false;

		Coordinate c = (Coordinate) obj;

		return Objects.equals(x, c.x) && Objects.equals(y, c.y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
