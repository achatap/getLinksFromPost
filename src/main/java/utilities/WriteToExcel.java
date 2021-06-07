package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteToExcel {

    public static void createNewExelFile(File src){

        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("sitemaps");
        try {
            OutputStream fileOut = new FileOutputStream(src);
            System.out.println("Excel File has been created successfully.");

            wb.write(fileOut);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
