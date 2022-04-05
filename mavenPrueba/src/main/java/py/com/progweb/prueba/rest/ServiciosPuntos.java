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
import py.com.progweb.prueba.model.BolsaPuntos;

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
        return Response.ok("El monto fue agregado al cliente").build();
	}

	@GET
	@Path("/")
	public Response listarTodos() { return  Response.ok(bolsaPuntosDAO.listarTodos()).build(); }

	@GET
	@Path("/obtenerPuntos/{monto_operacion}")
	public Response obtenerPuntos(@PathParam("monto_operacion") Integer monto_operacion) {
		Integer puntos = bolsaPuntosDAO.obtenerPuntos(monto_operacion);

		return Response.ok("La cantidad de puntos equivalente es "+puntos).build();
	}

	
	
}
