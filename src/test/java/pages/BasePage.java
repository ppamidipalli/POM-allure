package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.WaitUtils;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    protected void type(WebElement element, String text) {
        WebElement visibleElement = waitUtils.waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected void click(WebElement element) {
        waitUtils.waitForClickability(element).click();
    }

    protected String getText(WebElement element) {
        return waitUtils.waitForVisibility(element).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        return waitUtils.waitForVisibility(element).isDisplayed();
    }
}
