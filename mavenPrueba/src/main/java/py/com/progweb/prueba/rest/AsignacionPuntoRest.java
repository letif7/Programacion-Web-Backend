package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.AsignacionPuntoDAO;
import py.com.progweb.prueba.model.AsignacionPunto;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("asignacion_puntos")
@Consumes("application/json")
@Produces("application/json")
public class AsignacionPuntoRest {
    @Inject
    private AsignacionPuntoDAO asignacionPuntoDAO;

    @GET
    @Path("/")
    public Response listarTodos() { return  Response.ok(asignacionPuntoDAO.listarTodos()).build(); }

    @GET
    @Path("/id/{id_asignacion}")
    public Response obtenerAsignaciondePuntos(@PathParam("id_asignacion") Integer idAsignacion){
        AsignacionPunto asignacionPunto = asignacionPuntoDAO.obtener(idAsignacion);
        try {
            if (asignacionPunto != null) {
                return Response.ok(asignacionPunto).build();
            } else {
                return Response.status(404).entity("La asignacion de puntos no se encuentra en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("eliminar/{id_asignacion}")
    public Response deleteAsignaciondePuntostRest(@PathParam("id_asignacion") Integer id_asignacion){
        AsignacionPunto asignacionEliminado= asignacionPuntoDAO.obtener(id_asignacion);
        try {
            if (asignacionEliminado != null){
                return Response.ok("Asignacion: "+asignacionEliminado.getId_asignacion()+ " ha sido eliminada").build();
            }else{
                return Response.status(404).entity("Esta asignacion no existe en la Base de Datos").build();
            }
        }catch (Exception ex){
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/")
    public Response crear(AsignacionPunto c){
        this.asignacionPuntoDAO.agregar(c);
        return Response.ok().build();
    }

    @PUT
    @Path("actualizar")
    public Response updateAsignaciondePuntosRest(AsignacionPunto cliente){
        Integer estado = asignacionPuntoDAO.actualizar(cliente);
        Response respuesta = Response.status(400).build();
        try{
            if (estado == 200){
                respuesta = Response.ok("La asignacion ha sido actualizada").build();
            }else if (estado == 404){
                respuesta = Response.status(404).entity("La actualizacion no se encuentra en la Base de datos").build();
            }
        }catch (Exception ex){
            respuesta = Response.serverError().build();
        }
        return respuesta;
    }



}
