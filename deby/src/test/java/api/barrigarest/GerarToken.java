package api.barrigarest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.deby.util.GenerateDataMass;

import api.barrigarest.core.BaseTest;
import api.barrigarest.core.Constantes;
import funcionais.util.Util;
import io.restassured.RestAssured;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class GerarToken extends BaseTest{

	public void gerarToken() {
		
		Map<String, String> login = new HashMap<>();
		login.put("email", "silasuni@gmail.com");
		login.put("senha","123456");
		
		Util.obterToken= 
		given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
			
			RestAssured.requestSpecification.header("Authorization", "JWT "+ Util.obterToken);
		
	}	

}