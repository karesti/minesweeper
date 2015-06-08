package org.duchessfr.minesweeper;

public class Player {

	private final int minesToFind;
	private int minesLeft;
	private int minesFound;
	private boolean explosed;

	public Player(int mines) {
		this.minesLeft = mines;
		this.minesToFind = mines;
	}

	@Override
	public String toString() {
		return "Mines left :" + minesLeft;
	}

	public boolean isDead() {
		return explosed;
	}

	public boolean isTheMasterOfMines() {
		return minesFound == minesToFind;
	}

	public void setExplosed(boolean explosed) {
		this.explosed = explosed;
	}

	public boolean hasMinesLeft() {
		return minesLeft > 0;
	}

	public void incrementFoundMines() {
		this.minesFound++;
	}

	public void decrementFoundMines() {
		this.minesFound--;
	}

	public void decrementMinedLeft() {
		minesLeft--;
	}

	public void incrementMinesLeft() {
		minesLeft++;
	}
}
