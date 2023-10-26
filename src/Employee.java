import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.Scanner;

public class Employee extends User{

  String name;
  AdoptionProcess adoption;
  public void createProcess(String client) throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese el Id del animal que desea adoptar");
    String animalDeseado = sc.nextLine();
    for (int i = 0; i < Database.rows - 1; i++) {
      Row row = Database.sheet.getRow(i + 1);
      Cell cell = row.getCell(0);
      if (animalDeseado.equals(cell.getStringCellValue())){
        Admin admin = new Admin();
        System.out.println("Ingresa el nombre del vendedor que te esta atendiendo");
        for (int j = 0; j < Database.rows - 1; j++) {
          Row row1 = Database.sheet.getRow(j + 1);
          Cell cell1 = row1.getCell(3);
          int acum = 0;
          if (cell1.getStringCellValue().equals("Vendedor")){
            System.out.println(row1.getCell(1).getStringCellValue() + "- " + row1.getCell(0).getStringCellValue());
          }
        }
        name = sc.nextLine();
        adoption = new AdoptionProcess();
        adoption.addProcess(name, client, animalDeseado);
      }
    }

  }
  public void update() throws IOException {
    adoption = new AdoptionProcess();
    adoption.updateProcess();
  }
  public void read(){
    adoption.readProcess();
  }

}
