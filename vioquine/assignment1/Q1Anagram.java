import java.util.HashMap;

public class Q1Anagram {
    public static void main(String... args){

        System.out.println(checkAnagram("",""));
        System.out.println(checkAnagram("Fourth of July","Joyful Fourth"));
        System.out.println(checkAnagram("Fourth of July!","Joyful Fourth"));
        System.out.println(checkAnagram("Fourth of July","Joyful Fourth?"));
        System.out.println(checkAnagram("Forth of July","Joyful Fourth"));
        System.out.println(checkAnagram("Forth of July","Joyful fourth"));
    }

    /**
     * Checks if two given sentences are anagrams (case sensitive)
     * @param sentence1
     * @param sentence2
     * @return true, if they are anagrams; false, otherwise
     */
    private static boolean checkAnagram(String sentence1, String sentence2){
        // Normalize the sentences (Remove whitespaces and punctuation)
        sentence1 = normalizeString(sentence1);
        sentence2 = normalizeString(sentence2);

        // If sentence1 is longer/shorter than sentence2, they can't be anagrams
        if (sentence1.length()!= sentence2.length()){
            return false;
        }

        // Create a map to store all characters in sentence1 with their occurrences
        HashMap<Character,Integer> characters = new HashMap<>();

        // Go through sentence1
        for(int i=0; i<sentence1.length(); i++){
            char c = sentence1.charAt(i);
            // Check if the current character c is already in the map
            if (characters.containsKey(c)){
                // The character is in the map, so just increment the occurrence
                characters.replace(c,characters.get(c) + 1);
            }else {
                // The character is not in the map, so add the character with occurrence=1 to the map
                characters.put(c,1);
            }
        }

        // Go through sentence2
        for(int i=0; i<sentence2.length(); i++){
            char c = sentence2.charAt(i);
            // If c is not in the characters map, sentence2 can't be an anagram of sentence1
            if(!characters.containsKey(c)){
                return false;
            }
            int occurrence = characters.get(c);
            if(occurrence > 1){
                // Decrement the occurrence of c
                characters.replace(c,occurrence-1);
            }else {
                // Remove c from the map, because all occurrences are used
                characters.remove(c);
            }
        }
        return true;
    }

    /**
     * Removes whitespaces and punctuation of a given String
     * @param s
     * @return normalized version of s
     */
    private static String normalizeString(String s) {
        s = s.replace(" ", "");
        s = s.replace("\t", "");
        s = s.replace("\n", "");
        s = s.replace(".", "");
        s = s.replace(",", "");
        s = s.replace("!", "");
        s = s.replace("?", "");
        s = s.replace(":", "");
        return s;
    }
}