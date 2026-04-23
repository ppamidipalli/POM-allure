package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.open();
    }

    @When("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the inventory page should be displayed")
    public void theInventoryPageShouldBeDisplayed() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page did not load as expected");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Inventory page title mismatch");
    }

    @And("the user adds a backpack to the cart")
    public void theUserAddsABackpackToTheCart() {
        inventoryPage.addBackpackToCart();
    }

    @Then("the cart badge count should be {string}")
    public void theCartBadgeCountShouldBe(String count) {
        Assert.assertEquals(inventoryPage.getCartCount(), count, "Cart badge count mismatch");
    }

    @Then("an error message should be displayed")
    public void anErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"), "Expected login error message was not displayed");
    }
}
