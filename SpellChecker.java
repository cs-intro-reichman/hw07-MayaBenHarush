
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		 return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		word1= word1.toLowerCase(); 
		word2= word2.toLowerCase(); 
		if(word1.length() == 0 ){
			return word2.length();
		}
		if(word2.length() == 0 ){
			return word1.length();
		}
		if(word1.charAt(0) ==  word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		int min1 = levenshtein(tail(word1), word2);
		int min2 = levenshtein(word1, tail(word2));
		int min3 = levenshtein(tail(word1), tail(word2));
		int mostMin = Math.min(min3,Math.min(min2,min1));
		return 1+ mostMin;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i =0 ; i < dictionary.length; i++){
			dictionary[i] =  in.readLine();
		}
		return dictionary;
		
	}


	public static String spellChecker(String word, int threshold, String[] dictionary) {
		if(existInDictionary(word, dictionary) == true){
			return word;
		}
		for(int i = 0; i < dictionary.length; i++){
		
			if(levenshtein( word, dictionary[i]) == threshold){
				return dictionary[i];
			}

		}
		return word;

	}

	public static boolean existInDictionary(String word, String []dictionary) {
	
		for(int i =0 ; i < dictionary.length; i++){
			if( dictionary[i].equals(word)){
				return true;
			}
		}
		return false;
	}

}


