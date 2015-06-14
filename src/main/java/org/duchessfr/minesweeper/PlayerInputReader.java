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

	public GridConfig readConfiguration() {
		int size = -1;
		while (size <= 0) {
			System.out.print("Enter size (1..N) : ");
			size = readSaveInt();
		}

		System.out.println("");

		int mines = -1;
		System.out.print("Enter mines (smaller than " + size * size + ") : ");

		while (mines < 0 || mines > size * size) {
			mines = readSaveInt();
		}

		System.out.println("");

		return new GridConfig(size, mines);
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
