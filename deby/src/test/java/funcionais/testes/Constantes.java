package funcionais.testes;

import io.restassured.http.ContentType;

public interface Constantes {

	String APP_BASE_URL="base_url";
	Integer APP_PORT=443; 
	String APP_BASE_PATH=""; // /alguma coisa depois do endereço url
	
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	
	Long MAX_TIMOUT=9000l;
	
}
