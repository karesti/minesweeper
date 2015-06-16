package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void initial_state() {

		Player player = new Player(10);

		assertThat(player.isDead()).isFalse();
		assertThat(player.isTheMasterOfMines()).isFalse();
		assertThat(player.toString()).isEqualTo("Mines left : 10");
	}

	@Test
	public void is_dead_if_explosed() {

		Player player = new Player(10);
		player.setExplosed(true);

		assertThat(player.isDead()).isTrue();
		assertThat(player.isTheMasterOfMines()).isFalse();
		assertThat(player.toString()).isEqualTo("You are dead !!");
	}

	@Test
	public void is_master_of_mines_if_every_mine_found() {

		Player player = new Player(1);

		player.incrementFoundMines();

		assertThat(player.isDead()).isFalse();
		assertThat(player.isTheMasterOfMines()).isTrue();
		assertThat(player.toString()).isEqualTo("You are the master of the mines !! Well done");
	}

	@Test
	public void not_a_winner_but_not_over() {

		Player player = new Player(1);

		player.decrementMinesLeft();

		assertThat(player.isDead()).isFalse();
		assertThat(player.isTheMasterOfMines()).isFalse();
		assertThat(player.toString()).isEqualTo("You have no mines left and you didn't find them all ...");
	}
}
