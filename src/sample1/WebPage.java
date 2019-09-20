package sample1;



import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebPage
{

	static WebDriver d;
	static String browser;

	/**
	 * To get the webDriver Instance
	 * Where ever needed in the other class
	 * 
	 *  */
	/*
	 * public static WebDriver getWD() { //return dr.get();
	 * if(WebDriverFactory.isRemote) return WebDriverFactory.dr.get(); else return
	 * d; }
	 */
	/**
	 * To create the required browser instance.
	 * 
	 * @param Browser
	 * @return the instance of created web-driver
	 * @throws MalformedURLException
	 */


	public static void open(String browser, String url){
		System.out.println(browser);
		 if(browser.equalsIgnoreCase("chrome")){			 
			 String path = "driver/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", path); 
				 d=new ChromeDriver();
				System.out.println("Invoke chrome");
				 System.out.println("Invoke url:"+url);
				 d.get(url);
		 }
		
}

	/*
	 * public static WebDriver createWebDriverInstance(String context,String
	 * Browser) { browser=Browser; d=
	 * WebDriverFactory.createWebDriver(context,Browser); return d; }
	 */

	public static String getRunningBrowserName(){

		return browser;
	}
	public static WebElement getWebElement(By obj){

		return d.findElement(obj);	 	
	}

	/**
	to get elements list from the webpage.
	 */
	public static List<WebElement> getWebElements(By obj){
		return d.findElements(obj);	
	}

	public static String getText(By obj){
		//presenceOfElementLocated(obj);
		WebDriverWait wait = new WebDriverWait(d, 10);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		return el.getText();
		//return getWD().findElement(obj).getText();

	}

	public static String getText(By obj,int time){
		//presenceOfElementLocated(obj);
		WebDriverWait wait = new WebDriverWait(d, time);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		return el.getText();
		//return getWD().findElement(obj).getText();

	}

	public static String getText_NoWait(By obj){
		d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return d.findElement(obj).getText();
	}

	public static void click(By obj){

		WebDriverWait wait = new WebDriverWait(d, 10);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.click();
	}

	public static void switchParentWindow(String parentWindow){
		d.switchTo().window(parentWindow);
	}
	public static void click(By obj,int time){

		WebDriverWait wait = new WebDriverWait(d, time);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.click();
	}

	public static void click_linkText(String text){
		click(By.linkText(text));
	}

	public static void sendKeys(By obj, String data){
		WebDriverWait wait = new WebDriverWait(d, 30);
		//scrollBarHandle(obj);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.clear();
		sleep(2);
		el.sendKeys(data);

		//waitUntilEnterText(obj,data);
		//getWD().findElement(obj).sendKeys(text);
	}
	public static void Tab(By obj){
		WebDriverWait wait = new WebDriverWait(d, 5);
		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		inputField.sendKeys(Keys.TAB);
	}
	public static void sendKeysandTab(By obj,String text){		
		WebDriverWait wait = new WebDriverWait(d, 30);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.sendKeys(text);
		el.sendKeys(Keys.valueOf(("TAB")));	
	}

	public static void sendKeys_withoutwait(By obj, String data){	
		getWebElement(obj).click();
		sleep(3);
		getWebElement(obj).clear();
		sleep(4);
		getWebElement(obj).sendKeys(data);
		sleep(4);
	}

	public static void select(By obj,String text){	
		WebDriverWait wait = new WebDriverWait(d, 30);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		/*WebElement element=null;
		element =getWD().findElement(obj);*/
		new Select(el).selectByVisibleText(text);

	}
	public static void selectValue(By obj,String text){	
		WebElement element=null;
		element =d.findElement(obj);
		new Select(element).selectByValue(text);
	}
	public static void scrollBarHandle(By obj){

		JavascriptExecutor js = (JavascriptExecutor)d;
		WebElement element=d.findElement(obj);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	public static void jsSendKeys(By obj,String data){

		JavascriptExecutor js = (JavascriptExecutor)d;
		WebElement element=d.findElement(obj);
		js.executeScript("arguments[0].value='"+data+"';",element);
	}
	public static void clear(By obj){
		WebDriverWait wait = new WebDriverWait(d, 10);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.clear();
	}

	/**
	 * to get the Attribute value 
	 * @param obj
	 * @param AttributeName
	 * @return
	 */
	public static String getAttributeValue(By obj,String AttributeName){
		WebDriverWait wait = new WebDriverWait(d, 10);
		WebPage.scrollBarHandle(obj);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		return el.getAttribute(AttributeName);
		//return	getWD().findElement(obj).getAttribute(AttributeName);	
	}

	/**
	 * To Get the Text of Selected Option from the Drop down field
	 */
	public static String getSelectedValueFromDropDown(By obj){

		Select select = new Select(d.findElement(obj));
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	} 

	private static WebDriverWait setExplicitWait(int timeInSeconds){
		return new WebDriverWait(d,timeInSeconds);
	}

	public static boolean toCheckVisibilityofElement(By obj,int timeInSeconds){
		setExplicitWait(timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(obj));
		return true;
	}

	public static boolean toCheckInVisibilityofElement(By obj,int timeInSeconds){
		setExplicitWait(timeInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(obj));
		return true;
	}
	public static boolean verifyField(By obj){
		scrollBarHandle(obj);
		sleep(1);
		return d.findElement(obj).isDisplayed();	
	}
	public static boolean verifyisDisplayed(By obj){
		WebDriverWait wait = new WebDriverWait(d, 10);
		sleep(1);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		return el.isDisplayed();	
	}

	public static boolean verifyisEnabled(By obj){
		scrollBarHandle(obj);
		sleep(1);
		return d.findElement(obj).isEnabled();
	}

	public static boolean verifyisSelected(By obj){
		scrollBarHandle(obj);
		sleep(1);
		return d.findElement(obj).isSelected();
	}

	/**
	 * to open url.
	 * @param url
	 */
	public static void openURL(String url){

		d.manage().window().maximize();
		d.get(url);
	}

	public static void close(){
		d.close();
	}
	public static String getPageTitle(){

		return d.getTitle();
	}

	public static void maximize(){

		d.manage().window().maximize();
	}

	/**
	 * to refresh the page.
	 */
	public static void pageRefresh() {
		d.navigate().refresh();
	}

	public static void sleep(int sleeptime){

		try {
			Thread.sleep(sleeptime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * To Wait for required amount of time for each action performed in the called method
	 * @param Seconds
	 */

	public static void wait(int Seconds)
	{
		d.manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
	}

	/**
	 * Waits for page to load
	 * @throws Exception
	 */

	public static void waitForPageToLoad() 
	{
		try{
			wait(0);
			WebDriverWait wait = new WebDriverWait(d,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[not (.='')]")));
		}
		catch(Exception e){
			sleep(2);
		}
	}

	public static void elementToBeClickable(By obj){
		long seconds=10;
		WebDriverWait wait = new WebDriverWait(d, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(obj)).click();
	}

	public static void presenceOfElementLocated(By obj){	
		//WebDriverWait wait = new WebDriverWait(getWD(), 120);
		long seconds = 10;
		WebDriverWait wait = new WebDriverWait(d, seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
	}
	/**
	 * To perform keyboard actions
	 */
	public static void enterKey(By obj, String Key){

		d.findElement(obj).sendKeys(Keys.valueOf(Key));
	}

	public static void waitUntilEnterText(By obj,String data){	
		//WebDriverWait wait = new WebDriverWait(getWD(), 120);
		long seconds = 10;
		WebDriverWait wait = new WebDriverWait(d, seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(obj)).sendKeys(data);
	}
	/**
	 * To get the row count from appication
	 */
	public static int getRowCount(By obj){	

		return d.findElements(obj).size();

	}

	public static void waitForLoad(WebDriver d ) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return ((JavascriptExecutor)d).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(d, 30);
		wait.until(pageLoadCondition);
	}
	public  static void handleAlert(){
		Alert accept= d.switchTo().alert();
		accept.accept();
	}
	public static void jsClick(By obj){
		JavascriptExecutor js = (JavascriptExecutor)d;
		WebElement element=d.findElement(obj);
		js.executeScript("arguments[0].click();", element);
	}
	public static void explicittime(By obj) throws Exception{ 
		new WebDriverWait(d, 10).until(ExpectedConditions.visibilityOfElementLocated(obj));
	}


	public static void mouseHoverSendKeys(By obj,String text){
		WebElement element = d.findElement(obj);
		Actions action = new Actions(d);
		action.moveToElement(element).click().sendKeys(text).build().perform();
	}
	public static void SelectbymouseHover(By obj){
		WebElement element = d.findElement(obj);

		Actions action = new Actions(d);

		action.moveToElement(element).click().build().perform();
	}
	public static void selectmultiplerowsusingctrlkey(By obj ){
		//WebElement element = getWD().findElement((By) obj);
		WebElement element=d.findElement(obj);

		Actions action = new Actions(d);
		action.click(element).keyDown(Keys.CONTROL)
		.click(element).keyUp(Keys.CONTROL).build().perform();

	}

	public static void keywordoperation(By obj){

		Actions action = new Actions(d);
		WebElement element=d.findElement(obj);
		action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();
	}
	public static void Enterkeywordoperation(By obj){
		WebElement textbox = d.findElement(obj);
		textbox.sendKeys(Keys.ENTER);
	}
	public static void Enterkeyworddeleteoperation(By obj){
		WebElement textbox = d.findElement(obj);
		textbox.sendKeys(Keys.DELETE);
	}
	public static void click_ExplicitWait	(By obj,int time){

		WebDriverWait wait = new WebDriverWait(d, time);
		WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		el.click();
	}

	public static void scrollUpPage(){
		JavascriptExecutor js = (JavascriptExecutor) d;
		// js.executeScript("window.scrollBy(0,-250)", "");
		js.executeScript("scroll(0, -250);");
	}
	public static void scrollDownPage(){
		JavascriptExecutor js = (JavascriptExecutor) d;
		// js.executeScript("window.scrollBy(0,250)", "");
		// js.executeScript("scroll(0, 250);"); 
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	/**
	 * to get all iframes
	 * @return 
	 */
	public static Integer getFrames(){
		JavascriptExecutor exe = (JavascriptExecutor) d;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		//Log.info("Number of iframes on the page: " + numberOfFrames);
		return numberOfFrames;
	}

	/**
	 * To switchToframe
	 */
	public static void switchToframe(int frameIndex){
		d.switchTo().frame(frameIndex);
	}

	/**
	 * To defaultContent from frame
	 */
	public static void switchToframe_DefaultContent(){
		d.switchTo().defaultContent();
	}

	/**
	 * to open url.
	 * @param url
	 */
	public static void quit(){
		d.close();
	}
	
	public void closeBrowser (){
		WebPage.d.close();
	}
}



