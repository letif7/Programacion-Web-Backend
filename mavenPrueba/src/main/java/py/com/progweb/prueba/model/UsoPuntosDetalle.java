package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "detalle")
public class UsoPuntosDetalle {
    @Id
    @Column(name = "id_detalle")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoDetallesSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="usoDetallesSec", sequenceName = "uso_detalles_sec", allocationSize = 0)
    private Integer idDetalle;

    @Column(name="puntaje_utilizado")
    @Basic(optional = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private  Integer puntajeUtilizado;

    @ManyToOne()
    @JoinColumn(name = "id_cabecera", referencedColumnName = "id_uso")
    @Basic(optional = false)
    private UsoPuntosCabecera cabecera;

    @ManyToOne()
    @JoinColumn(name = "id_bolsa", referencedColumnName = "idbolsa")
    @Basic(optional = false)
    private BolsaPuntos idBolsa;

    public UsoPuntosDetalle(){

    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public UsoPuntosCabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(UsoPuntosCabecera cabecera) {
        this.cabecera = cabecera;
    }

    public BolsaPuntos getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(BolsaPuntos idBolsa) {
        this.idBolsa = idBolsa;
    }
}
