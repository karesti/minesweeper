package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CellOpenerTest {

	@Test
	public void opening_a_cell_opens_every_neighbour_empty_cell_without_neighbours() {
		Grid grid = new Grid(3, 0);
		CellOpener opener = new CellOpener(grid, 0, 0);

		opener.open();

		StringBuilder afterOpeningState = new StringBuilder();
		afterOpeningState.append("(0,0)[ _ ] (0,1)[ _ ] (0,2)[ _ ] \n");
		afterOpeningState.append("(1,0)[ _ ] (1,1)[ _ ] (1,2)[ _ ] \n");
		afterOpeningState.append("(2,0)[ _ ] (2,1)[ _ ] (2,2)[ _ ] \n");

		assertThat(grid.toString()).isEqualTo(afterOpeningState.toString());
	}

}
