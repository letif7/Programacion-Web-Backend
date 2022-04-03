package py.com.progweb.prueba.ejb;

import org.jetbrains.annotations.NotNull;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class VencimientoDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public boolean agregar(@NotNull VencimientoPuntos vencimientoPuntos){
        if (validar(VencimientoPuntos.sumarRestarDias(vencimientoPuntos.getFecha_inicio_validez(),1))&& validar(VencimientoPuntos.sumarRestarDias(vencimientoPuntos.getFecha_fin_validez(),1))){
            vencimientoPuntos.setFecha_inicio_validez(VencimientoPuntos.sumarRestarDias(vencimientoPuntos.getFecha_inicio_validez(),1));
            vencimientoPuntos.setFecha_fin_validez(VencimientoPuntos.sumarRestarDias(vencimientoPuntos.getFecha_fin_validez(),1));
            this.em.persist(vencimientoPuntos);
            return true;
        }
        return false;
    }

    private boolean validar(Date fecha) {
        Query q = this.em.createQuery("select v.dias_duracion_puntaje from VencimientoPuntos v where :fechaDate between v.fecha_inicio_validez and v.fecha_fin_validez");
        return q.setParameter("fechaDate",fecha).getResultList().isEmpty();
    }

    public List<VencimientoPuntos> listarTodos(){ //obtener todos los vencimientos
        Query q = this.em.createQuery("SELECT v FROM VencimientoPuntos v");
        return (List<VencimientoPuntos>) q.getResultList();
    }

    public VencimientoPuntos obtener(Integer idvencimiento){ //obtener uno
        return this.em.find(VencimientoPuntos.class, idvencimiento);
    }

    public Integer eliminarVencimiento(Integer idvencimiento){ //eliminar vencimiento
        VencimientoPuntos vencimientoPuntos= this.obtener(idvencimiento);
        if (vencimientoPuntos != null){
            this.em.remove(vencimientoPuntos);
            return 200;
        }else{
            return 404;
        }
    }

    public Integer actualizar(@NotNull VencimientoPuntos vencimientoAct){ //actualizar los datos
        VencimientoPuntos vencimientoOld = this.obtener(vencimientoAct.getIdVencimiento());
        //hacemos un if para verificar que el objeto exista
        if (vencimientoOld != null){
            if (vencimientoAct.getFecha_inicio_validez()!=null){
                vencimientoOld.setFecha_inicio_validez((vencimientoAct.getFecha_inicio_validez()));
            }
            if (vencimientoAct.getFecha_fin_validez()!=null){
                vencimientoOld.setFecha_fin_validez((vencimientoAct.getFecha_fin_validez()));
            }
            if (vencimientoAct.getDias_duracion_puntaje()!=null){
                vencimientoOld.setDias_duracion_puntaje((vencimientoAct.getDias_duracion_puntaje()));
            }
            return 200; //SUCCESS
        }else{
            return 404; //NOT FOUND
        }
    }
}
