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
		Coordinate co = new Coordinate(x, y);
		int action = reader.readAction();

		switch (action) {
		case 1:
			open(co);
			break;
		case 2:
			tagMine(co);
			break;
		case 3:
			untagMine(co);
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

	void open(Coordinate co) {
		grid.open(co);
		player.setExplosed(grid.get(co).isExplosed());
	}

	void tagMine(Coordinate co) {
		if (grid.get(co).isTagged())
			return;

		if (player.hasMinesLeft()) {
			player.decrementMinedLeft();
			grid.tagMine(co);
			if (grid.get(co).hasMine()) {
				player.incrementFoundMines();
			}
		}
	}

	void untagMine(Coordinate co) {
		if (!grid.get(co).isTagged())
			return;

		grid.untagMine(co);
		player.incrementMinesLeft();
		if (grid.get(co).hasMine()) {
			player.decrementFoundMines();
		}
	}
}
