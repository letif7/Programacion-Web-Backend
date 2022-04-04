package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

@Path("servicios-puntos")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosPuntos {
	@Inject
	private BolsaPuntosDAO bolsaPuntosDAO;

	@POST
	@Path("/")
	public Response cargarPuntos(BolsaPuntos bolsaPuntos){
		bolsaPuntosDAO.agregar(bolsaPuntos);
		return Response.ok().build();
	}

	@GET
	@Path("/")
	public Response listarTodos() { return  Response.ok(bolsaPuntosDAO.listarTodos()).build(); }


}