package extentreport.version1;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

@ExtendWith({TestResultListener.class, ExtentTestListener.class})
public class ExtentReportTests {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;

    @BeforeAll
    public static void beforeAll() throws IOException {
        WebDriverManager.chromedriver().setup();

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\test-output\\testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");

        ExecutionContext.CURRENT_DRIVER = new ChromeDriver();
        ExecutionContext.CURRENT_DRIVER.manage().window().maximize();
    }

    @Test
    public void findElementByCompleteTextMatch() {
        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest("Find Element by Complete Text Match", "test finding element by complete text match");
        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement checkBoxDemoPage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Checkbox Demo']"));
        checkBoxDemoPage.click();

        WebElement header = ExecutionContext.CURRENT_DRIVER.findElement(By.tagName("h1"));
        Assertions.assertEquals("Checkbox Demo", header.getText());
    }

    @Test
    public void findElementByCompleteTextMatch_second() {
        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest("Find Element by Complete Text Match Second", "test finding element by complete text match");

        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement checkBoxDemoPage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Checkbox Demo']"));
        checkBoxDemoPage.click();

        WebElement header = ExecutionContext.CURRENT_DRIVER.findElement(By.tagName("h1"));
        Assertions.assertEquals("Checkbox Demo", header.getText());
    }

    @Test
    public void findElementByCompleteTextMatch_third() {
        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest("Find Element by Complete Text Match Third", "test finding element by text that FAILS");

        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement checkBoxDemoPage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Checkbox Demo']"));
        checkBoxDemoPage.click();

        WebElement header = ExecutionContext.CURRENT_DRIVER.findElement(By.tagName("h1"));
        Assertions.assertEquals("Checkbox Demo NOT CORRECT", header.getText());
    }

    @Test
    public void findElementByCompleteTextMatch_4() {
        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest("Find Element by Complete Text Match 4", "test finding element by text that FAILS 4");

        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement checkBoxDemoPage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Checkbox Demo']"));
        checkBoxDemoPage.click();

        WebElement header = ExecutionContext.CURRENT_DRIVER.findElement(By.tagName("h1"));
        Assertions.assertEquals("Checkbox Demo NOT CORRECT", header.getText());
    }

    @Test
    public void SingleInputTest() {
        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest("Single Input Test", "single input test priority 1");

        ExecutionContext.CURRENT_EXTENT_TEST.log(Status.INFO,"Starting the tests : " + ExecutionContext.CURRENT_EXTENT_TEST.getStatus());
        ExecutionContext.CURRENT_EXTENT_TEST.assignCategory("P1");

        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement simpleFormDemo = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Simple Form Demo']"));
        simpleFormDemo.click();

        WebElement messageInputBox = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//input[@id='user-message']"));
        messageInputBox.sendKeys("Hello World");

        WebElement showMessageButton = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//button[text()='Get Checked value']"));
        showMessageButton.click();

        WebElement userMessage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//label[text()='Your Message: ']//..//p"));
        String actualUserText = userMessage.getText();

        Assertions.assertEquals(actualUserText,"Hello World", "Expected and actual texts do not match.");
    }

    @Test
    public void MultipleInputTest() {
        String methodName = new Exception().getStackTrace()[0].getMethodName();

        ExecutionContext.CURRENT_EXTENT_TEST = extent.createTest(methodName, "TestCase_MultipleInputTest");

        ExecutionContext.CURRENT_EXTENT_TEST.log(Status.INFO,"Starting the tests :");
        ExecutionContext.CURRENT_EXTENT_TEST.assignCategory("P0");

        ExecutionContext.CURRENT_DRIVER.get("https://www.lambdatest.com/selenium-playground/");

        WebElement simpleFormDemo = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//a[text()='Simple Form Demo']"));
        simpleFormDemo.click();

        WebElement firstInputBox = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//input[@id='sum1']"));
        firstInputBox.sendKeys("5");

        WebElement secondInputBox = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//input[@id='sum2']"));
        secondInputBox.sendKeys("10");

        WebElement getTotalButton = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//button[text()='Get values']"));
        getTotalButton.click();

        WebElement userMessage = ExecutionContext.CURRENT_DRIVER.findElement(By.xpath("//p[@id='addmessage']"));
        String actualUserText = userMessage.getText();

        Assertions.assertEquals(actualUserText,"15", "Expected and actual values do not match.");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (ExecutionContext.CURRENT_TEST_RESULT.equals(TestResult.SUCCESS)) {
            System.out.println("Test " + testInfo.getDisplayName() + " succeeded.");

            ExecutionContext.CURRENT_EXTENT_TEST.log(Status.PASS, ExecutionContext.CURRENT_TEST_EXCEPTION);
        } else {
            System.out.println("Test " + testInfo.getDisplayName() + " failed with error: " + ExecutionContext.CURRENT_TEST_EXCEPTION.getMessage());
            ExecutionContext.CURRENT_EXTENT_TEST.log(Status.FAIL, ExecutionContext.CURRENT_TEST_EXCEPTION);
        }
    }

    @AfterAll
    public static void afterClassCore(TestInfo testInfo) {
        if (ExecutionContext.CURRENT_DRIVER != null) {
            ExecutionContext.CURRENT_DRIVER.quit();
        }

        extent.flush();
    }
}