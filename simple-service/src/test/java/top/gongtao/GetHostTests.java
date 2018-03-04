package top.gongtao;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.BDDAssertions.then;

public class GetHostTests {

    private static FirefoxDriver browser;

    @BeforeClass
    public static void openBrowser(){
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        browser.quit();
    }

    @Test
    public void getHostAndPw(){
        String baseUrl = "https://cloud.tencent.com/developer/labs/lab/10004";

        browser.get(baseUrl);

        browser.findElementByClassName("lab-btn large J-start-btn").click();
    }


}
