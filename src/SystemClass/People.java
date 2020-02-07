package SystemClass;

import SystemUtil.SystemAnnotation;

@SystemAnnotation(tableName = "people")
public class People {
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setUsername",
			getMethod = "getUsername",
			columnName = "username"
			)
	String username;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setPassword",
			getMethod = "getPassword",
			columnName = "password"
			)
	String password;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setName",
			getMethod = "getName",
			columnName = "name"
			)
	String name;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(Object username) {
		this.username = (String) username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(Object password) {
		this.password = (String) password;
	}
	public String getName() {
		return name;
	}
	public void setName(Object name) {
		this.name = (String) name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		People other = (People) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "People [username=" + username + ", password=" + password + ", name=" + name + "]";
	}
	
	public People(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public People() {}
	
	
}
