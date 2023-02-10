package com.utpl.hospital.authentication;

public enum ApplicationPermission {
	APPOINTMENT_READ("appointment:read"),
	APPOINTMENT_WRITE("appointment:write"),
	APPOINTMENT_DELETE("appointment:delete"),
	PATIENT_READ("patient:read"),
	PATIENT_WRITE("patient:write"),
	PATIENT_DELETE("patient:delete"),
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	USER_DELETE("user:delete"),
	DOCTOR_READ("doctor:read"),
	DOCTOR_WRITE("doctor:write"),
	DOCTOR_DELETE("doctor:delete"),
	SPECIALIZATION_READ("specialization:read"),
	SPECIALIZATION_WRITE("specialization:write"),
	SPECIALIZATION_DELETE("specialization:delete"),
	ADMIN("admin"),
	GOD_MODE("godmode");
	private final String permission;

	private ApplicationPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
}
