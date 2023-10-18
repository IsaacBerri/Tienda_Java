import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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
        InputStream miexcel;
        miexcel = new FileInputStream("Database.xlsx");
        // High level representation of a workbook.
        // Representación del más alto nivel de la hoja excel.
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(miexcel);
        // We chose the sheet is passed as parameter.
        // Elegimos la hoja que se pasa por parámetro.
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
        // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
        HSSFRow hssfRow;
        // Initialize the object to read the value of the cell
        // Inicializo el objeto que leerá el valor de la celda
        HSSFCell cell;
        // I get the number of rows occupied on the sheet
        // Obtengo el número de filas ocupadas en la hoja
        int rows = hssfSheet.getLastRowNum();
        // I get the number of columns occupied on the sheet
        // Obtengo el número de columnas ocupadas en la hoja
        int cols = 0;
        // A string used to store the reading cell
        // Cadena que usamos para almacenar la lectura de la celda
        String cellValue;
        // For this example we'll loop through the rows getting the data we want
        // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos
        for (int r = 1; r < hssfSheet.getLastRowNum(); r++) {
            hssfRow = hssfSheet.getRow(r);
            if (hssfRow == null){
                break;
            }else{
                System.out.print("Row: " + r + " -> ");
                for (int c = 1; c < (cols = hssfRow.getLastCellNum()); c++) {
                        /*
                            We have those cell types (tenemos estos tipos de celda):
                                CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
                        */
                    cellValue = String.valueOf(hssfRow.getCell(c));
                    System.out.print("[Column " + c + ": " + cellValue + "] ");
                }
                System.out.println();
            }
        }
    }




}
