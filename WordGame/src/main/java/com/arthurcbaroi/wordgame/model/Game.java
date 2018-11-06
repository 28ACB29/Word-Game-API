package com.arthurcbaroi.wordgame.model;

import java.util.ArrayList;
import java.util.List;

public class Game
{
	private String id;

	private List<Board> moves;

	private boolean playedFirstMove;

	private Game()
	{
		this.id = "";
		this.moves = new ArrayList<Board>();
		this.moves.add(new Board());
		this.playedFirstMove = false;
	}

	public Game(String id)
	{
		this.id = id;
		this.moves = new ArrayList<Board>();
		this.moves.add(new Board());
		this.playedFirstMove = false;
	}

	public Game(String id, int rows, int columns)
	{
		this.id = id;
		this.moves = new ArrayList<Board>();
		this.moves.add(new Board(rows, columns));
		this.playedFirstMove = false;
	}

	public String getId()
	{
		return this.id;
	}

	public int getMovesCount()
	{
		return this.moves.size() - 1;
	}

	public Board getLatest()
	{
		return this.moves.get(this.moves.size() - 1);
	}

	public boolean play(MoveCommand nextMove)
	{
		Board currentBoard = new Board(this.getLatest());
		boolean madeMove = false;

		// Play differently based on whether it is the first move.
		if (this.playedFirstMove)
		{
			madeMove = currentBoard.makeFirstMove(nextMove);

			// If a move was made, we have played the first move.
			this.playedFirstMove = madeMove;
		}
		else
		{
			madeMove = currentBoard.makeNextMove(nextMove);
		}

		// If a move was made, add the new board as the latest move.
		if (madeMove)
		{
			this.moves.add(currentBoard);
		}
		return madeMove;
	}
}
