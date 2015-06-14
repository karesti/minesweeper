package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class GridConfigTest {

	@Test
	public void can_check_validity() {
		assertThat(new GridConfig(10, 5).isValid()).isTrue();
	}

	@Test
	public void invalid_game_if_mines_value_is_greather_than_size() {
		assertThat(new GridConfig(10, 101).isValid()).isFalse();
	}

	@Test
	public void invalid_game_if_negative_size() {
		assertThat(new GridConfig(-1, -1).isValid()).isFalse();
	}

	@Test
	public void can_return_valid_adjacents_coordinates() {

		GridConfig config = new GridConfig(3, 0);

		List<Coordinate> adjacents = config.getValidAdjacentCoordinates(new Coordinate(0, 0));

		assertThat(adjacents).hasSize(3);
		assertThat(adjacents).containsOnly(new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(1, 0));

		adjacents = config.getValidAdjacentCoordinates(new Coordinate(1, 1));

		assertThat(adjacents).hasSize(8);
		assertThat(adjacents).doesNotContain(new Coordinate(1, 1));

		adjacents = config.getValidAdjacentCoordinates(new Coordinate(2, 2));
		assertThat(adjacents).containsOnly(new Coordinate(1, 2), new Coordinate(2, 1), new Coordinate(1, 1));

	}

}
