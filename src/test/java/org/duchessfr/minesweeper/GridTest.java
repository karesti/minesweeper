package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GridTest {

	@Test
	public void can_create_valid_game() {
		assertThat(new Grid(10, 5).isValid()).isTrue();
	}

	@Test
	public void invalid_game_if_mines_value_is_less_than_size() {
		assertThat(new Grid(10, 11).isValid()).isFalse();
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

		Grid grid = new Grid(5, 3);

		StringBuilder initState = new StringBuilder();
		initState.append("[ # ][ # ][ # ][ # ][ # ]\n");
		initState.append("[ # ][ # ][ # ][ # ][ # ]\n");
		initState.append("[ # ][ # ][ # ][ # ][ # ]\n");
		initState.append("[ # ][ # ][ # ][ # ][ # ]\n");
		initState.append("[ # ][ # ][ # ][ # ][ # ]\n");

		assertThat(grid.toString()).isEqualTo(initState.toString());

	}

	@Test
	public void all_the_mines_are_found_when_every_mine_is_tagged() {

		Grid grid = new Grid(1, 1);

		assertThat(grid.allMinesFound()).isFalse();

		grid.tagMine(0, 0);

		assertThat(grid.allMinesFound()).isTrue();
	}

	@Test
	public void can_untag_cell() {
		Grid grid = new Grid(1, 1);

		grid.tagMine(0, 0);
		grid.untagMine(0, 0);

		assertThat(grid.allMinesFound()).isFalse();
	}
}
