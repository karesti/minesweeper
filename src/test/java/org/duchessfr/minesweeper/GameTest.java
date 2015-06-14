package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;
	Grid grid = mock(Grid.class);
	GridConfig config = mock(GridConfig.class);
	Player player = mock(Player.class);
	PlayerInputReader reader = mock(PlayerInputReader.class);

	@Before
	public void init() {
		when(reader.readConfiguration()).thenReturn(config);
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

}
