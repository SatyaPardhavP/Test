package sample1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

public class Sute {
	
	
	@Parameters({"browser","url"})	
	@BeforeClass
	public void BeforeClass(String browser, String url){
		WebPage.open(browser, url);
	}
	
	@AfterClass
	public void browserClose() {
		WebPage.close();		
		System.out.println("browser closed");
	}
}
