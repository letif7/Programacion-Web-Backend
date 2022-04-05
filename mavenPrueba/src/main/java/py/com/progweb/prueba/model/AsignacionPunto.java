package py.com.progweb.prueba.model;

import javax.persistence.*;
@Entity
@Table(name="asignacion_puntos")
public class AsignacionPunto {
    @Id
    @Column(name = "id_asignacion")
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "asignacion_puntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "asignacion_puntosSec",sequenceName = "asignacion_puntos_sec",allocationSize = 0)
    private Integer id_asignacion;
    @Column(name = "limite_inferior", length = 50)
    @Basic(optional = true)
    private String limite_inferior;
    @Column(name = "limite_superior", length = 50)
    @Basic(optional = true)
    private String limite_superior;
    @Column(name = "monto_punto", length = 50)
    @Basic(optional = true)
    private String monto_punto;

    public AsignacionPunto () {

    }

    public Integer getIdAsignacionPuntos() {
        return id_asignacion;
    }

    public void setIdAsignacionPuntos(Integer idAsignacionPuntos) {
        this.id_asignacion = idAsignacionPuntos;
    }

    public String getLimite_inferior() {
        return limite_inferior;
    }

    public void setLimite_inferior(String limite_inferior) {
        this.limite_inferior = limite_inferior;
    }

    public String getLimite_superior() {
        return limite_superior;
    }

    public void setLimite_superior(String limite_superior) {
        this.limite_superior = limite_superior;
    }

    public String getMonto_punto() {
        return monto_punto;
    }

    public void setMonto_punto(String monto_punto) {
        this.monto_punto = monto_punto;
    }
}
