package api.barrigarest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.deby.util.GenerateDataMass;

import api.barrigarest.core.BaseTest;
import api.barrigarest.core.Constantes;
import funcionais.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NaoIncluirNomeRepetido extends BaseTest{

	static GerarToken token = new GerarToken();
		
	@BeforeClass
	public static void inicio() {
		
		token.gerarToken();
	}
	
	@Test
	public void napDeveIncluirContaNomeRepetido() {
		
		given()
			.body("{\"nome\":\"Silas Marques 2\"}")
		.when()
			.post("/contas")
		.then()
			.body("error", Matchers.is("Já existe uma conta com esse nome!"))
			.statusCode(400)
		;	
		
	}
	
	
	

}
