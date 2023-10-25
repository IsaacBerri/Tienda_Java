import java.io.IOException;
import java.util.logging.Level;

public class Client extends User {
   String address;
    int contactNumber;
    int stratum;

    Client ()throws IOException{
        try {
            Database.newBasedate(); // Asegúrate de que el workbook esté inicializado
            String[] titlesBasedate = {"Doc Id", "Name", "Address", "Contac Number", "Stratum"};
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

}