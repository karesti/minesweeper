package org.duchessfr.minesweeper;

import java.util.Scanner;

public class PlayerInputReader implements AutoCloseable {

	private final Scanner scanner;

	public PlayerInputReader() {
		this.scanner = new Scanner(System.in);
	}

	public PlayerInputReader(Scanner scanner) {
		this.scanner = scanner;
	}

	public int readSize() {
		System.out.println("Enter size :");

		return readSaveInt();
	}

	public int readMines() {
		System.out.println("Enter mines :");

		return readSaveInt();
	}

	public int readX(int max) {
		System.out.println("Enter X :");

		return readSaveCoordinate(0, max);
	}

	public int readY(int max) {
		System.out.println("Enter Y :");

		return readSaveCoordinate(0, max);
	}

	public int readAction() {
		System.out.println("Open [1], Tag Mine [2], Untag Mine [3], Cancel [4] : ");

		return readSaveInt();
	}

	@Override
	public void close() throws Exception {
		scanner.close();
	}

	private int readSaveCoordinate(int min, int max) {

		int coordinate = readSaveInt();

		if (coordinate < min || coordinate > max) {
			System.out.println("Give a valid coordinate please");
			return -1;
		}

		return coordinate;
	}

	private int readSaveInt() {

		while (!scanner.hasNextInt()) {
			scanner.next();
		}

		return scanner.nextInt();
	}

}
