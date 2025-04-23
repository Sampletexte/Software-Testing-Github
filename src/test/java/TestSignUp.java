import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class TestSignUp {

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
        driver.get("https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
    }

    @AfterClass
    public void close_browser() throws InterruptedException {
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void home_page() throws InterruptedException {
        driver.get("https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
    }

    @Test(priority = 1)
    public void testEmailBlankConstraints() throws InterruptedException {
        WebElement EmailInput = driver.findElement(By.xpath("//*[@id=\'email\']"));
        EmailInput.sendKeys(" ");
        WebElement PasswordInput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        PasswordInput.sendKeys(" ");
        Thread.sleep(1000);
        WebElement EmailError = driver.findElement(By.xpath("//*[contains(text(),'Email cannot be blank')]"));
        String ActualErrorText = EmailError.getText();
        String expectedErrorText = "Email cannot be blank";
        Assert.assertEquals(ActualErrorText, expectedErrorText, "The correct Error did not appear");
    }

    @Test(priority = 2)
    public void testAlreadyUsedConstraints() throws InterruptedException {
        WebElement EmailInput = driver.findElement(By.xpath("//*[@id=\'email\']"));
        EmailInput.sendKeys("Email@gmail.com");
        WebElement PasswordInput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        PasswordInput.sendKeys(" ");
        Thread.sleep(3000);
        String pageText = driver.getPageSource();
        Assert.assertTrue(pageText.contains("already associated with an account"), "Expected error text not found in page source.");
    }

    @Test(priority = 3)
    public void testPasswordNeeds() throws InterruptedException {
        WebElement PasswordInput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        PasswordInput.sendKeys("password");
        WebElement EmailInput = driver.findElement(By.xpath("//*[@id=\'email\']"));
        EmailInput.sendKeys(" ");
        Thread.sleep(2000);
        String pageText = driver.getPageSource();
        Assert.assertTrue(pageText.contains("Password needs a number"), "Expected error text not found in page source.");
        PasswordInput.sendKeys("1");
        EmailInput.sendKeys(" ");
        Thread.sleep(2000);
        String pageText2 = driver.getPageSource();
        Assert.assertTrue(pageText2.contains("Password may be compromised"), "Expected error text not found in page source.");
    }

    @Test(priority = 4)
    public void testCountryAndBox() throws InterruptedException {
        WebElement countryDropdown = driver.findElement(By.id("country-dropdown-container"));
        countryDropdown.click();
        Thread.sleep(1000);
        List<WebElement> countryOptions = driver.findElements(By.xpath("//div[@id='country-dropdown-container']//li"));
        countryOptions.get(12).click();
        Thread.sleep(1000);
        WebElement EmailP = driver.findElement(By.xpath("//*[@id=\'user_signup[marketing_consent]\']"));
        EmailP.click();
        Thread.sleep(1000);
        WebElement Continue1 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[2]/div[2]/div/div[2]/div/form/div[3]/button"));
        Continue1.click();
        Thread.sleep(1000);
        String pageText3 = driver.getPageSource();
        Assert.assertTrue(pageText3.contains("Aruba"), "Country was not changed");
    }

    @Test(priority = 5)
    public void testMovingThruSignUp() throws InterruptedException {
        WebElement EmailInput = driver.findElement(By.xpath("//*[@id=\'email\']"));
        EmailInput.sendKeys("kjfolsnbiu4730@gmail.com");
        WebElement PasswordInput = driver.findElement(By.xpath("//*[@id=\'password\']"));
        PasswordInput.sendKeys("nPIJfiusrb2385");
        Thread.sleep(1000);
        WebElement UserName = driver.findElement(By.xpath("//*[@id=\'login\']"));
        UserName.sendKeys("khbsdhlashabvaslb");
        Thread.sleep(2000);
        WebElement Continue2 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/main/div/div[2]/div[2]/div/div[2]/div/form/div[3]/button"));
        Continue2.click();
        Thread.sleep(3000);
        String pageText4 = driver.getPageSource();
        Assert.assertTrue(pageText4.contains("Verify your account"), "Sign up page did not continue");
    }
}

