package com.wordgame.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordgame.model.Board;
import com.wordgame.model.Directions;
import com.wordgame.model.MoveCommand;

class BoardTest
{
	private static final String testBoardString = " | | \r\n" + "-----\r\n" + " | | \r\n";

	public Board defaultBoard;

	public Board testBoard;

	@BeforeEach
	void setUp()
	{
		defaultBoard = new Board();
		testBoard = new Board(2, 3);
	}

	@Test
	final void testGetRows()
	{
		assertEquals(15, defaultBoard.getRows());
		assertEquals(2, testBoard.getRows());
	}

	@Test
	final void testGetColumns()
	{
		assertEquals(15, defaultBoard.getColumns());
		assertEquals(3, testBoard.getColumns());
	}

	@Test
	final void testGet()
	{
		for (int i = 0; i < defaultBoard.getRows(); i++)
		{
			for (int j = 0; j < defaultBoard.getColumns(); j++)
			{
				assertNull(defaultBoard.get(i, j));
			}
		}
		for (int i = 0; i < testBoard.getRows(); i++)
		{
			for (int j = 0; j < testBoard.getColumns(); j++)
			{
				assertNull(testBoard.get(i, j));
			}
		}
	}

	@Test
	final void testToString()
	{
		assertEquals(BoardTest.testBoardString, defaultBoard.toString());
	}

	@Test
	final void testMakeFirstMove()
	{
		MoveCommand badMove = new MoveCommand("The", 0, 0, Directions.Down);
		Board badBoard = new Board(testBoard);
		assertFalse(badBoard.makeFirstMove(badMove));
		assertEquals(testBoard, badBoard);

		MoveCommand goodMove = new MoveCommand("The", 0, 0, Directions.Right);
		Board goodBoard = new Board(testBoard);
		assertTrue(goodBoard.makeFirstMove(goodMove));
		assertNotEquals(testBoard, goodBoard);
	}

	@Test
	final void testMakeNextMove()
	{
		Board playedBoard = new Board(testBoard);
		MoveCommand firstMove = new MoveCommand("And", 0, 0, Directions.Right);
		MoveCommand secondMove = new MoveCommand("An", 0, 0, Directions.Down);
		playedBoard.makeFirstMove(firstMove);
		Board firstBoard = new Board(playedBoard);
		assertTrue(playedBoard.makeNextMove(secondMove));
		assertNotEquals(firstBoard, playedBoard);
	}

}
