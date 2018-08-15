package com.revature.screens;

import com.revature.beans.User;
import com.revature.daos.UserSerializer;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private User user;
	private double userInputAmount;

	private String userTransactionLog;

	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

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

				userTransactionLog = "You deposited: " + formatter.format(userInputAmount);
				System.out.println(userTransactionLog);

				userTransactionLog = userTransactionLog + " on " + dtf.format(now);
				user.addTransactionLog(userTransactionLog);

				break;
			case "2":
				System.out.println("Please enter the amount you want to withdraw.");
				double check = getUserInput();

				if(check > user.getBalance()){
					System.out.println("You don't have enough funds in this account.");
					break;
				}

				user.setBalance(user.getBalance() - check);
				userTransactionLog = "You withdraw: " + formatter.format((check));

				userTransactionLog = userTransactionLog +  " on " + dtf.format(now);
				user.addTransactionLog(userTransactionLog);

				break;
			case "3":
                System.out.println("--------------------");
				System.out.println("Your current balance is: " + formatter.format(user.getBalance()));
                System.out.println("--------------------");
				break;
			case "4":
				System.out.println("--------------------");
				System.out.println("Your transaction history: ");
				user.printTransactionLog();
				break;
			default:
				break;
		}

		return this;
	}

}
