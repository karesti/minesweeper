package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void override_equals() {

		assertThat(new Coordinate(0, 0)).isEqualTo(new Coordinate(0, 0));
		assertThat(new Coordinate(1, 0)).isNotEqualTo(new Coordinate(0, 1));
		assertThat(new Coordinate(1, 1)).isNotEqualTo(new Coordinate(1, 0));
		assertThat(new Coordinate(1, 0)).isNotEqualTo(new Coordinate(0, 0));
	}

	@Test
	public void overrride_hashcode() {

		assertThat(new Coordinate(0, 0).hashCode()).isEqualTo(new Coordinate(0, 0).hashCode());
		assertThat(new Coordinate(1, 0).hashCode()).isNotEqualTo(new Coordinate(0, 1).hashCode());
		assertThat(new Coordinate(1, 1).hashCode()).isNotEqualTo(new Coordinate(1, 0).hashCode());
		assertThat(new Coordinate(1, 0).hashCode()).isNotEqualTo(new Coordinate(0, 0).hashCode());
	}

	@Test
	public void can_print() {
		assertThat(new Coordinate(0, 1).toString()).isEqualTo("(0,1)");
	}
}
