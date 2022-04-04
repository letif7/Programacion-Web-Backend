package py.com.progweb.prueba.ejb;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

@Stateless
public class BolsaPuntosDAO {
	
	@PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
	
	public Integer asignarPuntos(Cliente cliente, Integer monto_operacion){
		
		BolsaPuntos bolsaPunto = new BolsaPuntos();
		bolsaPunto.setIdcliente(cliente.getIdCliente());
		bolsaPunto.setMonto_operacion(monto_operacion);
		Date fecha_asignacion = new Date();
		bolsaPunto.setFecha_asignacion_puntaje(fecha_asignacion);
		
		
		
		return 200;
	}
	
	public BolsaPuntos obtener(Integer idcliente){ //obtener uno
        BolsaPuntos bolsaPuntos= this.em.find(BolsaPuntos.class, idcliente);
        return bolsaPuntos;
    }
	
	
	public BolsaPuntosDAO() {
		
	}

}
