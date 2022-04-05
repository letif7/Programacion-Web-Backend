package py.com.progweb.prueba.ejb;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.UsoPuntos;
@Stateless
public class UsoPuntosDAO {

	@PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
	
	
	public void agregarUso(UsoPuntos usoPuntos) {
		
		Integer puntos_concepto = obtenerPuntosConcepto(usoPuntos.getConcepto_uso());//cambiar cuando flor envie su parte
		Integer saldo_puntos = obtenerSaldoPuntos(usoPuntos.getIdcliente());
		if(saldo_puntos>=puntos_concepto) {
			usoPuntos.setPuntaje_utilizado(puntos_concepto);
			Date fecha_uso = new Date();
			usoPuntos.setFecha_uso(fecha_uso);
			
			//actualizar bolsapuntos
			BolsaPuntos bolsaPuntosCliente = this.em.find(BolsaPuntos.class, usoPuntos.getIdcliente());
			this.actualizarBolsaPuntos(bolsaPuntosCliente, usoPuntos);
			this.em.persist(usoPuntos);
		}
	}
	
	public Integer obtenerPuntosConcepto(Integer id_punto) { //devuelve los puntos de ese concepto
		Query q = this.em.createQuery("select v.idpunto from Punto v where idpunto=id_punto;");
        return q.getFirstResult();
	}
	
	public Integer obtenerSaldoPuntos(Integer id_cliente) { //devuelve lel saldo de puntos de ese cliente
		Query q = this.em.createQuery("select v.saldo_puntos from BolsaPuntos v where idcliente=id_cliente;");
        return q.getFirstResult();
	}
	
	
	public Integer actualizarBolsaPuntos(BolsaPuntos bolsaPuntosCliente, UsoPuntos usoPuntos){ //actualizar los datos
			
        if(bolsaPuntosCliente.getIdcliente()!=null) {
        	bolsaPuntosCliente.setPuntaje_utilizado(usoPuntos.getPuntaje_utilizado());
        	Integer saldo_puntos = bolsaPuntosCliente.getSaldo_puntos()-usoPuntos.getPuntaje_utilizado();
        	bolsaPuntosCliente.setSaldo_puntos(saldo_puntos);
        	
            return 200; //SUCCESS
        }else{
            return 404; //NOT FOUND
        }
    }

}
