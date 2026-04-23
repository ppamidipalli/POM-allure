package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCartButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addBackpackToCart() {
        click(addBackpackToCartButton);
    }

    public String getCartCount() {
        return getText(cartBadge);
    }

    public boolean isLoaded() {
        return waitUtils.waitForTextPresent(pageTitle, "Products");
    }
}
