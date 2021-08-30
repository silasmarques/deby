package api.testes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import api.barrigarest.core.BaseTest;
import funcionais.util.Util;
import funcionais.testes.GeraToken;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Testes extends BaseTest {

	@Test
	public void naoDeveAcessarSemTokem() {

		given()
		
		.when()
			.get("https://openbanking-brasil.github.io/areadesenvolvedor/swagger/swagger_common_apis.yaml")

		.then()
			.log().all()
			.statusCode(200)
		;
	}

	//@Test
	public void deveAcessarAPI() {

		GeraToken.main(null);
		System.out.print("Teste Silas Token " + Util.token + "\n");

		given().header("Authorization", Util.token)
		
		.when().get("/solicitacoes-saque")
		
		.then()
			.log().all()
			.assertThat()
			.statusCode(200).body(is(not(nullValue()))).body("$", hasSize(5)) //
			.body("numeroPedido", hasItems(3932, 3932, 3932, 3932, 3932))
			.body("dataHoraPedido[1]", is("20/05/2020"));

		;

	}

	//@Test
	public void validarListaDentroLista() {
		
		GeraToken.main(null);
		
		given()
			.header("Authorization", Util.token)
			
		.when()
			.get("/solicitacoes-saque")

		.then().log().all().assertThat()
			.statusCode(200).body(is(not(nullValue()))).body("$", hasSize(5)) //
			.body("numeroPedido", hasItems(3932, 3932, 3932, 3932, 3932))
			.body("contas.empregador.razaoSocial[2]",hasItem("ORLANE MARIA DAMASCENO SOUSA"))

		;
		
	}

}
