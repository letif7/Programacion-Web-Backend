package py.com.progweb.prueba.ejb;

import org.jetbrains.annotations.NotNull;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar(Cliente entidad){ //agregar
        this.em.persist(entidad);
    }

    public List<Cliente> listarTodos(){ //listar todos
        Query q = this.em.createQuery("SELECT c FROM Cliente c");
        return (List<Cliente>) q.getResultList();
    }

    public Cliente obtener(Integer idcliente){ //obtener uno
        Cliente cliente= this.em.find(Cliente.class, idcliente);
        return cliente;
    }

    public String eliminarCliente(Integer idcliente){ //eliminar un cliente
        Cliente cliente= this.obtener(idcliente);
        String nombreCliente;
        if (cliente != null){
            nombreCliente = cliente.getNombre()+" "+cliente.getApellido();
            this.em.remove(cliente);
        }else{
            nombreCliente = null;
        }
        return nombreCliente;
    }

    public Integer actualizar(@NotNull Cliente clienteAct){ //actualizar los datos de un cliente
        Cliente clienteOld = this.obtener(clienteAct.getIdCliente());
        //hacemos un if para verificar que el cliente exista
        if (clienteOld != null){
            if (clienteAct.getNombre()!=null){
                clienteOld.setNombre(clienteAct.getNombre());
            }
            if (clienteAct.getApellido()!=null){
                clienteOld.setApellido(clienteAct.getApellido());
            }
            if (clienteAct.getNacionalidad()!=null){
                clienteOld.setNacionalidad((clienteAct.getNacionalidad()));
            }
            if (clienteAct.getEmail()!=null){
                clienteOld.setEmail((clienteAct.getEmail()));
            }
            if (clienteAct.getCedula()!=null){
                clienteOld.setCedula((clienteAct.getCedula()));
            }
            if (clienteAct.getTelefono()!=null){
                clienteOld.setTelefono((clienteAct.getTelefono()));
            }
            if (clienteAct.getTipo_documento()!=null){
                clienteOld.setTipo_documento((clienteAct.getTipo_documento()));
            }
            if (clienteAct.getFecha_nacimiento()!=null){
                clienteOld.setFecha_nacimiento((clienteAct.getFecha_nacimiento()));
            }
            return 200; //SUCCESS
        }else{
            return 404; //NOT FOUND
        }
    }
}
