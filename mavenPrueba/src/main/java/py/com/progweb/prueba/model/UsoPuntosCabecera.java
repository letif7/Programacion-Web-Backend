package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "uso_puntos")
public class UsoPuntosCabecera {
    @Id
    @Column(name = "id_uso")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="usoPuntosSec", sequenceName = "uso_puntos_sec", allocationSize = 0)
    private Integer idUso;

    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional=false)
    private Cliente cliente;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = true)
    private Integer puntaje_utilizado;

    @Basic(optional = false)
    @Column(name = "fecha_uso")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_uso;

    @JoinColumn(name = "concepto_uso", referencedColumnName = "idpunto")
    @ManyToOne(optional=false)
    private Punto concepto_uso;

    public UsoPuntosCabecera() {

    }

    public Integer getIdUso() {
        return idUso;
    }

    public void setIdUso(Integer idUso) {
        this.idUso = idUso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntaje_utilizado() {
        return puntaje_utilizado;
    }

    public void setPuntaje_utilizado(Integer puntaje_utilizado) {
        this.puntaje_utilizado = puntaje_utilizado;
    }

    public Date getFecha_uso() {
        return fecha_uso;
    }

    public void setFecha_uso(Date fecha_uso) {
        this.fecha_uso = fecha_uso;
    }

    public Punto getConcepto_uso() {
        return concepto_uso;
    }

    public void setConcepto_uso(Punto concepto_uso) {
        this.concepto_uso = concepto_uso;
    }
}
