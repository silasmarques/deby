package funcionais.pageobject;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.deby.components.FormComponent;
import com.deby.extra.Login;
import com.deby.util.Log;

import funcionais.massa.MassaLogin;
import funcionais.util.Util;

public class LoginPage {

	private static Log log;
	private static WebDriver driver;
	private static FormComponent fc;
	Login login = new Login(driver);
	MassaLogin massaLogin = new MassaLogin();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		login = new Login(driver);
		fc = new FormComponent(driver);
	}

	public void login() throws Exception {
		try {
			
			Log.test("Acessar URL", Util.url);
			Log.test("Informar usuário e senha", "");
			Log.test("Selecionar a opção para logar", "");
			Log.nextTestCase();
			
			MassaLogin massaLogin = new MassaLogin();
			login.login(massaLogin.campoUsuario, massaLogin.usuario, massaLogin.campoSenha, massaLogin.senha,
					massaLogin.botaoLogin);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
