package org.duchessfr.minesweeper;

public class Cell {

	public enum Status {
		CLOSED, TAGGED, OPENED, EXPLOSED
	}

	private final int x;

	private final int y;

	private int neighbourMinesCount;

	private boolean mine;

	private Status status = Status.CLOSED;

	public Cell() {
		this.x = 0;
		this.y = 0;
	}

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {

		StringBuilder image = new StringBuilder();
		image.append("(");
		image.append(x);
		image.append(",");
		image.append(y);
		image.append(")");

		switch (status) {
		case CLOSED:
			image.append("[ # ]");
			break;

		case TAGGED:
			image.append("[ M ]");
			break;

		case OPENED:
			if (neighbourMinesCount == 0)
				image.append("[ _ ]");
			else
				image.append("[ " + neighbourMinesCount + " ]");
			break;
		case EXPLOSED:
			image.append("[ @ ]");
			break;
		default:
			throw new IllegalStateException("Status cell is not correct");

		}

		return image.toString();
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNeighbourMinesCount() {
		return neighbourMinesCount;
	}

	public void incrementNeighbourMineCount() {
		this.neighbourMinesCount++;
	}

}
