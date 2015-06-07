package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CellTest {

	@Test
	public void can_tag_and_untag_cell() {

		Cell cell = new Cell();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.CLOSED);

		cell.tagAsMine();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.TAGGED);
		assertThat(cell.isTagged()).isTrue();

		cell.untagAsMine();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.CLOSED);
	}

	@Test
	public void can_untag_if_already_tagged() {

		Cell cell = new Cell();

		cell.open();
		cell.untagAsMine();

		assertThat(cell.isClosed()).isFalse();
	}

	@Test
	public void opening_a_mine_causes_explosion() {
		Cell cell = new Cell();
		cell.putMine();
		cell.open();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.EXPLOSED);
		assertThat(cell.isExplosed()).isTrue();
	}

	@Test
	public void can_open_a_closed_cell() {
		Cell cell = new Cell();
		cell.open();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.OPENED);
	}

	@Test
	public void can_tag_only_a_closed_cell() {
		Cell cell = new Cell();
		cell.open();
		cell.tagAsMine();

		assertThat(cell.getStatus()).isEqualTo(Cell.Status.OPENED);
	}

	@Test
	public void can_print_closed_cell() {

		Cell cell = new Cell();

		assertThat(cell.toString()).isEqualTo("(0,0)[ # ]");
	}

	@Test
	public void can_print_discovered_empty_cell() {

		Cell cell = new Cell();
		cell.open();

		assertThat(cell.toString()).isEqualTo("(0,0)[ _ ]");

	}

	@Test
	public void can_print_discovered_with_mines() {

		Cell cell = new Cell();
		cell.setNeighbourMinesCount(2);
		cell.open();

		assertThat(cell.toString()).isEqualTo("(0,0)[ 2 ]");

	}

	@Test
	public void can_print_tagged_cell() {

		Cell cell = new Cell();
		cell.tagAsMine();

		assertThat(cell.toString()).isEqualTo("(0,0)[ M ]");

	}

}
