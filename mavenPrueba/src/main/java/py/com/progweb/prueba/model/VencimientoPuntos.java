package py.com.progweb.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "vencimiento_puntos")
public class VencimientoPuntos {
    @Id
    @Column(name = "id_vencimiento")  //apunta a id_persona en la DB
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "vencimientoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vencimientoSec",sequenceName = "vencimiento_sec",allocationSize = 0)
    private Integer idVencimiento;
    @Column(name = "fecha_inicio_validez")
    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date fecha_inicio_validez;
    @Column(name = "fecha_fin_validez")
    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date fecha_fin_validez;
    @Column(name = "dias_duracion_puntaje")
    @Basic(optional = true)
    private Integer dias_duracion_puntaje;

    public VencimientoPuntos(){

    }

    public Integer getIdVencimiento() {
        return idVencimiento;
    }

    public void setIdVencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }

    public Date getFecha_inicio_validez() {
        return fecha_inicio_validez;
    }

    public void setFecha_inicio_validez(Date fecha_inicio_validez) {
        this.fecha_inicio_validez = fecha_inicio_validez;
    }

    public Date getFecha_fin_validez() {
        return fecha_fin_validez;
    }

    public void setFecha_fin_validez(Date fecha_fin_validez) {
        this.fecha_fin_validez = fecha_fin_validez;
    }

    public Integer getDias_duracion_puntaje() {
        return dias_duracion_puntaje;
    }

    public void setDias_duracion_puntaje(Integer dias_duracion_puntaje) {
        this.dias_duracion_puntaje = dias_duracion_puntaje;
    }

    //funciones extra para administrar fechas
    public static Date sumarRestarDias(java.util.Date fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_YEAR, dias);
        return java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime()));
    }
}
