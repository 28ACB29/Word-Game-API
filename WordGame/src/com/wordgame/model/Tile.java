/**
 * 
 */
package com.wordgame.model;

/**
 * @author arthu
 *
 */
public class Tile
{
	private Character letter;

	private Tile()
	{
		this.letter = null;
	}

	public Tile(Character letter)
	{
		this.letter = letter;
	}

	public Tile(Tile oldTile)
	{
		this.letter = oldTile.letter;
	}

	public Character getLetter()
	{
		return this.letter;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean areEqual = false;
		if (obj instanceof Character)
		{
			Character letter = (Character) obj;
			areEqual = this.letter.equals(letter);
		}
		else if (obj instanceof Tile)
		{
			Tile tile = (Tile) obj;
			areEqual = this.letter.equals(tile.letter);
		}
		return areEqual;
	}

	@Override
	public String toString()
	{
		return this.letter.toString();
	}
}
