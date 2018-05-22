package edu.codeU.assignment1.anagrams;

import java.util.*;

/**
 * The class is used for checking if all words in one sentence
 * have an anagram in another with appropriate number of repeating.
 */
public class Anagrams{

    /**
     * The method is used for dividing sentence to word with or without reversing.
     * @param sentence is initial string.
     * @param reverseFlag responsible for the reversing of the string.
     * @return the map with keys as words and values as count of words.
     */
    private static Map<String, Integer> splitSentence2Word(String sentence, boolean reverseFlag){
        if (reverseFlag){
            sentence = new StringBuilder(sentence).reverse().toString();
        }
        String regex = "[^a-zA-Z']+";
        String[] split = sentence.split(regex);
        List<String> tokens = Arrays.asList(split);
        Map<String, Integer> words = new HashMap<>();
        for (String token: tokens){
            String word = token.toLowerCase();
            if (words.containsKey(word)){
                int count = words.get(word);
                words.put(word, count + 1);
            }
            else{
                words.put(word, 1);
            }
        }
        return words;
    }

    /**
     * The method checks two sentences for anagrams.
     * @param str1 is the first string.
     * @param str2 is the second string.
     * @return true/false depending on the verification.
     */
    public static boolean check(String str1, String str2){
        Map<String, Integer> words1 = splitSentence2Word(str1.trim(), false);
        Map<String, Integer> words2 = splitSentence2Word(str2.trim(), true);
        if (words1.size() != words2.size()){
            return false;
        }
        for(String word: words1.keySet()){
            if (!(words2.containsKey(word) && words1.get(word).equals(words2.get(word)))){
                return false;
            }
        }
        return true;
    }
}
