package extentreport.version1;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class ExecutionContext {
    public static TestResult CURRENT_TEST_RESULT = null;
    public static Throwable CURRENT_TEST_EXCEPTION = null;
    public static ExtentTest CURRENT_EXTENT_TEST = null;
    public static WebDriver CURRENT_DRIVER = null;
}
