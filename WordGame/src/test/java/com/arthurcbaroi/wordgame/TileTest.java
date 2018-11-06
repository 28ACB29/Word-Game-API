package com.arthurcbaroi.wordgame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.arthurcbaroi.wordgame.model.Tile;

class TileTest
{
	public Tile testTile;

	@Before
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
