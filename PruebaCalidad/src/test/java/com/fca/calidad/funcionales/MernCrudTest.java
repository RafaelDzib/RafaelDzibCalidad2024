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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

public class MernCrudTest {
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
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testPractica() throws Exception {
    driver.get("https://mern-crud-mpfr.onrender.com/");
    

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/button")));
    addButton.click();
    
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Rafael De Jesus");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("dzibrafael45@gmail.com");
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("21");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();

    
    WebElement submitButton = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"));
    submitButton.click();
    WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i")));
    closeModalButton.click();
    WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[4]")));
    row.click();
    row.click(); 


    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Rafita Dzib");
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("22");
    
    
    submitButton = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"));
    submitButton.click();
    closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i")));
    closeModalButton.click();
    WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")));
    deleteButton.click();
    WebElement confirmDeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Yes')]")));
    confirmDeleteButton.click();
    WebElement backButton = driver.findElement(By.xpath("//div[@id='root']/div/div[2]"));
    backButton.click();
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