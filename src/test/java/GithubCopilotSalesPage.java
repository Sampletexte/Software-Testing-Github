import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class GithubCopilotSalesPage {
    WebDriver driver;

    @BeforeClass
        // Open the page to test
    void prepForEachTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://github.com/features/copilot");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    void setupTest() throws InterruptedException {
        driver.get("http://github.com/features/actions");
    }

    @AfterClass
    void finishTest() {
        driver.quit();
    }

    @Test(priority=1)
    void checkMainPageLinks() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();
        Thread.sleep(3000);
        String[] xpaths = {
                "//*[@id=\"hero\"]/div[1]/div/section/div/div/div/div[2]/a[1]", // Get started for free
                "//*[@id=\"hero\"]/div[1]/div/section/div/div/div/div[2]/a[2]", // See plans and pricing
                "//*[@id=\"features\"]/div/div[2]/section[1]/div[2]/div[1]/p/span/a", // Try agent Mode
                "//*[@id=\"features\"]/div/div[2]/section[2]/div[1]/div[2]/p/a",   // Try claude sonnet 3.7
                "//*[@id=\"features\"]/div/div[2]/section[3]/div[1]/div[3]/a",  // Try next edit suggestions
                "//*[@id=\"features\"]/div/div[2]/section[4]/div[1]/div[3]/a",  // Discover Code Review
                "//*[@id=\"features\"]/div/div[2]/section[5]/div[1]/div[3]/a",  // Explore Extensions
                "/html/body/div[1]/div[4]/main/react-app/div/div/div/section[2]/div/div/div/div[1]/a",  // Try Copilot in the CLI
                "/html/body/div[1]/div[4]/main/react-app/div/div/div/section[2]/div/div/div/div[2]/a"   // Chat in Github Mobile
        };

        for (String xpath : xpaths) {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            element.click();
            Thread.sleep(1000);
            driver.navigate().back();
            Thread.sleep(1000);
        }
    }

    @Test(priority=2)
    void checkGeneralFAQ() throws InterruptedException {
        Thread.sleep(3000);
        String generalFAQXPath = "/html/body/div[1]/div[4]/main/react-app/div/div/div/section[4]/div/div/div/div/div[3]/div[2]/div[1]/details[";

        for(int i=1; i < 8; i++) {
            WebElement element = driver.findElement(By.xpath(generalFAQXPath + Integer.toString(i) + "]"));
            element.click();

            WebElement textElem = driver.findElement(By.xpath(generalFAQXPath + Integer.toString(i) + "]"+ "/section/div/p[1]"));
            Assert.assertTrue(textElem.isDisplayed());  // Check that the element is displayed
        }
    }

    @Test(priority=5)
    void checkPlatformLinks() throws InterruptedException {
        Thread.sleep(3000);
        String[][] bottomLinks = {
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[1]/a", "https://docs.github.com/en/get-started/exploring-integrations/about-building-integrations"},
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[2]/a", "https://partner.github.com/"},
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[3]/a", "https://github.com/education"},
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[4]/a", "https://cli.github.com/"},
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[5]/a", "https://github.com/apps/desktop"},
                {"/html/body/div[1]/footer/div[1]/div/nav[2]/ul/li[6]/a", "https://github.com/mobile"}
        };

        for (String[] link : bottomLinks) {
            WebElement element = driver.findElement(By.xpath(link[0]));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(1000);
            element.click();
            Thread.sleep(1000);
            Assert.assertEquals(driver.getCurrentUrl(), link[1], "URL mismatch for: " + link[1]);
            driver.navigate().back(); // Navigate back to the main page
        }
    }

    @Test(priority=4)
    void checkPluginLinks() throws InterruptedException {
        Thread.sleep(3000);
        String[] expectedLinks = {
                "https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2Fcopilot",    // The user needs to log in
                "https://marketplace.visualstudio.com/items?itemName=GitHub.copilot",
                "https://visualstudio.microsoft.com/github-copilot/",
                "https://github.com/github/CopilotForXcode",
                "https://plugins.jetbrains.com/plugin/17718-github-copilot",
                "https://github.com/github/copilot.vim",
                "https://learn.microsoft.com/en-us/azure-data-studio/extensions/github-copilot-extension-overview",
                "https://marketplace.eclipse.org/content/github-copilot#details"
        };

        Thread.sleep(5000);
        for(int i=0; i < expectedLinks.length; i++) {
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/react-app/div/div/div/div[4]/div[2]/a"))
            );

            driver.findElements(By.className("lp-IDE-item")).get(i).click();


            Assert.assertEquals(driver.getCurrentUrl(), expectedLinks[i], "Expected URL: " + expectedLinks[i]);
//            Thread.sleep(1000);
            driver.navigate().back();
        }
    }

    @Test(priority=5)
    void checkCopilotYoutubeTutorials() throws InterruptedException {
        Thread.sleep(3000);
        // Move over to the tutorial page
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div[2]/div/nav/div/ul/li[3]/a")).click();

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        // Iterate through all iframes and check for YouTube video
        for (WebElement iframe : iframes) {
            String src = iframe.getAttribute("src");
            if(src != null && src.contains("youtube")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframe);
                Thread.sleep(1000);

                driver.switchTo().frame(iframe);

                // Start the video
                driver.findElement(By.tagName("body")).click();

                // Switch back to the main content after checking the iframe
                driver.switchTo().defaultContent();
            }
        }

        // Enjoy the ear torture
        Thread.sleep(3000);
    }

}
