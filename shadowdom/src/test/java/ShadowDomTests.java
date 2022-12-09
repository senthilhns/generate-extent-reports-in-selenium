import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
!@
public class ShadowDomTests {
    private final int WAIT_FOR_ELEMENT_TIMEOUT = 30;
    private ChromeDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT));
    }

    @Test
    public void testCustomSignupForm() throws URISyntaxException {
        URL resource = ShadowDomTests.class.getResource("index.html");
        var indexPagePath = Paths.get(resource.toURI()).toString();
        driver.navigate().to(indexPagePath);

        var heading = driver.findElement(By.tagName("h2"));
        var userName = driver.findElement(By.xpath("//input[@name='username']"));
        var email = driver.findElement(By.xpath("//input[@name='email']"));
        var password = driver.findElement(By.xpath("//input[@name='password']"));
        var passwordConfirm = driver.findElement(By.xpath("//input[@name='confirm_password']"));
        var acceptTerms = driver.findElement(By.xpath("//input[@type='checkbox']"));
        var signupButton = driver.findElement(By.tagName("button"));

        Assertions.assertEquals("The Ultimate Webinar! Join us now!", heading.getText());
        Assertions.assertEquals("rgba(92, 211, 180, 1)", signupButton.getCssValue("background-color"));
        Assertions.assertEquals("Sign Up", signupButton.getText());

        userName.sendKeys("anton_angelov");
        email.sendKeys("anton_angelov@unique-solutions.com");
        password.sendKeys("super_s3cr3t");
        passwordConfirm.sendKeys("super_s3cr3t");

        acceptTerms.click();
        signupButton.click();
    }

    @Test
    public void testNewYearsEveForm() throws URISyntaxException {
        URL resource = ShadowDomTests.class.getResource("newyears-form.html");
        var indexPagePath = Paths.get(resource.toURI()).toString();
        driver.navigate().to(indexPagePath);

        var shadowHost = driver.findElement(By.tagName("unique-signup-form"));
        var shadowRoot = shadowHost.getShadowRoot();

        var heading = shadowRoot.findElement(By.cssSelector("div > h2"));
        var userName = shadowRoot.findElement(By.cssSelector("input[name='username']"));
        var email = shadowRoot.findElement(By.cssSelector("input[name='email']"));
        var password = shadowRoot.findElement(By.cssSelector("input[name='password']"));
        var passwordConfirm = shadowRoot.findElement(By.cssSelector("input[name='confirm_password']"));
        var acceptTerms = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        var signupButton = shadowRoot.findElement(By.cssSelector("button[type='submit']"));

        Assertions.assertEquals("New year's eve 2022", heading.getText());
        Assertions.assertEquals("rgba(191, 66, 245, 1)", signupButton.getCssValue("background-color"));
        Assertions.assertEquals("Register", signupButton.getText());

        userName.sendKeys("anton_angelov");
        email.sendKeys("anton_angelov@unique-solutions.com");
        password.sendKeys("super_s3cr3t");
        passwordConfirm.sendKeys("super_s3cr3t");

        acceptTerms.click();
        signupButton.click();
    }

    @Test
    public void testChristmasForm() throws URISyntaxException {
        URL resource = ShadowDomTests.class.getResource("christmas-form.html");
        var indexPagePath = Paths.get(resource.toURI()).toString();
        driver.navigate().to(indexPagePath);

        var shadowHost = driver.findElement(By.tagName("unique-signup-form"));
        var shadowRoot = shadowHost.getShadowRoot();
        // var shadowRoot = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowHost);

        var heading = shadowRoot.findElement(By.cssSelector("div > h2"));
        var userName = shadowRoot.findElement(By.cssSelector("input[name='username']"));
        var email = shadowRoot.findElement(By.cssSelector("input[name='email']"));
        var password = shadowRoot.findElement(By.cssSelector("input[name='password']"));
        var passwordConfirm = shadowRoot.findElement(By.cssSelector("input[name='confirm_password']"));
        var acceptTerms = shadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        var signupButton = shadowRoot.findElement(By.cssSelector("button[type='submit']"));

        Assertions.assertEquals("Christmas 2022", heading.getText());
        Assertions.assertEquals("rgba(245, 66, 66, 1)", signupButton.getCssValue("background-color"));
        Assertions.assertEquals("Participate", signupButton.getText());

        userName.sendKeys("anton_angelov");
        email.sendKeys("anton_angelov@unique-solutions.com");
        password.sendKeys("super_s3cr3t");
        passwordConfirm.sendKeys("super_s3cr3t");

        acceptTerms.click();
        signupButton.click();
    }

    @Test
    public void videoPlayerTest() throws URISyntaxException {
        URL resource = ShadowDomTests.class.getResource("video-player.html");
        var indexPagePath = Paths.get(resource.toURI()).toString();
        driver.navigate().to(indexPagePath);
        webDriverWait.until(d -> driver.executeScript("return document.readyState").toString().equals("complete"));

        var videoPlayer = driver.findElement(By.tagName("video"));

        var shadowRoot = videoPlayer.getShadowRoot();
        //WebElement shadowRoot = (WebElement) driver.executeScript("return arguments[0].shadowRoot", videoPlayer);


        driver.executeScript("arguments[0].play();", videoPlayer);
        driver.executeScript("arguments[0].pause();", videoPlayer);
        driver.executeScript("arguments[0].requestFullscreen();", videoPlayer);
        driver.executeScript("arguments[0].currentTime = arguments[1];", videoPlayer, 500);
        driver.executeScript("arguments[0].volume = arguments[1];", videoPlayer, 0.7);

//        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
//        var fullscreenButton = shadowContent.findElement(By.cssSelector("input[pseuse$='fullscreen-button']"));
//        fullscreenButton.click();
    }

    @Test
    public void nestedShadowRootTest() throws URISyntaxException {
        URL resource = ShadowDomTests.class.getResource("nested-shadow-root.html");
        var indexPagePath = Paths.get(resource.toURI()).toString();
        driver.navigate().to(indexPagePath);

        var shadowHost = driver.findElement(By.id("shadow_host"));
        var shadowRoot = shadowHost.getShadowRoot();

        String firstShadowRootContent = shadowRoot.findElement(By.cssSelector("#shadow_content")).getText();
        Assertions.assertEquals("Let's play with Shadow DOM!", firstShadowRootContent);

        var shadowContent = shadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
        var shadowRootTwo = shadowContent.getShadowRoot();
        String nestedText = shadowRootTwo.findElement(By.cssSelector("#nested_shadow_content > div")).getText();

        Assertions.assertEquals("Shadow DOM is awesome!", nestedText);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}