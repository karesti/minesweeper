package org.duchessfr.minesweeper;

import org.duchessfr.minesweeper.Grid.GridBuilder;

public class GameRunner {

	public static void main(String[] args) {

		try (PlayerInputReader reader = new PlayerInputReader()) {

			GridConfig config = reader.readConfiguration();

			Grid grid = GridBuilder.config(config).create();

			Game game = new Game(grid, new Player(config.getMines()), reader);

			game.run();

		} catch (Exception ex) {
			System.err.println("Very bad problem found ! report bug => @karesti");
			ex.printStackTrace();
		}
	}
}
