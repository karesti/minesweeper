package org.duchessfr.minesweeper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.duchessfr.minesweeper.Cell.CellBuilder;
import org.duchessfr.minesweeper.Cell.Status;

public class Grid {

	private final GridConfig config;

	private final Cell[][] cells;

	private Grid(GridConfig config, Cell[][] cells) {
		this.config = config;
		this.cells = cells;
	}

	public static class GridBuilder {
		private GridConfig config;
		private Cell[][] cells;

		private GridBuilder(GridConfig config, Cell[][] cells) {
			this.config = config;
			this.cells = cells;
		}

		public static GridBuilder config(GridConfig config) {
			return new GridBuilder(config, new Cell[config.getSize()][config.getSize()]);
		}

		public Grid create() {

			if (!config.isValid()) {
				throw new IllegalStateException("Provided grid configuration is not correct");
			}

			settingRandomMines();

			settingEmptyCellsAndAdjacentsMinesCount();

			return new Grid(config, cells);
		}

		private void settingRandomMines() {
			Random random = new Random();
			int mines = config.getMines();
			while (mines > 0) {
				int randomX = random.nextInt(config.getSize());
				int randomY = random.nextInt(config.getSize());
				if (empty(randomX, randomY)) {
					cells[randomX][randomY] = CellBuilder.start(new Coordinate(randomX, randomY)).withMine().build();
					mines--;
				}
			}
		}

		private void settingEmptyCellsAndAdjacentsMinesCount() {
			for (int x = 0; x < config.getSize(); x++) {
				for (int y = 0; y < config.getSize(); y++) {
					if (empty(x, y)) {
						int numMines = calculateAdjacentMineCount(x, y);
						cells[x][y] = CellBuilder.start(new Coordinate(x, y)).withAdjacentMinesCount(numMines).build();
					}
				}
			}
		}

		private int calculateAdjacentMineCount(int x, int y) {
			int numMines = 0;
			for (Coordinate adjCo : config.getValidAdjacentCoordinates(new Coordinate(x, y))) {
				Cell adj = cells[adjCo.getX()][adjCo.getY()];
				if (adj != null && adj.hasMine()) {
					numMines++;
				}
			}
			return numMines;
		}

		private boolean empty(int x, int y) {
			return cells[x][y] == null;
		}

	}

	@Override
	public String toString() {
		StringBuilder image = new StringBuilder();
		for (int x = 0; x < config.getSize(); x++) {
			for (int y = 0; y < config.getSize(); y++) {
				image.append(cells[x][y]);
				image.append(" ");
			}
			image.append("\n");
		}
		return image.toString();
	}

	public boolean open(Coordinate co) {

		boolean saveOpen = true;

		Cell selected = get(co);
		if (!selected.isClosed())
			return saveOpen;

		if (selected.hasMine()) {
			explose();
			return false;
		}

		selected = selected.copy(Status.OPENED);
		put(selected);

		if (selected.getAdjacentMinesCount() > 0)
			return saveOpen;

		openAdjacents(selected);

		return saveOpen;
	}

	private void explose() {
		for (int x = 0; x < config.getSize(); x++) {
			for (int y = 0; y < config.getSize(); y++) {
				if (cells[x][y].hasMine()) {
					put(cells[x][y].copy(Status.EXPLOSED));
				}
			}
		}

	}

	private void openAdjacents(Cell selected) {
		Queue<Cell> aux = new LinkedList<Cell>();
		aux.add(selected);
		while (!aux.isEmpty()) {
			Cell current = aux.poll();
			for (Coordinate adjCo : config.getValidAdjacentCoordinates(current.getCoordinate())) {
				Cell adj = this.get(adjCo);
				if (adj.isClosed() && !adj.hasMine()) {
					adj = adj.copy(Status.OPENED);
					put(adj);
					if (adj.getAdjacentMinesCount() == 0)
						aux.add(adj);
				}
			}
		}
	}

	public boolean tagMine(Coordinate co) {
		Cell cell = get(co);
		if (cell.isClosed()) {
			put(cell.copy(Status.TAGGED));
		}
		return cell.hasMine();
	}

	public boolean untagMine(Coordinate co) {
		Cell cell = get(co);
		if (cell.isTagged()) {
			put(cell.copy(Status.CLOSED));
		}
		return get(co).hasMine();
	}

	public int getMines() {
		return config.getMines();
	}

	public int getSize() {
		return config.getSize();
	}

	public Cell get(Coordinate co) {
		return cells[co.getX()][co.getY()];
	}

	private void put(Cell cell) {
		cells[cell.getCoordinate().getX()][cell.getCoordinate().getY()] = cell;
	}

}
