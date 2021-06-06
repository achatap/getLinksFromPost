package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExtractURL {

    public static void getExtractedURL() throws IOException {

        File src = new File("C:\\Users\\Anurag\\Desktop\\itphobia.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        // for collection of sponsor sites

        int allURL = sheet.getLastRowNum();

        int value=0;

        for (int count = 1; count <= allURL; count++) {

            try {

                String url = sheet.getRow(count).getCell(1).toString();

                String[] sites= url.split("\n");

                int total= sites.length;

                String last="";

                System.out.println("total sites: "+ total);

                for (int k=0; k<total; k++)
                {
                    if(sites[k].contains("null"))
                    {
                        continue;
                    }
                    if(sites[k].contains("noragouma"))
                    {
                        continue;
                    }
                    if(sites[k].contains("wordpress"))
                    {
                        continue;
                    }
                    if(sites[k].contains("tweeter"))
                    {
                        continue;
                    }
                    if(sites[k].contains("facebook"))
                    {
                        continue;
                    }
                    if(sites[k].contains("javascript"))
                    {
                        continue;
                    }
                    if(sites[k].contains("youtube"))
                    {
                        continue;
                    }
                    if(sites[k].contains("jpg"))
                    {
                        continue;
                    }

                    if(sites[k].contains("flickr"))
                    {
                        continue;
                    }
                    if(sites[k].contains("google"))
                    {
                        continue;
                    }

                    if(sites[k].contains("amazon"))
                    {
                        continue;
                    }
                    if(sites[k].contains("apple"))
                    {
                        continue;
                    }
                    if(sites[k].contains("wikipedia"))
                    {
                        continue;
                    }
                    if(sites[k].contains("youtu.be"))
                    {
                        continue;
                    }
                    if(sites[k].contains("thebalance"))
                    {
                        continue;
                    }
                    if(sites[k].contains("pinterest.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("linkedin.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("huffingtonpost.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("forbes.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("entrepreneur.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("buzzfeed.com"))
                    {
                        continue;
                    }
                    if(sites[k].contains("twitter.com"))
                    {
                        continue;
                    }

                    last= last+ "\n" +sites[k];

                }

                System.out.println("**************************");
                System.out.println(last);
                System.out.println("**************************");

                value= value+1;

                System.out.println("Count value is:"+ value);

                sheet.getRow(count).createCell(1).setCellValue(last);

            }

            catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }
        FileOutputStream fout = new FileOutputStream(new File("C:\\Users\\Anurag\\Desktop\\JharaphulaRootLinks.xlsx"));
        wb.write(fout);
        fout.close();
    }
}
