package com.example.rest.controller.restcontroller.versionimg;

public class name {
	
	private String firstname;
	private String lastname;
	public name(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	@Override
	public String toString() {
		return "PersonV2 [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
