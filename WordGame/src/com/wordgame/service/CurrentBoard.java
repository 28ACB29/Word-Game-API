package com.wordgame.service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordgame.model.Game;

@Path("/currentboard")
public class CurrentBoard
{
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCurrentBoard(@PathParam("id") String id)
	{
		Game game = GamesRepository.getGame(id);
		if (game == null)
		{
			return Response.status(HttpServletResponse.SC_FORBIDDEN).build();
		}
		else
		{
			return Response.status(HttpServletResponse.SC_OK).entity(game.getLatest().toString()).build();
		}
	}

}
