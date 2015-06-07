package org.duchessfr.minesweeper;

import org.assertj.core.util.VisibleForTesting;

public class Game {

	private Grid grid;
	private int mines;
	private boolean explosed;
	private boolean minesFound;

	public Game() {

	}

	@VisibleForTesting
	Game(Grid grid) {
		this.grid = grid;
		this.mines = grid.getMines();
	}

	public static void main(String[] args) {

		Game game = new Game();

		game.start();

		while (!game.isOver()) {
			game.askAction();
		}

		game.end();

	}

	private void end() {
		// TODO Auto-generated method stub

	}

	private void askAction() {
		// TODO Auto-generated method stub

	}

	public boolean isOver() {
		return explosed || minesFound;
	}

	void start() {
		grid = new Grid(5, 3);
	}

	public void start(int size, int mines) {
		grid = new Grid(size, mines);
		this.mines = mines;
	}

	public void open(int x, int y) {
		this.explosed = !grid.open(x, y);
	}

	public void tagMine(int x, int y) {
		if (mines > 0) {
			mines--;
			this.minesFound = grid.tagMine(x, y);
		}
	}

	public int getMines() {
		return this.mines;
	}

	public void untagMine(int x, int y) {
		grid.untagMine(x, y);
		mines++;
	}
}
