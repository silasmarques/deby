package funcionais.testes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.server.browserlaunchers.MockBrowserLauncher;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.deby.browser.Browser;
import com.deby.components.FormComponent;
import com.deby.components.VisualComponent;
import com.deby.constants.BrowserEnum;
import com.deby.constants.Constants;
import com.deby.extra.Login;
import com.deby.util.ReportHTML;
import com.deby.util.GenerateDataMass;
import com.deby.util.Log;
import com.deby.util.PDFContent;
import com.deby.util.ScreenshotUtil;

import funcionais.pageobject.LoginPage;
import funcionais.pageobject.MenuPage;
import funcionais.util.Util;
import funcionais.massa.MassaLogin;
import funcionais.massa.MassaMenu;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CriarUsuarios extends ReportHTML {

	private static WebDriver driver;
	private static FormComponent fc;
	private static ScreenshotUtil sc;
	private static GenerateDataMass mass;
	Login login = new Login(driver);
	GenerateDataMass mock = new GenerateDataMass(driver);
	VisualComponent visual = new VisualComponent(driver);
	PDFContent lePdf = new PDFContent(driver);
	MassaLogin massaLogin = new MassaLogin();

	@BeforeClass
	public static void inicio_poc() throws Exception {

		try {

			driver = Browser.getDriver(BrowserEnum.valueOf(Util.navegadorChrome.toUpperCase()), Util.url, Util.chrome);

// -----------------------------------------------------------------------------------------------------------

			Log.startTestCase();
			Log.startAutomationFunctionalTest(new Object() {
			}.getClass().getEnclosingClass().getSimpleName());

// -----------------------------------------------------------------------------------------------------------

			InputStream configStream = Thread.currentThread().getContextClassLoader().getSystemClassLoader()
					.getResourceAsStream("config/log4j.xml");
			new DOMConfigurator().doConfigure(configStream, LogManager.getLoggerRepository());

// -----------------------------------------------------------------------------------------------------------

			Log.test("Acessar URL", Util.url);
			Log.nextTestCase();

// ---------------------------------------------------------------------------------------------------------
			
			header(driver);

// ---------------------------------------------------------------------------------------------------------

			sc = new ScreenshotUtil(driver);
			fc = new FormComponent(driver);
			mass = new GenerateDataMass(driver);
			
// ---------------------------------------------------------------------------------------------------------
			fc.clickElement(By.linkText("Formulário"));
			fc.clickElement(By.linkText("Criar Usuários"));


		} catch (Exception ex) {
			throw ex;
		} finally {
		}

	}

	@AfterClass
	public static void FechaBrowser() throws IOException {

		footer();
		Log.endTestCase();
		driver.quit();
	}

	@Test
	public void test01_CriarUsuario() throws InterruptedException, Exception {

		try {

			mock.nameFirst(By.id("user_name"));
				Util.user_name = driver.findElement(By.id("user_name")).getAttribute("value");

			mock.nameLast(By.id("user_lastname"));
			mock.EmailAddress(By.id("user_email"));
			mock.AddressFull(By.id("user_address"));

			mock.universityName(By.id("user_university"));
			mock.jobTitle(By.id("user_profile"));
			fc.sendKeysElement(By.id("user_gender"), "gênero");
			mock.more18Years(By.id("user_age"));

			fc.clickElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/form/div[5]/div/div"));
			
			fc.assertElementCurrencyContent(By.xpath("/html/body/div[2]/div[2]/div[3]/div/p[1]"), Util.user_name);
			fc.assertContainsText(By.id("notice"), "Usuário Criado com sucesso");

		} catch (Exception ex) {
			throw ex;
		} finally {

			sc.getScreen(new Object() {
			}.getClass().getEnclosingMethod().getName());
			Log.getNameTest("Funcionalidade ", new Object() {
			}.getClass().getEnclosingMethod().getName());
			Log.test("Informar Nome", "massa");
			Log.test("Informar Último Nome ", "massa");

			Log.nextTestCase();

		}

	}
	
	@Test
	public void test02_EditarCriarUsuario() throws InterruptedException, Exception {

		try {

			fc.clickElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/a[1]"));
			
			fc.waitElement(By.id("user_name"));
			fc.clearElementText(By.id("user_name"));
			mock.nameFirst(By.id("user_name"));
				Util.user_name = driver.findElement(By.id("user_name")).getAttribute("value");
				
			fc.clearElementText(By.id("user_profile"));
			mock.jobTitle(By.id("user_profile"));
				Util.user_profile = driver.findElement(By.id("user_profile")).getAttribute("value");
			
			fc.clickElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/form/div[5]/div/div"));
				fc.waitElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/p[1]"));
			
			fc.assertContainsText(By.xpath("/html/body/div[2]/div[2]/div[3]/div/p[1]"), Util.user_name);
			fc.assertContainsText(By.xpath("/html/body/div[2]/div[2]/div[3]/div/p[6]"), Util.user_profile);
			fc.assertContainsText(By.id("notice"), "Seu Usuário foi Atualizado!");
			
				
				
			
		} catch (Exception ex) {
			throw ex;
		} finally {

			sc.getScreen(new Object() {
			}.getClass().getEnclosingMethod().getName());
			Log.getNameTest("Funcionalidade ", new Object() {
			}.getClass().getEnclosingMethod().getName());
			Log.test("Informar Nome", "massa");
			Log.test("Informar Último Nome ", "massa");

			Log.nextTestCase();

		}

	}
	
	
	
	

}
