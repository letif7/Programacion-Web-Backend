package py.com.progweb.prueba.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column(name = "idcliente")  //apunta a id_persona en la DB
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "clienteSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clienteSec",sequenceName = "cliente_sec",allocationSize = 0)
    private Integer idCliente;
    @Column(name = "cedula", length = 10)
    @Basic(optional = true)
    private String cedula;
    @Column(name = "nombre", length = 50)
    @Basic(optional = true)
    private String nombre;
    @Column(name = "apellido", length = 50)
    @Basic(optional = true)
    private String apellido;
    @Column(name = "tipo_documento", length = 20)
    @Basic(optional = true)
    private String tipo_documento;
    @Column(name = "nacionalidad", length = 30)
    @Basic(optional = true)
    private String nacionalidad;
    @Column(name = "email", length = 50)
    @Basic(optional = true)
    private String email;
    @Column(name = "telefono", length = 30)
    @Basic(optional = true)
    private String telefono;
    @Column(name = "fecha_nacimiento")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date fecha_nacimiento;

    public Cliente(){ //constructor

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

}
