package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Grid grid = mock(Grid.class);
	GridConfig config = mock(GridConfig.class);
	Player player = mock(Player.class);
	Cell cell = mock(Cell.class);
	PlayerInputReader reader = mock(PlayerInputReader.class);
	Coordinate coordinate = mock(Coordinate.class);

	Game game;

	@Before
	public void init() {
		when(reader.readConfiguration()).thenReturn(config);
		when(grid.get(any(Coordinate.class))).thenReturn(cell);

		game = new Game(grid, player, reader);
	}

	@Test
	public void game_is_over_when_player_exploses_a_mine() {

		when(player.isDead()).thenReturn(true);

		assertThat(game.isOver()).isTrue();
	}

	@Test
	public void game_is_over_when_player_finds_all_the_mines() {

		when(player.isTheMasterOfMines()).thenReturn(true);

		assertThat(game.isOver()).isTrue();
	}

	@Test
	public void game_continues_when_mines_are_left() {

		when(player.hasMinesLeft()).thenReturn(true);

		assertThat(game.isOver()).isFalse();
	}

	@Test
	public void opening_a_mine_cell_exploses_player() {
		when(cell.isClosed()).thenReturn(true);
		when(cell.isExplosed()).thenReturn(true);

		game.open(coordinate);

		verify(grid).open(coordinate);
		verify(player).setExplosed(true);
	}

	@Test
	public void opening_a_non_closed_cell_does_nothing() {

		game.open(coordinate);

		verify(grid, never()).open(coordinate);
		verifyZeroInteractions(player);
	}

	@Test
	public void tagging_a_closed_empty_cell_decrements_player_mines_left() {

		when(cell.isClosed()).thenReturn(true);
		when(player.hasMinesLeft()).thenReturn(true);

		game.tagMine(coordinate);

		verify(grid).tagMine(coordinate);
		verify(player).decrementMinesLeft();
		verify(player, never()).incrementFoundMines();
	}

	@Test
	public void tagging_a_closed_mine_cell_decrements_player_mines_left_and_increments_mines_found() {

		when(cell.isClosed()).thenReturn(true);
		when(cell.hasMine()).thenReturn(true);
		when(player.hasMinesLeft()).thenReturn(true);

		game.tagMine(coordinate);

		verify(grid).tagMine(coordinate);
		verify(player).decrementMinesLeft();
		verify(player).incrementFoundMines();
	}

	@Test
	public void tagging_a_closed_cell_does_nothing_if_the_player_cannot_tag() {

		when(cell.isClosed()).thenReturn(true);
		when(player.hasMinesLeft()).thenReturn(false);

		game.tagMine(coordinate);

		verify(grid, never()).tagMine(coordinate);
		verify(player, never()).decrementMinesLeft();
		verify(player, never()).incrementFoundMines();
	}

	@Test
	public void tagging_a_non_closed_mine_does_nothing() {

		game.tagMine(coordinate);

		verifyZeroInteractions(player);
		verify(grid, never()).tagMine(coordinate);
	}

	@Test
	public void untagging_a_non_tagged_cell_does_nothing() {

		game.untagMine(coordinate);

		verifyZeroInteractions(player);
		verify(grid, never()).untagMine(coordinate);
	}

	@Test
	public void untagging_a_tagged_empty_cell_increments_player_mines_left() {

		when(cell.isTagged()).thenReturn(true);

		game.untagMine(coordinate);

		verify(grid).untagMine(coordinate);
		verify(player).incrementMinesLeft();
		verify(player, never()).decrementFoundMines();
	}

	@Test
	public void untagging_a_tagged_mine_cell_increments_player_mines_left_and_decrements_mines_found() {

		when(cell.isTagged()).thenReturn(true);
		when(cell.hasMine()).thenReturn(true);

		game.untagMine(coordinate);

		verify(grid).untagMine(coordinate);
		verify(player).incrementMinesLeft();
		verify(player).decrementFoundMines();
	}

}
