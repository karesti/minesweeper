package org.duchessfr.minesweeper;

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
				cells[x][y] = new Cell();
			}
		}

		while (getActiveMines() < mines) {
			cells[random.nextInt(size)][random.nextInt(size)].putMine();
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
		StringBuilder initState = new StringBuilder();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				initState.append(cells[x][y]);
			}
			initState.append("\n");
		}
		return initState.toString();
	}

	public boolean open(int x, int y) {
		cells[x][y].open();
		return !cells[x][y].isExplosed();
	}

	public boolean tagMine(int x, int y) {
		cells[x][y].tagAsMine();
		return allMinesFound();
	}

	public boolean untagMine(int x, int y) {
		cells[x][y].untagAsMine();
		return cells[x][y].isClosed();
	}

	boolean allMinesFound() {

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (cells[x][y].hasMine() && !cells[x][y].isTagged())
					return false;
			}
		}

		return true;
	}

	public int getMines() {
		return mines;
	}

}
