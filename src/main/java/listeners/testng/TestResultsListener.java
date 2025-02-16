package listeners.testng;

import driverFactory.Driver;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ScreenshotManager;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestResultsListener implements ITestListener, IExecutionListener {

    @Override
    public void onExecutionStart() {

        System.out.println("*******  Selenium Framework Started  ************");

    }

    @Override
    public void onExecutionFinish() {
        System.out.println("******* Selenium Framework Finished  ************");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // You could log test start here
        System.out.println("******* Start of Test:  " + result.getName() + "   ************");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("******* Success of Test:  " + result.getName() + "   ************");
        // sendTestResultToZephyrScale(result, "PASS");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("******* Failure of Test:  " + result.getName() + "  ************");
        System.out.println("taking screenshot ....");
        Driver driver = null;
        ThreadLocal<Driver> driverThreadLocal = null;
        Object currentClass = result.getInstance();

        // Get fields from current class and its superclass
        Class<?> testClass = result.getTestClass().getRealClass();
        Field[] fields = testClass.getDeclaredFields();

        try {
            // Access fields from the superclass
            while (testClass != null) {
               fields = testClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);  // Ensure you can access private fields
                    System.out.println("Field found: " + field.getName() + " of type: " + field.getType());

                    if (Driver.class.isAssignableFrom(field.getType())) {
                        driver = (Driver) field.get(currentClass);
                    } else if (ThreadLocal.class.isAssignableFrom(field.getType())) {
                        driverThreadLocal = (ThreadLocal<Driver>) field.get(currentClass);
                        driver = driverThreadLocal != null ? driverThreadLocal.get() : null;
                    }
                }
                // Move up to the superclass
                testClass = testClass.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            System.out.println("Unable to access field: " + e.getMessage());
        }

        if (driver != null) {
            ScreenshotManager.captureScreenshot(driver.get(), result.getName());
        } else {
            System.out.println("Driver instance is null. Unable to capture screenshot.");
        }
        // sendTestResultToZephyrScale(result, "FAIL");
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("******* Skipped of Test:  " + result.getName() + "   ************");

        //sendTestResultToZephyrScale(result, "SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {
        // Setup code if needed
    }

    @Override
    public void onFinish(ITestContext context) {
        // Finalization code after all tests complete
    }

    // Helper method to get the current date and time in the required format
    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return dateFormat.format(new Date());
    }

    private void sendTestResultToZephyrScale(ITestResult result, String status) {
        String projectKey = "HUB";
        String testCaseKey = "HUB-T1";
        String testCycleKey = "HUB-R1";

        // You should dynamically fetch or map test cases to Zephyr Scale test case keys
        String apiUrl = "https://api.zephyrscale.smartbear.com/v2/testexecutions";
        // Create test script results
        String testScriptResults = "["
                + "{"
                + "\"statusName\": \"Pass\","
                + "\"actualEndDate\": \"" + getCurrentDateTime() + "\","
                + "\"actualResult\": \"Step 1 executed successfully.\""
                + "},"
                + "{"
                + "\"statusName\": \"Pass\","
                + "\"actualEndDate\": \"" + getCurrentDateTime() + "\","
                + "\"actualResult\": \"Step 2 executed successfully.\""
                + "},"
                + "{"
                + "\"statusName\": \"Blocked\","
                + "\"actualEndDate\": \"" + getCurrentDateTime() + "\","
                + "\"actualResult\": \"Step 3 blocked due to dependency.\""
                + "}"
                + "]";


        String jsonPayload = "{"
                + "\"projectKey\": \"" + projectKey + "\","
                + "\"testCaseKey\": \"" + testCaseKey + "\","
                + "\"testCycleKey\": \"" + testCycleKey + "\","
                + "\"statusName\": \"" + status + "\","
                + "\"testScriptResults\": " + testScriptResults
                + "}";

        // Send POST request to Zephyr Scale API
        try {
            io.restassured.response.Response response = RestAssured.given()
                    .contentType("application/json")
                    .body(jsonPayload)
                    .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL3V0ZGV2MS5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI3MTIwMjA6OTFhODIzZjEtM2NlMS00MTZmLWE5NWYtZTM4ZDZlNWU5ODIwIiwidG9rZW5JZCI6ImYyMGJmMjQ1LTNhMTQtNDgxYi1hN2MyLTlkZDY0ZGI1NzgwZCJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJzdWIiOiJhYTUwNzk2Zi0wYzM5LTNlZTgtOTU1MC05ZDFlMmU2ZTYzYjciLCJleHAiOjE3NzA5MTUzOTYsImlhdCI6MTczOTM3OTM5Nn0.dL-I9G4yPK8Lfp9qfnN938mjDqE0syBkHT8SCbqFeVk")
                    .when()
                    .post(apiUrl);

            // Log the response for debugging
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody().asString());

            // Check if the status code is 200
            //   response.then().statusCode(200);  // Ensure the response status code is 200

        } catch (Exception e) {
            System.out.println("Error occurred while sending test result to Zephyr Scale: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private String getTestCaseKey(ITestResult result) {
        // Map your TestNG test method name to Zephyr Scale test case key
        return "ZC-123";  // Example static test case key, you could dynamically fetch this if needed
    }
}

