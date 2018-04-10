package ie.gmit.sw.ai;

public class Pairbypair {
	
	static String decipherS;
	static char cipher [][] = new char[5][5];
	static int index = 0, alphIndex = 0, length;
	static StringBuilder cipherTxt = new StringBuilder();
	static StringBuilder plainTxt = new StringBuilder();
	static char[] alphabet = {'A','B','C','D','E','F','G','H','I','K','L',
			'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public String pairByPair(String message, String answer, String key) {
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


		//System.out.println(message);
		for(int i = 0; i < length; i += 2) {
			
			A = message.charAt(i);
			if(i + 1 > length)
				B = 'X';
			else
				
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

			if(answer.equals("E")){
				cipherTxt.append(findCharPosition(A, B, cipher, answer));										
			}
			else{
				plainTxt.append(findCharPosition(A, B, cipher, answer));
			}
			
			
		}
		printCipher();
		
		if(answer.equals("D"))
			System.out.println("\nThe plain text is: " + plainTxt);
		else if(answer.equals("E"))
			System.out.println("\nThe cipher text is: " + cipherTxt);
		else
			System.out.println("error command");
		
		
		
		return answer;
		
		

		
		
	}
	
	





	public static String findCharPosition(char A, char B, char[][] matrix,String answer) {
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



	public static StringBuilder getPlainTxt() {
		return plainTxt;
	}


	public static void setPlainTxt(StringBuilder plainTxt) {
		Pairbypair.plainTxt = plainTxt;
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
	
	
	// output the key square.
	public void printCipher() {
		System.out.println();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(cipher[i][j] + " ");
			}
			System.out.println();
		}
	

}
	public String removeDuplicates(String s) {
		StringBuilder noDupes = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			String si = s.substring(i, i + 1);
			if (noDupes.indexOf(si) == -1)
				noDupes.append(si);
		}
		return noDupes.toString();
	}








}
