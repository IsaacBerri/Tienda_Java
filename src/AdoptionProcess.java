import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

public class AdoptionProcess {
  int idProcess;
  Date date = new Date();
  String state = "Pendiente";


  public AdoptionProcess() throws IOException {
    try{
      Database.newBasedate();
      String[] titles = {"Id process", "Name employee", "Name client", "Id animal",
              "Date", "State"};
      Tools.createSheet("adoption process", titles);
    }catch (IOException e) {
      Tools.updateLogger(Level.SEVERE, "No se pudo crear la hoja correctamente");
    }
  }

  public void addProcess(String employee, String cliente, String animal){
    Row row = Database.sheet.createRow(Database.rows);
    int numRandom =(int)Math.floor(Math.random()*1000);
    idProcess = numRandom;
    Client.idProcessAdoption = numRandom;

    Object[] process = {idProcess, employee, cliente, animal, date, state};

    for (int i = 0; i < process.length; i++) {
      Cell cell = row.createCell(i);
      cell.setCellValue(process[i].toString());
    }
    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
      Database.workbook.write(outputStream);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public void updateProcess(){
    System.out.println("Ingresa id del proceso a editar");
    Scanner sc = new Scanner(System.in);
    String id = sc.nextLine();

    int rowIndexToUpdate = 0;
    for (int i = 0; i < Database.rows - 1; i++) {
      Row row = Database.sheet.getRow(i+1);
      Cell cell = row.getCell(0);
      if(id.equals(cell.getStringCellValue())){
        rowIndexToUpdate = i+1;
      }
    }
    if (rowIndexToUpdate != 0) {
     Row row = Database.sheet.getRow(rowIndexToUpdate);
     Cell cell = row.getCell(5);
      System.out.println("Ingresa nuevo state del registro de adopción con id: "+ id + "\n 1- Aceptado \n 2- Reachazado");
      int option = sc.nextInt();
      if(option == 1){
        cell.setCellValue("Aceptado");
        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
          Database.workbook.write(outputStream);
        }catch (Exception e){
          e.printStackTrace();
        }
      } else if (option == 2) {
        cell.setCellValue("Rechazado");
        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
          Database.workbook.write(outputStream);
        }catch (Exception e){
          e.printStackTrace();
        }
      }else {
        System.out.println("Numero ingresado no es valido para cambiar estado");
      }
    }
  }

  public void readProcess() {
    for (int i = 0; i < Database.rows; i++) {
      Row row = Database.sheet.getRow(i+1);
      String[] date = new String[row.getLastCellNum()];
      for (int j = 0; j < row.getLastCellNum(); j++) {
        Cell cell = row.getCell(j);
        String value = cell.getStringCellValue();
        date[j] = value;
      }
      System.out.println("Id process: "+ date[0] + " | "+"Name employee: "+ date[1] +" | "+"Name client: "+ date[2] +" | "+
              "Id animal: "+ date[3] +" | "+"Date: "+ date[4] +" | "+"State: "+date[5]);
    }

  }
}
