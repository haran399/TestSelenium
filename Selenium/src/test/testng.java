package test;

import org.testng.Assert;
import org.testng.annotations.*;

public class testng {

   @BeforeSuite
   public void testbeforesuite()
   {
	   Assert.assertEquals("actual", "expected");
   }
}
