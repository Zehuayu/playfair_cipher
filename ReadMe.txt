Playfair Cipher AI Project
Student name: Zehua Yu   Student Number: G00307270 Lecturer: Jhon Healy
Github: https://github.com/Zehuayu/playfair_cipher

The introduction of Project and requirement

You are required to use the simulated annealing algorithm to break a Playfair Cipher. Your application should have the following minimal set of features: • A menu-driven command line UI that enables a cipher-text source to be specified (a file or URL) and an output destination file for decrypted plain-text. • Decrypt cipher-text with a simulated annealing algorithm that uses a log-probability and n-gram statistics as a heuristic evaluation function. A full description of the Playfair Cipher, a simulated annealing algorithm for breaking ciphers and n-gram statistics are provided below as supplementary material. Note that extra marks will only be given for features that directly relate to the content covered in this module. Do not waste your time developing a complex GUI application.



The Playfair Cipher


The Playfair system was invented by Charles Wheatstone, who first described it in 1854.
The Playfair cipher or Playfair square or Wheatstone-Playfair cipher is a manual symmetric encryption technique and was the first literal digram substitution cipher. The scheme was invented in 1854 by Charles Wheatstone, but bears the name of Lord Playfair for promoting its use.

the Pairbypair class 

this class contain the main encrypt functionality

The FileHander Class

the main function is get the information of text. And transfer the type to String


ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			is.close();

and update the line



The CipherBreaker Class 

This is main class, there is the command line, by this class, the java can run this process.

public static void main(String[] args) throws Exception, Throwable {

			System.out.println("*****************Playfair Cipher*******************");
			System.out.println("*********welcome to En/Decrypt program*************");
			System.out.println();

			
			Scanner kb = new Scanner (System.in);

			//do {
			System.out.println("********************************************************");
			System.out.print("Do you want to encrypt or decrypt (E/D)? ");
			System.out.println("\n********************************************************");



The HashGram Class

his file is used to store the contents of the 4-grams file. Key is gram, value is count. Create a static variable named total, used to calculate the total count of the entire file.

The SimulatedAnnealing Class

This file uses the shuffle algorithm to obtain the initial key and split it into a 4-grams array. Traverse the cryptogram 4-gram, if it exists, take count and calculate the logarithmic probabilityAnd includes the simulated annealing algorithm



Output result

*****************Playfair Cipher*******************
*********welcome to En/Decrypt program*************

********************************************************
Do you want to encrypt or decrypt (E/D)? 
********************************************************
d
********************************************************
please input the text(example:tip.txt): 
********************************************************
tip.txt

Simulated Annealing Algorithm Start............
Simulated Annealing Algorithm End----->[Keys=ZPYXWUZBQYJYYCTLPSBATBSOM; Log Similarity=-92.5707578240755]
********************************************************
Copy of the key characters here or put partical key
ZPYXWUZBQYJYYCTLPSBATBSOM
********************************************************

Z P Y X W 
U B Q J C 
T L S A O 
M D E F G 
H I K N R 
HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUXVYCBXABQZQZURHQD………
the string has 742 character
********************************************************

The plain text is: NMYUSPYMGPKHLHBYBEZHKZOD…………….






Resource and some knowledge point




Rule 1: Diagraph Letters in Different Rows and Columns Create a “box” inside the matrix with each diagraph letter as a corner and read off the letter at the opposite corner of the same row, e.g. AR→SI. This can also be expressed as cipher(B, P)={matrix[row(B)][col(P)], matrix[row(P)][col(B)]}. Reverse the process to decrypt a cypher-text diagraph. 

Department of Computer Science & Applied Physics, GMIT 
CP4SD – Advanced Object Oriented Design Principles & Patterns 

Rule 2: Diagraph Letters in Same Row Replace any letters that appear on the same row with the letters to their immediate right, wrapping around the matrix if necessary. Decrypt by replacing cipher-text letters the with letters on their immediate left. 
Rule 3: Diagraph Letters in Same Column Replace any letters that appear on the same column with the letters immediately below, wrapping back around the top of the column if necessary. Decrypt by replacing cipher- text letters the with letters immediately above. 
The Playfair Cipher suffers from the following three basic weaknesses that can be exploited to break the cipher, even with a pen and paper: 
Repeated plain-text digrams will create repeated cipher-text digrams.  
Digram frequency counts can reveal the most frequently occurring English digrams.  
The most frequently occurring cipher-text letters are likely to be near the most frequent  English letters, i.e. E, T, A and O in the 5x5 square. This helps to reconstruct the 5x5 square.  
We will be exploiting weakness (2) in this assignment. Note that these weaknesses rely on repetition and frequency counts and, in the absence of cribs, require enough cipher-text to reveal patterns. In practice, this implies that at least 200 characters of cipher-text are available. 




The Simulated Annealing Algorithm 
Simulated annealing (SA) is an excellent approach for breaking a cipher using a randomly generated key. Unlike conventional Hill Climbing algorithms, that are easily side-tracked by local optima, SA uses randomization to avoid heuristic plateaux and attempt to find a global maxima solution. The following pseudocode shows how simulated annealing can be used break a Playfair Cipher. Note that the initial value of the variables temp and transitions can have a major impact on the success of the SA algorithm. Both variables control the cooling schedule of SA and should be experimented with for best results (see slide 20 of the lecture notes on Heuristic Search). 


















