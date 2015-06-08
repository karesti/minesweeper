package org.duchessfr.minesweeper;

import java.util.InputMismatchException;
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

		return readSaveInt("Size must be a number !");
	}

	public int readMines() {
		System.out.println("Enter mines :");

		return readSaveInt("Mines must be a number !");
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

		return readSaveInt("Must be a valid action !");
	}

	@Override
	public void close() throws Exception {
		scanner.close();
	}

	private int readSaveCoordinate(int min, int max) {

		int coordinate = readSaveInt("Must be a valid coordinate !");

		if (coordinate < min || coordinate > max) {
			System.out.println("Give a valid coordinate please");
			return -1;
		}

		return coordinate;
	}

	private int readSaveInt(String message) {
		int mines = -1;

		try {
			mines = scanner.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println(message);
		}
		return mines;
	}

}
