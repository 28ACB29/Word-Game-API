package com.arthurcbaroi.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.arthurcbaroi.wordgame.model.Board;
import com.arthurcbaroi.wordgame.model.Directions;
import com.arthurcbaroi.wordgame.model.Game;
import com.arthurcbaroi.wordgame.model.MoveCommand;

class GameTest
{
	public Game defaultGame;

	@Before
	void setUp()
	{
		defaultGame = new Game("default");
	}

	@Test
	final void testGetId()
	{
		assertEquals("default", defaultGame.toString());
	}

	@Test
	final void testGetMovesCount()
	{
		assertEquals(0, defaultGame.getMovesCount());
	}

	@Test
	final void testGetLatest()
	{
		assertEquals(new Board(), defaultGame.getLatest());
	}

	@Test
	final void testPlay()
	{
		MoveCommand goodMove = new MoveCommand("The", 0, 0, Directions.Right);
		assertNotEquals(new Board(), defaultGame.getLatest());
	}

}
