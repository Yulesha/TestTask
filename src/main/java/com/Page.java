package com;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final WebDriver driver;

    public Page(WebDriver driver) {
       this.driver = driver;
    }

    public void openPage(String uiUrl) {
        driver.get(uiUrl + "/admin/login");
    }

    public void enterLogin(String login) {
        driver.findElement(By.id("UserLogin_username")).sendKeys(login);
    }

    public void enterPass(String pass) {
        driver.findElement(By.id("UserLogin_password")).sendKeys(pass);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//input[@name='yt0']")).click();
    }

    public boolean isDashboardUrlCorrect(String uiUrl) {
        return driver.getCurrentUrl().equals(uiUrl + "/configurator/dashboard/index");
    }

    public boolean isDashboardDisplayed() {
        return driver.findElement(By.cssSelector(".page")).isDisplayed();
    }

    public boolean isAuthorizedNameCorrect(String login) {
        return driver.findElement(By.xpath("//*[@class='dropdown text-normal nav-profile']//span")).getText().equals(login);
    }

    public void openPlayersList() {
        driver.findElement(By.xpath("//div[@class='page']//a[@href='/user/player/admin']")).click();
    }

    public boolean isPlayersListUrlCorrect(String uiUrl) {
        return driver.getCurrentUrl().equals(uiUrl + "/user/player/admin");

    }

    public boolean isPlayersListDisplayed() {
        return driver.findElement(By.id("payment-system-transaction-grid")).isDisplayed();
    }

    public void sortByDate() {
        driver.findElement(By.cssSelector("#payment-system-transaction-grid_c9 > a")).click();
        String oldFirstDate = getFirstDate();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.not(ExpectedConditions.textToBe(By.xpath("//tbody//tr[1]/td[10]"),oldFirstDate))
        );

    }

    public boolean isListSortedCorrect() {
        ArrayList<String> dates = getDates();
        return Ordering.natural().isOrdered(dates);
    }

    private ArrayList<String> getDates(){
            ArrayList<String> dates = new ArrayList<String>();
            List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
            for (int i=0; i<rows.size()-1; i++) {
                dates.add(rows.get(i).findElement(By.xpath("./td[10]")).getText());
            }
            return dates;
    }

    private String getFirstDate(){
        return driver.findElement(By.xpath("//tbody//tr[1]/td[10]")).getText();
    }

}
