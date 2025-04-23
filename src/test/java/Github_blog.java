import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Github_blog {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Navigation_to_Blog_Page() throws InterruptedException, IOException {
        //Navigate to Blog Page
        driver.get("https://github.com/");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/header/div/div[2]/div/nav/ul/li[1]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/header/div/div[2]/div/nav/ul/li[1]/div/div[2]/div/ul/li[5]/a")).click();
        Thread.sleep(1000);

        //Store all tabs, switch to new tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);
        jse.executeScript("window.scrollTo(600, 0)");

        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File desination = new File("Github_blog_screenshot.png");
        Files.copy(Screenshot.toPath(), desination.toPath());
    }

    @Test(dependsOnMethods = "Navigation_to_Blog_Page")
    public void Header_navigation_testing() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //Go to Changelong Page

        driver.findElement(By.xpath("//a[@href='https://github.blog/changelog/']")).click();
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(600, 0)");
        Thread.sleep(1000);

        //Go to docs page

        driver.findElement(By.xpath("//a[@href='https://docs.github.com/']")).click();
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(600, 0)");
        Thread.sleep(1000);

        driver.navigate().back();
        Thread.sleep(1000);

        //Go to customer stories page

        driver.findElement(By.xpath("//a[@href='https://github.com/customer-stories']")).click();

        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(600, 0)");
        Thread.sleep(1000);

        driver.navigate().back();
        Thread.sleep(1000);

        //Go to Copilot page

        driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div[1]/a[1]")).click();

        Thread.sleep(1000);


        //Collect tabs as list; switch
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));

        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(600, 0)");
        Thread.sleep(1000);

        driver.switchTo().window(tabs.get(1));
        Thread.sleep(1000);

        //Go to contact sales page

        driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div[1]/a[2]")).click();
        Thread.sleep(1000);

        //Get updated tabs as list; switch
        ArrayList<String> updated_tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(updated_tabs.get(3));

        jse.executeScript("window.scrollTo(0, 600)");
        Thread.sleep(1000);

        jse.executeScript("window.scrollTo(600, 0)");
        Thread.sleep(1000);

        driver.navigate().back();
        Thread.sleep(1000);


        driver.switchTo().window(updated_tabs.get(1));
        Thread.sleep(1000);


    }

    @Test(dependsOnMethods = "Header_navigation_testing")
    public void View_more_testing() throws InterruptedException {

        //Scroll bottom
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);

        //View More
        driver.findElement(By.xpath("//a[@href='https://github.blog/changelog/page/2/']")).click();
        Thread.sleep(1000);

        //Scroll Bottom
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(1000);

        //View more
        driver.findElement(By.xpath("//a[@href='https://github.blog/changelog/page/3/']")).click();
        Thread.sleep(1000);

        //Scroll top
        jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        Thread.sleep(1000);

    }

    @Test(dependsOnMethods = "View_more_testing")
        public void View_latest_and_popular() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='https://github.blog/']")).click();

        //Scroll
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,1000)");
        Thread.sleep(1000);

        //View All Latest
        driver.findElement(By.xpath("//a[@href='https://github.blog/latest/']")).click();
        Thread.sleep(1000);

        //Return
        driver.findElement(By.xpath("//a[@href='https://github.blog/']")).click();
        Thread.sleep(1000);

        //Scroll
        jse.executeScript("window.scrollTo(0,1250)");
        Thread.sleep(1000);

        //Sort Categories for popular
        driver.findElement(By.xpath("//*[@id='start-of-content']/div[2]/div/div[1]/section[2]/header/form/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='popular-categories']/fieldset/label[9]/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='popular-categories']/fieldset/label[5]/input")).click();
        Thread.sleep(1000);

    }

    @Test(dependsOnMethods = "View_latest_and_popular")
    public void newsletter_signup() throws InterruptedException{
        //Scroll
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(300,0)");
        Thread.sleep(1000);

        //Opt for newsletter
        driver.findElement(By.id("optincheckbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("newsletter_emailAddress")).sendKeys("someone@somewhere.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='newsletter']/div/div/div[2]/form/div[1]/button")).click();
    }

}


