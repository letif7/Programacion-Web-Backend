package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PersonaDAO;
import py.com.progweb.prueba.model.Persona;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

//api para exponer la entidad
@Path("persona")
@Consumes("application/json")
@Produces("application/json")
public class PersonaRest {
    @Inject //el entorno se encarga de crear la referencia y demas
    private PersonaDAO personaDAO;

    @GET
    @Path("/") //su path es persona o /
    public Response listar(){
        return Response.ok(personaDAO.lista()).build();
    }

    @POST
    @Path("/")
    public Response crear(Persona p){
        this.personaDAO.agregar(p);
        return Response.ok().build();
    }
}
