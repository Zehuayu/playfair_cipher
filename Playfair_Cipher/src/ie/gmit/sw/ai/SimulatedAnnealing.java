package ie.gmit.sw.ai;

import java.util.Random;

public class SimulatedAnnealing {

	private static final String initencKey = getParent();
	private String parent;
	private String child;
	private double logscore;
	private String keymess;	
	
	
	//static Pairbypair pbp = new Pairbypair();
	

	//static StringBuilder plain_text = Pairbypair.getPlainTxt();
	
	private String shuffle(char[] key) {
		int index;
		char temp;
		Random random = new Random();
		for (int i = key.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = key[index];
			key[index] = key[i];
			key[i] = temp;
		}
		return String.valueOf(key);
	}
	
	private static String getParent() {
		String randomKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rd = new Random();
		int m = 0;
		String n = "";
		for (int i = 0; i < 25; i++) {
			m = rd.nextInt(26);
			n = n + randomKey.charAt(m);
		}
		return n;
	}
	
	
	private static String[] formGrams(String text, int ng) {
		int len = text.length();
		String[] res = new String[len - ng + 1];
		for (int i = 0; i < len - ng + 1; i++) {
			res[i] = text.substring(i, i + ng);
		}
		return res;
	}
	
	//counter the rate of probability
	private double getLogScore() { 
		double tempSum = 0;
		this.child = shuffle(initencKey.toCharArray()); // Temporarily shuffled keys. 
		// System.out.println("child == " + child);
		String[] fourgrams = formGrams(child, 4);
		for (String key : fourgrams) {
			if (HashGram.getMap().keySet().contains(key)) {
				double tempValue = Math.log10(HashGram.getMap().get(key) / HashGram.getTotal());
				// System.out.println("tempValue ==" + tempValue);
				tempSum += tempValue;
			}
		}
		return tempSum;
	}
	
	
	public String CiperBreaker(int temp, int transitions, int step, String encMessage) {
		System.out.println("");
		System.out.println("Simulated Annealing running............"); 
		logscore = getLogScore();
		// System.out.println(logProbability);
		this.parent = child;
		for (int i = temp; i > 0; i -= step) {
			//System.out.println("......");
			for (int j = transitions; j > 0; j--) {
				
				double f = getLogScore();
				double delta =  logscore;
				if (delta > 0) {
					parent = child;
					logscore = f;
				} else if (delta < 0) {
					//Double p = Math.exp(delta/temp);
					//if (0.5 < p) {
						parent = child;
						if(logscore < f)
							logscore = f;
						else f = logscore;
					}
				}
			}
			
		keymess = parent;
		
		// output the similarity
		System.out.println(" Similarity=" + logscore);
		System.out.println("result is =========[Keys=" + parent  + "]");
		
		return null;
	}

	
	
	
	
	public String getKeymess() {
		return keymess;
	}

	public void setKeymess(String keymess) {
		this.keymess = keymess;
	}

	
	
	

	
}
