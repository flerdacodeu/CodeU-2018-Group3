package assignment_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
 * This program will read from input file N pairs of words or sentences and check if they are anagrams. 
 * Input file format:
 *	N - pairs to be read
 *	the following N lines format: 
 *		a number (0 or 1 or 2) meaning which test to use: case sensitive, 
 *			case insensitive, phrases with case insensitive
 *		if the number was 0 or 1 the program will read 2 more words to be tested
 *		if the number was 2 the program will read on the same line the first 
 *			sentence and on the next line the second sentence to be compared
 */

public class Q1 {
	
	public static final String INPUT_FILE = "anagrams";
        public static final int CHARACTERS = 58;
	
	// number of pairs to be checked for being anagrams
	static int N;
        static Scanner sc;
	
	static boolean caseSensitiveAnagrams(String s1, String s2) {
            System.out.println(s1 + " " + s2);
            if(s1.length() != s2.length())
                return false;

            // 58 = 122('z') - 65('A') + 1
            int letters_occurrences[] = new int[CHARACTERS];
            for(int i = 0; i < s1.length(); i++) {
                letters_occurrences[122 - (int)s1.charAt(i)]++;
            }
            for(int i = 0; i < s1.length(); i++) {
                letters_occurrences[122 - (int)s2.charAt(i)]--;
            }
            for(int i = 0 ; i < CHARACTERS; i++)
                if(letters_occurrences[i] != 0)
                    return false;
            return true;
	}
        
        static boolean caseInsensitiveAnagrams(String s1, String s2) {
            return caseSensitiveAnagrams(s1.toLowerCase(), s2.toLowerCase());
        }
        
        static boolean caseInsensitiveSentenceAnagrams(ArrayList<String> s1, ArrayList<String> s2) {
            if(s1.size() != s2.size()) {
                return false;
            }

            ArrayList<ArrayList<Character>> sent1 = new ArrayList<>();
            ArrayList<ArrayList<Character>> sent2 = new ArrayList<>();
            
            // sort letters in each word
            for(int j = 0; j < s1.size(); j++) {
                ArrayList<Character> word = new ArrayList<>();
                for(int k = 0; k < s1.get(j).length(); k++) {
                    word.add(s1.get(j).toLowerCase().charAt(k));
                }
                Collections.sort(word);
                sent1.add(word);
                
                ArrayList<Character> word2 = new ArrayList<>();
                for(int k = 0; k < s2.get(j).length(); k++) {
                    word2.add(s2.get(j).toLowerCase().charAt(k));
                }
                Collections.sort(word2);
                sent2.add(word2);
            }
            
            // sort words in sentences
            Comparator comp = new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    ArrayList word1 = (ArrayList) o1;
                    ArrayList word2 = (ArrayList) o2;
                    
                    for(int j = 0; j < word1.size(); j++) {
                        if(j >= word2.size())
                            return -1;
                        if(word1.get(j).equals(word2.get(j)))
                            continue;
                        return Character.compare((char)word1.get(j),(char)word2.get(j));
                    }
                    
                    if(word2.size()> word1.size())
                        return 1;
                    return 0;
                }
            };
            
            sent1.sort(comp);
            sent2.sort(comp);
            
            if(sent1.equals(sent2))
                return true;
            return false;
        }
	
	static void getResult() {
            try {
                sc = new Scanner(new BufferedReader(new FileReader(
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
                            System.out.println(caseInsensitiveAnagrams(token.nextToken(), token.nextToken()));
                        }

                        // phrase + case insensitive
                        else {
                            
                            // read first sentence
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
                            
                            // apply test
                            System.out.println(caseInsensitiveSentenceAnagrams(first_sentence, second_sentence));
                        }
                    }
                }
                sc.close();
            } catch (IOException e) {
                    throw new RuntimeException(e);
                }
	}
	
	public static void main(String[] args) {
		getResult();
	}
}

