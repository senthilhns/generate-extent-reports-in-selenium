package extentreport.version1;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestResultListener implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) {
            ExecutionContext.CURRENT_TEST_RESULT = TestResult.FAILURE;
            ExecutionContext.CURRENT_TEST_EXCEPTION = extensionContext.getExecutionException().get();
        } else {
            ExecutionContext.CURRENT_TEST_RESULT = TestResult.SUCCESS;
        }
    }
}