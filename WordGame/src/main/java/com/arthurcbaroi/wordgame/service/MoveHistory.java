package com.arthurcbaroi.wordgame.service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.arthurcbaroi.wordgame.model.Game;

@Path("/movehistory")
public class MoveHistory
{
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMoveHistory(@PathParam("id") String id)
	{
		Game game = GamesRepository.getGame(id);
		if (game == null)
		{
			return Response.status(HttpServletResponse.SC_BAD_REQUEST).entity("No game exists with id " + id).build();
		}
		else
		{
			return Response.status(HttpServletResponse.SC_OK).entity(game.toString()).build();
		}
	}

}
