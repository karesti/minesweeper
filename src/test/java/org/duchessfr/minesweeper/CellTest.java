package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.duchessfr.minesweeper.Cell.CellBuilder;
import org.duchessfr.minesweeper.Cell.Status;
import org.junit.Test;

public class CellTest {

	@Test
	public void can_print_explosed_cell() {

		Cell cell = CellBuilder.start(new Coordinate(0, 2)).withMine().withStatus(Status.EXPLOSED).build();

		assertThat(cell.toString()).isEqualTo("(0,2)[ @ ]");
	}

	@Test
	public void can_print_closed_cell() {

		Cell cell = CellBuilder.start(new Coordinate(0, 2)).build();

		assertThat(cell.toString()).isEqualTo("(0,2)[ # ]");
	}

	@Test
	public void can_print_opened_empty_cell() {

		Cell cell = CellBuilder.start(new Coordinate(0, 2)).withStatus(Status.OPENED).build();

		assertThat(cell.toString()).isEqualTo("(0,2)[ _ ]");
	}

	@Test
	public void can_print_opened_with_mines_around() {

		Cell cell = CellBuilder.start(new Coordinate(0, 2)).withAdjacentMinesCount(3).withStatus(Status.OPENED).build();

		assertThat(cell.toString()).isEqualTo("(0,2)[ 3 ]");
	}

	@Test
	public void can_print_tagged_cell() {

		Cell cell = CellBuilder.start(new Coordinate(0, 2)).withAdjacentMinesCount(3).withMine().withStatus(Status.TAGGED).build();

		assertThat(cell.toString()).isEqualTo("(0,2)[ M ]");
	}

	@Test
	public void can_copy() {

		Cell cell = CellBuilder.start(new Coordinate(3, 4)).withAdjacentMinesCount(4).withMine().withStatus(Status.CLOSED).build();

		Cell copy = cell.copy(Status.TAGGED);

		assertThat(copy.getCoordinate()).isEqualTo(cell.getCoordinate());
		assertThat(copy.hasMine()).isEqualTo(cell.hasMine());
		assertThat(copy.getAdjacentMinesCount()).isEqualTo(cell.getAdjacentMinesCount());
		assertThat(copy.isTagged()).isTrue();
	}

}
