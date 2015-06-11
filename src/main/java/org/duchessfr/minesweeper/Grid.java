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

	public Grid(Cell[][] cells) {
		this.size = cells.length;
		this.mines = getActiveMines();
		this.cells = cells;
	}

	void init() {
		if (!isValid())
			return;

		initMatrix();

		putRandomMines();
	}

	private void putRandomMines() {
		Random random = new Random();
		while (getActiveMines() < mines) {
			int randomX = random.nextInt(size);
			int randomY = random.nextInt(size);
			Cell cell = cells[randomX][randomY];
			cell.putMine();
			for (Cell adj : getAdjacents(randomX, randomY))
				adj.incrementNeighbourMineCount();
		}
	}

	boolean isValid() {
		return size >= 1 && mines <= (size * size);
	}

	private void initMatrix() {
		this.cells = new Cell[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				cells[x][y] = new Cell(x, y);
			}
		}
	}

	int getActiveMines() {
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

		boolean explosed = new CellOpener(this, x, y).open();

		return explosed;
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

	public boolean hasMine(int x, int y) {
		return getCell(x, y).hasMine();
	}

	public boolean isTagged(int x, int y) {
		return getCell(x, y).isTagged();
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
