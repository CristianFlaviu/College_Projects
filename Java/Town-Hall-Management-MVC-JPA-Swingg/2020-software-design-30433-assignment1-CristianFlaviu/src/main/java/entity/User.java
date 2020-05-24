package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

	@Id
	private String id;

	@Column
	private String FirstName;

	@Column
	private String LastName;

	@Column
	private String email;

	@Column
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<House> houses;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Request> requests;


	public User()
	{
		this.id="default-User-id";
		this.FirstName="default-FirstName";
		this.LastName="default-LastName";
		this.email="popescu@yahoo.com";
		this.password="Yes";
		this.houses=new HashSet<>();
		this.requests=new HashSet<>();

	}
	public User(String userId,String FirstName, String LastName, String email, String password) {
		this.id=userId;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.email = email;
		this.password = password;
		this.requests=new HashSet<>();
		this.houses=new HashSet<>();
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public Set<House> getHouses() {
		return houses;
	}

	public void setHouses(Set<House> houses) {
		this.houses = houses;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}