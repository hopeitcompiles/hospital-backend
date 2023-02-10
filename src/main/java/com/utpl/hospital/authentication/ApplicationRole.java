package com.utpl.hospital.authentication;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {

	PATIENT(Sets.newHashSet(
			ApplicationPermission.APPOINTMENT_READ,
			ApplicationPermission.DOCTOR_READ,
			ApplicationPermission.SPECIALIZATION_READ
	),0),
	DOCTOR(combine_set(PATIENT.getPermissions(), Sets.newHashSet(
			ApplicationPermission.PATIENT_READ,
			ApplicationPermission.APPOINTMENT_WRITE
			)),1),

	SECRETARY(combine_set(DOCTOR.getPermissions(), Sets.newHashSet(
			ApplicationPermission.DOCTOR_WRITE,
			ApplicationPermission.PATIENT_WRITE,
			ApplicationPermission.USER_WRITE)),1),
	ADMIN(combine_set(SECRETARY.getPermissions(),Sets.newHashSet(
			ApplicationPermission.SPECIALIZATION_WRITE,
			ApplicationPermission.SPECIALIZATION_DELETE,
			ApplicationPermission.APPOINTMENT_DELETE,
			ApplicationPermission.USER_DELETE,
			ApplicationPermission.PATIENT_DELETE,
			ApplicationPermission.DOCTOR_DELETE,
			ApplicationPermission.ADMIN)),2),
	ROOT(combine_set(ADMIN.getPermissions(),Sets.newHashSet(
			ApplicationPermission.GOD_MODE)),9000);
	private final Set<ApplicationPermission> permissions;
	private final int level;
	ApplicationRole(Set<ApplicationPermission> permissions,int level) {
		this.permissions = permissions;
		this.level=level;
	}
	public static Set<ApplicationPermission> combine_set(Set<ApplicationPermission> a,Set<ApplicationPermission> b){
		return new HashSet<ApplicationPermission>(){{
			addAll(a);
			addAll(b);
		}};
	}
	public Set<ApplicationPermission> getPermissions() {
		return permissions;
	}
	
	//cast the permissions to Set<SimpleGrantedAuthority>
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions=
		getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permissions;
	}
	public int getLevel() {
		return level;
	}
}
