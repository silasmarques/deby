package funcionais.util;

public class TokenRequest {

	private String grantType;
	private String clientId;
	private String clientSecret;
	
	public String getGrantType() {
		return grantType;
	}
	
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Override
	public String toString() {
		return "grant_type=" + grantType + "&client_id=" + clientId + "&client_secret=" + clientSecret;
	}
	
}

