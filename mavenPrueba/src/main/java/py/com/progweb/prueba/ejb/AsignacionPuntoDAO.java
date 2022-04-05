package py.com.progweb.prueba.ejb;

import org.jetbrains.annotations.NotNull;
import py.com.progweb.prueba.model.AsignacionPunto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;

@Stateless
public class AsignacionPuntoDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    
    public void agregar(AsignacionPunto entidad){ //agregar
        this.em.persist(entidad);
    }
    public List<AsignacionPunto> listarTodos(){ //listar todos
        Query q = this.em.createQuery("SELECT c FROM AsignacionPunto c");
        return (List<AsignacionPunto>) q.getResultList();
    }

    public AsignacionPunto obtener(Integer idAsignacionPunto){ //obtener uno
        AsignacionPunto asigancion_punto= this.em.find(AsignacionPunto.class,idAsignacionPunto);
        return asigancion_punto;
    }

    public String eliminarAsignacionPuntos(Integer idAsignacionPunto){ //eliminar un cliente
        AsignacionPunto asignacionPunto;
        asignacionPunto = this.obtener(idAsignacionPunto);
        String nombrePuntoAsignado;
        if (idAsignacionPunto != null){
            nombrePuntoAsignado = asignacionPunto.getMonto_punto();
            this.em.remove(asignacionPunto);
        }else{
            nombrePuntoAsignado = null;
        }
        return nombrePuntoAsignado;
    }

    public Integer actualizar(@NotNull AsignacionPunto puntoAsignadoAct) { //actualizar los datos de un cliente
      AsignacionPunto asignacionPuntoOld = this.obtener(puntoAsignadoAct.getIdAsignacionPuntos());
        if (asignacionPuntoOld != null) {
            if (asignacionPuntoOld.getLimite_inferior() != null) {
                    asignacionPuntoOld.setLimite_inferior(puntoAsignadoAct.getLimite_inferior());
            }
            if (asignacionPuntoOld.getLimite_inferior() != null) {
                asignacionPuntoOld.setLimite_inferior(puntoAsignadoAct.getLimite_superior());

            }
            if (asignacionPuntoOld.getLimite_superior() != null) {
                asignacionPuntoOld.setLimite_superior(puntoAsignadoAct.getLimite_superior());
            }
            if(asignacionPuntoOld.getMonto_punto() != null) {
                asignacionPuntoOld.setMonto_punto(puntoAsignadoAct.getMonto_punto());
            }
            return 200;
        } else {
            return 404;
        }

    }


}
