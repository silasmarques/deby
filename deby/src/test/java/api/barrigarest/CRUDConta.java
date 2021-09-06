package api.barrigarest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import com.deby.util.GenerateDataMass;

import api.barrigarest.core.BaseTest;
import api.barrigarest.core.Constantes;
import funcionais.util.DataUtils;
import funcionais.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CRUDConta extends BaseTest{

	static GerarToken token = new GerarToken();
	private static WebDriver driver;
	GenerateDataMass mock = new GenerateDataMass(driver);	
	
		
	@BeforeClass
	public static void inicio() {
		
		token.gerarToken();
	}
	
	@Test
	public void t01_deveIncluirContaSucesso() {
		
		Util.CONTA_ID = given()
//			.header("Authorization", "JWT "+ Util.obterToken)  tirei pq coloquei no GerarToken para todos os testes
//			.body("{\"nome\":\"Silas Marques 3\"}")
			.body("{\"nome\":\""+Util.CONTA_NAME+ "\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)
			.extract().path("id")
		;	
		
		System.out.print("##############: " + Util.CONTA_ID);
		
	}
	
	@Test
	public void t02_deveAlterarConta() {
		
		given()
//		.body("{\"nome\":\"Silas Marques Alterado\"}")
		.body("{\"nome\":\"" +Util.CONTA_NAME_ALTERADO+ "\"}")
			.when()
		.put("/contas/"+Util.CONTA_ID)
			.then()
			.body("nome", Matchers.is(Util.CONTA_NAME_ALTERADO))
	;	
		
	}
	
	@Test
	public void t03_DeveInserirMovimentacao() {
		
		Movimentacao mov = getMovimentacaoValida();
		
		int i = 0;
        while(i<=2){
        	
            i = i + 1;
            mov.setValor(200f +i);
            given()
    			.body(mov)
    		.when()
    			.post("/transacoes")
    		.then()
    			.log().all()
    			.statusCode(201)
    		;
            
        }
			
	}
	
	//-------------------------------------------------------------------------------------------------
	
	
	private Movimentacao getMovimentacaoValida() {
		
		Movimentacao mov = new Movimentacao();
		
		mov.setConta_id(Util.CONTA_ID);
//		mov.setUsuario_id(usuario_id);
		mov.setDescricao("Descrição da Movimentação pelo Util");
		mov.setEnvolvido("Envolvido na movimentação pelo Util");
		mov.setTipo("REC");
		mov.setData_transacao(DataUtils.getDataDiferencaDias(-1));
		mov.setData_pagamento(DataUtils.getDataDiferencaDias(5));
		mov.setValor(100f);
		mov.setStatus(true);
			
		return mov;
	}
	
}
