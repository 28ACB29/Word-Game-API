package com.wordgame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wordgame.model.Tile;

class TileTest
{
	public Tile testTile;

	@BeforeEach
	void setUp()
	{
		testTile = new Tile('A');
	}

	@Test
	final void testGetLetter()
	{
		assertEquals(new Character('A'), testTile.getLetter());
	}

	@Test
	final void testEqualsObject()
	{
		assertTrue(testTile.equals(testTile));
		assertTrue(testTile.equals(testTile.getLetter()));
	}

	@Test
	final void testToString()
	{
		assertEquals("A", testTile.toString());
	}

}
