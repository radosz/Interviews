import java.util.Arrays;
import java.util.Scanner;

public class PermutationsCounter {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String word = reader.nextLine();
		Integer bound = reader.nextInt();
		word = sortWord(word);	
		int counter = 0;
		
		for (int i = 0; i <= bound; i++) {
			String newWord = reader.nextLine();
			newWord =  sortWord(newWord);
			if (word.equals(newWord)){
				counter++;
			}		
		}
		System.out.println("\n"+counter);
		reader.close();
		
	}
	public static String sortWord(String  word){
	    char [] chars = word.toCharArray();
	    Arrays.sort(chars);
	    return new String(chars);
	}

}