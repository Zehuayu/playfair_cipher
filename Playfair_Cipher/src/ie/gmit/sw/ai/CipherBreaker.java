package ie.gmit.sw.ai;

import java.util.Scanner;
// resource from https://www.sanfoundry.com/java-program-enode-message-using-playfair-cipher/

public class CipherBreaker {

	
	
		
		static String answer;
		
		public static void main(String[] args) throws Exception, Throwable {
			Pairbypair pbp  = new Pairbypair();
           //this is the command line 
			System.out.println("*****************Playfair Cipher*******************");
			System.out.println("*********welcome to En/Decrypt program*************");
			System.out.println();

			
			Scanner kb = new Scanner (System.in);

			//do {
			// give the hint let user to pick e(encrypt) or d(decrypt)
			System.out.println("********************************************************");
			System.out.print("Do you want to encrypt or decrypt (E/D)? ");
			System.out.println("\n********************************************************");
			answer = kb.nextLine().toUpperCase();		
			
			
			
			
			
			System.out.println("********************************************************");
			System.out.print("please input the text(example:tips.txt): ");
			System.out.println("\n********************************************************");
			
			SimulatedAnnealing sl = new SimulatedAnnealing();
			String fileName = kb.nextLine(); 
			String message = new FileHandler().readFile(fileName);
			
			if(message.length() % 2 != 0) {
				StringBuilder ex = new StringBuilder(message);
				ex.append("X");
				message = ex.toString();
			}
		   
			sl.CiperBreaker(100, 55000, 1, message.toString());
		   
			
			
			System.out.println("********************************************************");
			System.out.println("the key is " + sl.getKeymess());
			String key = sl.getKeymess();
			
			
		    key = pbp.removeDuplicates(key).replaceAll("\\s+","");
		


			System.out.println("********************************************************");
			
		

			//	message = message.readFile();
			System.out.println(message);
			System.out.println("the string has " + message.length()+ " character");
		
	
			

			
			System.out.println("********************************************************");
			
			pbp.pairByPair(message, answer, key);

			
            kb.close();
		}


	}
	