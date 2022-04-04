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
import py.com.progweb.prueba.ejb.UsoPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.UsoPuntos;

@Path("servicios-puntos")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosPuntos {
	@Inject
	private BolsaPuntosDAO bolsaPuntosDAO;
	@Inject
	private UsoPuntosDAO usoPuntosDAO;
	
	@POST
    @Path("/")
    public Response cargarPuntos(BolsaPuntos bolsaPuntos){
		bolsaPuntosDAO.agregar(bolsaPuntos);
        return Response.ok().build();
	}

	@GET
	@Path("/")
	public Response listarTodos() { return  Response.ok(bolsaPuntosDAO.listarTodos()).build(); }

	@GET
	@Path("obtenerPuntos/{monto_operacion}")
	public Response obtenerPuntos(@PathParam("monto_operacion") Integer monto_operacion) {
		Integer puntos = bolsaPuntosDAO.obtenerPuntos(monto_operacion);

		return Response.ok("La cantidad de puntos equivalente es "+puntos).build();
	}

	@POST
	@Path("utilizar-puntos")
	public Response usarPuntos(UsoPuntos usoPuntos){
		usoPuntosDAO.agregarUso(usoPuntos);
        return Response.ok().build();
	}
}
