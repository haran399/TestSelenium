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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class test {
	
	     
	@Test
        public void testHealthInsurance()
        {
		Properties objpro=new Properties();
		try {
			FileInputStream objfis=new FileInputStream(".\\src\\test\\values.properties");
		    objpro.load(objfis);
    	} catch (Exception e) {
			
			e.printStackTrace();
    	}
		String url= objpro.getProperty("url");
		String Insurance_products_xpath=objpro.getProperty("Insurance_products_xpath");
		String Insurance_menu_items_xpath=objpro.getProperty("Insurance_menu_items_xpath");
    	WebDriver driver;
    	System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        Actions a=new Actions(driver);
        WebElement w=driver.findElement(By.xpath(Insurance_products_xpath));
        a.moveToElement(w).perform();
        List<WebElement> menu=new ArrayList<WebElement>();
        menu=driver.findElements(By.xpath(Insurance_menu_items_xpath));
        for(int i=0;i<11;i++)
        {
        	System.out.println(menu.get(i).getText());
        }
        	
        
        }
}
