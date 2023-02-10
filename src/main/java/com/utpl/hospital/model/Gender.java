package com.utpl.hospital.model;

import javax.persistence.Entity;

public enum Gender {
	UNDEFINED("No definido"),
	FEMALE("Masculino"),
	MALE("Femenino"),
	OTHER("Otro");
	private final String name;
	private Gender(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
