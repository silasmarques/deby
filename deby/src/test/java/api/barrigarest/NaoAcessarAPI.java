package api.barrigarest;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import com.sun.scenario.effect.Filterable;

import api.barrigarest.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;

public class NaoAcessarAPI extends BaseTest{
	
	@Test
	public void naoDeveAcessarAPISemToken() {
		
//		FilterableRequestSpecification req = RestAssured.requestSpecification;
		FilterableRequestSpecification req = (FilterableRequestSpecification) RestAssured.requestSpecification;
		req.removeHeader("Authorization");
		
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401)
			.log().all()
		;	
	}

}
