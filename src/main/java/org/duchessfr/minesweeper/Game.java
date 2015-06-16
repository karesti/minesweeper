package org.duchessfr.minesweeper;

public class Game {

	private final PlayerInputReader reader;
	private final PlayerOutputWriter writer;
	private final Grid grid;
	private final Player player;

	public Game(Grid grid, Player player, PlayerInputReader reader, PlayerOutputWriter writer) {
		this.grid = grid;
		this.player = player;
		this.reader = reader;
		this.writer = writer;
	}

	public void run() {

		while (!isOver()) {
			writer.printGameStatus(this);
			askAction();
		}

		writer.printGameStatus(this);
	}

	private void askAction() {

		int x = reader.readX(grid.getSize());
		int y = reader.readY(grid.getSize());
		Coordinate co = new Coordinate(x, y);

		switch (reader.readAction()) {
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

	boolean isOver() {
		return player.isDead() || player.isTheMasterOfMines();
	}

	void open(Coordinate co) {
		if (!grid.get(co).isClosed())
			return;

		grid.open(co);
		player.setExplosed(grid.get(co).isExplosed());
	}

	void tagMine(Coordinate co) {
		if (!grid.get(co).isClosed())
			return;

		if (player.hasMinesLeft()) {
			player.decrementMinesLeft();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(player);
		builder.append(grid);
		return builder.toString();
	}
}
