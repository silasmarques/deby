package api.barrigarest;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.deby.util.GenerateDataMass;

import api.barrigarest.core.BaseTest;
import api.barrigarest.core.Constantes;
import funcionais.util.DataUtils;
import funcionais.util.Util;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler.Default.Harmonizer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class deveValidarCamposObrigatoriosMovimentacao extends BaseTest{

	static GerarToken token = new GerarToken();
	
	@BeforeClass
	public static void inicio() {
		
		token.gerarToken();

		
	}
	
	@Test
	public void test01_deveValidarContaIDCampoObrigatoriosMovimentacao() {

		Movimentacao mov = getMovimentacaoValida();
		
		given()
			.body(mov)
			
		.when()
			.post("/transacoes")
		.then()
			.log().all()
			.statusCode(400)
			.body("$", hasSize(8))
			.body("msg", hasItems("Conta é obrigatório"))
			
		;	
		
	}
	
	@Test
	public void test02_naoDeveCadastrarMovimentacaoFutura() {
		
		Movimentacao mov = getMovimentacaoValida();
		mov.setData_transacao(DataUtils.getDataDiferencaDias(2));
		
		given()
			.body(mov)
		.when()
			.post("/transacoes")
		.then()
			.log().all()
			.statusCode(400)
			.body("$", hasSize(1))
			.body("msg", hasItem("Data da Movimentação deve ser menor ou igual à data atual"))
			;
			
	}
	
	
	private Movimentacao getMovimentacaoValida() {
		
		Movimentacao mov = new Movimentacao();
		
		mov.setConta_id(781581);
//		mov.setUsuario_id(usuario_id);
		mov.setDescricao("Descrição da Movimentação");
		mov.setEnvolvido("Envolvido na movimentação");
		mov.setTipo("REC");
		mov.setData_transacao(DataUtils.getDataDiferencaDias(-1));
		mov.setData_pagamento(DataUtils.getDataDiferencaDias(5));
		mov.setValor(100f);
		mov.setStatus(true);
			
		return mov;
		
		
	}
	

}
