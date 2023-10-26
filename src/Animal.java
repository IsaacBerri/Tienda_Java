import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;

public class Animal{
    private int id;
    String name;
    int age;
    String race;
    String specie;
    String description;
    Scanner sc = new Scanner(System.in);


    public Animal() throws IOException {
        try {
            Database.newBasedate(); // Asegúrate de que el workbook esté inicializado
            String[] titlesBasedate = {"Id", "Name", "Age", "Race", "Specie", "Description"};
            Tools.createSheet("Animal", titlesBasedate);
        } catch (IOException e) {
            // Maneja la excepción si ocurre un problema al inicializar el workbook
            Tools.updateLogger(Level.WARNING, "Algo no salio bien al intentar crar la pagina de exel");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {

        this.race = race;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {

        this.specie = specie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        if (description.length() <= 25) {

            this.description = description;
        }else {
            throw new RuntimeException("La descripcion es muy larga");
        }

    }

    public void registerAnimal() throws IOException {
        try {
            System.out.println("Enter name");
            setName(sc.nextLine());
            System.out.println("Enter age");
            setAge(Integer.parseInt(sc.nextLine()));
            System.out.println("Enter race");
            setSpecie(sc.nextLine());
            System.out.println("Enter specie");
            setRace(sc.nextLine());
            System.out.println("Enter description");
            setDescription(sc.nextLine());
        }catch(Exception err) {
            String exceptionType = err.getClass().getName();
            if (exceptionType.equals("java.lang.NumberFormatException")) {
                Tools.updateLogger(Level.SEVERE, "El usuario ingresó una cadena de texto en age");
            }
            if (exceptionType.equals("java.lang.RuntimeException")) {
                Tools.updateLogger(Level.SEVERE, "El usuario ingresó una cadena de texto muy larga en descripton");
            }
        }
    }

    public void addAnimal() {
        Row row = Database.sheet.createRow(Database.rows);
        id = (int) Math.floor(Math.random()*1000);
        Object[] newAnimal = {id, name, age, race, specie, description};

        for (int i = 0; i < newAnimal.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(newAnimal[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
            Database.workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readAnimal() throws IOException {
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1 );
            String[] dataAnimal = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                dataAnimal[j] = cell.getStringCellValue();
            }
            System.out.println("Id: " + dataAnimal[0] + " | Name: " + dataAnimal[1] + " | Age: " + dataAnimal[2] + " | Specie: " + dataAnimal[3] + " | Race: " + dataAnimal[4] + " | Description: " + dataAnimal[5]);
        }
    }

    public void updateAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del animal que desea actualizar: ");
        String targetId = scanner.nextLine();
        int rowIndexToUpdate = 0;
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1);
            Cell cell = row.getCell(1);
            if (targetId.equals(cell.getStringCellValue())){
                rowIndexToUpdate = i + 1;
            }
        }
        if (rowIndexToUpdate != 0){
            System.out.println("Que deseas actualizar? \n 1- Name \n 2- Age \n 3- Specie \n 4- Race \n 5- Description");
            int option = scanner.nextInt();
            Row row = Database.sheet.getRow(rowIndexToUpdate);
            switch (option) {
                case 1:
                    Cell cellName = row.getCell(1);
                    System.out.println("Ingresa el nuevo valor para actualizar");
                    String newName = scanner.next();
                    cellName.setCellValue(newName);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Cell cellAge = row.getCell(2);
                    System.out.println("Ingrese el nuevo valor para actualizar");
                    String newAge = scanner.next();
                    cellAge.setCellValue(newAge);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    Cell cellSpecie = row.createCell(3);
                    System.out.println("Ingrese el valor para actualizar");
                    String newSpecie = scanner.next();
                    cellSpecie.setCellValue(newSpecie);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    Cell cellRace = row.createCell(4);
                    System.out.println("Ingrese el valor para actualizar");
                    String newRace = scanner.next();
                    cellRace.setCellValue(newRace);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    Cell cellDescription = row.createCell(5);
                    System.out.println("Ingrese el valor para actualizar");
                    String newDescription = scanner.next();
                    cellDescription.setCellValue(newDescription);
                    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                        Database.workbook.write(outputStream);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void deleteAnimal() throws IOException {
        System.out.println("Ingrese el ID del animal a eliminar");
        String rowId = sc.nextLine();
        int rowIndexToDelete = 0; // Reemplaza con el índice de la fila que deseas eliminar
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1);
            Cell cell = row.getCell(0);
            if (rowId.equals(cell.getStringCellValue())) {
                rowIndexToDelete = i + 1;
                System.out.println("El animal se elimino correctamente");
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
