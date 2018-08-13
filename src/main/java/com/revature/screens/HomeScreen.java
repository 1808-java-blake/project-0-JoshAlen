package com.revature.screens;

import com.revature.beans.User;

import java.text.NumberFormat;
import java.util.Scanner;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User user;
	private double userInputAmount;
	NumberFormat formatter = NumberFormat.getCurrencyInstance();

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
		switch (selection) {
		case "1":
			System.out.println("Please enter the amount you want to deposit.");
			user.setBalance(user.getBalance() + getUserInput());
			System.out.println("You deposited: " + formatter.format(userInputAmount));
			break;
		case "2":
			System.out.println("Please enter the amount you want to withdraw.");
			double check = getUserInput();
			if(check > user.getBalance()){
				System.out.println("You don't have enough funds in this account.");
				break;
			}
			user.setBalance(user.getBalance() - check);
			System.out.println("You withdraw: " + formatter.format(check));
			break;
		case "3":
			System.out.println("Your current balance is: " + formatter.format(user.getBalance()));
			break;
		case "4":
			System.out.println("Your transaction history: ");
			break;
		default:
			break;
		}

		return this;
	}

}
