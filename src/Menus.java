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
                subMenu1(respuestaAdmin);
                break;
            case 2:
                System.out.println("¿Que quieres hacer?");
                System.out.println("1- Agregar animal \n 2- Editar animal \n 3- Eliminar animal \n 4- Ver animales \n 5- volver");
                int respuestaEmpleado = sc.nextInt();
                subMenu2(respuestaEmpleado);
                break;
            case 3:
                System.out.println("¿Que quieres hacer?");
                System.out.println("1- Ver animales \n 2- Adoptar animal \n 3- Ver proceso de adopción \n 4- volver");
                int respuestaCliente = sc.nextInt();
                subMenu3(respuestaCliente);
                break;
            default:
                break;
        }
    }
    
    public static void subMenu1(int num) throws IOException {
        switch (num) {
            case 1:
                System.out.println("Agregamos empleado");
                break;
            case 2:
                System.out.println("Vemos los empleados");
                break;
            case 3:
                System.out.println("Actualizo la informacion");
                break;
            case 4:
                System.out.println("Elimino empleado");
                break;
            case 5:
                mainMenu();
                break;
        }
    }

    public static void subMenu2(int num2) throws IOException {
        switch (num2) {
            case 1:
                animal.registerAnimal();
                animal.addAnimal();
                break;
            case 2:
                System.out.println("Actualizo la informacion");
                break;
            case 3:
                System.out.println("Eliminamos animal");
                break;
            case 4:
                animal.readAnimal();
                break;
            case 5:
                mainMenu();
                break;
        }
    }

    public static void subMenu3(int num3) throws IOException {
        switch (num3) {
            case 1:
                animal.readAnimal();
                break;
            case 2:
                System.out.println("Adoptamos animal");
                break;
            case 3:
                System.out.println("Ver proceso de adopcion");
                break;
            case 4:
                mainMenu();
                break;
        }
    }
}
