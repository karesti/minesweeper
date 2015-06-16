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
		String status = null;
		if (isDead()) {
			status = "You are dead !!";

		} else if (isTheMasterOfMines()) {
			status = "You are the master of the mines !! Well done";

		} else if (minesLeft == 0) {
			status = "You have no mines left and you didn't find them all ...";

		} else {
			status = "Mines left : " + minesLeft;
		}

		return status;
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

	public void decrementMinesLeft() {
		minesLeft--;
	}

	public void incrementMinesLeft() {
		minesLeft++;
	}
}
