package src.ie.gmit.ai;

import java.util.Scanner;

public class Runner {

	
	
		static char cipher [][] = new char[5][5];
		static String message, decipherS, answer;
		static StringBuilder cipherTxt = new StringBuilder();
		static StringBuilder plainTxt = new StringBuilder();
		static int index = 0, alphIndex = 0, length;
		static char[] alphabet = {'A','B','C','D','E','F','G','H','I','K','L',
			'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		public static void main(String[] args) {

			System.out.println("*****Playfair Cipher*****");
			System.out.println();

			System.out.print("Please write the key: ");
			Scanner kb = new Scanner (System.in);
			String key = kb.nextLine().toUpperCase();
			key = removeDuplicates(key).replaceAll("\\s+","");


			//do {
			System.out.print("Do you want to encrypt or decrypt (E/D)? ");
			answer = kb.nextLine().toUpperCase();
			//}while(!answer.equals("E") || !answer.equals("D"));

			System.out.print("\nPlease write the message: ");
			message = kb.nextLine().toUpperCase();

			// Remueve espacios en blanco en el string
			message = message.replaceAll("\\s+","");

			// Remove J
			key = key.replaceAll("J", " ");
			key = key.replaceAll("\\s", "");


			// Remueve las letras que se repitan entre la llave y el alfabeto
			for(int i = 0; i < 25; i++) {
				StringBuilder k = new StringBuilder(key);
				if(key.indexOf(alphabet[i]) == -1)  // -1 significa que la letra no se repite
					k.append(alphabet[i]);
				key = k.toString();
			}

			// Añade la llave y el alfabeto a la matriz
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

			printCipher();

			// Agrega una X al final del mensaje si su numero de caracteres es impar	
			if(message.length() % 2 != 0) {
				StringBuilder ex = new StringBuilder(message);
				ex.append("X");
				message = ex.toString();
			}

			pairByPair(message);

			if(answer.equals("D"))
				System.out.println("\nThe plain text is: " + plainTxt);
			else if(answer.equals("E"))
				System.out.println("\nThe cipher text is: " + cipherTxt);
			else
				System.out.println("Valores invalidos, por favor intente de nuevo");

			kb.close();
		}

		//***************************************************//

		
		
		
		
		
		// Remueve letras que se repitan
		
		public static String removeDuplicates(String s) {
			StringBuilder noDupes = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				String si = s.substring(i, i + 1);
				if (noDupes.indexOf(si) == -1)
					noDupes.append(si);
			}
			return noDupes.toString();
		}

		// Imprime el cipher en consola
		public static void printCipher() {
			System.out.println();
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					System.out.print(cipher[i][j] + " ");
				}
				System.out.println();
			}
		}


		//***************************************************//

		// Envia cada par de letras consecutivas a decifrar o cifrar
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

				// Agrega un caracter 'X' si hay dos letras iguales consecutivas
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
				// Sustituye los caracteres de 'J' por caracteres de 'I'
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

		// Busca la localizacion en la matriz de cada par de letras del mensaje
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

		//***************************************************//

		// Cifra el mensaje
		public static String encrypt(int rowA, int colA, int rowB, int colB, char[][] matrix) {
			char s1, s2;
			StringBuilder pair = new StringBuilder();
			// si estan en la misma fila
			if(rowA == rowB){
				s1 = matrix[rowA][(colA + 1) % 5];
				s2 = matrix[rowB][(colB + 1) % 5];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			// si estan en la misma columna
			else if(colA == colB) {
				s1 = matrix[(rowA + 1) % 5][colA];
				s2 = matrix[(rowB + 1) % 5][colB];
				pair.append(s1).append(s2);
				return pair.toString();
			}
			// si forman un rectangulo
			else if(rowA != rowB && colA != colB) {
				s1 = matrix[rowA][colB];
				s2 = matrix[rowB][colA];
				pair.append(s1).append(s2);
			}
			return pair.toString();
		}

		//***************************************************//

		// Decifra el mensaje
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

