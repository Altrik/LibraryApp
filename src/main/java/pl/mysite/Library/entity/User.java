package pl.mysite.Library.entity;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
    @Column(unique = true)
	private String login; 
	@NotEmpty
	private String password;
	@NotEmpty
    @Column(unique = true)
	private String email; 
	@CreationTimestamp
	private Date lastLogIn;
	@CreationTimestamp
	private Date created;
	@ColumnDefault("false")
	private boolean is_Admin;
	
	public User () {}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	public User(String login, String password, String email, Date lastLogIn, Date created, Boolean is_Admin) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.lastLogIn = lastLogIn;
		this.created = created;
		this.is_Admin = is_Admin;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() { 
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() { 
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLogIn() {
		return lastLogIn;
	}
	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}
	public long getId() {
		return id;
	}
	public boolean isIs_Admin() {
		return is_Admin;
	}
	public void setIs_Admin(boolean is_Admin) {
		this.is_Admin = is_Admin;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}