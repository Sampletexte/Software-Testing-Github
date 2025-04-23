import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestRepositoryPage {

    public WebDriver chrome;

    // Path to the ChromeDriver executable on laptop
    // String driverPath = "C:\\Users\\vti3\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    // Path to the ChromeDriver executable on desktop
    // String driverPath = "D:\\OneDrive - Florida Gulf Coast University\\_Semesters\\_Spring 2025\\Software Testing\\Drivers\\chromedriver.exe";

    private String repoName;

    public String updateRepoName( String newName) {
        return newName;
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", driverPath);
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();

        chrome.get("https://github.com/");
        chrome.findElement(By.linkText("Sign in")).click();
        Thread.sleep(500);

        String username = "simplefgcuburner@gmail.com";
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

    @Test(priority = 1)
    public void TestCreateRepository() throws InterruptedException {
        chrome.get("https://github.com/");
        chrome.findElement(By.id("global-create-menu-anchor")).click();
        Thread.sleep(1000);

        chrome.findElement(By.linkText("New repository")).click();
        Thread.sleep(2000);

        // URL: https://github.com/new
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/react-app/div/form/div[4]/div[1]/div[1]/div[2]/span/button/span/span")).click();
        Thread.sleep(2000);

        WebElement textBox_repoName = chrome.findElement(By.xpath("//*[@id=':r5:']"));
        repoName = textBox_repoName.getDomAttribute("value");
        System.out.println("Repository Name:   \t" + repoName);
        repoName = updateRepoName(repoName);

        String description = "Boy howdy, I sure do love making GitHub repositories!";
        WebElement textBox_description = chrome.findElement(By.name("Description"));
        textBox_description.sendKeys(description);
        Thread.sleep(500);

        // Private repository radio button
        chrome.findElement(By.id(":rg:")).click();
        Thread.sleep(1000);

        // Public repository radio button
        chrome.findElement(By.id(":rf:")).click();
        Thread.sleep(500);

        // README checkbox
        chrome.findElement(By.xpath("//*[@id=':ri:']")).click();
        Thread.sleep(500);

        // .gitignore dropdown
        chrome.findElement(By.id(":rm:")).click();
        Thread.sleep(500);

        String filter = "python";
        WebElement textBox_gitignoreFilter = chrome.findElement(By.xpath("//*[@id='__primerPortalRoot__']/div/div/div/div[2]/div[1]/span/input"));
        textBox_gitignoreFilter.sendKeys(filter + Keys.ENTER);
        Thread.sleep(2000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // // Scroll down 200 pixels
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);

        // Click "Create repository" button
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/main/react-app/div/form/div[6]/button/span/span")).click();
        Thread.sleep(5000);
    }

    public void SetupTest() throws InterruptedException {
        repoName = updateRepoName("automatic-fortnight");
        chrome.get("https://github.com/SimpleFGCUBurner/" + repoName);
    }

    @Test(priority = 2)
    public void TestEditReadMe() throws InterruptedException, IOException {
        // Verify we're on the right page
        String expectedUrl = "https://github.com/SimpleFGCUBurner/" + repoName;
        String actualUrl = chrome.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "ERROR: Not in previously created repository.  P A N I C !");

        chrome.findElement(By.linkText("README.md")).click();
        Thread.sleep(3000);

        // Click edit button
        chrome.findElement(By.id(":R6ksptal9lab:")).click();
        Thread.sleep(1000);

        String filePath = "src/importReadme.md";
        String markdownContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Enter new text into file
        WebElement textBox_ReadMe = chrome.findElement(By.xpath("//*[@id='repo-content-pjax-container']/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/file-attachment/div/div/div[2]/div[2]"));
        textBox_ReadMe.click();
        Thread.sleep(1000);
        textBox_ReadMe.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        textBox_ReadMe.sendKeys(markdownContent);
        Thread.sleep(3000);

        // Commit changes
        chrome.findElement(By.xpath("//*[@id='repo-content-pjax-container']/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[3]/div[1]/div[2]/button/span/span")).click();
        Thread.sleep(2000);

        // Add Extended Description
        WebElement textBox_extendedDescription = chrome.findElement(By.className("prc-Textarea-TextArea-13q4j"));
        String extendDesc = "I'm not sorry.";
        textBox_extendedDescription.sendKeys(extendDesc);
        Thread.sleep(2000);

        // Commit changes (for real this time)
        chrome.findElement(By.xpath("//*[@id='__primerPortalRoot__']/div/div/div/div[3]/button[2]")).click();
        Thread.sleep(2000);

        chrome.get("https://github.com/SimpleFGCUBurner/" + repoName);
    }

    @Test(priority = 3)
    public void TestCreateIssue() throws InterruptedException, IOException {
        // Verify we're on the right page
        String expectedUrl = "https://github.com/SimpleFGCUBurner/" + repoName;
        String actualUrl = chrome.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "ERROR: Not in previously created repository.  P A N I C !");

        // Click Issues tab
        chrome.findElement(By.xpath("//*[@id='issues-tab']")).click();
        Thread.sleep(1500);

        // Click New issue
        chrome.findElement(By.xpath("//*[@id='repo-content-turbo-frame']/react-app/div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/a[3]")).click();
        Thread.sleep(2000);

        WebElement textBox_title = chrome.findElement(By.xpath("/html/body/div[1]/div[5]/div/main/turbo-frame/react-app/div/div/div/div[2]/div/div/div[1]/div[1]/div/div/div/span/input"));
        textBox_title.sendKeys("2/10 - Too much water.");
        Thread.sleep(1000);

        WebElement textBox_description = chrome.findElement(By.xpath("/html/body/div[1]/div[5]/div/main/turbo-frame/react-app/div/div/div/div[2]/div/div/div[1]/div[2]/div/div/slash-command-expander/fieldset/div[2]/div/div[1]/span/textarea"));
        textBox_description.sendKeys(
                "Pokémon Alpha Sapphire introduces needed updates and a gorgeous " +
                "new view of Hoenn, but a clearer view reveals its dated holdovers." +
                "\n\nThis meme issue brought to you by IGN's review of Pokemon Omega Ruby and Alpha Sapphire." +
                "\n\nLink: https://www.ign.com/articles/2014/11/18/pokemon-alpha-sapphire-and-omega-ruby-review#:~:text=Review%20scoring-,Good,-Pok%C3%A9mon%20Alpha%20Sapphire");
        Thread.sleep(2000);

        // Click "Assign yourself"
        chrome.findElement(By.xpath("/html/body/div[1]/div[5]/div/main/turbo-frame/react-app/div/div/div/div[2]/div/div/div[2]/div/div[1]/div/span/button/span/span")).click();
        Thread.sleep(400);

        // Click "Labels"
        WebElement labels = chrome.findElement(By.xpath("//*[@id='repo-content-turbo-frame']/react-app/div/div/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]/button"));
        labels.click();
        Thread.sleep(1000);

        // Check "bug" and ""
        chrome.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/div[2]/div[2]/ul/li[1]")).click();
        Thread.sleep(1000);
        chrome.findElement(By.xpath("/html/body/div[4]/div[3]/div/div/div[2]/div[2]/ul/li[6]")).click();
        Thread.sleep(1000);

        // Click "labels" to close menu
        textBox_description.click();
        Thread.sleep(1000);

        // Send Ctrl + Enter to create the issue
        textBox_description.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        Thread.sleep(5000);

        chrome.get("https://github.com/SimpleFGCUBurner/" + repoName);
    }

    @Test(priority = 4)
    public void TestDeleteRepository () throws InterruptedException, IOException {
        // Verify we're on the right page
        String expectedUrl = "https://github.com/SimpleFGCUBurner/" + repoName;
        String actualUrl = chrome.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "ERROR: Not in previously created repository.  P A N I C !");

        // Click Pull requests tab
        chrome.findElement(By.partialLinkText("Settings")).click();
        Thread.sleep(1500);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // // Scroll down 3300 pixels
        js.executeScript("window.scrollBy(0,3300)", "");
        Thread.sleep(2000);

        // Click "Delete this repository" button
        chrome.findElement(By.id("dialog-show-repo-delete-menu-dialog")).click();
        Thread.sleep(1000);

        // Click "I want to delete this repository" button
        chrome.findElement(By.id("repo-delete-proceed-button")).click();
        Thread.sleep(1000);

        // Click "I have read and understand these effects" button
        chrome.findElement(By.id("repo-delete-proceed-button")).click();
        Thread.sleep(1000);

        // Enter verification text
        WebElement textBox_verification = chrome.findElement(By.xpath("//*[@id='verification_field']"));
        textBox_verification.sendKeys("SimpleFGCUBurner/" + repoName);
        Thread.sleep(1000);

        // Click "Delete this repository" button
        chrome.findElement(By.id("repo-delete-proceed-button")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void TestCreatePullRequest() throws InterruptedException, IOException {
        // Navigate to vigilant-chainsaw repository
        chrome.get("https://www.github.com/SimpleFGCUBurner/vigilant-chainsaw");
        Thread.sleep(2000);

        // Click Pull requests tab
        chrome.findElement(By.partialLinkText("Pull requests")).click();
        Thread.sleep(1500);

        // Click New pull request
        chrome.findElement(By.partialLinkText("New pull request")).click();
        Thread.sleep(2000);

        // Click branch name under Example comparisons
        chrome.findElement(By.partialLinkText("other-branch")).click();
        // chrome.findElement(By.partialLinkText("other-branch")).click();

        Thread.sleep(2000);

        // Scroll down the page
        JavascriptExecutor js = (JavascriptExecutor) chrome;

        // // Scroll down 350 pixels
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(4000);

        // Click Create pull request button
        chrome.findElement(By.cssSelector("#repo-content-pjax-container > div > div.js-details-container.Details.js-compare-pr > div > button")).click();
        Thread.sleep(2000);

        // Input title and description
        WebElement txtBx_pullTitle = chrome.findElement(By.xpath("//*[@id='pull_request_title']"));
        txtBx_pullTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtBx_pullTitle.sendKeys("Puullllll me uuunnnnnddeeerrr");
        Thread.sleep(2000);

        WebElement txtBx_pullDesc = chrome.findElement(By.xpath("//*[@id='pull_request_body']"));
        txtBx_pullDesc.sendKeys(
                "Pull me under, pull me under\n" +
                "Pull me under, I'm not afraid\n" +
                "All that I feel is honor and spite\n" +
                "All I can do is to set it right" +
                "\n\n...Y'all ever heard of Dream Theater? (plz say yes)");
        Thread.sleep(2500);

        // // Scroll down 200 pixels
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(1000);

        // Click "Create pull request" button
        chrome.findElement(By.xpath("//*[@id='new_pull_request']/div/div[1]/div/div[2]/div/div[3]/div[3]/div/div/button")).click();
        Thread.sleep(2000);

        // Click "Commits" tab
        chrome.findElement(By.partialLinkText("Commits")).click();
        Thread.sleep(2000);

        // Click "Conversation" tab
        chrome.findElement(By.partialLinkText("Conversation")).click();
        Thread.sleep(2000);
        
        chrome.findElement(By.xpath("//*[@id='partial-discussion-sidebar']/div[2]/form/span/button")).click();
        Thread.sleep(2000);

        // // Scroll down 350 pixels
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(4000);

        // Click button to make merge
        chrome.findElement(By.cssSelector("#discussion_bucket > div > div.Layout-main > div > div.discussion-timeline-actions > div.merge-pr.Details.is-merging > react-partial > div > div > div > div.border.rounded-2.borderColor-success-emphasis > div > div > div > div:nth-child(1) > button")).click();
        Thread.sleep(3000);

        // // Scroll down 200 pixels
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        // Click "Confirm merge" button
        chrome.findElement(By.className("prc-Button-ButtonBase-c50BI")).click();
        Thread.sleep(4000);
    }

    @AfterClass
    public void tearDown() {
        chrome.quit();
    }

}
