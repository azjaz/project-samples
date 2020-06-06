package utils;

import com.epam.testng.utils.JIRAAttachment;
import com.google.common.io.BaseEncoding;
import drivers.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private Logger logger = LogManager.getRootLogger();
    private static final String SCREENSHOTS_NAME_FOLDER = ".//target/screenshots/screenshots/";

    public void onTestStart(ITestResult result) {
        logger.info(toUpperCaseFirstLetter(result.getName()) + " started");
    }

    public void onTestSuccess(ITestResult result) {
        logger.info(toUpperCaseFirstLetter(result.getName()) + " passed");
    }

    public void onTestFailure(ITestResult result) {
        JIRAAttachment.addAttachment(((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE), result.getName());
        logger.error(toUpperCaseFirstLetter(result.getName()) + " failed");
        takeScreenshot(DriverSingleton.getDriver(), "Screenshot ", result);
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss-");
        return ZonedDateTime.now().format(formatter);
    }

    private String toUpperCaseFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public void takeScreenshot(WebDriver driver, String messageToPortal, ITestResult result) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_FOLDER + getCurrentTimeAsString() + toUpperCaseFirstLetter(result.getName());
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            Path path = Paths.get(screenshotName + ".png");
            logger.debug("RP_MESSAGE#BASE64#{}#{}",
                    BaseEncoding.base64().encode(Files.readAllBytes(path)), messageToPortal);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
