import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Admin extends User {
    String rol;
    String fechaDeContratacion;
    int id;
    String nombre;

    Admin() throws IOException {
        try{
            Database.newBasedate();
            String[] titulos = {"nombre", "id", "fechaContrato", "rol"};
            Tools.createSheet("empleados", titulos);
        }catch (IOException e) {
            Tools.updateLogger(Level.SEVERE, "No se pudo crear la hoja correctamente");
        }

    }

    public void agregarEmpleadoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre del empleado: ");
        nombre = scanner.nextLine();

        System.out.print("Fecha de contratación: ");
        fechaDeContratacion = scanner.nextLine();

        System.out.print("Rol: ");
        rol = scanner.nextLine();

        Row row = Database.sheet.createRow((Database.rows));
        id = (int) Math.floor(Math.random()*1000);
        Object[] newEmpleado = { nombre, id, fechaDeContratacion, rol};

        for (int i = 0; i < newEmpleado.length; i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(newEmpleado[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
            Database.workbook.write(outputStream);
            System.out.println("Empleado agregado a la hoja de Excel.");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}