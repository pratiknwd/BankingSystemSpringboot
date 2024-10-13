//package com.virtusa.banking.model;
//
//import com.virtusa.banking.repository.AccountDAOImpl;
//import com.virtusa.banking.repository.BankDAOImpl;
//import com.virtusa.banking.repository.FinancialProductDAO;
//import com.virtusa.banking.repository.FinancialProductDAOImpl;
//import com.virtusa.banking.repository.UserDAOImpl;
//
//public class Main4Create {
//	public static void main(String[] args) {
//
//		BankDAOImpl bankDAOImpl = new BankDAOImpl();
////		BranchDAOImpl branchDAOImpl = new BranchDAOImpl();
//		AccountDAOImpl accountDAO = new AccountDAOImpl();
////		TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
//		UserDAOImpl userDAOImpl = new UserDAOImpl();
//		FinancialProductDAO financialProductDAOImpl = new FinancialProductDAOImpl();
//
//		UniqueIDGenerator uniqueIDGenerator = new UniqueIDGenerator();
////		AccountServiceImpl accountServiceImpl = new AccountServiceImpl(accountDAO, transactionDAOImpl);
//
//// ------BANK-----------------------------------------------------------------
//		
//		
//
//		Bank hdfc = new Bank(1, "HDFC");
//		Bank icici = new Bank(2, "ICICI");
//		Bank boi = new Bank(3, "BOI");
//		Bank axis = new Bank(4, "AXIS");
//
//		Branch hdfcBranchMadhapur = new Branch("HDFCMadhapur", "Madhapur");
//		Branch hdfcBranchKothaguda = new Branch("HDFCKothaguda", "Kothaguda");
//
//		Branch iciciBranchGachibowli = new Branch("ICICIGachibowli", "Gachibowli");
//		Branch iciciBranchRaidurg = new Branch("ICICIRaidurg", "Raidurg");
//
//		Branch boiBranchKondapur = new Branch("BOIKondapur", "Kondapur");
//		Branch boiBranchGowlidoddy = new Branch("BOIGowlidoddy", "Gowlidoddy");
//
//		Branch axisBranchManikonda = new Branch("AXISManikonda", "Manikonda");
//		Branch axisBranchTngo = new Branch("AXISTngo", "Tngo");
//
//		hdfcBranchMadhapur.setBank(hdfc);
//		hdfcBranchKothaguda.setBank(hdfc);
//
//		iciciBranchGachibowli.setBank(icici);
//		iciciBranchRaidurg.setBank(icici);
//
//		boiBranchKondapur.setBank(boi);
//		boiBranchGowlidoddy.setBank(boi);
//
//		axisBranchManikonda.setBank(axis);
//		axisBranchTngo.setBank(axis);
//
//		hdfc.getBranches().add(hdfcBranchMadhapur);
//		hdfc.getBranches().add(hdfcBranchKothaguda);
//
//		icici.getBranches().add(iciciBranchGachibowli);
//		icici.getBranches().add(iciciBranchRaidurg);
//
//		boi.getBranches().add(boiBranchKondapur);
//		boi.getBranches().add(boiBranchGowlidoddy);
//
//		axis.getBranches().add(axisBranchManikonda);
//		axis.getBranches().add(axisBranchTngo); // Set Branch to Bank
//
//		bankDAOImpl.saveBank(hdfc);
//		bankDAOImpl.saveBank(icici);
//		bankDAOImpl.saveBank(boi);
//		bankDAOImpl.saveBank(axis);
//
//// ------BANK-----------------------------------------------------------------
//
//// ------BRANCH---------------------------------------------------------------
//
//		// Get Branch By IFSC Branch
////   branch1=branchDAOImpl.getBranchByIfsc("IFSCHDFC");
////   System.out.println(branch1);
//
//// ------BRANCH---------------------------------------------------------------
//
//// ------USER and ACCOUNT----------------------------------------------------------------------
//
//		User user1 = new User(uniqueIDGenerator.generateUniqueUserId(), "Vishal Verma", UserType.Customer,
//				"Bion Site, Kondapur, Hyderabad, Telangana", 7488280927L, "vishal@gmail.com");
//
//		User user2 = new User(uniqueIDGenerator.generateUniqueUserId(), "Ambar Gupta", UserType.Admin,
//				"Manjeera Towers, Tellapur, Hyderabad, Telangana", 8470982746L, "ambar@gmail.com");
//
//		User user3 = new User(uniqueIDGenerator.generateUniqueUserId(), "Bhagya Makhija", UserType.Customer,
//				"Mantri Celestia, Gowlidoddy, Hyderabad, Telangana", 7452047256L, "bhagya@gmail.com");
//
//		User user4 = new User(uniqueIDGenerator.generateUniqueUserId(), "Sunil Verma", UserType.Customer,
//				"789 Gachibowli, Hyderabad, Telangana", 835247509L, "sunil.verma@gmail.com");
//
//		User user5 = new User(uniqueIDGenerator.generateUniqueUserId(), "Priya Desai", UserType.Customer,
//				"321 Madhapur, Hyderabad, Telangana", 9376028436L, "priya.desai@gmail.com");
//
//		User user6 = new User(uniqueIDGenerator.generateUniqueUserId(), "Arjun Reddy", UserType.Customer,
//				"654 Ameerpet, Hyderabad, Telangana", 7492856392L, "arjun.reddy@gmail.com");
//
//		User user7 = new User(uniqueIDGenerator.generateUniqueUserId(), "Neha Gupta", UserType.Customer,
//				"987 Begumpet, Hyderabad, Telangana", 9475629475L, "neha.gupta@gmail.com");
//
//		User user8 = new User(uniqueIDGenerator.generateUniqueUserId(), "Rajesh Patel", UserType.Customer,
//				"543 Kukatpally, Hyderabad, Telangana", 6483648292L, "rajesh.patel@gmail.com");
//
//		// Account
//		// Account
//		Account acc1 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.SAVINGS,
//				"IFSCHDFC");
//		acc1.setBranch(hdfcBranchMadhapur);
//		acc1.setUser(user1);
//		acc1.setBalance(1000);
//		user1.getAccounts().add(acc1);
//
//		userDAOImpl.addUser(user1);
//
//		Account acc2 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.CHECKING,
//				"IFSCHDFC");
//		acc2.setBranch(hdfcBranchKothaguda);
//		acc2.setUser(user2);
//		acc2.setBalance(2000);
//		user2.getAccounts().add(acc2);
//
//		userDAOImpl.addUser(user2);
//
//		Account acc3 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.SAVINGS,
//				"IFSCICICI");
//		acc3.setBranch(iciciBranchGachibowli);
//		acc3.setUser(user3);
//		acc3.setBalance(2000);
//		user3.getAccounts().add(acc3);
//
//		userDAOImpl.addUser(user3);
//
//		Account acc4 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.CHECKING,
//				"IFSCICICI");
//		acc4.setBranch(iciciBranchRaidurg);
//		acc4.setUser(user4);
//		acc4.setBalance(2000);
//		user4.getAccounts().add(acc4);
//
//		userDAOImpl.addUser(user4);
//
//		Account acc5 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.INVESTMENT,
//				"IFSCBOI");
//		acc5.setBranch(boiBranchKondapur);
//		acc5.setUser(user5);
//		acc5.setBalance(2000);
//		user5.getAccounts().add(acc5);
//
//		userDAOImpl.addUser(user5);
//
//		Account acc6 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.INVESTMENT,
//				"IFSCBOI");
//		acc6.setBranch(boiBranchGowlidoddy);
//		acc6.setUser(user6);
//		acc6.setBalance(2000);
//		user6.getAccounts().add(acc6);
//
//		userDAOImpl.addUser(user6);
//
//		Account acc7 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.SAVINGS,
//				"IFSCAXIS");
//		acc7.setBranch(axisBranchManikonda);
//		acc7.setUser(user7);
//		acc7.setBalance(2000);
//		user7.getAccounts().add(acc7);
//
//		userDAOImpl.addUser(user7);
//
//		Account acc8 = new Account(uniqueIDGenerator.generateUnique14DigitAccountNumber(), AccountType.SAVINGS,
//				"IFSCAXIS");
//		acc8.setBranch(axisBranchTngo);
//		acc8.setUser(user8);
//		acc8.setBalance(2000);
//		user8.getAccounts().add(acc8);
//
//		userDAOImpl.addUser(user8);
//
////		accountServiceImpl.depositFunds(acc1, 594.00, ModeOfTransaction.Mobile_App);
////		accountServiceImpl.depositFunds(acc2, 190.00, ModeOfTransaction.Online_Banking);
//
//		FinancialProduct f1 = new FinancialProduct("Credit Card", 14, 0, 0, 0);
//		FinancialProduct f2 = new FinancialProduct("Debit Card", 0, 0, 0, 0);
//		f1.setAccount(acc1);
//		financialProductDAOImpl.addFinancialProduct(f1);
//
//		f2.getBanks().add(hdfc);
//		financialProductDAOImpl.addFinancialProduct(f2);
//
//		acc1.getFinancialProducts().add(f1);
//		hdfc.getFinancialProductsOffered().add(f2);        
//
//		accountDAO.updateAccount(acc1);
//		bankDAOImpl.updateBank(hdfc);
//		
//		System.out.println("Creation of dummy data completed!");
//		// accountServiceImpl.depositFunds(account, 100000.00, ModeOfTransaction.Mobile_App);
//
//// ------USER and ACCOUNT----------------------------------------------------------------------
//
//	}
//
//}
