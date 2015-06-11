package org.duchessfr.minesweeper;

public class Game {

	private final PlayerInputReader reader;
	private final Grid grid;
	private final Player player;

	public Game(Grid grid, Player player, PlayerInputReader reader) {
		this.reader = reader;
		this.grid = grid;
		this.player = player;
	}

	public void run() {

		while (!isOver()) {
			askAction();
		}

		end();
	}

	private void askAction() {
		printGameStatus();

		int x = reader.readX(grid.getSize());
		int y = reader.readY(grid.getSize());
		int action = reader.readAction();

		switch (action) {
		case 1:
			open(x, y);
			break;
		case 2:
			tagMine(x, y);
			break;
		case 3:
			untagMine(x, y);
			break;
		default:
		}
	}

	private void end() {
		printGameStatus();

		if (player.isDead())
			System.out.println("You are dead !!");
		else
			System.out.println("You are the master of the mines !! Well done");

	}

	private void printGameStatus() {
		System.out.println(player);
		System.out.println(grid);
	}

	boolean isOver() {
		return player.isDead() || player.isTheMasterOfMines();
	}

	void open(int x, int y) {
		boolean explosed = !grid.open(x, y);
		player.setExplosed(explosed);
	}

	void tagMine(int x, int y) {
		if (player.hasMinesLeft()) {
			player.decrementMinedLeft();
			boolean isMine = grid.tagMine(x, y);
			if (isMine) {
				player.incrementFoundMines();
			}
		}
	}

	void untagMine(int x, int y) {
		if (grid.isTagged(x, y)) {
			grid.untagMine(x, y);
			player.incrementMinesLeft();
			boolean isMine = grid.hasMine(x, y);
			if (isMine) {
				player.decrementFoundMines();
			}
		}
	}
}
