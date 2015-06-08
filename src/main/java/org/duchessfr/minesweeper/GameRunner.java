package org.duchessfr.minesweeper;

public class GameRunner {

	public static void main(String[] args) {

		try (PlayerInputReader reader = new PlayerInputReader()) {

			int size = reader.readSize();

			int mines = reader.readMines();

			Game game = new Game(new Grid(size, mines), new Player(mines), reader);

			game.run();

		} catch (Exception ex) {
			System.err.println("Very bad problem found ! report bug => @karesti");
			ex.printStackTrace();
		}
	}
}
