package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCartButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    private static final By CART_BADGE_LOCATOR = By.cssSelector(".shopping_cart_badge");

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addBackpackToCart() {
        click(addBackpackToCartButton);
    }

    public String getCartCount() {
        // Wait for cart badge to be present in the DOM before trying to get its text
        waitUtils.waitForPresence(CART_BADGE_LOCATOR);
        return getText(cartBadge);
    }

    public boolean isLoaded() {
        return waitUtils.waitForTextPresent(pageTitle, "Products");
    }
}
