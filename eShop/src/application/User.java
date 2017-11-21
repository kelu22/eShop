package application;

public abstract class User {
	protected int id;
	protected String username;
	protected String password;
	protected boolean isAdmin;
	
	public User(int id, String username, String password, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
