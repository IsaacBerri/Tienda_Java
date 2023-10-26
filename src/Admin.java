import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mostrarEmpleados() {
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1 );
            String[] dataEployee = new String[4];
            for (int j = 0; j < 4; j++) {
              Cell cell = row.getCell(j);
              dataEployee[j] = cell.getStringCellValue();
            }
            System.out.println("Name: " + dataEployee[0] + " | ID: " + dataEployee[1] + " | Date: " + dataEployee[2] + " | Rol: " + dataEployee[3]);
        }
    }
    public void actualizarEmpleado() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del empleado que desea actualizar: ");
        String targetId = scanner.nextLine();
        int rowIndexToUpdate = 0;
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1);
            Cell cell = row.getCell(1);
            if (targetId.equals(cell.getStringCellValue())){
                rowIndexToUpdate = i + 1;
                System.out.println("Si");
            }
        }
        if (rowIndexToUpdate != 0){
            System.out.println("Que deseas actualizar? \n 1- Name \n 2- Fecha \n 3- Rol");
            int option = scanner.nextInt();
            Row row = Database.sheet.getRow(rowIndexToUpdate);
            switch (option) {
                case 1:
                    Cell cell = row.getCell(1);
                    System.out.println("Ingresa el nuevo valor para actualizar");
                    String newName = scanner.next();
                    cell.setCellValue(newName);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Cell cell1 = row.getCell(2);
                    System.out.println("Ingrese el nuevo valor para actualizar");
                    String newDate = scanner.next();
                    cell1.setCellValue(newDate);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Cell cell2 = row.createCell(3);
                System.out.println("Ingrese el valor para actualizar");
                String newRol = scanner.next();
                cell2.setCellValue(newRol);
                try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                    Database.workbook.write(outputStream);
                }catch (Exception e){
                    e.printStackTrace();
                }
                    break;
            }
        }

    }

    public void eliminarEmpleado() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID del empleado a eliminar");
        String rowId = scanner.nextLine();
        int rowIndexToDelete = 0; // Reemplaza con el índice de la fila que deseas eliminar
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1);
            Cell cell = row.getCell(1);
            if (rowId.equals(cell.getStringCellValue())) {
                rowIndexToDelete = i + 1;
                System.out.println("El empleado se elimino correctamente");
            }
        }


        if (rowIndexToDelete < 0 || rowIndexToDelete > Database.sheet.getLastRowNum()) {
            System.out.println("Índice de fila no válido");
            return;
        }

        XSSFSheet sheet = (XSSFSheet) Database.sheet;
        for (int i = rowIndexToDelete; i < Database.sheet.getLastRowNum(); i++) {
            XSSFRow currentRow = sheet.getRow(i);
            XSSFRow nextRow = sheet.getRow(i + 1);

            // Copia los valores de la fila siguiente a la fila actual
            for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                XSSFCell currentCell = currentRow.getCell(j);
                XSSFCell nextCell = nextRow.getCell(j);
                if (nextCell != null) {
                    if (currentCell == null) {
                        currentCell = currentRow.createCell(j);
                    }
                    currentCell.setCellValue(nextCell.getStringCellValue()); // Cambia esto según el tipo de celdas
                }
            }
        }

        // Elimina la última fila duplicada
        int lastRowIndex = sheet.getLastRowNum();
        XSSFRow lastRow = sheet.getRow(lastRowIndex);
        sheet.removeRow(lastRow);

        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
            Database.workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






