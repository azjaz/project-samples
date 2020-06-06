package tests;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import models.PizzaModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import services.creators.PizzaCreator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class BaseMobile {
    protected PizzaModel pizzaModel = PizzaCreator.getPizzaModel();
    protected Logger logger = LogManager.getRootLogger();
    private final String deviceSerialLG = "LGH870S9c2f609";
    private final String deviceSerialSony = "BH903QJ07";
    private final String authBearerA = "Authorization: Bearer xxxxxxxxx";
    private final String authBearerN = "Authorization: Bearer xxxxxxxxx";

    @BeforeSuite(groups = "mobile.cloud")
    public void prepareDevice() {
        StringBuilder resultAfterTakingDevice = takeAvailableDevice();
        if(resultAfterTakingDevice.toString().contains(deviceSerialLG)) {
            installAppOnRemoteDevice();
        }
    }

    private StringBuilder takeAvailableDevice() {
        final String[] useDevice = {"curl.exe", "-H", "Content-Type: application/json", "-H", authBearerA, "-X", "POST", String.format("https://mobilecloud.epam.com/automation/api/device/%s",deviceSerialLG)}; //, "--insecure"
        return executeCommand(useDevice, "Result device using command: ");
    }

    private void installAppOnRemoteDevice() {
        final String[] installApp = {"curl.exe", "-v", "-H", authBearerA, "-F", "file=@C:\\Users\\Public\\pj.apk", String.format("https://mobilecloud.epam.com/automation/api/storage/install/%s",deviceSerialLG)};
        executeCommand(installApp, "Result application installation command: ");
    }

    private StringBuilder executeCommand(String[] command, String message) {
        Process process = null;
        StringBuilder curlResult = null;
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            curlResult= new StringBuilder();
            String line;
            process = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                curlResult.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
            logger.error("Mistake with curl execution");
        }finally {
            assert process != null;
            process.destroy();
            logger.info(message + curlResult);
        }
        return curlResult;
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(groups = "mobile.cloud")
    public void resetApplication() {
        AppiumDriver driver = (AppiumDriver) DriverSingleton.getDriver();
        driver.resetApp();
        logger.info("driver is reset");
    }

    @AfterClass(groups = {"mobile.slabs", "mobile.local"})
    public void killDriver() {
        DriverSingleton.closeDriver();
        logger.info("driver is killed");
    }

    @AfterSuite(groups = {"mobile.cloud"})
    public void tearDown() {
        DriverSingleton.closeDriver();
        logger.info("driver is torn down");
    }

}
