package tecnica.pruebatecnica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tecnica.pruebatecnica.logica.Controladora;
import tecnica.pruebatecnica.logica.Empleado;

public class PruebaTecnica {

    public static void main(String[] args) {

        // Creamos la Controladora para persistir los datos
        Controladora control = new Controladora();
        // Creamos el Scanner para ingresar datos
        Scanner lector = new Scanner(System.in);

        String nombre = "";
        String apellido;
        String cargo;
        Double salario = 0.0;
        String fechaInicio;
        String saltoLinea;
        boolean continuarPrograma = true;
        boolean seguirCreando = true;
        // Creamos un ArrayList para almacenar temporalmente al empleado
        ArrayList<Empleado> empleados = new ArrayList<>();
        List<Empleado> listaEmpleados = control.traerListaEmpleados();
        while (continuarPrograma) {
            System.out.println("***********************************");
            System.out.println("*     MENÚ DE ACCIONES            *");
            System.out.println("* 1: Nuevo empleado               *");
            System.out.println("* 2: Mostrar empleados            *");
            System.out.println("* 3: Actualizar datos empleados   *");
            System.out.println("* 4: Borrar empleado              *");
            System.out.println("* 5: Listar empleados por cargo   *");
            System.out.println("* 0: Fin                          *");
            System.out.println("***********************************");

            String opcionStr = lector.next();

            switch (opcionStr) {
                // Se solicitan datos al usuario para la creación del registro de un empleado en la BD
                case "1":
                    saltoLinea = lector.nextLine();
                    System.out.println("Nuevo empleado");
                    while (seguirCreando) {
                        int id = 1;
                        System.out.println("Introduzca el nombre del empleado");
                        // lector.nextLine();
                        nombre = lector.nextLine();

                        while (nombre.trim().equals("") || !nombre.matches(".*[a-zA-Z]+.*")) {
                            if (nombre.trim().isEmpty()) {
                                System.err.print("--El nombre no debe contener solo espacios en blanco--");
                            } else if (!nombre.matches(".*[a-zA-Z]+.*")) {
                                System.err.print("--El nombre no debe contener solo caracteres--");
                            }
                            nombre = lector.nextLine();
                        }
                        System.out.println("Introduzca el apellido del empleado");
                        apellido = lector.nextLine();
                        while (apellido.trim().equals("") || !apellido.matches(".*[a-zA-Z]+.*")) {
                            if (apellido.trim().isEmpty()) {
                                System.err.println("--El apellido no debe contener solo espacios en blanco--");
                            } else if (!apellido.matches(".*[a-zA-Z]+.*")) {
                                System.err.println("--El apellido no debe contener solo caracteres--");
                            }
                            apellido = lector.nextLine();
                        }
                        System.out.println("Introduzca el cargo del empleado ");
                        cargo = lector.nextLine();
                        while (cargo.trim().equals("") || !cargo.matches(".*[a-zA-Z]+.*")) {
                            if (cargo.trim().isEmpty()) {
                                System.err.println("--Cargo no proporcionado--");
                            } else if (!cargo.matches(".*[a-zA-Z]+.*")) {
                                System.err.println("--El cargo no debe contener solo caracteres--");
                            }
                            cargo = lector.nextLine();
                        }
                        System.out.println("Introduzca el salario del empleado");
                        salario = 0.0;
                        while (salario == 0.0) {
                            while (!lector.hasNextDouble()) {
                                System.err.println("--Ingrese un valor numérico--");
                                lector.next();
                            }
                            salario = lector.nextDouble();
                            if (salario == 0.0) {
                                System.err.println("--Salario no proporcionado--");
                            }
                        }

                        saltoLinea = lector.nextLine();
                        System.out.println("Introduzca la fecha dd/mm/aaaa de ingreso ");
                        fechaInicio = lector.nextLine();
                        while (fechaInicio.trim().equals("")) {
                            System.err.println("--Fecha no proporcionada--");
                            fechaInicio = lector.nextLine();
                        }
                        empleados.add(new Empleado(id, nombre, apellido, cargo, salario, fechaInicio));

                        // Se solicita confirmación sobre si desea añadir más empleados al sistema o volver al menú principal de la aplicación
                        System.out.println("¿Desea añadir más empleados? (1: Sí)");

                        String respuesta = lector.nextLine();
                        if (!respuesta.equals("1")) {
                            seguirCreando = false;
                        }
                    }
                    // Se ingresa el empleado a la base de datos
                    for (Empleado lista : empleados) {
                        control.crearEmpleado(lista);
                    }
                    break;

                case "2":

                    // Se muestra una lista de los empleados
                    System.out.println("Listar Empleados");
                    // Se realiza un control para que, en caso de no existir registros, se muestre un mensaje de error pero se pueda continuar en la aplicación
                    listaEmpleados = control.traerListaEmpleados();
                    if (listaEmpleados.isEmpty()) {
                        System.err.println("No se encuentra ningun empleado registrado");
                    } else {
                        System.out.println(" Lista de empleados actualizada");
                        for (Empleado datosEmpleados : listaEmpleados) {
                            System.out.println(datosEmpleados.toString());
                        }
                    }
                    break;
                case "3":
                    System.out.println("Actualizar datos del empleado");

                    if (listaEmpleados.isEmpty()) {
                        System.err.println("No existen empleados actualmente.");
                    } else {
                        System.out.println("Lista empleados");
                        for (Empleado datosEmpleado : listaEmpleados) {
                            System.out.println(datosEmpleado.toString());
                        }

                        int empleadoId = 0;
                        boolean empleadoEncontrado = false;

                        while (!empleadoEncontrado) {
                            System.out.println("Introduzca el ID del empleado que desea actualizar: ");
                            empleadoId = lector.nextInt();

                            for (Empleado empleado : listaEmpleados) {
                                if (empleado.getId() == empleadoId) {
                                    empleadoEncontrado = true;
                                    break;
                                }
                            }

                            if (!empleadoEncontrado) {
                                System.err.println("El ID PROPORCIONADO NO EXISTE");
                            }
                        }

                        Empleado empleadoAActualizar = control.traerEmpleado(empleadoId);
                        System.out.println("Datos del empleado a actualizar: " + empleadoAActualizar);

                        boolean continuarActualizacion = true;

                        while (continuarActualizacion) {
                            System.out.println("****************************************");
                            System.out.println("*    MENÚ DE MODIFICACIONES DE DATOS   *");
                            System.out.println("* 1: Modificar Nombre                  *");
                            System.out.println("* 2: Modificar Apellido                *");
                            System.out.println("* 3: Modificar Cargo                   *");
                            System.out.println("* 4: Modificar Salario                 *");
                            System.out.println("* 5: Modificar Fecha de inicio         *");
                            System.out.println("* 6: Salir sin modificar               *");
                            System.out.println("****************************************");
                            int opcion = 0;
                            while (opcion < 1 || opcion > 6) {
                                System.out.println("Seleccione una opción válida (1-6): ");
                                while (!lector.hasNextInt()) {
                                    System.err.println("--Ingrese un valor numérico--");
                                    lector.next();
                                }
                                opcion = lector.nextInt();
                            }

                            lector.nextLine(); // Limpia el buffer

                            if (opcion == 6) {
                                continuarActualizacion = false;
                                break;
                            }

                            String nuevoValor = "";

                            switch (opcion) {
                                case 1:
                                    System.out.println("Introduzca el nuevo nombre del empleado: ");
                                    nombre = lector.nextLine();
                                    while (nombre.trim().isEmpty() || !nombre.matches("[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]+")) {
                                        if (nombre.trim().isEmpty()) {
                                            System.err.println("--El nombre no debe contener solo espacios en blanco--");
                                        } else {
                                            System.err.println("--El nombre solo debe contener letras--");
                                        }
                                        System.out.println("Introduzca el nuevo nombre del empleado: ");
                                        nombre = lector.nextLine();
                                    }
                                    empleadoAActualizar.setNombre(nombre);
                                    break;
                                case 2:
                                    System.out.println("Introduzca el nuevo apellido del empleado: ");
                                    apellido = lector.nextLine();
                                    while (apellido.trim().isEmpty() || !apellido.matches("[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]+")) {
                                        if (apellido.trim().isEmpty()) {
                                            System.err.println("--El apellido no debe contener solo espacios en blanco--");
                                        } else {
                                            System.err.println("--El apellido solo debe contener letras--");
                                        }
                                        System.out.println("Introduzca el nuevo apellido del empleado: ");
                                        apellido = lector.nextLine();
                                    }
                                    empleadoAActualizar.setApellido(apellido);
                                    break;
                                case 3:
                                    System.out.println("Introduzca el nuevo cargo del empleado: ");
                                    cargo = lector.nextLine();
                                    while (cargo.trim().isEmpty() || !cargo.matches("[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]+")) {
                                        if (cargo.trim().isEmpty()) {
                                            System.err.println("--El cargo no debe contener solo espacios en blanco--");
                                        } else {
                                            System.err.println("--El cargo solo debe contener letras--");
                                        }
                                        System.out.println("Introduzca el nuevo cargo del empleado: ");
                                        cargo = lector.nextLine();
                                    }
                                    empleadoAActualizar.setCargo(cargo);
                                    break;
                                case 4:
                                    System.out.println("Introduzca el nuevo salario del empleado: ");
                                    while (salario == 0.0) {
                                        System.out.println("Introduzca el nuevo salario del empleado: ");
                                        while (!lector.hasNextDouble()) {
                                            System.err.println("--Ingrese un valor numérico--");
                                            lector.next();
                                        }
                                        salario = lector.nextDouble();
                                        if (salario == 0.0) {
                                            System.err.println("--Salario no proporcionado--");
                                        }
                                    }
                                    empleadoAActualizar.setSalario(salario);
                                    break;
                                case 5:
                                    System.out.println("Introduzca la nueva fecha de inicio del empleado: ");
                                    fechaInicio = lector.nextLine();
                                    while (fechaInicio.trim().isEmpty()) {
                                        System.err.println("--Fecha no proporcionada--");
                                        System.out.println("Introduzca la nueva fecha de inicio del empleado: ");
                                        fechaInicio = lector.nextLine();
                                    }
                                    empleadoAActualizar.setFechaInicio(fechaInicio);
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }

                            control.editarEmpleado(empleadoAActualizar);
                            System.out.println("Empleado actualizado: " + empleadoAActualizar);

                            System.out.println("¿Desea modificar otro dato de este empleado? (1: Sí, 2: No)");

                            int seguir = 0;
                            while (seguir < 1 || seguir > 2) {
                                System.out.println("Seleccione una opción válida (1-2): ");
                                while (!lector.hasNextInt()) {
                                    System.err.println("--Ingrese un valor numérico--");
                                    lector.next();
                                }
                                seguir = lector.nextInt();
                            }

                            if (seguir == 2) {
                                continuarActualizacion = false;
                            }
                        }
                    }
                    break;

                case "4":
                    System.out.println("Eliminar empleado");

                    if (listaEmpleados.isEmpty()) {
                        System.err.println("No existen empleados actualmente.");
                    } else {
                        System.out.println("Lista de empleados:");
                        for (Empleado datosEmpleado : listaEmpleados) {
                            System.out.println(datosEmpleado.toString());
                        }

                        int empleadoId = 0;
                        boolean empleadoEncontrado = false;

                        while (!empleadoEncontrado) {
                            System.out.println("Introduzca el ID del empleado que desea eliminar: ");
                            empleadoId = lector.nextInt();

                            for (Empleado empleado : listaEmpleados) {
                                if (empleado.getId() == empleadoId) {
                                    empleadoEncontrado = true;
                                    break;
                                }
                            }

                            if (!empleadoEncontrado) {
                                System.err.println("El ID proporcionado no existe.");
                            }
                        }

                        control.eliminarEmpleado(empleadoId);
                        System.out.println("Empleado eliminado correctamente.");
                    }
                    break;

                case "5":
                    System.out.println("Listar empleados por cargo");
                    if (listaEmpleados.isEmpty()) {
                        System.err.println("No existen empleados actualmente.");
                    } else {
                        lector.nextLine(); // Limpia el buffer

                        // Solicitar al usuario que ingrese el cargo a listar
                        System.out.println("Introduzca el cargo para listar empleados: ");
                        String cargoBuscar = lector.nextLine();
                        ArrayList<Empleado> empleadosPorCargo = new ArrayList<>();

                        for (Empleado empleado : listaEmpleados) {
                            if (empleado.getCargo().equalsIgnoreCase(cargoBuscar)) {
                                empleadosPorCargo.add(empleado);
                            }
                        }

                        if (empleadosPorCargo.isEmpty()) {
                            System.out.println("No se encontraron empleados con el cargo: " + cargoBuscar);
                        } else {
                            System.out.println("Empleados con el cargo '" + cargoBuscar + "':");
                            for (Empleado empleado : empleadosPorCargo) {
                                System.out.println(empleado.toString());
                            }
                        }
                    }
                    break;
                case "0":
                    continuarPrograma = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
