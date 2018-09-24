package com.wordgame.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordgame.model.Board;
import com.wordgame.model.Directions;
import com.wordgame.model.Game;
import com.wordgame.model.MoveCommand;

class GameTest
{
	public Game defaultGame;

	@BeforeEach
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
