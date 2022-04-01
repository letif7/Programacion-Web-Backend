package py.com.progweb.prueba.model;

import javax.persistence.*;

//mapear la tabla persona de la DB
@Entity
@Table(name="persona")  //como se llama en la DB

public class Persona {

    @Id
    @Column(name = "id_persona")  //apunta a id_persona en la DB
    @Basic(optional = false)    //el atributo no es null
    @GeneratedValue(generator = "personaSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "personaSec",sequenceName = "persona_sec",allocationSize = 0)
    private Integer idPersona;
    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    private String nombre;
    @Column(name = "apellido", length = 50)
    private String apellido;
    public Persona(){

    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
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

}
