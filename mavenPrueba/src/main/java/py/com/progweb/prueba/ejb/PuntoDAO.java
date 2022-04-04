package py.com.progweb.prueba.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jetbrains.annotations.NotNull;

import py.com.progweb.prueba.model.Punto;

import java.util.List;

@Stateless
public class PuntoDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar(Punto entidad){ //agregar
        this.em.persist(entidad);
    }

    public List<Punto> listarTodos(){ //listar todos
        Query q = this.em.createQuery("SELECT p FROM Punto p");
        return (List<Punto>) q.getResultList();
    }

    public Punto obtener(Integer idpunto){ //obtener uno
        Punto punto= this.em.find(Punto.class, idpunto);
        return punto;
    }

    public Integer eliminarPunto(Integer idpunto){ //eliminar un concepto de punto
        Punto punto= this.obtener(idpunto);
        Integer idPunto = null;
        if (punto != null){
            idPunto = punto.getIdPunto();
            this.em.remove(punto);
        }
        return idPunto;
    }

    public Integer actualizar(@NotNull Punto puntoActualizado){ //actualizar los datos de un concepto de puntos
        Punto puntoV = this.obtener(puntoActualizado.getIdPunto());
        //hacemos un if para verificar que el cliente exista
        if (puntoV != null){
        	if(puntoActualizado.getDescripcionConcepto() != null){
        		puntoV.setDescripcionConcepto(puntoActualizado.getDescripcionConcepto());
        	}
        	if(puntoActualizado.getPuntosRequeridos() != null){
        		puntoV.setPuntosRequeridos(puntoActualizado.getPuntosRequeridos());
        	}
            return 200; //SUCCESS
        }else{
            return 404; //NOT FOUND
        }
    }
}
