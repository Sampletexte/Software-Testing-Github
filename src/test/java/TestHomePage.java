import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestHomePage {

    // Path to the ChromeDriver executable on laptop
    String driverPath = "C:\\Users\\vti3\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    // Path to the ChromeDriver executable on desktop
    // String driverPath = "D:\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    public WebDriver driver;

    @BeforeClass
    public void open_browser() throws InterruptedException {
        System.setProperty("WebDriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com/");
    }

    @AfterClass
    public void close_browser() throws InterruptedException {
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void home_page() throws InterruptedException {
        driver.get("https://github.com/");
    }

    @Test (priority = 1)
    public void testSignUpOnHome() throws InterruptedException {
        WebElement SignUp = driver.findElement(By.xpath("//*[@id=\'hero_user_email\']"));
        SignUp.sendKeys("email@gmail.com");
        Thread.sleep(2000);
        WebElement SignUpButton = driver.findElement(By.xpath("//*[@id=\'FormControl--:Rjahb:\']/div/button/span/span"));
        SignUpButton.click();
        Thread.sleep(2000);
        String expectedURL = "https://github.com/signup?source=form-home-signup&user_email=email%40gmail.com";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "User is not on the correct page.");
    }

    @Test (priority = 2)
    public void testTabsOnHome() throws InterruptedException {
        WebElement HomeTab = driver.findElement(By.xpath("//*[@id=\'hero\']/div[2]/div/div[1]/div/button[1]"));
        HomeTab.click();
        Thread.sleep(2000);
        WebElement PlanTab = driver.findElement(By.xpath("//*[@id=\'hero\']/div[2]/div/div[1]/div/button[2]"));
        PlanTab.click();
        Thread.sleep(2000);
        WebElement CollaborateTab = driver.findElement(By.xpath("//*[@id=\'hero\']/div[2]/div/div[1]/div/button[3]"));
        CollaborateTab.click();
        Thread.sleep(2000);
        WebElement AutomateTab = driver.findElement(By.xpath("//*[@id=\'hero\']/div[2]/div/div[1]/div/button[4]"));
        AutomateTab.click();
        Thread.sleep(2000);
        WebElement SecureTab = driver.findElement(By.xpath("//*[@id=\'hero\']/div[2]/div/div[1]/div/button[5]"));
        SecureTab.click();
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    public void testDiscoverOnHomePage() throws InterruptedException {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scroll(0,3550)", "");
        Thread.sleep(2000);
        WebElement Discover2 = driver.findElement(By.xpath("//*[@id=\'automation\']/div/div[3]/div/div/div[2]/dl/div[2]/dt/button/div"));
        Discover2.click();
        Thread.sleep(1000);
        WebElement Discover3 = driver.findElement(By.xpath("//*[@id=\'automation\']/div/div[3]/div/div/div[2]/dl/div[3]/dt/button/div"));
        Discover3.click();
        Thread.sleep(1000);
        WebElement Discover4 = driver.findElement(By.xpath("//*[@id=\'automation\']/div/div[3]/div/div/div[2]/dl/div[4]/dt/button/div"));
        Discover4.click();
        Thread.sleep(1000);
        WebElement MarketLink = driver.findElement(By.xpath("//*[@id=\':Rbib:_3\']/div/a/span/span"));
        MarketLink.click();
        String expectedURL = "https://github.com/marketplace";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "User is not on the correct page.");
        Thread.sleep(1000);
    }

    @Test (priority = 4)
    public void testCustomerStory() throws InterruptedException {
        WebElement ByIndustryOnHome = driver.findElement(By.xpath("//*[@id=\'customer-stories\']/div[1]/div[2]/div/div[1]/div/button[1]/span"));
        ByIndustryOnHome.click();
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scroll(0,8350)", "");
        Thread.sleep(2000);
        WebElement BySizeOnHome = driver.findElement(By.xpath("//*[@id=\'customer-stories\']/div[1]/div[2]/div/div[1]/div/button[2]/span"));
        BySizeOnHome.click();
        Thread.sleep(2000);
        WebElement CustomerStory = driver.findElement(By.xpath("//*[@id=\'customer-stories\']/div[1]/div[4]/div/div/div[2]/a/span/span/div[2]/div[2]/div/span"));
        CustomerStory.click();
        String expectedURL = "https://github.com/customer-stories/philips";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "User is not on the correct page.");
    }

    @Test (priority = 5)
    public void testDoNotShare() throws InterruptedException {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        exe.executeScript("window.scroll(0,20000)", "");
        Thread.sleep(2000);
        WebElement DoNotShare = driver.findElement(By.xpath("/html/body/div[1]/footer/div[2]/div/nav[1]/ul/li[7]/cookie-consent-link/button"));
        DoNotShare.click();
        WebElement Reject1 = driver.findElement(By.xpath("//*[@id=\'wcpCookiePreferenceCtrl\']/div[2]/div/form/dl/dt[2]/div/div/div[2]/label"));
        Reject1.click();
        Thread.sleep(2000);
        WebElement Reject2 = driver.findElement(By.xpath("//*[@id=\'wcpCookiePreferenceCtrl\']/div[2]/div/form/dl/dt[3]/div/div/div[2]/label"));
        Reject2.click();
        Thread.sleep(2000);
        WebElement Reject3 = driver.findElement(By.xpath("//*[@id=\'wcpCookiePreferenceCtrl\']/div[2]/div/form/dl/dt[4]/div/div/div[2]/label"));
        Reject3.click();
        Thread.sleep(2000);
        WebElement SaveChanges = driver.findElement(By.xpath("//*[@id=\'wcpCookiePreferenceCtrl\']/div[2]/div/div[2]/button[1]"));
        SaveChanges.click();
        Thread.sleep(2000);
    }
}
