package sample1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InvokeBrowser {

public static WebDriver d;
	
	public void Invoke(String browser, String url){
		System.out.println(browser);
		 if(browser.equalsIgnoreCase("chrome")){			 
			 String path = "driver/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", path); 
				 d=new ChromeDriver();
				System.out.println("Invoke chrome");
				 System.out.println("Invoke url:"+url);
				
		}
		 d.manage().window().maximize();  
		 System.out.println("url:"+url);
	d.get(url);
			
	}
}
