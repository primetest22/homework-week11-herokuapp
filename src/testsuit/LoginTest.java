package testsuit;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        //find username element and send keys
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");
        //find password element and send keys
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        //find login button and click
        WebElement loginBtn = driver.findElement(By.className("radius"));
        loginBtn.click();

        String expectedDisplay = "Secure Area";
        String actualDisplay = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]")).getText();
        //validate expected and actual message
        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);

    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //find username element and send keys
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith1");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");

        WebElement loginBtn = driver.findElement(By.className("radius"));
        loginBtn.click();

        String expectedDisplay = "Your username is invalid!";
        String actualDisplay = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        //use substring method
        String actualSub = actualDisplay.substring(0, 25);

        Assert.assertEquals("Not Matching", expectedDisplay, actualSub);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");

        WebElement loginBtn = driver.findElement(By.className("radius"));
        loginBtn.click();

        String expectedDisplay = "Your password is invalid!";
        String actualDisplay = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        //use substring method
        String actualSub = expectedDisplay.substring(0, 25);

        Assert.assertEquals("Not Matching", expectedDisplay, actualSub);
    }


    @After
    public void tearDown() {
        // driver.quit();
    }
}
