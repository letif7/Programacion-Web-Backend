package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.UsoPuntosDAO;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("uso_puntos")
@Consumes("application/json")
@Produces("application/json")
public class UsoPuntosRest {
    @Inject
    private UsoPuntosDAO usoPuntosDAO;

    @POST
    @Path("/")
    public Response utilizarPuntos(List<UsoPuntosCabecera> listUsoPuntos){
        try{
            for (UsoPuntosCabecera usoPuntosCabecera : listUsoPuntos){
                String respuesta = this.usoPuntosDAO.utilizarPuntos(usoPuntosCabecera);
                if(!respuesta.isEmpty()){
                    return Response.status(400).entity(respuesta).build();
                }
            }
        return Response.ok().build();
        }catch (Exception ex) {
            System.out.println(ex);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/")
    public Response obtenerTodos(){
        return Response.ok(usoPuntosDAO.obtenerTodos()).build();
    }
}
