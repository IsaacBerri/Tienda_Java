import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Tools{
    public static void updateLogger(Level level, String mensaje) throws IOException {
        FileHandler fileHandler = null;

        try {
            Logger logger = Logger.getLogger("mylog.log");
            fileHandler = new FileHandler("data/mylog.log", true); // El segundo argumento "true" indica que se agregará al archivo existente
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.log(level, mensaje);
        } catch (SecurityException | IOException e) {
            e.printStackTrace(); // Manejo básico de excepciones para depuración
        } finally {
            if (fileHandler != null) {
                fileHandler.close();
            }
        }
    }

    public static void createPage(String name, String[] array) {
        if (Createbasedate.workbook != null) {

            Createbasedate.sheet = Createbasedate.workbook.getSheet(name);

            if (Createbasedate.sheet == null) {
                Createbasedate.sheet = Createbasedate.workbook.createSheet(name);
            }

            Row row = Createbasedate.sheet.createRow(0);
            Createbasedate.rows = Createbasedate.sheet.getPhysicalNumberOfRows();

            for (int i = 0; i < array.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(array[i]);
            }

            try (FileOutputStream outputStream = new FileOutputStream("C:/Users/berri/OneDrive/Escritorio/Proyectos/c13/Modulo  3 - Java/projectFinal/database.xlsx")) {
                Createbasedate.workbook.write(outputStream);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
