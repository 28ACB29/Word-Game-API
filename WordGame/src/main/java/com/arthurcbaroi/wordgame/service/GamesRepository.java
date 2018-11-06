package com.arthurcbaroi.wordgame.service;

import java.util.HashSet;
import java.util.Set;

import com.arthurcbaroi.wordgame.model.Game;

public class GamesRepository
{

	public static Game getGame(String id)
	{
		Game game = new Game(id);
		return game;
	}

	public static Set<Game> getAllGames()
	{
		Set<Game> games = new HashSet<Game>();
		games.add(new Game("game1"));
		games.add(new Game("game2"));
		games.add(new Game("game3"));
		return games;
	}
}
