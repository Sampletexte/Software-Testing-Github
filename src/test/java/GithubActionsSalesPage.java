import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GithubActionsSalesPage {
    WebDriver driver;

    @BeforeClass
        // Open the page to test
    void prep() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://github.com/features/actions");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    void setupTest() throws InterruptedException {
        driver.get("http://github.com/features/actions");
    }

    @AfterClass
    void finishTestClass() throws InterruptedException {
        driver.quit();
    }

    @Test(priority = 4)
    void ContactSales() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();
        
        // Scroll Down to "Contact sales" button
        WebElement contactSalesButton = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/react-app/div/div/div/div[2]/div/div/div/section/div/div[1]/div/div/a[2]"));//driver.findElement(By.xpath("//a[@href='/contact']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contactSalesButton);

        // Click on the "Contact Sales" button
        contactSalesButton.click();

        // Input form data below
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Doe");
        driver.findElement(By.xpath("//input[@name='company']")).sendKeys("TestCompany");
        driver.findElement(By.xpath("//input[@name='job_title']")).sendKeys("Developer");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("john.doe@example.com");
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//*[@id=\"form-field-request_details\"]")).sendKeys("This is a test message.");
        Thread.sleep(1000);

        // Choose United States in country dropdown
        Select countryDropdown = new Select(driver.findElement(By.xpath("//select[@name='country']")));
        countryDropdown.selectByVisibleText("United States of America");

        // Click the contact button
        driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/react-app/div/div/div/div[2]/div[2]/div/div/div/form/div/button")).click();
    }

    @Test(priority = 3)
    void AllMainPageLinks() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();
        
        String[] buttonXpaths = {
                "//a[contains(@href, 'docs.github.com/actions')]",
                "//a[contains(@href, '/enterprise/contact?')]",
                "//a[contains(@href, 'marketplace?type=actions')]",
                "//a[contains(@href, 'docs.github.com/packages')]",
                "//a[contains(@href, 'https://docs.github.com/en/billing/managing-billing-for-your-products/managing-billing-for-github-actions/about-billing-for-github-actions#per-minute-rates')]",
                "//a[contains(@href, 'https://docs.github.com/en/billing/managing-billing-for-your-products/managing-billing-for-github-actions/about-billing-for-github-actions#included-storage-and-minutes')]"
        };

        for (String xpath : buttonXpaths) {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            Assert.assertNotNull(driver.getCurrentUrl(), "URL should not be null after clicking: " + xpath);
            driver.navigate().back(); // Navigate back to the main page
        }
    }

    @Test(priority = 2)
    void checkTopBarFeatureLinks() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();

        String[][] features = {
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[1]/a/span", "https://github.com/features/copilot"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[2]/a/span", "https://github.com/security/advanced-security"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[4]/a/span", "https://github.com/features/codespaces"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[5]/a/span", "https://github.com/features/issues"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[6]/a/span", "https://github.com/features/code-review"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[7]/a/span", "https://github.com/features/discussions"},
                {"/html/body/div[1]/div[4]/main/react-app/div/div/div/div[1]/div/nav/div/ul/li[8]/a/span", "https://github.com/features/code-search"}
        };

        for (String[] feature : features) {
            driver.findElement(By.xpath(feature[0])).click();
            Thread.sleep(3000);
            Assert.assertEquals(driver.getCurrentUrl(), feature[1], "URL mismatch for: " + feature[1]);
            driver.navigate().back(); // Navigate back to the main page
            Thread.sleep(1000);
        }
    }

    @Test(priority = 5)
    void checkProductDropdownMenu() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();

        // Open the product dropdown
        WebElement dropdown = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div/nav/ul/li[1]/button"));
        dropdown.click();

        // Wait for dropdown to appear and gather all links
        // 8 List items
        for (int i = 1; i <= 8; i++) {
            if(i != 3) {
                WebElement item = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div/nav/ul/li[1]/div/div[1]/div/ul/li[" + Integer.toString(i) + "]"));
                String linkText = item.getText().trim();
                System.out.println("Link text: " + linkText);

                item.click();
                Assert.assertNotNull(driver.getCurrentUrl(), "URL should not be null after clicking: " + item);
                System.out.println("Navigated to: " + driver.getCurrentUrl() + " via " + item);

                driver.navigate().back(); // Go back to main page
                Thread.sleep(1000);
                // Re-open dropdown before each click, as the DOM is refreshed
                dropdown = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div/nav/ul/li[1]/button"));
                dropdown.click();
            }
        }
    }

    @Test(priority = 1)
    void checkBottomBarProductLinks() throws InterruptedException {
        // This can't be @BeforeTest because it breaks the test order
        setupTest();

        String[][] bottomLinks = {
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[1]/a", "https://github.com/features"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[2]/a", "https://github.com/enterprise"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[3]/a", "https://github.com/features/copilot"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[4]/a", "https://github.com/security"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[5]/a", "https://github.com/pricing"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[6]/a", "https://github.com/team"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[7]/a", "https://resources.github.com/"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[8]/a", "https://github.com/github/roadmap"},
                {"/html/body/div[1]/footer/div[1]/div/nav[1]/ul/li[9]/a", "https://resources.github.com/devops/tools/compare/"}
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
}
