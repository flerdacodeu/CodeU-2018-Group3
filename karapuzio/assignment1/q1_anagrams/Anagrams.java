package edu.codeU.assignment1.anagrams;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
     * Present word concatenation of chars and number of presentations.
     * @param words initial Map
     * @return transforming Map with words
     */
    public static Map<String, Integer> representWords(Map<String, Integer> words){
        Map<String, Integer> newWords = new HashMap<>();
        for (String word: words.keySet()){
            Integer[] alphabet = new Integer[255];
            Arrays.fill(alphabet, 0);
            for (int i = 0; i < word.length(); i++){
                int charCode = (int)word.charAt(i);
                alphabet[charCode]++;
            }
            StringBuilder newWord = new StringBuilder();
            for (int i = 0 ; i < 255; i++){
                if (alphabet[i] != 0){
                    String charAndCount =(char) i + Integer.toString(alphabet[i]);
                    newWord.append(charAndCount);
                }
            }
            newWords.put(String.valueOf(newWord), words.get(word));
        }
        return newWords;
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
        words1 = representWords(words1);
        words2 = representWords(words2);
        for(String word: words1.keySet()){
            if (!(words2.containsKey(word) && words1.get(word).equals(words2.get(word)))){
                return false;
            }
        }
        return true;
    }
}
