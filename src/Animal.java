import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Animal{
    int id;
    String name;
    int age;
    String race;
    String specie;
    String description;
    Scanner sc = new Scanner(System.in);


    public Animal() throws IOException {
        try {
            Createbasedate.newBasedate(); // Asegúrate de que el workbook esté inicializado
            String[] titlesBasedate = {"Id", "Name", "Age", "Race", "Specie", "Description"};
            Tools.createPage("Animal", titlesBasedate);
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
        Row row = Createbasedate.sheet.createRow(Createbasedate.rows);
        id = (int) Math.floor(Math.random()*1000);
        Object[] newAnimal = {id, name, age, race, specie, description};

        for (int i = 0; i < newAnimal.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(newAnimal[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("C:/Users/berri/OneDrive/Escritorio/Proyectos/c13/Modulo  3 - Java/projectFinal/database.xlsx")) {
            Createbasedate.workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
