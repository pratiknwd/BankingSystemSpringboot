//package com.virtusa.banking.model;
//
//import java.util.Scanner;
//
//import com.virtusa.banking.controller.AccountController;
//import com.virtusa.banking.controller.AuthController;
//import com.virtusa.banking.repository.AccountDAO;
//import com.virtusa.banking.repository.AccountDAOImpl;
//import com.virtusa.banking.repository.TransactionDAO;
//import com.virtusa.banking.repository.TransactionDAOImpl;
//import com.virtusa.banking.repository.UserDAO;
//import com.virtusa.banking.repository.UserDAOImpl;
//
//public class Main {
//	
//	
//    
//	static TransactionDAO transactionDAO = new TransactionDAOImpl();
//	static AccountDAO accountDAO = new AccountDAOImpl();
//	static UserDAO userDAO = new UserDAOImpl();
//	static AccountController accountController = new AccountController(accountDAO, transactionDAO);
//	static AuthController authController = new AuthController(userDAO);
//
//	public static void main(String[] args) {
//		
//
//		Scanner sc = new Scanner(System.in);
//		// Initialize Hibernate
////		 HibernateUtil.getSessionFactory();
//		 
//		boolean running = true;
//
//		while (running) {
//			System.out.println("1. Online Banking");
//			System.out.println("2. Deposit Money");
//			System.out.println("3. Withdraw Money");
//			System.out.println("4. Send Money");
//			System.out.println("5. Exit");
//			System.out.print("Enter your choice: ");
//			int choice = sc.nextInt();
//			sc.nextLine(); 
//
//			switch (choice) {
//
//			case 1:
//				onlineBankingMenu();
//				break;
//			case 2:
//				depositMoneyMenu();
//				break;
//			case 3:
//				withdrawMoneyMenu();
//				break;
//			case 4:
//				// logic
//				break;
//			case 5:
//				System.out.println("Exiting...");
//				running = false;
//				break;
//			default:
//				System.out.println("Invalid choice");
//				break;
//			} // end of switch
//
//		} // end of while loop
//
//		sc.close();
//		System.out.println("*****************************************************************");
//		System.out.println("Thank you for using our online banking system");
//
//	} // end of main
//
//	private static void onlineBankingMenu() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("----------------------------------Online Banking System----------------------------------");
//		System.out.println("1. Create a new Bank Account");
//		System.out.println("2. Register for Internet Banking");
//		System.out.println("3. Login into Internet Banking");
//		int choice = sc.nextInt();
//		sc.nextLine();
//
//		switch (choice) {
//		case 1:
//			accountController.createAccount();
//			break;
//		case 2:
//			authController.registerForInternetBankingAccount();
//			break;
//		case 3:
//			int userId = authController.logIntoInternetBanking();
//			if (userId != 0) {
//				loggedInMenu(userId);
//			}
//			break;
//		default:
//			System.out.println("Invalid choice. Please try again.");
//			break;
//
//		} // end of switch
//	}
//
//	private static void loggedInMenu(int userId) {
//		Scanner sc = new Scanner(System.in);
//
//		boolean loggedIn = true;
//		while (loggedIn) {
//			System.out.println("1. Check Balance");
//			System.out.println("2. Transfer Money");
//			System.out.println("3. Logout");
//			System.out.print("Enter your choice: ");
//			int choice = sc.nextInt();
//			sc.nextLine();
//
//			switch (choice) {
//			case 1:
//				accountController.checkBalance(userId);
//				break;
//			case 2:
//				accountController.transferMoney(userId);
//				break;
//			case 3:
//				System.out.println("Logging out...");
//				loggedIn = false;
//				break;
//			default:
//				System.out.println("Invalid choice. Please try again.");
//				break;
//
//			} // end of switch
//
//		} // end of while loop
//
//	} // end of loggedInMenu method
//
//	private static void depositMoneyMenu() {
//		Scanner sc = new Scanner(System.in);
//		boolean insideDepositMoneyMenu = true;
//
//
//		while (insideDepositMoneyMenu) {
//			System.out.println("1. Through Cash Deposit Machine(CDM)");
//			System.out.println("2. Through Cash Deposit Form(CDF)");
//			System.out.println("3. Exit to Main Menu");
//			System.out.print("Enter your choice: ");
//			int choice = sc.nextInt();
//			sc.nextLine();
//
//			switch (choice) {
//			case 1:
//				accountController.depositThroughCDM();
//				break;
//			case 2:
//				accountController.depositThroughCDF();
//				break;
//			case 3:
//				System.out.println("Exiting to Main Menu");
//				insideDepositMoneyMenu = false;
//				break;
//			default:
//				System.out.println("Invalid choice. Please try again.");
//				break;
//
//			} // end of switch
//
//		} // end of while
//
//	}
//
//	public static void withdrawMoneyMenu() {
//		Scanner sc = new Scanner(System.in);
//		boolean insideWithdrawMoneyMenu = true;
//
//		while (insideWithdrawMoneyMenu) {
//			System.out.println("1. Through ATM");
//			System.out.println("2. Through Withdrawal Form");
//			System.out.println("3. Exit to Main Menu");
//			System.out.print("Enter your choice: ");
//			int choice = sc.nextInt();
//			sc.nextLine();
//
//			switch (choice) {
//			case 1:
//				accountController.withdrawThroughATM();
//				break;
//			case 2:
//				accountController.withdrawThroughWF();
//				break;
//			case 3:
//				System.out.println("Exiting to Main Menu");
//				insideWithdrawMoneyMenu = false;
//				break;
//			default:
//				System.out.println("Invalid choice. Please try again.");
//				break;
//
//			} // end of switch
//
//		} // end of while
//	}
//
//}