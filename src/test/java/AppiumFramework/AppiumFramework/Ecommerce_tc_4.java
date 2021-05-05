package AppiumFramework.AppiumFramework;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_tc_4 extends Base{

	@Test
	public void totalValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		service=startServer();
		AndroidDriver<AndroidElement> driver=Capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));");
		driver.findElement(By.xpath("//*[@text='India']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\"))"
				+ ".scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
		
		int count=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(text.equalsIgnoreCase("Jordan 6 Rings"))
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
			
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(6000);
			String amount1=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
			amount1=amount1.substring(1);
			Double amoun1value=Double.parseDouble(amount1);
			
			String amount2=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
			amount2=amount2.substring(1);
			Double amoun2value=Double.parseDouble(amount2);
			
			Double sumoftotal=amoun1value+amoun2value;  
			System.out.println(sumoftotal+"sum of two number");
			String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
			
			total=total.substring(1);
			Double totalvalue=Double.parseDouble(total);
			
			System.out.println(totalvalue+"sum of product number");
			Assert.assertEquals(sumoftotal, totalvalue);
			System.out.println("*************************************");
			service.stop();
			
	}
	
	
	@BeforeTest
	public void KillAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
}
