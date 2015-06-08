package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class GridTest {

	@Test
	public void can_create_valid_game() {
		assertThat(new Grid(10, 5).isValid()).isTrue();
	}

	@Test
	public void invalid_game_if_mines_value_is_less_than_size() {
		assertThat(new Grid(10, 101).isValid()).isFalse();
	}

	@Test
	public void invalid_game_if_negative_size() {
		assertThat(new Grid(-1, -1).isValid()).isFalse();
	}

	@Test
	public void can_init_game_with_random_mines() {

		Grid grid = new Grid(10, 5);

		assertThat(grid.getActiveMines()).isEqualTo(5);
	}

	@Test
	public void can_open_a_cell_succesfully() {
		Grid grid = new Grid(1, 0);

		assertThat(grid.open(0, 0)).isTrue();
	}

	@Test
	public void fail_if_opened_cell_is_mine() {
		Grid grid = new Grid(1, 1);

		assertThat(grid.open(0, 0)).isFalse();
	}

	@Test
	public void can_print_cell() {

		Grid grid = new Grid(2, 1);

		StringBuilder initState = new StringBuilder();
		initState.append("(0,0)[ # ] (0,1)[ # ] \n");
		initState.append("(1,0)[ # ] (1,1)[ # ] \n");

		assertThat(grid.toString()).isEqualTo(initState.toString());

	}

	@Test
	public void can_get_adjacents() {

		Grid grid = new Grid(3, 0);

		List<Cell> adjacents = grid.getAdjacents(0, 0);

		assertThat(adjacents).hasSize(3);
		assertThat(adjacents).containsOnly(grid.getCell(0, 1), grid.getCell(1, 1), grid.getCell(1, 0));

		adjacents = grid.getAdjacents(1, 1);

		assertThat(adjacents).hasSize(8);
		assertThat(adjacents).doesNotContain(grid.getCell(1, 1));

		adjacents = grid.getAdjacents(2, 2);
		assertThat(adjacents).containsOnly(grid.getCell(1, 2), grid.getCell(2, 1), grid.getCell(1, 1));

	}
}
