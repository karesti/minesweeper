package org.duchessfr.minesweeper;

public class Cell {

	public enum Status {
		CLOSED, TAGGED, OPENED, EXPLOSED
	}

	private final Status status;

	private final Coordinate coordinate;

	private final boolean mine;

	private final int adjacentMinesCount;

	private Cell(Coordinate coordinate, int neighbourMinesCount, boolean hasMine, Status status) {
		this.coordinate = coordinate;
		this.mine = hasMine;
		this.adjacentMinesCount = neighbourMinesCount;
		this.status = status;
	}

	static public class Builder {

		private final Coordinate coordinate;

		private Status status = Status.CLOSED;

		private boolean mine = false;

		private int adjacentMinesCount = 0;

		private Builder(Coordinate coordinate) {
			this.coordinate = coordinate;
		}

		public static Builder start(Coordinate coordinate) {
			return new Builder(coordinate);
		}

		public Builder withMine() {
			this.mine = true;
			return this;
		}

		public Builder withAdjacentMinesCount(int adjMinesCount) {
			this.adjacentMinesCount = adjMinesCount;
			return this;
		}

		public Builder withStatus(Status status) {
			this.status = status;
			return this;
		}

		public Cell build() {
			return new Cell(coordinate, adjacentMinesCount, mine, status);
		}
	}

	@Override
	public String toString() {

		StringBuilder image = new StringBuilder();
		image.append(coordinate);

		switch (status) {
		case CLOSED:
			image.append("[ # ]");
			break;

		case TAGGED:
			image.append("[ M ]");
			break;

		case OPENED:
			if (adjacentMinesCount == 0)
				image.append("[ _ ]");
			else
				image.append("[ " + adjacentMinesCount + " ]");
			break;
		case EXPLOSED:
			image.append("[ @ ]");
			break;
		default:
			throw new IllegalStateException("Status cell is not correct");

		}

		return image.toString();
	}

	public boolean hasMine() {
		return mine;
	}

	public Status getStatus() {
		return status;
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

	public boolean isOpened() {
		return status == Status.OPENED;
	}

	public int getAdjacentMinesCount() {
		return adjacentMinesCount;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Cell copy(Status status) {
		return new Cell(this.coordinate, this.adjacentMinesCount, this.mine, status);
	}

}
