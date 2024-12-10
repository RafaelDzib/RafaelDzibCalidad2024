package com.fca.calidad.funcionales;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class MernCrudTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://mern-crud-mpfr.onrender.com/");
    }

    @Test
    public void testCrearUsuario() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/button")));
        addButton.click();
        driver.findElement(By.name("name")).sendKeys("Rafael De Jesus");
        driver.findElement(By.name("email")).sendKeys("dzibrafael45@gmail.com");
        driver.findElement(By.name("age")).sendKeys("21");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
        WebElement submitButton = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"));
        submitButton.click();
        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i")));
        closeModalButton.click();
        boolean isUserCreated = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Rafael De Jesus')]"))).isDisplayed();
        assertTrue("No se agrego", isUserCreated);
    }

    @Test
    public void testActualizarUsuario() {
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")));
        editButton.click();
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Rafita Dzib");
        WebElement ageField = driver.findElement(By.name("age"));
        ageField.clear();
        ageField.sendKeys("22");
        WebElement submitButton = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]"));
        submitButton.click();
        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i")));
        closeModalButton.click();
        boolean isUserUpdated = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Rafita Dzib')]"))).isDisplayed();
        assertTrue("No se actualizo", isUserUpdated);
    }

    @Test
    public void testBorrarUsuario() {
        
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")));
        deleteButton.click();
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']")));
        confirmDeleteButton.click();
        boolean isUserDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[contains(text(),'Rafita Dzib')]")));
        assertTrue("No se elimino", isUserDeleted);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}