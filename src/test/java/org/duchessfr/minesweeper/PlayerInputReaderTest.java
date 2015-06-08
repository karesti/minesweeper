package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Scanner;

import org.junit.Test;

public class PlayerInputReaderTest {

	@Test
	public void can_read_only_valid_size() throws Exception {

		Scanner scanner = new Scanner("10");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readSize()).isEqualTo(10);
		}

		scanner = new Scanner("foo");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readSize()).isEqualTo(-1);
		}

	}

	@Test
	public void can_read_only_valid_mines() throws Exception {

		Scanner scanner = new Scanner("9");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readMines()).isEqualTo(9);
		}

		scanner = new Scanner("foo");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readMines()).isEqualTo(-1);
		}
	}

	@Test
	public void can_read_only_valid_actions() throws Exception {

		Scanner scanner = new Scanner("1");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readAction()).isEqualTo(1);
		}

		scanner = new Scanner("foo");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readAction()).isEqualTo(-1);
		}
	}

	@Test
	public void can_read_only_valid_x_coordinate() throws Exception {

		Scanner scanner = new Scanner("8");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(8);
		}

		scanner = new Scanner("31");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(-1);
		}

		scanner = new Scanner("foo");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(-1);
		}
	}

	@Test
	public void can_read_only_valid_y_coordinate() throws Exception {

		Scanner scanner = new Scanner("8");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(8);
		}

		scanner = new Scanner("31");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readY(30)).isEqualTo(-1);
		}

		scanner = new Scanner("foo");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readY(30)).isEqualTo(-1);
		}
	}
}
