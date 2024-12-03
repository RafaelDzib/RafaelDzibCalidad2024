package com.fca.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class GatitosTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testUntitledTestCase() throws Exception {
	  
  /*  driver.get("https://www.google.com/search?q=j9askjfpoa&rlz=1C1CHZN_esMX934MX935&oq=j9askjfpoa&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzQzNmowajeoAgiwAgE&sourceid=chrome&ie=UTF-8");
    driver.get(baseUrl + "chrome://newtab/");
    driver.get("https://www.google.com/search?q=j9askjfpoa&rlz=1C1CHZN_esMX934MX935&oq=j9askjfpoa&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzQzNmowajeoAgiwAgE&sourceid=chrome&ie=UTF-8");
    driver.get("https://www.google.com/search?q=monas+chinas&rlz=1C1CHZN_esMX934MX935&oq=monas+chinas&gs_lcrp=EgZjaHJvbWUyDAgAEEUYORixAxiABDIHCAEQABiABDIHCAIQABiABDIHCAMQABiABDIHCAQQABiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABNIBCDE4MDZqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8");
    driver.close();
    //ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.close();
    driver.get(baseUrl + "");*/
    //driver.findElement(By.xpath("//div[@id='rso']/div/div/div/div/div/div/div/div/div/div/span/a/div/div/div/div[2]/cite")).click();

	driver.get("https://es.wikipedia.org/wiki/Chihuahua_(perro)");
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Chihuahua[\\s\\S]$"));
    //ERROR: Caught exception [unknown command [browse]]
    //ERROR: Caught exception [unknown command []]
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
