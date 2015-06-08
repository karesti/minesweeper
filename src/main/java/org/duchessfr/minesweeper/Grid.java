package org.duchessfr.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

	private final int size;
	private final int mines;

	private Cell[][] cells;

	public Grid(int size, int mines) {
		this.size = size;
		this.mines = mines;

		init();
	}

	void init() {
		if (!isValid())
			return;

		Random random = new Random();
		this.cells = new Cell[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				cells[x][y] = new Cell(x, y);
			}
		}

		while (getActiveMines() < mines) {
			cells[random.nextInt(size)][random.nextInt(size)].putMine();
		}

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int mines = 0;
				for (Cell adj : getAdjacents(x, y)) {
					if (adj.hasMine())
						mines++;
				}
				cells[x][y].setNeighbourMinesCount(mines);
			}
		}
	}

	public boolean isValid() {
		return size >= 1 && mines <= size;
	}

	public int getActiveMines() {
		int mines = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (cells[x][y].hasMine()) {
					mines++;
				}
			}
		}
		return mines;
	}

	@Override
	public String toString() {
		StringBuilder image = new StringBuilder();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				image.append(cells[x][y]);
				image.append(" ");
			}
			image.append("\n");
		}
		return image.toString();
	}

	public boolean open(int x, int y) {
		return new CellOpener(this, x, y).open();
	}

	public List<Cell> getAdjacents(int x, int y) {
		List<Cell> adj = new ArrayList<Cell>();
		if (x > 0) {
			adj.add(cells[x - 1][y]);
			if (y < size - 1) {
				adj.add(cells[x - 1][y + 1]);
			}
			if (y > 0) {
				adj.add(cells[x - 1][y - 1]);
			}
		}
		if (x < size - 1) {
			adj.add(cells[x + 1][y]);
			if (y < size - 1) {
				adj.add(cells[x + 1][y + 1]);
			}
			if (y > 0) {
				adj.add(cells[x + 1][y - 1]);
			}
		}

		if (y > 0) {
			adj.add(cells[x][y - 1]);
		}
		if (y < size - 1) {
			adj.add(cells[x][y + 1]);
		}

		return adj;
	}

	public Cell getCell(int x, int y) {
		return cells[x][y];
	}

	public boolean tagMine(int x, int y) {
		cells[x][y].tagAsMine();
		return cells[x][y].hasMine();
	}

	public boolean untagMine(int x, int y) {
		cells[x][y].untagAsMine();
		return cells[x][y].hasMine();
	}

	public int getMines() {
		return mines;
	}

	public int getSize() {
		return size;
	}

}
