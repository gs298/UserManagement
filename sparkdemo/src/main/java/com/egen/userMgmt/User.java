package com.egen.userMgmt;

import java.util.Date;
import java.util.UUID;

public class User {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private String dateCreated;
	private Company company;
	private String profilePic;

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String string) {
		this.dateCreated = string;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

}
