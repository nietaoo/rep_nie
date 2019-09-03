package com.emp.entity;

public class permission {
	
	
	public permission(int id, String permission_name) {
		super();
		this.id = id;
		this.permission_name = permission_name;
	}
	private int id;
	private String permission_name;
	public permission() {
		super();
	
	}
	@Override
	public String toString() {
		return "permission [id=" + id + ", permission_name=" + permission_name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}

}
