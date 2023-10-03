import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.util.Scanner;

public class Animal {
    int id;
    String name;
    int age;
    String race;
    String specie;
    String description;


    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("Enter name");
        Scanner sc = new Scanner(System.in);
        this.name = sc.nextLine();
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        System.out.println("Enter age");
        Scanner sc = new Scanner(System.in);
        this.age = sc.nextInt();
    }

    public String getRace() {
        return race;
    }

    public void setRace() {
        System.out.println("Enter race");
        Scanner sc = new Scanner(System.in);
        this.race = sc.nextLine();
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie() {
        System.out.println("Enter specie");
        Scanner sc = new Scanner(System.in);
        this.specie = sc.nextLine();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        System.out.println("Enter description");
        Scanner sc = new Scanner(System.in);
        this.description = sc.nextLine();
    }

    public void addAnimal() {
        Row row = Createbasedate.sheet.createRow(1);
        id = (int) Math.floor(Math.random()*1000);
        Object[] newAnimal = {id, name, age, race, specie, description};




        Row row1 = Createbasedate.sheet.getRow(1);
        for (int i = 0; i < newAnimal.length; i++) {
            Cell cell = row1.createCell(i);
            cell.setCellValue(newAnimal[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("C:/Users/berri/OneDrive/Escritorio/Proyectos/c13/Modulo  3 - Java/projectFinal/database.xlsx")) {
            Createbasedate.workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
