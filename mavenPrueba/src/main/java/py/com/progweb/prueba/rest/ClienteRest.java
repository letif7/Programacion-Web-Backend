package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/")
    public Response listarTodos() { return  Response.ok(clienteDAO.listarTodos()).build(); }

    @GET
    @Path("/id/{idcliente}")
    public Response obtenerCliente(@PathParam("idcliente") Integer idcliente){
        Cliente cliente = clienteDAO.obtener(idcliente);
        try {
            if (cliente != null) {
                return Response.ok(cliente).build();
            } else {
                return Response.status(404).entity("El cliente no se encuentra en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/")
    public Response crear(Cliente c){
        this.clienteDAO.agregar(c);
        return Response.ok().build();
    }

    @DELETE
    @Path("eliminar/{idcliente}")
    public Response deleteClientRest(@PathParam("idcliente") Integer idcliente){
        String clienteEliminado= clienteDAO.eliminarCliente(idcliente);
        try {
            if (clienteEliminado != null){
                return Response.ok("Usuario: "+clienteEliminado+ " ha sido eliminado").build();
            }else{
                return Response.status(404).entity("Este cliente no existe en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("actualizar")
    public Response updateClienteRest(Cliente cliente){
        Integer estado = clienteDAO.actualizar(cliente);
        Response respuesta = Response.status(400).build();
        try{
            if (estado == 200){
                respuesta = Response.ok("El cliente ha sido actualizado").build();
            }else if (estado == 404){
                respuesta = Response.status(404).entity("El cliente no se encuentra en la Base de datos").build();
            }
        }catch (Exception ex){
            respuesta = Response.serverError().build();
        }
        return respuesta;
    }
}
