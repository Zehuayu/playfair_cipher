package src.ie.gmit.ai;

import java.util.Scanner;

public class Runner {

	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("*****Playfair Cipher*****");
		System.out.println();
		int pick = 0;
		boolean exit = false;		
		//String fileName = "";

		do {

			System.out.printf("Please type number to pick Decryptor or encryptor"+"\n"+"1:     Decrypt a file"+"\n"+"2:   "
					+ "  Encrypt a file"+"\n"+"3     Exit\n");
			pick = input.nextInt();
			

			
		}while(!exit);
		input.close();

		System.out.println("Bye bye...");
	}
}
