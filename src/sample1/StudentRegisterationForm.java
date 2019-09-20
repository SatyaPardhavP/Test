package sample1;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class StudentRegisterationForm extends Sute{

	@Test
	public void enterRegisterationForm() {
		System.out.println("ABC");
		WebPage.sendKeys(By.id("studentname"), "abc");
		WebPage.sendKeys(By.id("fathername"), "ABCd");
		WebPage.sendKeys(By.id("paddress"), "Hyderabad");
		WebPage.sendKeys(By.id("personaladdress"), "TS");
		WebPage.click(By.xpath("//form[@name='StudentRegistration']/table/tbody/tr[6]/td[2]/input[1]"));
		WebPage.select(By.xpath("//form[@name='StudentRegistration']/table/tbody/tr[7]/td[2]/select"), "GOA");
	}

	
}
