package com.wordgame.model;

public class Board
{
	private static final int DEFAULT_SIZE = 15;

	private static final String EMPTY_TILE_STRING = " ";

	private static final String VERTICAL_SEPARATOR = "|";

	private static final String HORIZONTAL_SEPARATOR = "-";

	private static final String ROW_END = "\r\n";

	private int rows;

	private int columns;

	private Tile[][] board;

	public Board()
	{
		this.rows = Board.DEFAULT_SIZE;
		this.columns = Board.DEFAULT_SIZE;
		this.board = new Tile[this.rows][this.columns];
		this.clear();
	}

	public Board(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		this.board = new Tile[this.rows][this.columns];
		this.clear();
	}

	public Board(Board oldBoard)
	{
		this.board = new Tile[oldBoard.rows][oldBoard.columns];

		// Do a deep copy of every tile.
		for (int i = 0; i < this.rows; i++)
		{
			for (int j = 0; j < this.columns; j++)
			{
				this.board[i][j] = new Tile(oldBoard.board[i][j]);
			}
		}
	}

	public int getRows()
	{
		return this.rows;
	}

	public int getColumns()
	{
		return this.columns;
	}

	public Tile get(int row, int column)
	{
		return this.board[row][column];
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean areEqual = false;
		if (obj instanceof Board)
		{
			Board other = (Board) obj;
			if (this.rows == other.rows && this.columns == other.columns)
			{
				areEqual = true;
				for (int i = 0; i < this.rows; i++)
				{
					for (int j = 0; j < this.columns; j++)
					{
						areEqual &= this.board[i][j].equals(other.board[i][j]);
					}
				}
			}
		}
		return areEqual;
	}

	@Override
	public String toString()
	{
		// Create the buffer and write the first row.
		// First of the row does not have a separator.
		StringBuilder buffer = new StringBuilder(this.board[0][0].toString());
		for (int j = 1; j < this.columns; j++)
		{
			buffer.append(Board.VERTICAL_SEPARATOR);
			buffer.append(Board.safeTileToString(this.board[0][j]));
		}

		// End the row and start a new one.
		buffer.append(Board.ROW_END);

		// Do the rest of the rows
		for (int i = 1; i < this.rows; i++)
		{

			// Create the horizontal separator row.
			for (int j = 0; j < 2 * this.columns - 1; j++)
			{
				buffer.append(Board.HORIZONTAL_SEPARATOR);
			}

			// End the row and start a new one.
			buffer.append(Board.ROW_END);

			// First of the row does not have a separator.
			buffer.append(this.board[i][0].toString());
			for (int j = 1; j < this.columns; j++)
			{
				buffer.append(Board.VERTICAL_SEPARATOR);
				buffer.append(Board.safeTileToString(this.board[i][j]));
			}

			// End the row and start a new one.
			buffer.append(Board.ROW_END);
		}
		return buffer.toString();
	}

	private static String safeTileToString(Tile tile)
	{
		return tile == null ? Board.EMPTY_TILE_STRING : tile.toString();
	}

	private void clear()
	{

		// Initialize each location to have no tile.
		for (int i = 0; i < this.rows; i++)
		{
			for (int j = 0; j < this.columns; j++)
			{
				this.board[i][j] = null;
			}
		}
	}

	private static boolean inBetween(int position, int length, int start, int end)
	{

		// Check if an interval can fit inside the bounds.
		return (position > start) && (position + length < end);
	}

	private boolean willFit(MoveCommand nextMove)
	{
		boolean doesFit = false;
		switch (nextMove.getDirection())
		{
			case Down:
				doesFit = Board.inBetween(nextMove.getRow(), nextMove.getWord().length(), -1, this.rows);
				break;

			case Right:
				doesFit = Board.inBetween(nextMove.getColumn(), nextMove.getWord().length(), -1, this.columns);
				break;
		}
		return doesFit;
	}

	private static boolean matchesLetter(Tile currentTile, Character letter)
	{

		// No tile, no match.
		boolean matches = false;

		// Check for the existence of a tile.
		if (currentTile != null)
		{

			// Check if the tile's letter matches the given letter.
			matches = currentTile.equals(letter);
		}
		return matches;
	}

	private boolean overlapsExisting(MoveCommand nextMove)
	{

		// Be pessimistic.
		boolean overlaps = false;
		switch (nextMove.getDirection())
		{

			// Check if there is a letter that matches an existing tile's letter
			// and stop if it does.
			case Down:
				for (int i = 0; !overlaps && i < nextMove.getWord().length(); i++)
				{
					overlaps |= Board.matchesLetter(this.board[i + nextMove.getRow()][nextMove.getColumn()], nextMove.getWord().charAt(i));
				}
				break;

			case Right:
				for (int i = 0; !overlaps && i < nextMove.getWord().length(); i++)
				{
					overlaps |= Board.matchesLetter(this.board[nextMove.getRow()][i + nextMove.getColumn()], nextMove.getWord().charAt(i));
				}
				break;
		}
		return overlaps;
	}

	private static void assignTile(Tile currentTile, Character letter)
	{

		// Create a new tile if it doesn't exist yet.
		if (currentTile == null)
		{
			currentTile = new Tile(letter);
		}
	}

	public boolean makeFirstMove(MoveCommand nextMove)
	{

		// Check if the move is valid.
		boolean madeMove = this.willFit(nextMove);
		if (madeMove)
		{

			// Make the move by creating tiles in the correct location.
			switch (nextMove.getDirection())
			{
				case Down:
					for (int i = 0; i < nextMove.getWord().length(); i++)
					{
						Board.assignTile(this.board[i + nextMove.getRow()][nextMove.getColumn()], nextMove.getWord().charAt(i));
					}
					break;

				case Right:
					for (int i = 0; i < nextMove.getWord().length(); i++)
					{
						Board.assignTile(this.board[nextMove.getRow()][i + nextMove.getColumn()], nextMove.getWord().charAt(i));
					}
					break;
			}
		}
		return madeMove;
	}

	public boolean makeNextMove(MoveCommand nextMove)
	{

		// Check if the move is valid.
		boolean madeMove = this.willFit(nextMove) && this.overlapsExisting(nextMove);
		if (madeMove)
		{

			// Make the move by creating tiles in the correct location.
			switch (nextMove.getDirection())
			{
				case Down:
					for (int i = 0; i < nextMove.getWord().length(); i++)
					{
						Board.assignTile(this.board[i + nextMove.getRow()][nextMove.getColumn()], nextMove.getWord().charAt(i));
					}
					break;

				case Right:
					for (int i = 0; i < nextMove.getWord().length(); i++)
					{
						Board.assignTile(this.board[nextMove.getRow()][i + nextMove.getColumn()], nextMove.getWord().charAt(i));
					}
					break;
			}
		}
		return madeMove;
	}
}
