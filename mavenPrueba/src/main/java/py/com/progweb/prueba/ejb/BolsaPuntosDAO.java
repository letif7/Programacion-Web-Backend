package py.com.progweb.prueba.ejb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jetbrains.annotations.NotNull;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class BolsaPuntosDAO {
	
	@PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
	
	public void agregar(BolsaPuntos bolsaPuntos) {
		
		Date fecha_asignacion = new Date();
		bolsaPuntos.setFecha_asignacion_puntaje(fecha_asignacion);
		Date fechaExpiracion = sumarRestarDias(fecha_asignacion,obtenerDuracion(fecha_asignacion)+1);
		bolsaPuntos.setFecha_caducidad_puntaje(fechaExpiracion);
		//Integer puntaje = bolsaPuntos.getMonto_operacion()/obtenerMontoPunto(bolsaPuntos.getMonto_operacion());
		//bolsaPuntos.setPuntaje_asignado(puntaje);
		bolsaPuntos.setPuntaje_utilizado(0);
		//bolsaPuntos.setSaldo_puntos(bolsaPuntos.getSaldo_puntos()+puntaje);
		this.em.persist(bolsaPuntos);
	}
	
	private int obtenerDuracion(Date fecha) {
        Query q = this.em.createQuery("select v.dias_duracion_puntaje from VencimientoPuntos v where :fechaDate between v.fecha_inicio_validez and v.fecha_fin_validez");
        return q.setParameter("fechaDate",fecha).getFirstResult();
    }
	
	private int obtenerMontoPunto(Integer monto_operacion ) {
        Query q = this.em.createQuery("select v.monto_punto from AsignacionPuntos v where :monto_operacion between v.limite_inferior and v.limite_superior");
        return q.setParameter("monto_operacion",monto_operacion).getFirstResult();
    }
	
	public Date sumarRestarDias(java.util.Date fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_YEAR, dias);
        return java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime()));
    }
	
	public List<BolsaPuntos> listarTodos(){ //obtener todos los vencimientos
        Query q = this.em.createQuery("SELECT v FROM BolsaPuntos v");
        return (List<BolsaPuntos>) q.getResultList();
    }
	
	public Integer obtenerPuntos(Integer monto_operacion) {
		Integer puntos = monto_operacion/obtenerMontoPunto(monto_operacion);
		return puntos;
	} 
	


}
