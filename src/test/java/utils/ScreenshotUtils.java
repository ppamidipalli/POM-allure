package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String saveScreenshot(String scenarioName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String sanitizedName = scenarioName.replaceAll("[^a-zA-Z0-9-_]", "_");
        Path screenshotDir = Paths.get("build", "screenshots");

        try {
            Files.createDirectories(screenshotDir);
            File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            Path destination = screenshotDir.resolve(sanitizedName + "_" + timestamp + ".png");
            FileUtils.copyFile(source, destination.toFile());
            return destination.toString();
        } catch (IOException e) {
            throw new RuntimeException("Unable to save screenshot", e);
        }
    }
}
