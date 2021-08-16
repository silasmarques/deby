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

public class CriarUsuario extends ReportHTML {

	private static WebDriver driver;
	private static FormComponent fc;
	private static ScreenshotUtil sc;
	private static GenerateDataMass mass;
	Login login = new Login(driver);
	GenerateDataMass mock = new GenerateDataMass(driver);

	MassaLogin massaLogin = new MassaLogin();

	PDFContent lePdf = new PDFContent(driver);

	@BeforeClass
	public static void inicio_poc() throws Exception {

		try {

			driver = Browser.getDriver(BrowserEnum.valueOf(Util.navegadorChrome.toUpperCase()), Util.url2, Util.chrome);

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

		} catch (Exception ex) {
			throw ex;
		} finally {
		}

	}

	@AfterClass
	public static void FechaBrowser() throws IOException {

		footer();
		Log.endTestCase();
		//driver.quit();
	}

	@Test
	public void test01_CriarUsuario() throws InterruptedException, Exception {

		try {
			
			fc.uploadFile(By.id("imagesrc"), "C:\\Users\\Silas\\Pictures\\insta.png");
			
			mock.nameFirst(By.xpath("//input[@ng-model='FirstName']"));
			mock.nameFirst(By.xpath("//input[@ng-model='LastName']"));
			mock.AddressComplement(By.xpath("/html/body/section/div/div/div[2]/form/div[2]/div/textarea"));
			mock.EmailAddress(By.xpath("//input[@ng-model='EmailAdress']"));
			fc.clickElement(By.xpath("//input[@ng-model='Phone']"));
			fc.sendKeysElement(By.xpath("//input[@ng-model='Phone']"), "4374444444");
			
			fc.clickElement(By.xpath("/html/body/section/div/div/div[2]/form/div[5]/div/label[1]/input"));
			fc.clickElement(By.id("checkbox2"));
					
			fc.clickElement(By.id("msdd")); // Languages
			fc.clickElement(By.xpath("/html/body/section/div/div/div[2]/form/div[7]/div/multi-select/div[2]/ul/li[29]/a"));
			fc.clickElement(By.xpath("/html/body/section/div/div/div[2]/form/div[7]/div/multi-select/div[2]/ul/li[8]/a"));

			fc.selectDropdownItem(By.id("Skills"), "Java");
			fc.selectDropdownItem(By.id("countries"), "Brazil");
			
			fc.clickElement(By.xpath("/html/body/section/div/div/div[2]/form/div[10]/div/span/span[1]/span"));
			fc.selectDropdownAutocomplete(By.xpath("/html/body/span/span/span[1]/input"), "new", By.xpath("/html/body/span/span/span[2]/ul/li"));
			
			fc.scrollToElement(By.id("yearbox"));
			fc.selectDropdownItem(By.id("yearbox"), "1983");
			fc.selectDropdownItem(By.xpath("/html/body/section/div/div/div[2]/form/div[11]/div[2]/select"), "March");
			fc.selectDropdownItem(By.id("daybox"), "1");
			
			String password ="Smarques765";
			fc.sendKeysElement(By.id("firstpassword"), password);
			fc.sendKeysElement(By.id("secondpassword"), password);
			
			fc.clickElement(By.id("submitbtn"));
			
		} catch (Exception ex) {
			throw ex;
		} finally {

			sc.getScreen(new Object() {}.getClass().getEnclosingMethod().getName());
			Log.getNameTest("Funcionalidade ", new Object() {}.getClass().getEnclosingMethod().getName());
			Log.test("Informar Nome", "massa");
			Log.test("Informar Último Nome ", "massa");
			Log.test("Informar E-mail ", "massa");
			Log.test("Informar Endereço Completo", "massa");
			Log.test("Informar Universidade", "massa");
			Log.test("Informar Profissão ", "massa");
			Log.test("Informar Gênero", "massa");
			Log.test("Informar Idade", "massa");
			Log.nextTestCase();

		}

	}

	//@Test
	public void test02_ValidarCadastroUsuario() throws InterruptedException, Exception {

		try {
			fc.waitElementVisibility(By.id("notice"));
			fc.assertElementTexContent(By.id("notice"), "Usuário Criado com sucesso");

		} catch (Exception ex) {
			throw ex;
		} finally {

			sc.getScreen(new Object() {}.getClass().getEnclosingMethod().getName());
			Log.getNameTest("Funcionalidade ", new Object() {}.getClass().getEnclosingMethod().getName());
			Log.test("Validar Mensagem de cadastro ", "massa");
			Log.nextTestCase();
		}
	}
}
