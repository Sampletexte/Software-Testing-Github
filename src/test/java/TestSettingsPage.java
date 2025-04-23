import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSettingsPage {

    public WebDriver chrome;

    // Path to the ChromeDriver executable on laptop
    // String driverPath = "C:\\Users\\vti3\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    // Path to the ChromeDriver executable on desktop
    // String driverPath = "D:\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    @BeforeClass
    public void setUp() throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", driverPath);
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();

        chrome.get("https://github.com/");
        chrome.findElement(By.linkText("Sign in")).click();
        Thread.sleep(500);

        String username = "slick-cherry";
        String password = "FloridaGulf5757";

        WebElement textBox_username = chrome.findElement(By.id("login_field"));
        textBox_username.sendKeys(username);
        Thread.sleep(500);

        WebElement textBox_password = chrome.findElement(By.id("password"));
        textBox_password.sendKeys(password);
        Thread.sleep(500);

        chrome.findElement(By.className("js-sign-in-button")).click();
        Thread.sleep(500);
    }

    public void SetUpTest() throws InterruptedException {
        chrome.get("https://www.github.com/settings/profile");
    }

    @Test(priority = 1)
    public void TestOpenSettingsPage() throws InterruptedException {
        chrome.findElement(By.cssSelector("body > div.logged-in.env-production.page-responsive.full-width > div.position-relative.header-wrapper.js-header-wrapper > header > div > div.AppHeader-globalBar-end > div.AppHeader-user > deferred-side-panel > include-fragment > react-partial-anchor > button")).click();
        Thread.sleep(500);

        // Click on the "Settings" link
        chrome.findElement(By.partialLinkText("Settings")).click();
        Thread.sleep(1000);

        // Click on the "Account" tab
        chrome.findElement(By.partialLinkText("Account")).click();
        Thread.sleep(1000);

        // Click on the "Appearance" tab
        chrome.findElement(By.partialLinkText("Appearance")).click();
        Thread.sleep(1000);

        // Click on the "Accessibility" tab
        chrome.findElement(By.partialLinkText("Accessibility")).click();
        Thread.sleep(1000);

        // Click on the "Notifications" tab
        chrome.findElement(By.partialLinkText("Notifications")).click();
        Thread.sleep(1000);

        // Click on the "Public profile" tab
        chrome.findElement(By.partialLinkText("Public profile")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 2)
    public void TestEditPublicProfile() throws InterruptedException {
        // TODO: Remove this in actual test
        SetUpTest();

        // Add name to the public profile
        WebElement nameField = chrome.findElement(By.id("user_profile_name"));
        nameField.clear();
        nameField.sendKeys("Alexander Ovechkin");
        Thread.sleep(1000);

        // Add bio to the public profile
        WebElement bioField = chrome.findElement(By.id("user_profile_bio"));
        bioField.clear();
        bioField.sendKeys("Professional hockey player and captain of the Washington Capitals.\nJust broke Wayne Gretzky's record for most goals in NHL history.");
        Thread.sleep(1000);

        // Choose pronouns
        WebElement dropdown_pronouns = chrome.findElement(By.id("user_profile_pronouns_select"));
        dropdown_pronouns.click();
        Thread.sleep(500);
        Select ddSelect = new Select(dropdown_pronouns);
        ddSelect.selectByVisibleText("he/him");
        Thread.sleep(1000);
        dropdown_pronouns.click();
        Thread.sleep(500);
        
        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // // Scroll down 600 pixels
        js.executeScript("window.scrollBy(0,600)", "");
        Thread.sleep(2000);

        // Click on the "Update profile" button
        chrome.findElement(By.xpath("//*[@id=\"edit_user_206245241\"]/div/p[2]/button")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 3)
    public void TestChangeTheme() throws InterruptedException {
        // Navigate to "Appearance" settings page
        SetUpTest();
        chrome.findElement(By.partialLinkText("Appearance")).click();
        Thread.sleep(1000);

        // Change theme mode to "Single theme"
        WebElement dropdown_theme = chrome.findElement(By.id("color_mode_type_select"));
        dropdown_theme.click();
        Thread.sleep(500);
        Select ddSelect = new Select(dropdown_theme);
        ddSelect.selectByVisibleText("Single theme");
        Thread.sleep(1000);
        dropdown_theme.click();
        Thread.sleep(500);

        // Select "Light default" theme
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/section[1]/appearance-form/div[3]/form/div/div[1]")).click();
        Thread.sleep(500);

        // Select "Light high contrast" theme
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/section[1]/appearance-form/div[3]/form/div/div[2]")).click();
        Thread.sleep(500);

        // Select "Dark default" theme
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/section[1]/appearance-form/div[3]/form/div/div[5]")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void TestDisableNotifications() throws InterruptedException {
        // Navigate to "Notifications" settings page
        SetUpTest();
        chrome.findElement(By.partialLinkText("Notifications")).click();
        Thread.sleep(1000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // Disable "Automatically watch teams" option
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/react-app/div/div[3]/div[2]/div[2]/button")).click();
        Thread.sleep(1000);

        // Disable "Subscriptions" notifications
        // // Disable "Watching" notifications
        chrome.findElement(By.xpath("//*[@id=\":RjbH2:\"]")).click();
        Thread.sleep(1000);
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div/div[2]/div/div/turbo-frame/react-app/div/div[4]/div/div[2]/div/div/dialog/form/div[2]/ul/li[1]")).click();
        Thread.sleep(500);
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div/div[2]/div/div/turbo-frame/react-app/div/div[4]/div/div[2]/div/div/dialog/form/div[2]/ul/li[2]")).click();
        Thread.sleep(500);
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/react-app/div/div[4]/div/div[2]/div/div/dialog/form/div[3]/div[2]/button[2]")).click();
        Thread.sleep(1000);

        // // Scroll down 400 pixels
        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

        // // Disable "Participating, @mentions and custom" notifications
        chrome.findElement(By.xpath("//*[@id=\":RrbH2:\"]")).click();
        Thread.sleep(1000);
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div/div[2]/div/div/turbo-frame/react-app/div/div[4]/div/div[3]/div/div/dialog/form/div[2]/ul/li[1]")).click();
        Thread.sleep(500);
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div/div[2]/div/div/turbo-frame/react-app/div/div[4]/div/div[3]/div/div/dialog/form/div[2]/ul/li[2]")).click();
        Thread.sleep(500);
        chrome.findElement(By.xpath("//*[@id=\"settings-frame\"]/react-app/div/div[4]/div/div[3]/div/div/dialog/form/div[3]/div[2]/button[2]")).click();
        Thread.sleep(1000);

        // // Scroll down 600 pixels
        js.executeScript("window.scrollBy(0,600)", "");
        Thread.sleep(1000);

        // Disable "In-product messages" notifications
        chrome.findElement(By.xpath("//*[@id=\":R1kbH2:\"]/button")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void TestManageEmailSettings() throws InterruptedException {
        // Navigate to "Emails" settings page
        SetUpTest();
        chrome.findElement(By.partialLinkText("Emails")).click();
        Thread.sleep(1000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // Disable "Keep my email address private" option
        chrome.findElement(By.id("toggle_visibility")).click();
        Thread.sleep(2000);

        // // Scroll down 400 pixels
        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

        // Manage "Backup email address" settings
        WebElement select_backupEmail = chrome.findElement(By.id("backup_email_select"));
        select_backupEmail.click();
        Thread.sleep(500);
        Select ddSelect = new Select(select_backupEmail);
        ddSelect.selectByVisibleText("Only allow primary email");
        Thread.sleep(1000);
        select_backupEmail.click();
        Thread.sleep(500);
    }

    @Test(priority = 6)
    public void TestAddSuccessor() throws InterruptedException {
        // Navigate to "Account" settings page
        SetUpTest();
        chrome.findElement(By.partialLinkText("Account")).click();
        Thread.sleep(1000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // // Scroll down 400 pixels
        js.executeScript("window.scrollBy(0,400)", "");
        Thread.sleep(1000);

        // Add a successor
        WebElement textBox_successor = chrome.findElement(By.id("search-user"));
        textBox_successor.sendKeys("Fade2BlackOps");
        Thread.sleep(1000);

        // Click on search result
        chrome.findElement(By.className("typeahead-result")).click();
        Thread.sleep(1000);

        // Click on the "Add successor" button
        chrome.findElement(By.className("js-add-new-user")).click();
        Thread.sleep(1000);

        // // Scroll down 600 pixels (show successor is added)
        js.executeScript("window.scrollBy(0,600)", "");
        Thread.sleep(2000);
    }


    @AfterClass
    public void tearDown() {
        chrome.quit();
    }

}
