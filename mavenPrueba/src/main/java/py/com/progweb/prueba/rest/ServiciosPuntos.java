package py.com.progweb.prueba.rest;

import javax.ws.rs.Consumes;
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

	private BolsaPuntosDAO bolsaPuntosDAO;
	private ClienteDAO clienteDAO;
	
	@POST
    @Path("{idcliente}/{monto_operacion}")
    public Response cargarPuntos(@PathParam("idcliente") Integer idcliente, @PathParam("monto_operacion") Integer monto_operacion){
		Cliente cliente = clienteDAO.obtener(idcliente);
        Integer estado = bolsaPuntosDAO.asignarPuntos(cliente, monto_operacion);
        
        return Response.ok().build();
	}
}
