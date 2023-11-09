
package tecnica.pruebatecnica.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tecnica.pruebatecnica.logica.Empleado;
import tecnica.pruebatecnica.persistencia.exceptions.NonexistentEntityException;


public class ControladoraPersistencia {
    
    EmpleadoJpaController  empJpa = new EmpleadoJpaController();  

    public void crearEmpleado(Empleado emp) {
        empJpa.create(emp);
    }

    public void eliminarEmpleado(int id) {
        try {
            empJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

     public Empleado traerEmpleado(int id) {
           return  empJpa.findEmpleado(id);
    }

    public void editarEmpleado(Empleado emp) {
        try {
            empJpa.edit(emp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Empleado> traerListaEmpleados() {
         List<Empleado> lista = empJpa.findEmpleadoEntities();
         ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>(lista);
         return listaEmpleados;
                
                 
                 
    }
}