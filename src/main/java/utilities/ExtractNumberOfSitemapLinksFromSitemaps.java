package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractNumberOfSitemapLinksFromSitemaps{

    public static String fileNameOfExelWithAllSitemapLinks;

    public static void getAllSitemapLinks(String websiteSitemapURL, WebDriver driver) throws IOException, InterruptedException {

        String fileNameOfExelWithAllSitemapLinks = websiteSitemapURL.replaceAll("[^a-zA-Z0-9]", "_");

        File src = new File("src/main/resources/" + fileNameOfExelWithAllSitemapLinks + ".xlsx");

        // create new exel sheet with the name of website
        WriteToExcel.createNewExelFile(src);

        // open exel to read or write mode
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wbNew = new XSSFWorkbook(fis);
        Sheet sheet = wbNew.getSheet("sitemaps");

        // get all the sitemap links from sitemap page(main page)
        driver.get(websiteSitemapURL);
        WebElement articleBody = driver.findElement(By.xpath("//body"));

        List<WebElement> allUrls=articleBody.findElements(By.tagName("a"));
        int rows_count = allUrls.size();

        System.out.println("Total Number of links: "+ rows_count);
        String completeURL;

        if(websiteSitemapURL.contains("https")){
            completeURL = websiteSitemapURL.replaceAll("https://","");
        }
        else {
            completeURL = websiteSitemapURL.replaceAll("http://","");
        }

        String rootURL = completeURL.split("\\.")[0];
            int totalRow= 0;
            for (int i = 0; i < rows_count; i++) {
                String url = allUrls.get(i).getAttribute("href");
                if(url.contains(rootURL)){
                    sheet.createRow(totalRow).createCell(0).setCellValue(url);
                    totalRow++;
                }
            }

        // write all sites links from page and store into exel sheet
        System.out.println("Total Number of links Added in Exel sheet: "+ totalRow);
        FileOutputStream outputStream = new FileOutputStream("src/main/resources/" + fileNameOfExelWithAllSitemapLinks + ".xlsx");
        wbNew.write(outputStream);
        wbNew.close();

    }

    public static void getAllPostLinkFromParticularSiteMap(String websiteSitemapURL, WebDriver driver) throws IOException {

        String fileNameOfExelWithAllSitemapLinks = websiteSitemapURL.replaceAll("[^a-zA-Z0-9]", "_");

        File src = new File("src/main/resources/" + fileNameOfExelWithAllSitemapLinks + ".xlsx");

        // open exel to read or write mode
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wbNew = new XSSFWorkbook(fis);
        Sheet sheet = wbNew.getSheet("sitemaps");

        int lastRow= sheet.getLastRowNum();

        int totalRow= 0;
        for (int i = 0; i < lastRow; i++) {

            driver.get(sheet.getRow(i).getCell(0).getStringCellValue());

            System.out.println("URL is : "+ sheet.getRow(i).getCell(0).getStringCellValue());
            WebElement articleBody = driver.findElement(By.xpath("//body"));

            List<WebElement> allUrls=articleBody.findElements(By.tagName("a"));
            int allLinkCount = allUrls.size();
            System.out.println("Total Number of links on page: "+ allLinkCount);
            String completeURL;

            for (int j=0; j<allLinkCount;j++) {

                if(websiteSitemapURL.contains("https")){
                    completeURL = websiteSitemapURL.replaceAll("https://","");
                }
                else {
                    completeURL = websiteSitemapURL.replaceAll("http://","");
                }

                String rootURL = completeURL.split("\\.")[0];
                String url = allUrls.get(j).getAttribute("href");

                if(url.contains(rootURL)){

                    if(totalRow>=lastRow){
                        sheet.createRow(totalRow).createCell(1).setCellValue(url);
                    }
                    else sheet.getRow(totalRow).createCell(1).setCellValue(url);
                    totalRow++;
                }
            }
        }

        // write all sites links from page and store into exel sheet
        System.out.println("Total Number of links Added in Exel sheet: "+ totalRow);
        FileOutputStream outputStream = new FileOutputStream("src/main/resources/" + fileNameOfExelWithAllSitemapLinks + ".xlsx");
        wbNew.write(outputStream);
        wbNew.close();
    }
}
