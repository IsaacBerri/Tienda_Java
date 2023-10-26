import java.io.IOException;
import java.util.Scanner;

public class Menus {

    static Animal animal;

    static {
        try {
            animal = new Animal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void mainMenu () throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quien eres? \n 1- Administrador \n 2- Empleado \n 3- Cliente");
        int respuesta = sc.nextInt();
        switch (respuesta) {
            case 1:
                System.out.println("¿Que quieres hacer?");
                System.out.println("1- Agregar empleados \n 2- Ver empleados \n 3- Actualizar informacion \n 4- Eliminar Empeleado \n 5- volver");
                int respuestaAdmin = sc.nextInt();
                menuAdmin(respuestaAdmin);
                break;
            case 2:
                System.out.println("¿Que quieres hacer?");
                System.out.println("1- Agregar animal \n 2- Editar animal \n 3- Eliminar animal \n 4- Ver animales \n 5- Ver proceso de adopcion \n 6- Editar preceso de adopcion \n 7- Volver");
                int respuestaEmpleado = sc.nextInt();
                menuEmployee(respuestaEmpleado);
                break;
            case 3:
                System.out.println("¿Que quieres hacer? \n 1- Inicial sesion \n 2- Registrarte");
                int respuestaCliente = sc.nextInt();
                menuClient(respuestaCliente);
                break;
            default:
                break;
        }
    }
    
    public static void menuAdmin(int num) throws IOException {
        Admin admin = new Admin();
        switch (num) {
            case 1:
                admin.agregarEmpleadoDesdeConsola();
                break;
            case 2:
                admin.mostrarEmpleados();
                break;
            case 3:
                admin.actualizarEmpleado();
                break;
            case 4:
                admin.eliminarEmpleado();
                break;
            case 5:
                mainMenu();
                break;
        }
    }

    public static void menuEmployee(int num2) throws IOException {
        switch (num2) {
            case 1:
                animal.registerAnimal();
                animal.addAnimal();
                break;
            case 2:
                animal.updateAnimal();
                break;
            case 3:
                animal.deleteAnimal();
                break;
            case 4:
                animal.readAnimal();
                break;
            case 5:
                Employee employee = new Employee();
                employee.read();
                mainMenu();
                break;
            case 6:
                Employee employee1 = new Employee();
                employee1.update();
                break;
            case 7:
                mainMenu();
                break;
        }
    }

    public static void menuClient(int num3) throws IOException {
        Client client = new Client();
        switch (num3) {
            case 1:
                if (client.logging()){
                    subMenuCliente();
                }else {
                    System.out.println("No se encontra registrada tu informacion en el sistema");
                }
                break;
            case 2:
                client.register();
                client.addClient();
                break;
        }
    }

    public static void subMenuCliente() throws IOException{
        Employee employee = new Employee();
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que quieres hacer? \n 1- Ver animales \n 2- Adoptar animal \n 3- volver");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                Animal animal1 = new Animal();
                animal.readAnimal();
                subMenuCliente();
                break;
            case 2:
                Animal animal2 = new Animal();
                employee.createProcess(Client.name);
                break;
            case 4:
                mainMenu();
                break;
        }
    }
}
