package py.com.progweb.prueba.rest;


import py.com.progweb.prueba.ejb.VencimientoDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("vencimiento_puntos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoRest {
    @Inject
    private VencimientoDAO vencimientoDAO;

    @GET
    @Path("/")
    public Response listarTodos() { return  Response.ok(vencimientoDAO.listarTodos()).build(); }

    @GET
    @Path("/id/{id_vencimiento}")
    public Response obtenerVencimiento(@PathParam("id_vencimiento") Integer id_vencimiento){
        VencimientoPuntos vencimientoPuntos = vencimientoDAO.obtener(id_vencimiento);
        try {
            if (vencimientoPuntos != null) {
                return Response.ok(vencimientoPuntos).build();
            } else {
                return Response.status(404).entity("El objeto no se encuentra en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/")
    public Response crear(VencimientoPuntos v){
        boolean booleano = vencimientoDAO.agregar(v);
        if (booleano){
            return Response.ok().build();
        } else {
            return Response.status(400).entity("No se puede crear el objeto. Verifique la validez de los datos.").build();
        }
    }

    @DELETE
    @Path("eliminar/{id_vencimiento}")
    public Response deleteVencimientoRest(@PathParam("id_vencimiento") Integer id_vencimiento){
        Integer vencimientoEliminado= vencimientoDAO.eliminarVencimiento(id_vencimiento);
        try {
            if (vencimientoEliminado == 200){
                return Response.ok("Eliminado con éxito").build();
            }else{
                return Response.status(404).entity("Este objeto no existe en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("actualizar")
    public Response updateVencimientoRest(VencimientoPuntos vencimientoPuntos){
        Integer estado = vencimientoDAO.actualizar(vencimientoPuntos);
        Response respuesta = Response.status(400).build();
        try{
            if (estado == 200){
                respuesta = Response.ok("Ha sido actualizado con éxito").build();
            }else if (estado == 404){
                respuesta = Response.status(404).entity("El objeto no se encuentra en la Base de datos").build();
            }
        }catch (Exception ex){
            respuesta = Response.serverError().build();
        }
        return respuesta;
    }
}
