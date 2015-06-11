package org.duchessfr.minesweeper;

import java.util.LinkedList;
import java.util.Queue;

public class CellOpener {

	private final Grid grid;
	private final int x;
	private final int y;

	public CellOpener(Grid grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;

	}

	public boolean open() {

		boolean saveOpen = true;

		Cell selected = grid.getCell(x, y);
		if (!selected.isClosed())
			return saveOpen;

		selected.open();

		if (grid.getCell(x, y).isExplosed())
			return false;

		if (selected.getNeighbourMinesCount() > 0)
			return saveOpen;

		Queue<Cell> aux = new LinkedList<Cell>();
		aux.add(grid.getCell(x, y));
		while (!aux.isEmpty()) {
			Cell current = aux.poll();
			for (Cell adj : grid.getAdjacents(current.getX(), current.getY())) {
				if (adj.isClosed() && !adj.hasMine()) {
					adj.open();
					if (adj.getNeighbourMinesCount() == 0)
						aux.add(adj);
				}
			}
		}
		return saveOpen;
	}
}
