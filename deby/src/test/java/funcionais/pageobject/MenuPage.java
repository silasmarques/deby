package funcionais.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.deby.components.FormComponent;
import com.deby.util.Log;

public class MenuPage {

	private WebDriver driver;
	private static FormComponent fc;

	public MenuPage(WebDriver driver) {
		this.driver = driver;
		fc = new FormComponent(driver);
	}

	public void menu(String... listaMenus) throws Exception {
		try {
			fc = new FormComponent(driver);
			for (String menu : listaMenus) {Log.test("Acessar", menu);
				fc.clickElement(By.linkText(menu));
				Thread.sleep(200);
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

}
