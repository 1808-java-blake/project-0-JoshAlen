package com.revature.screens;

import com.revature.beans.User;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User user;
	private double userInputAmount;

	private String userTransactionLog;

	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public HomeScreen(User currentUser) {
		user = currentUser;
	}

	public double getUserInput(){
		return userInputAmount =  Double.valueOf(scan.nextLine());
	}


	public Screen start() {
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to deposit money");
		System.out.println("Enter 2 to withdraw money");
		System.out.println("Enter 3 to view your balance");
		System.out.println("Enter 4 to view your transaction history");

		String selection = scan.nextLine();

		return this;
	}

}
