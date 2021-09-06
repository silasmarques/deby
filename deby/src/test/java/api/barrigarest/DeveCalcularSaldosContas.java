package api.barrigarest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.deby.util.GenerateDataMass;

import api.barrigarest.core.BaseTest;
import api.barrigarest.core.Constantes;
import funcionais.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DeveCalcularSaldosContas extends BaseTest{

	static GerarToken token = new GerarToken();
		
	@BeforeClass
	public static void inicio() {
		
		token.gerarToken();
	}
	
	@Test
	public void deveCalcularSaldoContas() {
		
		given()
		.when()
			.get("/saldo")
		.then()
			.statusCode(200)
			.body("find{it.conta_id==781581}.saldo", Matchers.is("200.00"))
			.log().all()
			
		;	
		
	}
	
}
