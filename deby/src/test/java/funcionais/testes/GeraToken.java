package funcionais.testes;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;

import funcionais.util.RestClientService;
import funcionais.util.TokenRequest;
import funcionais.util.TokenResponse;
import funcionais.util.Util;

public class GeraToken {
	
	
	public static void main (String[] args){
		
		try {
			TokenRequest request = new TokenRequest();
			request.setClientId("cli-ser-fug");
			request.setClientSecret("29828302-4b9b-4f52-a64d-b316851f1c55");
			request.setGrantType("client_credentials");
			
			RestClientService service = new RestClientService("https://logindes.caixa.gov.br/auth/realms/internet");
			Response response = service.post("/protocol/openid-connect/token", MediaType.APPLICATION_FORM_URLENCODED, request.toString());
			TokenResponse representationResponse = response.readEntity(TokenResponse.class);
			
			Util.token = representationResponse.getAccessToken();
//			System.out.print("ssss " + Util.token +"\n");
//			System.out.println("ssss "+representationResponse.getAccessToken());
			
		} catch (MessageBodyProviderNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
