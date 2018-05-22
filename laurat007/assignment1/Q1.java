
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laura
 */
public class Q1 {
	
	public static final String INPUT_FILE = "anagrams";
	
	static boolean caseSensitiveAnagrams(String s1, String s2) {
		System.out.println(s1 + " " + s2);
		if(s1.length() != s2.length())
			return false;
		
		// 58 = 122('z') - 65('A')
		int letters_occurrences[] = new int[57];
		for(int i = 0 ; i < 57; i++)
			letters_occurrences[i] = 0;
		for(int i = 0; i < s1.length(); i++) {
			letters_occurrences[122 - (int)s1.charAt(i)]++;
		}
		for(int i = 0; i < s1.length(); i++) {
			letters_occurrences[122 - (int)s2.charAt(i)]--;
		}
		for(int i = 0 ; i < 57; i++)
			if(letters_occurrences[i] != 0)
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		int N, K;
		
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(
				INPUT_FILE)));
			N = Integer.parseInt(sc.nextLine());
			for(int i = 0; i < N; i++) {
				String buffer = sc.nextLine();
				StringTokenizer token = new StringTokenizer(buffer, " .,");
				int identifier = Integer.parseInt(token.nextToken());
				
				// case sensitive
				if(identifier == 0) {
					System.out.println(caseSensitiveAnagrams(token.nextToken(), token.nextToken()));
				}
					
				else {
					
					// case insensitive
					if(identifier == 1) {
						System.out.println(caseSensitiveAnagrams(token.nextToken().toLowerCase(), 
							token.nextToken().toLowerCase()));
					}
					
					// phrase + case insensitive
					else {
						ArrayList<String> first_sentence = new ArrayList<>();
						while(token.hasMoreTokens()) {
							first_sentence.add(token.nextToken());
						}
						System.out.println(first_sentence);
						buffer = sc.nextLine();
						StringTokenizer token2 = new StringTokenizer(buffer, " .,");
						
						// second sentence is on the next line
						ArrayList<String> second_sentence = new ArrayList<>();
						while(token2.hasMoreTokens()) {
							second_sentence.add(token2.nextToken());
						}
						System.out.println(second_sentence);
						if(first_sentence.size() != second_sentence.size()) {
							System.out.println(false);
							continue;
						}
						
						// check if each word in first sentence
						// matches any word in the second sentence
						int exit = 0;
						for(int j = 0; j < first_sentence.size(); j++) {
							exit = 0;
							for(int k = 0; k < second_sentence.size(); k++) {
								if(caseSensitiveAnagrams(first_sentence.get(j).toLowerCase(),
										second_sentence.get(k).toLowerCase())) {
									exit = 1;
									second_sentence.remove(k);
									break;
								}
							}
							if(exit == 0) {
								System.out.println(false);
								break;
							}
						}
						if(exit == 1) 
							System.out.println(true);
					}
				}
			}
			sc.close();
		} catch (IOException e) {
				throw new RuntimeException(e);
			}
		
		
	}
}
