import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import static org.junit.Assert.assertTrue;


public class TestWithIframes {
    WebDriver driver;
    WebDriverWait wait;


    final int waitTime = 1000;
    Robot robot = new Robot();

    public TestWithIframes() throws AWTException {
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

    public void iframes() throws IOException {
        driver.get("http://demo.guru99.com/test/guru99home/");
        driver.manage().window().maximize();
        robot.delay(waitTime);
        driver.findElement(By.xpath("//iframe")).click();
        robot.delay(waitTime);
        driver.switchTo().frame(0);

        moveOnScreen();
        checkVisible();

        moveOutScreen();
        checkNotVisible();
    }

    public void checkNotVisible() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'ytp-autohide')]"))!=null);
    }

    public void checkVisible() {
        Exception e = null;

        try {
            driver.findElement(By.xpath("//div[contains(@class,'ytp-autohide')]"));
        } catch (Exception ex) {
            e = ex;
        }

        assertTrue(e!=null);
    }

    private void moveOnScreen(){
        robot.mouseMove(1000, 560);
        robot.delay(waitTime);
    }
    private void moveOutScreen(){
        robot.mouseMove(900, 560);
        robot.delay(waitTime);
    }

}