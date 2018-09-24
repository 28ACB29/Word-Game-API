package com.wordgame.model;

public class MoveCommand
{
	private String word;
	private int row;
	private int column;
	private Directions direction;

	private MoveCommand()
	{
		this.word = "";
		this.row = 0;
		this.column = 0;
		this.direction = Directions.Down;
	}

	public MoveCommand(String word, int row, int column, Directions direction)
	{
		this.word = word;
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public MoveCommand(String word, int row, int column, String direction)
	{
		this.word = word;
		this.row = row;
		this.column = column;
		this.direction = Directions.valueOf(direction);
	}

	public String getWord()
	{
		return this.word;
	}

	public int getRow()
	{
		return this.row;
	}

	public int getColumn()
	{
		return this.column;
	}

	public Directions getDirection()
	{
		return this.direction;
	}
}
