import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class Createbasedate {
    public static Sheet sheet;
    public static Workbook workbook;
    public static  int rows = 0;


    public static void newBasedate() throws IOException {
        try {
            // Intenta abrir el archivo Excel si ya existe
            FileInputStream archivoEntrada = new FileInputStream("Database.xlsx");
            workbook = WorkbookFactory.create(archivoEntrada);
            archivoEntrada.close();
        } catch (IOException e) {
            // Si ocurre una excepción, el archivo no existe, así que lo creamos
            workbook = new XSSFWorkbook();

            try {
                FileOutputStream archivoSalida = new FileOutputStream("Database.xlsx");
                workbook.write(archivoSalida);
                archivoSalida.close();
            } catch (IOException e2) {
                Tools.updateLogger(Level.SEVERE, "El libro de exel no se puedo crear");
            }
        }
    }
}
