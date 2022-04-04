package py.com.progweb.prueba.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.inject.Inject;

import py.com.progweb.prueba.ejb.PuntoDAO;
import py.com.progweb.prueba.model.Punto;

@Path("punto")
@Consumes("application/json")
@Produces("application/json")
public class PuntoRest {

	@Inject
    private PuntoDAO puntoDAO;

    @GET
    @Path("/")
    public Response listarTodos() { return  Response.ok(puntoDAO.listarTodos()).build(); }

    @GET
    @Path("/idpunto/{idpunto}")
    public Response obtenerCliente(@PathParam("idpunto") Integer idpunto){
        Punto punto = puntoDAO.obtener(idpunto);
        try {
            if (punto != null) {
                return Response.ok(punto).build();
            } else {
                return Response.status(404).entity("El punto no se encuentra en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/")
    public Response crear(Punto p){
        this.puntoDAO.agregar(p);
        return Response.ok("se creo correctamente el concepto de puntos").build();
    }

    @DELETE
    @Path("eliminar/{idpunto}")
    public Response deleteClientRest(@PathParam("idpunto") Integer idpunto){
        Integer puntoEliminado= puntoDAO.eliminarPunto(idpunto);
        try {
            if (puntoEliminado != null){
                return Response.ok("Concepto de punto: "+puntoEliminado+ " ha sido eliminado").build();
            }else{
                return Response.status(404).entity("Este concepto de punto no existe en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("actualizar")
    public Response updatePuntoRest(Punto punto){
        Integer estado = puntoDAO.actualizar(punto);
        Response respuesta = Response.status(400).build();
        try{
            if (estado == 200){
                respuesta = Response.ok("El concepto de puntos ha sido actualizado").build();
            }else if (estado == 404){
                respuesta = Response.status(404).entity("El concepto de puntos no se encuentra en la Base de datos").build();
            }
        }catch (Exception ex){
            respuesta = Response.serverError().build();
        }
        return respuesta;
    }


}
