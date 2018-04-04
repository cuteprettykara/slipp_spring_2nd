package net.slipp.domain.users;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;

@Alias("User")
public class User {
	@NotNull @Size(min=4, max=12) 
	private String userId;
	
	@NotNull @Size(min=4, max=12)
	private String password;
	
	@NotEmpty
	private String name;
	
	@Email
	private String email;

	public User() {
	}
	
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean matchPassword(Authenticate authenticate) {
		if (this.password == null) return false;
		
		return this.password.equals(authenticate.getPassword());
	}
	
	public boolean matchUserId(String sessionUserId) {
		if (sessionUserId == null) return false;
		
		return this.userId.equals(sessionUserId);
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId  + ", name=" + name + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public User update(User updateUser) {
//		String userId = (String) temp;
		if (!matchUserId(updateUser.userId)) {
			throw new IllegalArgumentException();
		}
		return new User(updateUser.userId, updateUser.password, updateUser.name, updateUser.email);
	}
}
