import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Github_docs {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void Navigation_to_Doc_Page() throws InterruptedException, IOException {
        //Navigate to Documentation Page
        driver.get("https://github.com/");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/header/div/div[2]/div/nav/ul/li[1]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/header/div/div[2]/div/nav/ul/li[1]/div/div[2]/div/ul/li[3]/a")).click();
        Thread.sleep(1000);

        //Store all tabs, switch to new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);
        jse.executeScript("window.scrollTo(600, 0)");

        //Upload Screenshot
        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File desination = new File("Github_docs.png");
        Files.copy(Screenshot.toPath(), desination.toPath());
    }

    @Test(dependsOnMethods = "Navigation_to_Doc_Page" )
    public void Version_dropdown_testing() throws InterruptedException {
        //Switch to Enterprise cloud
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(5000);


        //Switch to Enterprise Server 3.16
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-server@3.16']")).click();
        Thread.sleep(5000);

        //Switch to Enterprise 3.15
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-server@3.15']")).click();
        Thread.sleep(5000);

        //Switch to Enterprise 3.14
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-server@3.14']")).click();
        Thread.sleep(5000);


        //Switch to Enterprise 3.13
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-server@3.13']")).click();
        Thread.sleep(5000);

        //Switch to Enterprise 3.12
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-server@3.12']")).click();
        Thread.sleep(5000);

        //Switch to Enterprise cloud
        driver.findElement(By.xpath("//*[@id=':Ripn6:']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(5000);


    }

    @Test(dependsOnMethods = "Version_dropdown_testing")
    public void Help_and_support_input() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);

        //Select Like Button, Type in box
        driver.findElement(By.cssSelector(".btn.mr-1")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("survey-comment")).sendKeys("I learned how to find and use Github CoPilot through the documentation!");
        Thread.sleep(1000);

        driver.findElement(By.id("survey-email")).sendKeys("someone@somewhere.com");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector(".btn.btn-sm.btn-invisible.mr-3")).click();
        Thread.sleep(1000);


    }

    @Test(dependsOnMethods = "Help_and_support_input")
    public void link_clicking() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(document.body.scrollHeight, 0)");

        // Try a Get Started Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/get-started']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try a collaborative coding link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/codespaces']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try a collaborative GitHub Copilot
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/copilot']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        // Try a CI/CD and DevOps Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/actions']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try a Security Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/code-security']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try Client Apps Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/github-cli']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try Project Management Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/issues']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try Enterprise and Teams Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/organizations']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try Developers Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/apps']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);

        //Try Community Link
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest/communities']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/en/enterprise-cloud@latest']")).click();
        Thread.sleep(1000);




        }
    @Test(dependsOnMethods = "link_clicking" )
    public void search_bar_and_change_language() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/header/div/div[2]/div[1]/div/div/form/label/span[2]/input")).sendKeys("repositories");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='__next']/div[1]/header/div[1]/div[2]/div[1]/div/div/form/button")).click();
        Thread.sleep(2500);

        driver.findElement(By.xpath("//*[@id='__next']/div[1]/header/div[1]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.partialLinkText("Fran")).click();

        Thread.sleep(3000);

        driver.quit();

        }
}
