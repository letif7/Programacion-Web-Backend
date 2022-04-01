package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Persona;

//esta clase se encarga de realizar la logica de negocios con la entidad persona para almacenar y obtener datos por ej la lista de personas
@Stateless
public class PersonaDAO {
    //objeto que nos permite manipular nuestras entidades
    //y realiza el mapeo correspondiente con la DB
    @PersistenceContext(unitName = "pruebaPU")  //para saber con que DB va a hacer su trabajo
    private EntityManager em;
    //agrega en la DB
    public void agregar(Persona entidad){
    	//this.em.getTransaction().begin();
        this.em.persist(entidad);
        //this.em.getTransaction().commit();
    }
    public List<Persona> lista(){
        Query q= this.em.createQuery("SELECT p FROM Persona p");
        return (List<Persona>)q.getResultList();
    }

}
