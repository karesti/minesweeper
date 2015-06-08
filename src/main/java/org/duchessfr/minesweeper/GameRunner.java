package org.duchessfr.minesweeper;

public class GameRunner {

	public static void main(String[] args) {

		try (PlayerInputReader reader = new PlayerInputReader()) {

			int size = -1;
			while (size != -1) {
				size = reader.readSize();
			}

			int mines = -1;
			while (mines != -1) {
				mines = reader.readMines();
			}

			Game game = new Game(new Grid(size, mines), new Player(mines), reader);

			game.run();

		} catch (Exception ex) {
			System.err.println("Very bad problem found ! report bug => @karesti");
			ex.printStackTrace();
		}
	}
}
