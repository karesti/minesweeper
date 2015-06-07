package org.duchessfr.minesweeper;

public class Cell {

	public enum Status {
		CLOSED, TAGGED, OPENED, EXPLOSED
	}

	private int neighbourMinesCount;

	private boolean mine;

	private Status status = Status.CLOSED;

	@Override
	public String toString() {

		String image = null;

		switch (status) {
		case CLOSED:
			image = "[ # ]";
			break;

		case TAGGED:
			image = "[ M ]";
			break;

		case OPENED:
			image = neighbourMinesCount == 0 ? "[ _ ]" : "[ " + neighbourMinesCount + " ]";
			break;

		default:
			throw new IllegalStateException("Status cell is not correct");

		}

		return image;
	}

	public void setNeighbourMinesCount(int neighbourMinesCount) {
		this.neighbourMinesCount = neighbourMinesCount;
	}

	public void putMine() {
		this.mine = true;
	}

	public boolean hasMine() {
		return mine;
	}

	public Status getStatus() {
		return status;
	}

	public void open() {
		this.status = mine ? Status.EXPLOSED : Status.OPENED;
	}

	public void tagAsMine() {
		if (status == Status.CLOSED) {
			status = Status.TAGGED;
		}
	}

	public void untagAsMine() {
		if (status == Status.TAGGED) {
			this.status = Status.CLOSED;
		}
	}

	public boolean isExplosed() {
		return status == Status.EXPLOSED;
	}

	public boolean isTagged() {
		return status == Status.TAGGED;
	}

	public boolean isClosed() {
		return status == Status.CLOSED;
	}
}
