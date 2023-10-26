import jdk.nashorn.internal.parser.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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

    public static void createSheet(String name, String[] array) {
        if (Database.workbook != null) {

            Database.sheet = Database.workbook.getSheet(name);

            if (Database.sheet == null) {
                Database.sheet = Database.workbook.createSheet(name);
            }

            Row row = Database.sheet.createRow(0);
            Database.rows = Database.sheet.getPhysicalNumberOfRows();

            for (int i = 0; i < array.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(array[i]);
            }

            try (FileOutputStream outputStream = new FileOutputStream("database.xlsx")) {
                Database.workbook.write(outputStream);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
