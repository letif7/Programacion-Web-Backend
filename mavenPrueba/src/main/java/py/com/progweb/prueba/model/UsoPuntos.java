package py.com.progweb.prueba.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="uso_puntos")
public class UsoPuntos {

	    @Id
	    @Column(name = "idp_uso")  //apunta a id_persona en la DB
	    @Basic(optional = false)    //el atributo no es null
	    @GeneratedValue(generator = "usoPuntosSec", strategy = GenerationType.SEQUENCE)
	    @SequenceGenerator(name = "usoPuntosSec",sequenceName = "uso_puntos_sec",allocationSize = 0)
	    private Integer idp_uso;
	    
	    @Column(name = "idcliente")
	    @Basic(optional = false)
	    private Integer idcliente;
	    
	    @Column(name = "puntaje_utilizado")
	    @Basic(optional = true)
	    private Integer puntaje_utilizado;
	    
	    @Column(name = "concepto_uso")
	    @Basic(optional = false)
	    private Integer concepto_uso;
	    
	    
	    @Column(name = "fecha_uso")
	    @Basic(optional = true)
	    @Temporal(TemporalType.DATE)
	    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	    private Date fecha_uso;



	
	public Integer getIdp_uso() {
			return idp_uso;
		}




		public void setIdp_uso(Integer idp_uso) {
			this.idp_uso = idp_uso;
		}




		public Integer getIdcliente() {
			return idcliente;
		}




		public void setIdcliente(Integer idcliente) {
			this.idcliente = idcliente;
		}




		public Integer getPuntaje_utilizado() {
			return puntaje_utilizado;
		}




		public void setPuntaje_utilizado(Integer puntaje_utilizado) {
			this.puntaje_utilizado = puntaje_utilizado;
		}




		public Integer getConcepto_uso() {
			return concepto_uso;
		}




		public void setConcepto_uso(Integer id_concepto_uso) {
			this.concepto_uso = id_concepto_uso;
		}




		public Date getFecha_uso() {
			return fecha_uso;
		}




		public void setFecha_uso(Date fecha_uso) {
			this.fecha_uso = fecha_uso;
		}




	public UsoPuntos() {

	}

}
