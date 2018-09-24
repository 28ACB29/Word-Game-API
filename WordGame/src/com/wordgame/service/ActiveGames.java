package com.wordgame.service;

import java.util.Arrays;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordgame.model.Game;

@Path("/activegames")
public class ActiveGames
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getActiveGames()
	{
		Set<Game> games = GamesRepository.getAllGames();
		String[] ids = (String[]) games.stream().map(game -> game.getId()).toArray();
		return Arrays.toString(ids);
	}

}
