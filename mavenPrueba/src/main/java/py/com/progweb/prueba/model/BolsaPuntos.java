package py.com.progweb.prueba.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="bolsa_puntos")
public class BolsaPuntos {
    @Id
    @Column(name = "idbolsa")  //apunta a id_persona en la DB
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "bolsaPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bolsaPuntosSec",sequenceName = "bolsa_puntos_sec",allocationSize = 0)
    private Integer idbolsa;
    
    @Column(name = "idcliente")
    @Basic(optional = false)
    private Integer idcliente;
    
    @Column(name = "fecha_asignacion_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date fecha_asignacion_puntaje;
    
    @Column(name = "fecha_caducidad_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    private Date fecha_caducidad_puntaje;
    
    @Column(name = "puntaje_asignado")
    @Basic(optional = false)
    private Integer puntaje_asignado;
    
    @Column(name = "puntaje_utilzado")
    @Basic(optional = false)
    private Integer puntaje_utilizado;
    
    @Column(name = "saldo_puntos")
    @Basic(optional = false)
    private Integer saldo_puntos;
    
    @Column(name = "monto_operacion")
    @Basic(optional = false)
    private Integer monto_operacion;
    
	public BolsaPuntos() {

	}

	public Integer getIdbolsa() {
		return idbolsa;
	}

	public void setIdbolsa(Integer idbolsa) {
		this.idbolsa = idbolsa;
	}

	public Integer getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public Date getFecha_asignacion_puntaje() {
		return fecha_asignacion_puntaje;
	}

	public void setFecha_asignacion_puntaje(Date fecha_asignacion_puntaje) {
		this.fecha_asignacion_puntaje = fecha_asignacion_puntaje;
	}

	public Date getFecha_caducidad_puntaje() {
		return fecha_caducidad_puntaje;
	}

	public void setFecha_caducidad_puntaje(Date fecha_caducidad_puntaje) {
		this.fecha_caducidad_puntaje = fecha_caducidad_puntaje;
	}

	public Integer getPuntaje_asignado() {
		return puntaje_asignado;
	}

	public void setPuntaje_asignado(Integer puntaje_asignado) {
		this.puntaje_asignado = puntaje_asignado;
	}

	public Integer getPuntaje_utilizado() {
		return puntaje_utilizado;
	}

	public void setPuntaje_utilizado(Integer puntaje_utilizado) {
		this.puntaje_utilizado = puntaje_utilizado;
	}

	public Integer getSaldo_puntos() {
		return saldo_puntos;
	}

	public void setSaldo_puntos(Integer saldo_puntos) {
		this.saldo_puntos = saldo_puntos;
	}

	public Integer getMonto_operacion() {
		return monto_operacion;
	}

	public void setMonto_operacion(Integer monto_operacion) {
		this.monto_operacion = monto_operacion;
	}

}
