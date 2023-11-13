package main;

import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ATMProgram atm = new ATMProgram();
		
		while(true) {
			System.out.println("Welcome - Select an option:");
			System.out.println("1. Make withdraw");
			System.out.println("2. Make deposit");
			System.out.println("3. Total balance");
			System.out.println("4. Exit");
			
			int option = sc.nextInt();
			
			switch(option) {
			case 1:
				System.out.println("Insert the amount to withdraw");
				int r = sc.nextInt();
				atm.makeWithdraw(r);
				System.out.println("\n====================================\n");
				break;
			case 2:
				System.out.println("Insert the amount to deposit");
				int d = sc.nextInt();
				atm.makeDeposit(d);
				System.out.println("\n====================================\n");
				break;
			case 3:
				System.out.println("Balance: $" + atm.getBalance());
				System.out.println("\n====================================\n");
				break;
			case 4:
				System.exit(0);
				sc.close();
			}
		}
	}

}
