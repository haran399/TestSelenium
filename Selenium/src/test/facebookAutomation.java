package test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;

public class facebookAutomation {
	
	static WebDriver driver;
	static Properties objpro=new Properties(); 
    /*function to initialize properties file*/
	public void propertiesSetup()
    {
    	try {
			FileInputStream objfis=new FileInputStream(".\\src\\test\\config.properties");
		    objpro.load(objfis);
    	} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
	/*function to create the driver*/
	public WebDriver createDriver()
	{    String browserName=objpro.getProperty("browser");
		 if(browserName.equalsIgnoreCase("Chrome"))
         {
             System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
             driver = new ChromeDriver();
             driver.manage().window().maximize();
             
         }
         else if(browserName.equalsIgnoreCase("firefox"))
         {
             System.setProperty("webdriver.gecko.driver","drivers\\geckodriver.exe");
             driver = new FirefoxDriver();
             driver.manage().window().maximize();
            
         }
		 return driver;
	}
	/*function to navigate to the url and enter all the fields and return date of birth elements*/
	public List<Select> submitFormElements()
	{   
		
		List<Select> dob=new ArrayList<Select>(3);
		String url=objpro.getProperty("url");
		String fname=objpro.getProperty("firstName");
		String sname=objpro.getProperty("surName");
		String phone=objpro.getProperty("mobileno");
		String pwd=objpro.getProperty("password");
		String date=objpro.getProperty("date");
		String month=objpro.getProperty("month");
		/*converting string to integer(Month) and decrementing by 1 to access the correct index*/
		int monthInt=(Integer.parseInt(month))-1;
		String year=objpro.getProperty("year");
		driver.get(url);
		try
		{
		
		WebElement search=driver.findElement(By.xpath("//a[@role='button' and @rel='async']"));
		search.click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		WebElement firstName=driver.findElement(By.name("firstname"));
		firstName.sendKeys(fname);
		WebElement surname=driver.findElement(By.name("lastname"));
		surname.sendKeys(sname);
		WebElement mobileNo=driver.findElement(By.name("reg_email__"));
		mobileNo.sendKeys(phone);
		WebElement password=driver.findElement(By.id("password_step_input"));
		password.sendKeys(pwd);
		Select day1=new Select(driver.findElement(By.xpath("//select[@id='day']")));
		day1.selectByVisibleText(date);
		Select month1=new Select(driver.findElement(By.xpath("//select[@id='month']")));
		month1.selectByIndex(monthInt);
		Select year1=new Select(driver.findElement(By.xpath("//select[@id='year']")));
		year1.selectByValue(year);
		dob.add(day1);
		dob.add(month1);
		dob.add(year1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dob;	
	}
	/*function to verify whether the entered date of birth matches with the given date of birth*/
	
	@Test
	
	public void verifyDOB(List<Select> dob)
	{
		Select day2=dob.get(0);
		Select month2=dob.get(1);
		Select year2=dob.get(2);
		WebElement day1=day2.getFirstSelectedOption();
		WebElement month1=month2.getFirstSelectedOption();
		WebElement year1=year2.getFirstSelectedOption();
		if(day1.getText().equals(objpro.getProperty("date"))&&month1.getAttribute("value").equals(objpro.getProperty("month"))&&year1.getText().equals(objpro.getProperty("year")))
		{
			System.out.println("The date of birth is verified ");
		}
		
	}
	/*function to close the browser*/
	public void close()
	{
		
		driver.close();
	}
	public static void main(String[] args)
	{
		facebookAutomation f=new facebookAutomation();
		f.propertiesSetup();
		f.createDriver();
		List<Select> dob=f.submitFormElements();
		f.verifyDOB(dob);
		f.close();
		
		
	}
}
