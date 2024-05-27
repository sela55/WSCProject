package TestComponents;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pojos.Q2Pojo;
import pojos.TestData;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected WebDriver driver;
    protected TestData testData;


    public WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeClass
    public void importInputsToTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File testDataFile = new File("src/main/resources/testData.json");
        testData = objectMapper.readValue(testDataFile, TestData.class);
    }

    @BeforeMethod(onlyForGroups = "browserTests")
    public void launchApplication() {
        driver = initializeDriver();
    }

    @AfterMethod(onlyForGroups = "browserTests")
    public void tearDown(){
        driver.quit();
    }

    public Q2Pojo runApiAndVerifyStatus(String url, Q2Pojo orderRequest) throws Exception {
        RestAssured.baseURI = url;
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(orderRequest);
        Response response = request.post();
        if (response.getStatusCode() != 200) {
            throw new Exception("API call failed with status code: " + response.getStatusCode());
        }
        ObjectMapper mapper = new ObjectMapper();
        Q2Pojo responseObject = mapper.readValue(response.getBody().asString(), Q2Pojo.class);

        return responseObject;
    }

}
