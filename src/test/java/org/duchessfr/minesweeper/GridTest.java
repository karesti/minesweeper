package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.duchessfr.minesweeper.Grid.GridBuilder;
import org.junit.Test;

public class GridTest {

	Coordinate coordinate = new Coordinate(0, 0);

	@Test
	public void can_init_random_mines() {

		Grid grid = GridBuilder.config(new GridConfig(3, 3)).create();
		int countMines = grid.getMines();

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid.get(new Coordinate(x, y)).hasMine())
					countMines--;
			}
		}

		assertThat(countMines).isZero();
	}

	@Test
	public void empty_cell_opens_sucessfully() {
		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();

		grid.open(coordinate);

		assertThat(grid.get(coordinate).isOpened()).isTrue();
	}

	@Test
	public void can_tag_closed_cell_sucessfully() {
		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();

		grid.tagMine(coordinate);

		assertThat(grid.get(coordinate).isTagged()).isTrue();
	}

	@Test
	public void can_untag_tagged_cell_sucessfully() {
		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();

		grid.tagMine(coordinate);
		grid.untagMine(coordinate);

		assertThat(grid.get(coordinate).isTagged()).isFalse();
		assertThat(grid.get(coordinate).isClosed()).isTrue();
	}

	@Test
	public void cannot_untag_opened_cell() {
		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();

		grid.open(coordinate);
		grid.untagMine(coordinate);

		assertThat(grid.get(coordinate).isTagged()).isFalse();
		assertThat(grid.get(coordinate).isOpened()).isTrue();
		assertThat(grid.get(coordinate).isClosed()).isFalse();
	}

	@Test
	public void cannot_tag_an_opened_cell() {
		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();
		grid.open(coordinate);
		grid.tagMine(coordinate);

		assertThat(grid.get(coordinate).isOpened()).isTrue();
		assertThat(grid.get(coordinate).isTagged()).isFalse();
	}

	@Test
	public void fail_if_opened_cell_is_mine() {
		Grid grid = GridBuilder.config(new GridConfig(1, 1)).create();

		boolean saveOpen = grid.open(coordinate);

		assertThat(saveOpen).isFalse();
		assertThat(grid.get(coordinate).isExplosed()).isTrue();
	}

	@Test
	public void opening_a_cell_opens_every_neighbour_empty_cell_without_neighbours() {
		Grid grid = GridBuilder.config(new GridConfig(3, 0)).create();

		boolean saveOpen = grid.open(coordinate);

		StringBuilder afterOpeningState = new StringBuilder();
		afterOpeningState.append("(0,0)[ _ ] (0,1)[ _ ] (0,2)[ _ ] \n");
		afterOpeningState.append("(1,0)[ _ ] (1,1)[ _ ] (1,2)[ _ ] \n");
		afterOpeningState.append("(2,0)[ _ ] (2,1)[ _ ] (2,2)[ _ ] \n");

		assertThat(saveOpen).isTrue();
		assertThat(grid.toString()).isEqualTo(afterOpeningState.toString());
	}

	@Test
	public void opening_a_tagged_cell_does_nothing() {

		Grid grid = GridBuilder.config(new GridConfig(1, 1)).create();

		grid.tagMine(coordinate);

		boolean saveOpen = grid.open(coordinate);

		assertThat(saveOpen).isTrue();
		assertThat(grid.get(coordinate).isTagged()).isTrue();
	}

	@Test
	public void opening_a_opened_cell_does_nothing() {

		Grid grid = GridBuilder.config(new GridConfig(1, 0)).create();
		grid.open(coordinate);

		boolean saveOpen = grid.open(coordinate);

		assertThat(saveOpen).isTrue();
	}

	@Test
	public void opening_a_cell_with_mine_exploses() {

		Grid grid = GridBuilder.config(new GridConfig(1, 1)).create();

		boolean saveOpen = grid.open(coordinate);

		assertThat(saveOpen).isFalse();
		assertThat(grid.get(coordinate).isExplosed()).isTrue();
	}

	@Test
	public void can_print() {

		Grid grid = GridBuilder.config(new GridConfig(2, 1)).create();

		StringBuilder initState = new StringBuilder();
		initState.append("(0,0)[ # ] (0,1)[ # ] \n");
		initState.append("(1,0)[ # ] (1,1)[ # ] \n");

		assertThat(grid.toString()).isEqualTo(initState.toString());

	}

}
