import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public abstract class User{
    static String name;
    int docId;

    public String getName() {
        return name;
    }

    public void setName() {
        Scanner sc = new Scanner(System.in);
        name = sc.next();
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId() {
        Scanner sc = new Scanner(System.in);
        this.docId = sc.nextInt();
    }

    public boolean logging() {
        Scanner sc = new Scanner(System.in);
        boolean userRegister = false;
        System.out.println("Ingresa tu nombre de usuario");
        String nombreIngresado = sc.nextLine();
        for (int i = 0; i < Database.rows - 1; i++) {
            Row row = Database.sheet.getRow(i + 1);
            Cell cell = row.getCell(1);
            if (nombreIngresado.equals(cell.getStringCellValue())){
                userRegister = true;
                name = nombreIngresado;
            }
        }
        return userRegister;
    }

    public void register() {
        System.out.println("Ingrese su nombre");
        setName();
        System.out.println("Ingrese el documento de identidad");
        setDocId();
        System.out.println("Ingrese la direccion");
        Client.setAddress();
        System.out.println("Igresa el nuemro de contacto");
        Client.setContactNumber();
        System.out.println("Ingrese su estrato socioeconomico");
        Client.setStratum();
    }
}
