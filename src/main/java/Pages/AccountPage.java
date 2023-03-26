package Pages;

import io.qameta.allure.Step;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class AccountPage {
    private WebDriver driver;

    public AccountPage(WebDriver driver){
        this.driver =  driver;
    }

    @Step("Account Kontrolü")
    public AccountPage accountSayfaKontrolu(String text){
        String accountName =  driver.findElement(By.cssSelector("#rightPanel > div > div > h1")).getText();
        Assert.assertEquals(accountName,text);
        return this;
    }
}
