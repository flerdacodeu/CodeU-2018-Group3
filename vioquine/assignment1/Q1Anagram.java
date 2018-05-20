import java.util.HashMap;

public class Q1Anagram {
    public static void main(String... args){
        System.out.println(checkAnagram("","",true));
        System.out.println(checkAnagram("Fourth of July","Joyful Fourth",true));
        System.out.println(checkAnagram("Fourth of July!","Joyful Fourth",true));
        System.out.println(checkAnagram("Fourth of July","Joyful Fourth?",true));
        System.out.println(checkAnagram("Forth of July","Joyful Fourth",true));
        System.out.println(checkAnagram("Forth of July","Joyful fourth",true));
        System.out.println(checkAnagram("Fourth of July","joyful fourth",false));
        System.out.println(checkAnagram("Fourth of July","joyful forth",false));
    }

    /**
     * Checks if two given sentences are anagrams (case sensitive)
     * @param sentence1
     * @param sentence2
     * @param caseSensitive true =c ase sensitive check; false = case insensitive check
     * @return true, if they are anagrams; false, otherwise
     */
    private static boolean checkAnagram(String sentence1, String sentence2, boolean caseSensitive){
        sentence1 = normalizeString(sentence1);
        sentence2 = normalizeString(sentence2);

        if (!caseSensitive){
            sentence1 = sentence1.toLowerCase();
            sentence2 = sentence2.toLowerCase();
        }
        // If sentence1 is longer/shorter than sentence2, they can't be anagrams
        if (sentence1.length()!= sentence2.length()){
            return false;
        }

        // Create a map to store all characters in sentence1 with their occurrences
        HashMap<Character,Integer> characters = new HashMap<>();

        for(int i=0; i<sentence1.length(); i++){
            char c = sentence1.charAt(i);
            if (characters.containsKey(c)){
                // The character is in the map, so just increment the occurrence
                characters.replace(c,characters.get(c) + 1);
            }else {
                // The character is not in the map, so add the character with occurrence=1 to the map
                characters.put(c,1);
            }
        }

        for(int i=0; i<sentence2.length(); i++){
            char c = sentence2.charAt(i);
            // If c is not in the characters map, sentence2 can't be an anagram of sentence1
            if(!characters.containsKey(c)){
                return false;
            }
            int occurrence = characters.get(c);
            if(occurrence > 1){
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