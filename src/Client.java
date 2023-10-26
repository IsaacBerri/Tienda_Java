import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Client extends User {
    int id;
    static String address;
    static int contactNumber;
    static int stratum;
    static int idProcessAdoption;


    Client ()throws IOException{
        try {
            Database.newBasedate(); // Asegúrate de que el workbook esté inicializado
            String[] titlesBasedate = {"ID", "Name", "Doc Id", "Address", "Contac Number", "Stratum", "idProcessAdoption"};
            Tools.createSheet("Client", titlesBasedate);
        } catch (IOException e) {
            // Maneja la excepción si ocurre un problema al inicializar el workbook
            Tools.updateLogger(Level.WARNING, "Algo no salio bien al intentar crar la pagina de exel");
        }
    }
    public String getAddress() {
        return address;
    }

    public static void setAddress() {
        Scanner sc = new Scanner(System.in);
        address = sc.nextLine();
    }

    public int getContactNumber() {
        return contactNumber;
    }
    public static void setContactNumber() {
        Scanner sc = new Scanner(System.in);
        contactNumber = sc.nextInt();
    }
    public int getStratum() {
        return stratum;
    }
    public static void setStratum() {
        Scanner sc = new Scanner(System.in);
        stratum = sc.nextInt();
    }
    public void addClient(){
        Row row = Database.sheet.createRow(Database.rows);
        int ramdon = (int)Math.floor(Math.random()*1000);
        id = ramdon;
        for (int i = 0; i < Database.rows - 1; i++) {
            String numString = String.valueOf(ramdon);
            Row rowx = Database.sheet.getRow(i + 1);
            Cell cell2 = rowx.getCell(0);
            while (numString.equals(cell2.getStringCellValue())){
                id = (int)Math.floor(Math.random()*1000);
            }
        }
        Object[] newClient = {id, name, docId, address, contactNumber, stratum, idProcessAdoption};
        for (int i = 0; i <newClient.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(newClient[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
            Database.workbook.write(outputStream);
            System.out.println("Registrado correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}