package AppiumFramework.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static AppiumDriverLocalService service;
	public AppiumDriverLocalService startServer() {
		boolean flag=checkIfServerIsRunning(4723);
		if(!flag) {
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
		}
		return service;
	}
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning =false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		}catch(IOException e) {
			isServerRunning =true;
		}finally {
			serverSocket= null;
		}
		return isServerRunning;
	}

	public static AndroidDriver<AndroidElement> Capabilities(String app) throws IOException {
		// TODO Auto-generated method stub

		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AppiumFramework\\AppiumFramework\\global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		
		AndroidDriver<AndroidElement> driver;
		File f=new File("src");
		File fs=new File(f,(String) prop.get(app));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		
			String device=(String) prop.get("device");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 18);
		cap.setCapability("-session-override", true);
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "New_Device_API_28");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;
		
		//driver.findElementByXPath("")
		
		
	}

}
