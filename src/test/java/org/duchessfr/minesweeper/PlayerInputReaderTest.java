package org.duchessfr.minesweeper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Scanner;

import org.junit.Test;

public class PlayerInputReaderTest {

	@Test
	public void can_read_only_valid_config() throws Exception {

		Scanner scanner = new Scanner("foo 3 bar 2");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			GridConfig config = reader.readConfiguration();
			assertThat(config.getSize()).isEqualTo(3);
			assertThat(config.getMines()).isEqualTo(2);
		}

	}

	@Test
	public void can_read_only_valid_actions() throws Exception {

		Scanner scanner = new Scanner(" foo 1");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readAction()).isEqualTo(1);
		}
	}

	@Test
	public void can_read_only_valid_x_coordinate() throws Exception {

		Scanner scanner = new Scanner("foo 8");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(8);
		}

		scanner = new Scanner("31");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(-1);
		}

	}

	@Test
	public void can_read_only_valid_y_coordinate() throws Exception {

		Scanner scanner = new Scanner("foo 8");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readX(30)).isEqualTo(8);
		}

		scanner = new Scanner("31");

		try (PlayerInputReader reader = new PlayerInputReader(scanner)) {
			assertThat(reader.readY(30)).isEqualTo(-1);
		}
	}
}
