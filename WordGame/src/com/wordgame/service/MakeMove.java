package com.wordgame.service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordgame.model.Game;
import com.wordgame.model.MoveCommand;

@Path("/makemove")
public class MakeMove
{
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response getNewBoard(@FormParam("id") String id, @FormParam("word") String word, @FormParam("row") int row, @FormParam("column") int column, @FormParam("direction") String direction)
	{
		Game game = GamesRepository.getGame(id);
		MoveCommand move = new MoveCommand(word, row, column, direction);
		if (game == null)
		{
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}
		else if (!game.play(move))
		{
			return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity(game.getLatest().toString()).build();
		}
		else
		{
			return Response.status(HttpServletResponse.SC_CREATED).entity(game.getLatest().toString()).build();
		}
	}

}
