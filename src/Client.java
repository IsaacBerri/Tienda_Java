import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class Client extends User {
    int id;
   String address;
    int contactNumber;
    int stratum;


    Client ()throws IOException{
        try {
            Database.newBasedate(); // Asegúrate de que el workbook esté inicializado
            String[] titlesBasedate = {"ID", "Name", "Doc Id", "Address", "Contac Number", "Stratum"};
            Tools.createSheet("Client", titlesBasedate);
        } catch (IOException e) {
            // Maneja la excepción si ocurre un problema al inicializar el workbook
            Tools.updateLogger(Level.WARNING, "Algo no salio bien al intentar crar la pagina de exel");
        }
    }
    public int getdocId() {

        return docId;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getaddress() {

        return address;
    }

    public void setContactNumber(int contactNumber) {

        this.contactNumber = contactNumber;
    }
    public int getStratum() {

        return stratum;
    }
    public void setRace(String race) {

        this.stratum = stratum;
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
        Object[] newClient = {id, name, docId, address, contactNumber, stratum};
        for (int i = 0; i <newClient.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(newClient[i].toString());
        }

        try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
            Database.workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}