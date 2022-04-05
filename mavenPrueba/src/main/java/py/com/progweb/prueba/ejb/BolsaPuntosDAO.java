package py.com.progweb.prueba.ejb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.AsignacionPunto;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.VencimientoPuntos;


@Stateless
public class BolsaPuntosDAO {

	@PersistenceContext(unitName = "pruebaPU")
	private EntityManager em;


	public void agregar(BolsaPuntos bolsaPuntos) {

		Date fecha_asignacion = new Date();
		bolsaPuntos.setFecha_asignacion_puntaje(fecha_asignacion);
		VencimientoPuntos duracionVencimiento = obtenerDuracionVencimiento(fecha_asignacion).get(0);
		Date fechaExpiracion = sumarRestarDias(fecha_asignacion,duracionVencimiento.getDias_duracion_puntaje());
		bolsaPuntos.setFecha_caducidad_puntaje(fechaExpiracion);
		Integer puntos = obtenerPuntos(bolsaPuntos.getMonto_operacion());
		bolsaPuntos.setPuntaje_asignado(puntos);
		bolsaPuntos.setPuntaje_utilizado(0);
		bolsaPuntos.setSaldo_puntos(puntos);
		this.em.persist(bolsaPuntos);
	}

	private int obtenerDuracion(Date fecha) {
        Query q = this.em.createQuery("select v.dias_duracion_puntaje from VencimientoPuntos v where :fechaDate between v.fecha_inicio_validez and v.fecha_fin_validez");
        return q.setParameter("fechaDate",fecha).getFirstResult();
    }
	
	private List<VencimientoPuntos> obtenerDuracionVencimiento(Date fecha) {
        Query q = this.em.createQuery("select v from VencimientoPuntos v where :fechaDate between v.fecha_inicio_validez and v.fecha_fin_validez");
        return q.setParameter("fechaDate",fecha).getResultList();
    }

	private int obtenerMontoPunto(Integer monto ) {
		Query q = this.em.createQuery("select v.monto_punto from AsignacionPunto v where :monto_operacion between v.limite_inferior and v.limite_superior");
		return q.setParameter("monto_operacion",monto).getFirstResult();
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

	public Integer getTotalPuntosByCliente(Integer idCliente){
		Query q = em.createQuery("Select SUM(b.saldo_puntos) from BolsaPuntos b where b.idcliente= :idCliente and b.saldo_puntos>0");
		Long result = (Long)q.setParameter("idCliente", idCliente).getSingleResult();
		if (result == null){
			return 0;
		}else {
			return result.intValue();
		}
	}

	public List<BolsaPuntos> getByClienteIdSaldoNoCero (Integer id_cliente){
		Query q = em.createQuery("Select b from BolsaPuntos b where b.idcliente= :cliente and b.saldo_puntos>0 order by fecha_asignacion_puntaje asc");
		return (List<BolsaPuntos>) q.setParameter("cliente",id_cliente).getResultList();
	}

	public void usarPuntos(BolsaPuntos bolsaPuntos, Integer puntosAUsar){
		BolsaPuntos bolsa = this.em.find(BolsaPuntos.class, bolsaPuntos.getIdbolsa());
		Integer saldo = bolsa.getSaldo_puntos();
		Integer puntajeUtilizado = bolsa.getPuntaje_utilizado();
		bolsa.setPuntaje_utilizado( puntajeUtilizado + puntosAUsar);
		bolsa.setSaldo_puntos( saldo - puntosAUsar );
	}
	
	private List<AsignacionPunto> obtenerMontoPuntoAsignaccion(Integer monto ) {
		Query q = this.em.createQuery("select v from AsignacionPunto v where :mont between v.limite_inferior and v.limite_superior");
		return (List<AsignacionPunto>) q.setParameter("mont",monto).getResultList();
	}

	public Integer obtenerPuntos(Integer monto_operacion) {
		List<AsignacionPunto> asignacion = obtenerMontoPuntoAsignaccion(monto_operacion);
		Integer puntos = monto_operacion/asignacion.get(0).getMonto_punto();
		return puntos;
	}



}
