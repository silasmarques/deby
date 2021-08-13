package funcionais.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Classe responsavel por facilitar solucoes com clientes de webservices rest.
 * 
 * Classe <code>RestClientService</code>
 * 
 * @author f798497
 * 
 */
public class RestClientService {

	private final WebTarget target;
	private MultivaluedMap<String, Object> headers; 
	private Map<String, String> queryParams;
	
	/**
	 * Construtor com uri do servico.
	 * 
	 * @param uri - endereco do servico
	 */
	public RestClientService(final String uri){
		Client client = ClientBuilder.newClient();
		target = client.target(uri);
	}

	/**
	 * Metodo responsavel por adicionar um header para chamada de um servico.
	 */
	public void addHeader(String key, String value){
		if (headers == null) {
			headers = new MultivaluedHashMap<String, Object>();
		}
		
		headers.add(key, value);
	}
	
	/**
	 * Metodo responsavel por limpar o header para a chamada de um servico.
	 */
	public void clearHeaders() {
		if (headers != null) {
			headers.clear();
		}
	}
	
	/**
	 * Metodo responsavel por adicionar query param para chamada de um servico.
	 */
	public void addQueryParam(String key, String value){
		if (queryParams == null) {
			queryParams = new HashMap<String, String>();
		}
		
		queryParams.put(key, value);
	}
	
	/**
	 * Metodo responsavel por limpar os query params para a chamada de um servico.
	 */
	public void clearQueryParams() {
		if (queryParams != null) {
			queryParams.clear();
		}
	}
	
	/**
	 * Metodo responsavel por realizar uma chamada a um servico por get.
	 *  
	 * @param path - endereco do servico
	 * @param representation - representacao da saida do servico
	 * @return Response - resposta do servico
	 */
	public Response get(final String path, final String representation){
		WebTarget resource = target.path(path);		
		
		if (this.queryParams != null) {
			
			for (Map.Entry<String, String> param : queryParams.entrySet()){
				resource = resource.queryParam(param.getKey(), param.getValue());
			}
		}
		
		Invocation.Builder buider = resource.request(representation).headers(headers);		
		return buider.get();
	}
	
	/**
	 * Metodo responsavel por realizar uma chamada a um servico por delete.
	 *  
	 * @param path - endereco do servico
	 * @param representation - representacao da saida do servico
	 * @return Response - resposta do servico
	 */
	public Response delete(final String path, final String representation){
		Invocation.Builder buider = target.path(path).request(representation).headers(headers);
		return buider.delete();
	}
	
	/**
	 * Metodo responsavel por realizar uma chamada a um servico por post.
	 *  
	 * @param path - endereco do servico
	 * @param representation - representacao da saida do servico
	 * @param body - corpo da requisicao
	 * @return Response - resposta do servico
	 */
	public <T> Response post(final String path, final String representation, final T body){
		Invocation.Builder buider = target.path(path).request(representation).headers(headers);
		return buider.post(Entity.entity(body, representation));
	}
	
	/**
	 * Metodo responsavel por realizar uma chamada a um servico por put.
	 *  
	 * @param path - endereco do servico
	 * @param representation - representacao da saida do servico
	 * @param body - corpo da requisicao
	 * @return Response - resposta do servico
	 */
	public <T> Response put(final String path, final String representation, final T body){
		Invocation.Builder buider = target.path(path).request(representation).headers(headers);
		return buider.put(Entity.entity(body, representation));
	}
			
}
