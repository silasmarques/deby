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

public class NaoDeveRemoverContaComMovimentacao extends BaseTest{

	static GerarToken token = new GerarToken();
		
	@BeforeClass
	public static void inicio() {
		
		token.gerarToken();
	}
	
	@Test
	public void test01_NaoDeveRemoverContaComMovimentacao() {
		
		given()

		.when()
			.delete("/contas/781581")
		.then()
			.statusCode(500)
			.body("constraint", Matchers.is("transacoes_conta_id_foreign"))
			.body("routine", Matchers.is("ri_ReportViolation"))
			
		;	
		
	}
	
	@Test
	public void removerMovimentacao() {
		
		given()
			.header("Authorization", "JWT "+ Util.obterToken)
		.when()
			.delete("/transacoes/730556")
		.then()
			.statusCode(204)

			
		;	
		
	}
	
	
	

}
