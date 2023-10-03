import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class Createbasedate {

    public static Sheet sheet;
    public static Workbook workbook;

    public void newBasedate() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("myDatabase");

        Row row = sheet.createRow(0);

        String[] titlesBasedate = {"Id", "Name", "Age", "Race", "Specie", "Description"};

        for (int i = 0; i < titlesBasedate.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(titlesBasedate[i]);
        }

        /*Cell cell1 = row.createCell(0);
        cell1.setCellValue("Id");

        Cell cell2 = row.createCell(1);
        cell2.setCellValue("Name");

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Race");

        Cell cell3 = row.createCell(2);
        cell3.setCellValue("Race");*/

        try (FileOutputStream outputStream = new FileOutputStream("C:/Users/berri/OneDrive/Escritorio/Proyectos/c13/Modulo  3 - Java/projectFinal/database.xlsx")) {
            workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
