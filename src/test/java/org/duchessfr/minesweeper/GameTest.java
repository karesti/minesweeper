package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Grid mockedGrid = mock(Grid.class);
	Game game;

	@Before
	public void init() {
		when(mockedGrid.getMines()).thenReturn(3);
		game = new Game(mockedGrid);

	}

	@Test
	public void can_start_game() {

		game.start();

	}

	@Test
	public void game_is_over_when_player_exploses_a_mine() {

		when(mockedGrid.open(anyInt(), anyInt())).thenReturn(false);

		game.open(0, 0);

		assertThat(game.isOver()).isTrue();
	}

	@Test
	public void game_continue_when_player_opens_safetly() {

		when(mockedGrid.open(anyInt(), anyInt())).thenReturn(true);

		game.open(0, 0);

		assertThat(game.isOver()).isFalse();
	}

	@Test
	public void game_is_over_when_player_finds_all_the_mines() {

		when(mockedGrid.tagMine(anyInt(), anyInt())).thenReturn(true);

		game.tagMine(0, 0);

		assertThat(game.isOver()).isTrue();
	}

	@Test
	public void game_continues_when_mines_are_left() {

		when(mockedGrid.tagMine(anyInt(), anyInt())).thenReturn(false);

		game.tagMine(0, 0);

		assertThat(game.isOver()).isFalse();
	}

	@Test
	public void can_tag_mine_if_moves_are_available() {

		game.start(5, 3);

		game.tagMine(0, 0);
		game.tagMine(0, 1);
		game.tagMine(0, 2);
		game.tagMine(0, 4);

		assertThat(game.getMines()).isEqualTo(0);
	}

	@Test
	public void mines_count_decreases_with_every_tag_mine() {

		game.start(5, 3);

		assertThat(game.getMines()).isEqualTo(3);

		game.tagMine(0, 0);

		assertThat(game.getMines()).isEqualTo(2);
	}

	@Test
	public void mines_count_increases_with_every_untag() {

		game.start(5, 3);

		assertThat(game.getMines()).isEqualTo(3);

		game.tagMine(0, 0);
		game.untagMine(0, 0);

		assertThat(game.getMines()).isEqualTo(3);
	}

}
