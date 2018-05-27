import java.util.Scanner;

/*
* Assumptions:
*   1- Sentences are punctuation sensitive ("good morning " and "good morning," are not anagrams)
*       if first sentence has a punctuation mark other is expected to have it somewhere as well.
*   2- For the sentences when looking for anagram words order doesn't matter.
*       (good morning and morning gdoo are anagrams)
*   3- For sentences does number of words matter?
*      Case 1: Sentences containing same letters but different number of words are anagrams
*      "good morning" and "gdoomorning " -> anagram
*      Case 2: Sentences containing same letters and same number of words are anagrams
*      "good morning" and "gdoomorning " ->  not anagram, "good morning" and "gdoo morning" -> anagram
*      Case 3: Sentences contains same number of words AND each word in S1 has a corresponding anagram in S2 are anagram.
*      Case 4: For the above sentences solutions consider multiple spaces as separating empty strings. Therefore, "sr es"
*      and "es  sr" would return NOT anagram. But we might also want the multiple spaces between sentences not considered
*      as empty strings so that "es sr" and "es      sr"  would return a result anagram.
*
*
*    Remark 1: In isSentenceAnagram "abc cdf" and "acd cbf" is anagram whereas it is not in isSentenceFullAnagram
*
*    Remark 2:
*       - If assumption 3 - case 1 is preferred then isAnagram by itself can be used for both words and
*    sentences anagrams.
*       - If assumption 3 - case 2 is preferred: use isAnagram for word anagram and use isSentenceAnagram
*       for sentence anagram.
*       - If assumption 3 - case3 is preferred: use isAnagram for word anagram and use isSentenceFullAnagram
*       for sentence anagram.
*       - If assumption 3 - case4 is preferred: use isAnagram for word anagram and use isSentenceFullAnagram2
 *       for sentence anagram.
*
*   Remark 3: In the code for sentence anagram isSentenceFullAnagram2 is used but other approach is implemented as well.
* */
public class Anagram {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Program finds if given 2 words/sentences are anagrams.");
        int option ;
        do{
            System.out.println("Enter first word/sentence:");
            String s1 = scan.nextLine();
            System.out.println("Enter second word/sentence:");
            String s2 = scan.nextLine();
            System.out.println("Do you want comparison to be case sensitive? (Write true if you want case sensitive false otw:");
            boolean caseSensitive = scan.nextBoolean();
            findAnagram(s1,s2,caseSensitive);
            System.out.println("Do you want to find anagram? (enter 1 to find anagram, or anything else to exit)");
            option = scan.nextInt();
            scan.nextLine();
        }while(option==1);

    }

    /**
     * Takes 2 string and a boolean value, if the operation is case insensitive
     * turns the strings to lower case. Checks whether the strings are words or sentences
     * calls the corresponding anagram function accordingly and prints out the results.
     *
     * @param s1 : string 1
     * @param s2 : string 2
     * @param caseSensitive : true if case sensitive comparison false otw
     */
    public static void findAnagram(String s1, String s2, boolean caseSensitive){

        boolean res = false;
        //checks if we want case insensitive (0) or sensitive (1)
        if(!caseSensitive){
            s1 = s1.toLowerCase();
            s2 = s2.toLowerCase();
        }
        // if either s1 or s2 has space char then at least one of them is sentence
        // Sentence Anagram
        if(s1.contains(" ") || s2.contains(" ")){
            res = isSentenceFullAnagram2(s1,s2);
        }
        else{  // word Anagram
            res= isAnagram(s1,s2);
        }
        if(res)
            System.out.println("\""+s1+ "\" and \""+s2+ "\" are anagrams.");
        else
            System.out.println("\""+s1+ "\" and \""+s2+ "\" are not anagrams.");
    }


    /**
     * Finds if the given two strings are anagram or not.
     * Can be used for word anagrams, also for sentences anagram
     * in some cases(check assumptions in the beginning of doc )
     * @param w1 : string 1
     * @param w2 : string 2
     * @return returns true if two string are anagrams, false otw.
     */
    public static boolean isAnagram(String w1, String w2) {
        // if the lengths of the words are different it returns false
        if (w1.length() != w2.length())
            return false;
        // Assumption: The alphabet is ASCII table
        // Alphabet includes the extended ASCII chars too - > 256 Chars in total
        int[] alphabet = new int[256];
        for (int i = 0; i < w1.length(); i++) {
            alphabet[w1.charAt(i)]++;
        }
        /* Since the words length are equal, if any of the letter in second word
          makes its corresponding element negative count, then two possible options:
          1 - w2 has a letter that w1 doesn't have
          2-  w2 and w1 have same letters but different letter counts (i.e. w1 = blaa and w2= blla)
          In both cases, they are not anagrams.
        */
        for(int j=0; j <w2.length(); j++){
            char c = w2.charAt(j);
            alphabet[(int)c] --;
            if(alphabet[(int)c] < 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Finds if the given two strings are anagram or not.
     * Can be used for sentence anagrams for assumption 3 case 2.
     * @param s1 : string 1
     * @param s2 : string 2
     * @return returns true if two string are anagrams, false otw.
     */
    public static boolean isSentenceAnagram(String s1, String s2){
        String[] s1Words = s1.split(" ");
        String[] s2Words = s2.split(" ");
        if(s1Words.length != s2Words.length)
            return false;
        boolean result= isAnagram(s1,s2);
        return result;
    }

    /**
     * Finds if the given two strings are anagram or not.
     * Can be used for sentence anagrams for assumption 3 case 3.
     * @param s1 : string 1
     * @param s2 : string 2
     * @return returns true if two string are anagrams, false otw.
     */
    public static boolean isSentenceFullAnagram(String s1, String s2){
        String[] s1Words = s1.split(" ");
        String[] s2Words = s2.split(" ");
        boolean result=false;
        if(s1Words.length != s2Words.length)
            return false;
        boolean[] check = new boolean[s2Words.length];
        boolean anagramFound = false;
        for(int i=0; i<s1Words.length;i++){
            for(int j=0; j <s2Words.length;j++){
               if(check[j]) // this word is already match as anagram for another word in s1
                  continue;
                result=isAnagram(s1Words[i], s2Words[j]);
                if(result) {  // anagram found go to next word in s1
                    anagramFound =true;
                    check[j]=true;
                    break;
                }
            }
            if(!anagramFound)
                return false;
            anagramFound =false;
        }
        return true;
    }

    /**
     * Finds if the given two strings are anagram or not.
     * Can be used for sentence anagrams for assumption 3 case 4.
     * @param s1 : string 1
     * @param s2 : string 2
     * @return returns true if two string are anagrams, false otw.
     */
    public static boolean isSentenceFullAnagram2(String s1, String s2){
        //Using regex we delete the multiple spaces next to each other and leave only one space
        s1 = s1.replaceAll("\\s+"," ");
        s2 = s2.replaceAll("\\s+"," ");
        boolean res =isSentenceFullAnagram(s1,s2);
        return res;
      }

}

