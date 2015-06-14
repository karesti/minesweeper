package org.duchessfr.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class GridConfig {

	private final int size;

	private final int mines;

	public GridConfig(int size, int mines) {
		this.size = size;
		this.mines = mines;
	}

	public int getMines() {
		return mines;
	}

	public int getSize() {
		return size;
	}

	public boolean isValid() {
		return size > 0 && mines >= 0 && mines <= size * size;
	}

	public List<Coordinate> getValidAdjacentCoordinates(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		List<Coordinate> adj = new ArrayList<Coordinate>();
		if (x > 0) {
			adj.add(new Coordinate(x - 1, y));
			if (y < this.getSize() - 1) {
				adj.add(new Coordinate(x - 1, y + 1));
			}
			if (y > 0) {
				adj.add(new Coordinate(x - 1, y - 1));
			}
		}
		if (x < this.getSize() - 1) {
			adj.add(new Coordinate(x + 1, y));
			if (y < this.getSize() - 1) {
				adj.add(new Coordinate(x + 1, y + 1));
			}
			if (y > 0) {
				adj.add(new Coordinate(x + 1, y - 1));
			}
		}

		if (y > 0) {
			adj.add(new Coordinate(x, y - 1));
		}
		if (y < this.getSize() - 1) {
			adj.add(new Coordinate(x, y + 1));
		}

		return adj;
	}
}
