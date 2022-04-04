package py.com.progweb.prueba.ejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;

@Singleton
public class ProcesoPlanificado {
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;
    
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    //se setea cuanto tiempo se quiere correr este proceso
    @Schedule(dayOfWeek = "*",hour = "*/1",persistent = false)
    public void updateBolsaPuntos(){
        Date fechaActual = new Date();
        //aca se borran los puntos una vez que expiran
        Query q=this.em.createQuery("update BolsaPuntos bp SET bp.saldo_puntos=0 where bp.fecha_caducidad_puntaje < :fechaActual");
        q.setParameter("fechaActual",fechaActual).executeUpdate();
    }
}
