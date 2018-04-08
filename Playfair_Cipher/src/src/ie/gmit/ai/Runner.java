package src.ie.gmit.ai;

import java.util.Scanner;
// resource from https://www.sanfoundry.com/java-program-enode-message-using-playfair-cipher/

public class Runner {

	
	
		static char cipher [][] = new char[5][5];
		static String message, decipherS, answer;
		static StringBuilder cipherTxt = new StringBuilder();
		static StringBuilder plainTxt = new StringBuilder();
		static int index = 0, alphIndex = 0, length;
		static char[] alphabet = {'A','B','C','D','E','F','G','H','I','K','L',
			'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		public static void main(String[] args) throws Exception, Throwable {

			System.out.println("*****************Playfair Cipher*******************");
			System.out.println("*********welcome to En/Decrypt program*************");
			System.out.println();

			
			Scanner kb = new Scanner (System.in);

			//do {
			System.out.println("********************************************************");
			System.out.print("Do you want to encrypt or decrypt (E/D)? ");
			System.out.println("\n********************************************************");
			answer = kb.nextLine().toUpperCase();		
			
			
		//	System.out.print("Please write the key: ");
			
			
			
			
			System.out.println("********************************************************");
			System.out.print("please input the text(example:tip.txt): ");
			System.out.println("\n********************************************************");
			
			Shufflekey sl = new Shufflekey();
			String fileName = kb.nextLine(); 
			String message = new FileHandler().readFile(fileName);
			String mess = sl.CiperBreaker(10, 50000, 1, message.toString());
			
			
			System.out.println("********************************************************");
			System.out.println("Copy of the above characters here or put partical key");
			String key = kb.nextLine().toUpperCase();
			key = removeDuplicates(key).replaceAll("\\s+","");

			for(int i = 0; i < 25; i++) {
				StringBuilder k = new StringBuilder(key);
				if(key.indexOf(alphabet[i]) == -1)  
					k.append(alphabet[i]);
				key = k.toString();
			}

			
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if(index < key.length()) {
						cipher[i][j] = key.charAt(index);
						index++;
					}
					else
						cipher[i][j] = alphabet[alphIndex++];
				}
			}
			
			key = key.replaceAll("J", " ");
			key = key.replaceAll("\\s", "");
			System.out.println("********************************************************");
			printCipher();
		//	message = message.readFile();
			System.out.println(message);
			System.out.println(message.length());
		
		//	message = message.replaceAll("\\s+","");

			// Remove J
			

	
			if(message.length() % 2 != 0) {
				StringBuilder ex = new StringBuilder(message);
				ex.append("X");
				message = ex.toString();
			}

			System.out.println("********************************************************");
			pairByPair(message);

			if(answer.equals("D"))
				System.out.println("\nThe plain text is: " + plainTxt);
			else if(answer.equals("E"))
				System.out.println("\nThe cipher text is: " + cipherTxt);
			else
				System.out.println("error command");
		}

		

		
		
		
		
		
		// remove the repeat character
		
		public static String removeDuplicates(String s) {
			StringBuilder noDupes = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				String si = s.substring(i, i + 1);
				if (noDupes.indexOf(si) == -1)
					noDupes.append(si);
			}
			return noDupes.toString();
		}

		// Print the cipher in console
		public static void printCipher() {
			System.out.println();
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					System.out.print(cipher[i][j] + " ");
				}
				System.out.println();
			}
		}


		

		
		public static void pairByPair(String message) {
			char A, B;

			// 
			if(message.length() % 2 == 0) {
				//System.out.println("even");
				length = message.length() - 1;
			}
			else {
				//System.out.println("odd");
				length = message.length();
			}


			//System.out.println(message);
			for(int i = 0; i < length; i += 2) {
				//System.out.println("letter A: " + message.charAt(i));
				A = message.charAt(i);
				if(i + 1 > length)
					B = 'X';
				else
					//System.out.println("letter : " + message.charAt(i+1));
					B = message.charAt(i + 1);

				// Add an 'X' character if there are two consecutive equal letters
				if(A == B) {
					B = 'X';

					// When an X is appended, if the string is uneven, add an X at the end
					// so it can be paired
					length += 2;
					if((length) % 2 == 1) {
						StringBuilder ex = new StringBuilder(message);
						ex.append("X");
						message = ex.toString();
					}
					i-= 1;		
				}
				
				if(A == 'J')
					A = 'I';
				if(B == 'J')
					B = 'I';

				if(answer.equals("E"))
					cipherTxt.append(findCharPosition(A, B, cipher));
				else
					plainTxt.append(findCharPosition(A, B, cipher));
			}
		}

		//***************************************************//

	
		public static String findCharPosition(char A, char B, char[][] matrix) {
			int msgIndex = 0, rowA = 0, rowB = 0, colA = 0, colB = 0;
			while(msgIndex < length) {
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 5; j++) {
						if(matrix[i][j] == A) {
							rowA = i;
							colA = j; }
						if(matrix[i][j] == B) {
							rowB = i;
							colB = j; }
					}
				}
				msgIndex++;
			}
			if(answer.equals("E"))
				decipherS = encrypt(rowA, colA, rowB, colB, matrix);
			else
				decipherS = decrypt(rowA, colA, rowB, colB, matrix);

			return decipherS;
		}

	

		// Encrypt the message
		public static String encrypt(int rowA, int colA, int rowB, int colB, char[][] matrix) {
			char s1, s2;
			StringBuilder pair = new StringBuilder();
			// if they are in the same row
			if(rowA == rowB){
				s1 = matrix[rowA][(colA + 1) % 5];
				s2 = matrix[rowB][(colB + 1) % 5];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			// if they are in the same column
			else if(colA == colB) {
				s1 = matrix[(rowA + 1) % 5][colA];
				s2 = matrix[(rowB + 1) % 5][colB];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			
			else if(rowA != rowB && colA != colB) {
				s1 = matrix[rowA][colB];
				s2 = matrix[rowB][colA];
				pair.append(s1).append(s2);
			}
			return pair.toString();
		}

		

		// Decipher the message
		public static String decrypt(int rowA, int colA, int rowB, int colB, char[][] matrix) {
			char s1, s2;
			StringBuilder pair = new StringBuilder();
			if(rowA == rowB){
				s1 = matrix[rowA][(colA + 4) % 5];
				s2 = matrix[rowB][(colB + 4) % 5];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			else if(colA == colB) {
				s1 = matrix[(rowA + 4) % 5][colA];
				s2 = matrix[(rowB + 4) % 5][colB];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			else if(rowA != rowB && colA != colB) {
				s1 = matrix[rowA][colB];
				s2 = matrix[rowB][colA];
				pair.append(s1).append(s2);
			}
			return pair.toString();
		}
	}