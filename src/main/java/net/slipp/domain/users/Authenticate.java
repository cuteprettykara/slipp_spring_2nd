package net.slipp.domain.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Authenticate {
	@NotNull @Size(min=4, max=12) 
	private String userId;
	
	@NotNull @Size(min=4, max=12)
	private String password;
		
	public Authenticate() {
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
