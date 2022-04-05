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
    private Integer limite_inferior;
    @Column(name = "limite_superior", length = 50)
    @Basic(optional = true)
    private Integer limite_superior;
    @Column(name = "monto_punto", length = 50)
    @Basic(optional = true)
    private Integer monto_punto;

    public AsignacionPunto () {

    }

	public Integer getId_asignacion() {
		return id_asignacion;
	}

	public void setId_asignacion(Integer id_asignacion) {
		this.id_asignacion = id_asignacion;
	}

	public Integer getLimite_inferior() {
		return limite_inferior;
	}

	public void setLimite_inferior(Integer limite_inferior) {
		this.limite_inferior = limite_inferior;
	}

	public Integer getLimite_superior() {
		return limite_superior;
	}

	public void setLimite_superior(Integer limite_superior) {
		this.limite_superior = limite_superior;
	}

	public Integer getMonto_punto() {
		return monto_punto;
	}

	public void setMonto_punto(Integer monto_punto) {
		this.monto_punto = monto_punto;
	}

}
