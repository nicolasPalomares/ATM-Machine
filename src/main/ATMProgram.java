package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/*
	The pourpose of this program is to make an Automated teller machine, in which the user accesses various options like "Withdraw", "Deposit" and "Total balance".
	The first time the user runs the program, a text file is created, so the balance is saved there though a "properties" value. With this, the balance
	is ready to be readed and used for future sessions.
*/

public class ATMProgram {
	
	private double balance = 0.0;
	private File file = new File("balance.txt");;
	private FileWriter fw;
	private BufferedWriter bw;
	
	public ATMProgram() {
		// The constructor is in charge of creating the text file if it doesn't exist; it also reads the file to recover the last session's balance.
		if(!file.exists()) {
			editATMfile(balance);
		}
		
		balance = readFile();
	}
	
	public void makeWithdraw(double withdraw) {
		/* 
			Withdrawals method. It reads the text file: if there is enough balance, withdraw is completed and the file is edited; or else,
			if there is not enough balance, a message is shown and the withdraw can't be completed.
		*/
		
		double fileValue = readFile();
		
		if(fileValue >= withdraw) {
			fileValue -= withdraw;
			System.out.println("Successful withdraw - Please grab your money");
			editATMfile(fileValue);
		}
		else {
			System.out.println("Error - You don't have enough balance to make a withdraw");
		}
		
		// New balance:
		balance = fileValue;
	}
	
	public void makeDeposit(double deposit) {
		// Deposits method; reads the file and updates the new balance.
		double fileValue = readFile();
		
		fileValue += deposit;
		System.out.println("Successful deposit");
		editATMfile(fileValue);
		
		// New balance:
		balance = fileValue;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void editATMfile(double value) {
		// Edits the text file through a BufferedWriter.
		try {
			file.createNewFile();
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
			bw.write("balance=" + value);
			bw.close();
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public double readFile() {
		// Reads the text file with a "properties" value to recover and edit the balance.
		FileInputStream in;
		Properties props = null;
		double prop = 0.0;
		
		try {
			in = new FileInputStream("balance.txt");
			props = new Properties();
			props.load(in);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String balanceString = props.getProperty("balance");
		if(balanceString != null) {
			prop = Double.parseDouble(balanceString);
		}
		
		return prop;
	}

}
