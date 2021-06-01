import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Set;


import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HandleCookies {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String correctLogin = "1303";
    String getCorrectPass = "Guru99";

    final int waitTime = 500;
    Robot robot = new Robot();

    public HandleCookies() throws AWTException {
    }

    @BeforeClass
    public void actionBeforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void closeChrome() {
        driver.quit();
    }


    @Test

    public void comparison() throws IOException {

        driver.get("http://demo.guru99.com/Agile_Project/Agi_V1/index.php");
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(getCorrectPass);
        driver.findElement(By.name("btnLogin")).click();

        driver.navigate().to("http://demo.guru99.com/Agile_Project/Agi_V1/customer/Customerhomepage.php");
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("\n CookieName: " + cookie.getName());
            System.out.println("\n CookieValue: " + cookie.getValue());
            System.out.println("\n CookieDomain: " + cookie.getDomain());
            System.out.println("\n CookiePath: " + cookie.getPath());
            System.out.println("\n CookieExpires: " + cookie.getExpiry());

        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        element = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element.getText().contains("Log out"));


    }


}