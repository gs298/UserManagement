package com.egen.userMgmt;

public class Main {
	public static void main(String[] args) {
		new UserController(new UserService());
	}
}
