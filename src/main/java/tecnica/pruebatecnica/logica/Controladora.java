
package tecnica.pruebatecnica.logica;

import java.util.ArrayList;
import tecnica.pruebatecnica.persistencia.ControladoraPersistencia;


public class Controladora {
    
         ControladoraPersistencia controlPersis = new ControladoraPersistencia();
         
         public void crearEmpleado (Empleado emp ){
             controlPersis.crearEmpleado(emp);
         }
         
         public void eliminarEmpleado(int id){
             
             controlPersis.eliminarEmpleado(id);
         }
         public void editarEmpleado(Empleado emp){
             controlPersis.editarEmpleado(emp);
         }
         
        public Empleado traerEmpleado(int id){
            return controlPersis.traerEmpleado(id);
        }
        
        public ArrayList<Empleado> traerListaEmpleados(){
            return  controlPersis.traerListaEmpleados();
        }
}
