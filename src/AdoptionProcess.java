import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class AdoptionProcess {
  int Id;
  String name;
  String adress;
  int contactNumber;
  int idPet;
  String adoptionPreferences;
  Scanner sc = new Scanner(System.in);

  public AdoptionProcess() throws IOException {
    try {
      Database.newBasedate();
      String[] titlesBasedate = {"Id","Adopter's name", "Adress", "Contact number", "Id pet", "adoptionPreferences"};
      Tools.createSheet("adoption registration", titlesBasedate);
    } catch (IOException e) {
      Tools.updateLogger(Level.WARNING, "Algo no salio bien al intentar crar la pagina de exel");
    }
  }
  public int getId(){
    return Id;
  }
  public void setId(int Id){
    this.Id = Id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public int getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(int contactNumber) {
    this.contactNumber = contactNumber;
  }

  public int getIdPet() {
    return idPet;
  }

  public void setIdPet(int idPet) {
    this.idPet = idPet;
  }

  public String getAdoptionPreferences() {
    return adoptionPreferences;
  }

  public void setAdoptionPreferences(String adoptionPreferences) {
    this.adoptionPreferences = adoptionPreferences;
  }

  public void registerProcess() throws IOException {
    try {
      System.out.println("Enter Id");
      setId(Integer.parseInt(sc.nextLine()));
      System.out.println("Enter adopter name");
      setName(sc.nextLine());
      System.out.println("Enter adress");
      setAdress(sc.nextLine());
      System.out.println("Enter contact number");
      setContactNumber(Integer.parseInt(sc.nextLine()));
      System.out.println("Enter pet it");
      setIdPet(Integer.parseInt(sc.nextLine()));
      System.out.println("Enter adoption preferences");
      setAdoptionPreferences(sc.nextLine());
    } catch (Exception err) {
      String exceptionType = err.getClass().getName();
      if (exceptionType.equals("java.lang.NumberFormatException")) {
        Tools.updateLogger(Level.SEVERE, "El usuario ingresó una cadena de texto en age");
      }
      if (exceptionType.equals("java.lang.RuntimeException")) {
        Tools.updateLogger(Level.SEVERE, "El usuario ingresó una cadena de texto muy larga en descripton");
      }
    }

  }

  public void addRegister(){
    Row row = Database.sheet.createRow(Database.rows);
    Object[] newRegister = {Id,name,adress, contactNumber, idPet, adoptionPreferences};

    for (int i = 0; i < newRegister.length; i++) {
      Cell cell = row.createCell(i);
      cell.setCellValue(newRegister[i].toString());
    }

    try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
      Database.workbook.write(outputStream);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

}