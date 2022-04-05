package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import py.com.progweb.prueba.model.BolsaPuntos;
@Stateless
public class UsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

//    @Inject
//    private Event<MailEvent> eventProducer;

    public void agregarCabecera(UsoPuntosCabecera usoPuntosCabecera){
        em.persist(usoPuntosCabecera);
    }

    public void agregarDetalle(UsoPuntosDetalle usoPuntosDetalle){
        em.persist(usoPuntosDetalle);
    }

    public List<UsoPuntosCabecera> obtenerTodos(){
        Query q = em.createQuery("select u from UsoPuntosCabecera u");
        return (List<UsoPuntosCabecera>)q.getResultList();
    }

    public List<UsoPuntosCabecera> getByConcepto(Integer idConcepto){
        Punto concepto = this.em.find(Punto.class, idConcepto);
        if(concepto == null){
            return null;
        }
        Query q = em.createQuery("select u from UsoPuntosCabecera u where u.concepto_uso = :concepto");
        return (List<UsoPuntosCabecera>)q.setParameter("concepto",concepto).getResultList();
    }

    public List<UsoPuntosCabecera> getByFecha(String fechaStr){
        java.util.Date fecha = java.sql.Date.valueOf(fechaStr);
        Query q = em.createQuery("select u from UsoPuntosCabecera u where u.fecha_uso = :fecha");
        return (List<UsoPuntosCabecera>) q.setParameter("fecha",fecha).getResultList();
    }

    public List<UsoPuntosCabecera> getByCliente(Integer idCliente){
        Cliente cliente = this.em.find(Cliente.class, idCliente);
        if (cliente == null){
            return null;
        }
        Query q = em.createQuery("select u from UsoPuntosCabecera u where u.cliente = :cliente");
        return (List<UsoPuntosCabecera>) q.setParameter("cliente",cliente).getResultList();
    }

    public String utilizarPuntos(UsoPuntosCabecera usoPuntosCabecera){
        Integer idCliente = usoPuntosCabecera.getCliente().getIdCliente();
        Integer idConceptoUso = usoPuntosCabecera.getConcepto_uso().getIdPunto();
        Cliente cliente = this.em.find(Cliente.class, idCliente);
        if (cliente == null){
            return "El cliente no existe";
        }
        Punto conceptoPuntos = this.em.find(Punto.class, idConceptoUso);
        if (conceptoPuntos == null){
            return "Concepto Puntos No existe";
        }
        Integer puntosRequeridos = conceptoPuntos.getPuntosRequeridos();
        UsoPuntosCabecera nuevaCabecera = new UsoPuntosCabecera();
        nuevaCabecera.setCliente(cliente);
        nuevaCabecera.setPuntaje_utilizado(puntosRequeridos);
        nuevaCabecera.setConcepto_uso(conceptoPuntos);
        nuevaCabecera.setFecha_uso(new Date());

        if (bolsaPuntosDAO.getTotalPuntosByCliente(idCliente) >= puntosRequeridos){
            this.agregarCabecera(nuevaCabecera);
            List<BolsaPuntos> bolsaPuntosList = bolsaPuntosDAO.getByClienteIdSaldoNoCero(idCliente);
            for (BolsaPuntos bolsa: bolsaPuntosList){
                UsoPuntosDetalle nuevoDetalle = new UsoPuntosDetalle();
                if (puntosRequeridos > bolsa.getSaldo_puntos()){
                    nuevoDetalle.setPuntajeUtilizado(bolsa.getSaldo_puntos());
                    bolsaPuntosDAO.usarPuntos(bolsa, bolsa.getSaldo_puntos());
                }else{
                    nuevoDetalle.setPuntajeUtilizado( puntosRequeridos );
                    bolsaPuntosDAO.usarPuntos(bolsa, puntosRequeridos);
                    puntosRequeridos = 0;
                }
                nuevoDetalle.setIdBolsa(bolsa);
                nuevoDetalle.setCabecera(nuevaCabecera);
                this.agregarDetalle(nuevoDetalle);
                if (puntosRequeridos == 0){
                    break;
                }
            }
        }else {
            return "El cliente no tiene los puntos requeridos para canjear este vale";
        }
        return "";
    }

}
