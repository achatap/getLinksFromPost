package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.ExtractNumberOfSitemapLinksFromSitemaps;

import java.io.IOException;

public class GetClientLinks extends BaseClass{

    @Parameters("sitemapURL")
    @Test(enabled = true, priority = 1)
    public void extractLinksFromSitemap(String sitemapURL) throws IOException, InterruptedException {
        ExtractNumberOfSitemapLinksFromSitemaps.getAllSitemapLinks(sitemapURL, driver);
    }

    @Parameters("sitemapURL")
    @Test(enabled = true, priority = 2)
    public void extractAllLinksFromParticularSitemap(String sitemapURL) throws IOException {
        ExtractNumberOfSitemapLinksFromSitemaps.getAllPostLinkFromParticularSiteMap(sitemapURL,driver);
    }

    @Parameters({"sitemapURL","contentElement", "rowStart"})
    @Test(enabled = true, priority = 3)
    public void extractClientsFromArticles(String sitemapURL, String contentElement, String rowStart) throws IOException {
        ExtractNumberOfSitemapLinksFromSitemaps.getAllClientsLink(sitemapURL,driver,contentElement,rowStart);
    }

}
