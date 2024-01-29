package com.example.rest.controller.restcontroller.versionimg;

public class PersonV2 {
	private name name;

	public PersonV2(com.example.rest.controller.restcontroller.versionimg.name name) {
		super();
		this.name = name;
	}

	public name getName() {
		return name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}


}
