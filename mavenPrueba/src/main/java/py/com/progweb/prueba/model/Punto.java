package py.com.progweb.prueba.model;
import javax.persistence.*;

@Entity
@Table(name="punto")
public class Punto {

    @Id
    @Column(name = "idpunto")  //apunta a id_persona en la DB
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "puntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "puntoSec",sequenceName = "punto_sec",allocationSize = 0)
    private Integer idPunto;
    
    @Column(name = "descripcion_concepto", length = 50)
    @Basic(optional = false)
    private String descripcionConcepto;
    
    @Column(name = "puntos_requeridos")
    @Basic(optional = false)
    private Integer puntosRequeridos;
    
    public Punto(){ //constructor

    }

    public Integer getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Integer idPunto) {
        this.idPunto = idPunto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcion_concepto) {
        this.descripcionConcepto = descripcion_concepto;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntos_requeridos) {
        this.puntosRequeridos = puntos_requeridos;
    }

}
