package costar.testing.api.data;


public class Client {
	
	private int clientId;
	private String name;
	private String email;
	
	public Client() {
	}

	public Client(int id, String name, String email) {
		this.setClientId(id);
		this.setName(name);
		this.setEmail(email);
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getClientId() {
		return clientId;
	}



	public void setClientId(int clientId) {
		this.clientId = clientId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
}
