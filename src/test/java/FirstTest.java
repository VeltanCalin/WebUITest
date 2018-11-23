import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private WebDriver driver;

    //Declare a test URL variable
    private String testURL = "http://ec2-34-251-162-89.eu-west-1.compute.amazonaws.com/";

    //Declare a Webdriver variable
    private WebDriverWait wait;

    String fullname;
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){

        //Set the webdriver path
        System.setProperty("webdriver.chrome.driver","C:\\Users\\calin.veltan\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //Create a new ChromeDriver
        driver = new ChromeDriver();


        //Navigate to AUT URL
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {
        //Get page title
        String title = driver.getTitle();

        //Print page's title
        System.out.println("Page Title: " + title);

        //Assertion
        Assert.assertEquals(title, "Have fun testing", "Title assertion is failed!");
    }

    @Test
    public void secondTest () {


        //Filling mandatory fields
        WebElement name =  driver.findElement(By.id("fullName"));
        name.sendKeys("Nixon");

        //Copied input to a variable for assertion
        String copiedtext = driver.findElement(By.xpath("//*[@id=\"fullName\"]")).getAttribute("value");


        driver.findElement(By.id("country")).sendKeys("SUA");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/input")).sendKeys("09031988");
        driver.findElement(By.id("position")).sendKeys("president");
        driver.findElement(By.id("url")).sendKeys("http://www.fakenews.com");
        driver.findElement(By.id("risk")).sendKeys("High");
        driver.findElement(By.className("btn-secondary")).click();

        WebElement popupwindow = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[1]/span"));


        //Wait for pop-up window
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(popupwindow));
        String textAfter = popupwindow.getText();

        //Check in console
        System.out.println(copiedtext);
        System.out.println(name);


        //Assertion
        Assert.assertEquals(textAfter,copiedtext,"Save input assertion has failed");
    }


    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}