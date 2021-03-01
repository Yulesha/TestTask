package definitions;

import com.GeneralMethods;
import com.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UiDefs {

    private Page page;

    @Given("^User opens login page$")
    public void openLoginPage() {
        page = new Page(GeneralMethods.getDriver());
        page.openPage();
    }

    @When("^User types LOGIN '([^\"]*)' and PASSWORD '([^\"]*)'$")
    public void enterCreds(String login, String pass) {
        page.enterlogin(login);
        page.enterPass(pass);
    }

    @And("^User clicks login button on Login page$")
    public void clickLogin() {
        page.clickLoginButton();
    }

    @Then("^Dashboard is opened$")
    public void checkPage(){
        page.checkDashboard();
    }

    @And("^User is authorized as '([^\"]*)'$")
    public void checkAuthorization(String login) {
        page.checkAuthorization(login);
    }

    @When("^User click sort by column$")
    public void sortList(){
        page.sortByDate();
    }

    @When("^User opens list of players$")
    public void openPlayerList(){
        page.openPlayersList();
    }

    @Then("^Players list is loaded$")
    public void checkList() {
        page.checkPlayersList();
    }

    @Then("^List is sorted correct$")
    public void checkListSort(){
        page.checkSort();

    }
}
