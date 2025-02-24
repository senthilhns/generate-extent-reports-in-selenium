package extentreport.version0;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.UUID;

import java.io.IOException;

public class ExtentReportTests {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static WebDriver driver;
    private static ExtentTest extentTest;


@BeforeAll
public static void beforeAll() throws IOException {
    WebDriverManager.chromedriver().setup();
    extent = new ExtentReports();
    sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
    extent.attachReporter(sparkReporter);
    sparkReporter.config().setOfflineMode(true);
    sparkReporter.config().setDocumentTitle("Simple Automation Report");
    sparkReporter.config().setReportName("Test Report");
    sparkReporter.config().setTheme(Theme.STANDARD);
    sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    sparkReporter.config().setEncoding("UTF-8");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--user-data-dir=/tmp/chrome-profile-" + UUID.randomUUID());
    options.addArguments("--headless=new");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
}


/*
    @BeforeAll
    public static void beforeAll() throws IOException {
        WebDriverManager.chromedriver().setup();
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\test-output\\testReport.html");
        extent.attachReporter(sparkReporter);

        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
*/

//    @BeforeEach
//    public void setUp()  {
//
//    }

    @Test
    public void findElementByCompleteTextMatch() {
        extentTest = extent.createTest("Find Element by Complete Text Match", "test finding element by complete text match");

        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement checkBoxDemoPage = driver.findElement(By.xpath("//a[text()='Checkbox Demo']"));
            checkBoxDemoPage.click();

            WebElement header = driver.findElement(By.tagName("h1"));
            Assertions.assertEquals("Checkbox Demo", header.getText());

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
            throw ex;
        }
    }

    @Test
    public void findElementByCompleteTextMatch_second() {
        extentTest = extent.createTest("Find Element by Complete Text SECOND", "test finding element by complete text  SECOND");
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement checkBoxDemoPage = driver.findElement(By.xpath("//a[text()='Checkbox Demo']"));
            checkBoxDemoPage.click();

            WebElement header = driver.findElement(By.tagName("h1"));
            Assertions.assertEquals("Checkbox Demo", header.getText());

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
        }
    }

    @Test
    public void findElementByCompleteTextMatch_third() {
        extentTest = extent.createTest("Find Element by Complete Text THIRD", "test finding element by complete text  THIRD");
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement checkBoxDemoPage = driver.findElement(By.xpath("//a[text()='Checkbox Demo']"));
            checkBoxDemoPage.click();

            WebElement header = driver.findElement(By.tagName("h1"));
            Assertions.assertEquals("Checkbox Demo", header.getText());

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
        }
    }

    @Test
    public void findElementByCompleteTextMatch_4() {
        extentTest = extent.createTest("Find Element by Complete Text 4th", "test finding element by complete text  4th");
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement checkBoxDemoPage = driver.findElement(By.xpath("//a[text()='Checkbox Demo']"));
            checkBoxDemoPage.click();

            WebElement header = driver.findElement(By.tagName("h1"));
            Assertions.assertEquals("Checkbox Demo", header.getText());

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
        }
    }

    @Test
    public void SingleInputTest() {
        extentTest = extent.createTest("Single Input Test", "single input test priority 1");

        extentTest.log(Status.INFO,"Starting the tests : " + extentTest.getStatus());
        extentTest.assignCategory("P1");
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement simpleFormDemo = driver.findElement(By.xpath("//a[text()='Simple Form Demo']"));
            simpleFormDemo.click();

            WebElement messageInputBox = driver.findElement(By.xpath("//input[@id='user-message']"));
            messageInputBox.sendKeys("Hello World");

            WebElement showMessageButton = driver.findElement(By.xpath("//button[text()='Get Checked value']"));
            showMessageButton.click();

            WebElement userMessage = driver.findElement(By.xpath("//label[text()='Your Message: ']//..//p"));
            String actualUserText = userMessage.getText();

            Assertions.assertEquals(actualUserText,"Hello World", "Expected and actual texts do not match.");

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
        }
    }

    @Test
    public void MultipleInputTest() {
        String methodName = new Exception().getStackTrace()[0].getMethodName();

        extentTest = extent.createTest(methodName, "TestCase_MultipleInputTest");

        extentTest.log(Status.INFO,"Starting the tests :");
        extentTest.assignCategory("P0");

        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");

            WebElement simpleFormDemo = driver.findElement(By.xpath("//a[text()='Simple Form Demo']"));
            simpleFormDemo.click();

            WebElement messageInputBox = driver.findElement(By.xpath("//input[@id='user-message']"));
            messageInputBox.sendKeys("Hello World");

            WebElement showMessageButton = driver.findElement(By.xpath("//button[text()='Get Checked value']"));
            showMessageButton.click();

            WebElement userMessage = driver.findElement(By.xpath("//label[text()='Your Message: ']//..//p"));
            String actualUserText = userMessage.getText();

            Assertions.assertEquals(actualUserText,"Hello World", "Expected and actual texts do not match.");

            extentTest.log(Status.PASS, "first test passed");
        } catch (Exception ex) {
            extentTest.log(Status.FAIL, ex);
        }
    }

    @AfterAll
    public static void afterClassCore(TestInfo testInfo) {
        if (driver != null) {
            driver.quit();
        }

        extent.flush();
    }
}