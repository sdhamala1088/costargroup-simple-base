package costar.testing.api.data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Client {

	private int clientId;
	private String name;
	private String email;
	LocalDate joinedDate;
}
