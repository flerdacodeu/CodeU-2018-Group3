import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
    I implemented the dictionary with a HashMap, so that it will
    check if a word is in the dictionary with a O(1) complexity
    and if a sequence is a prefix with a O(n) complexity.
 */

public class Dictionary extends HashMap<String, Object>{

    public Dictionary(String filename) {
        readData(filename);
    }

    public void readData(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));

            int nrWords = sc.nextInt();
            if (nrWords != 0)
                sc.nextLine();

            for(int i = 0; i < nrWords; i++) {
                put(sc.next(), true);
                if (i != nrWords - 1)
                    sc.nextLine();
            }

            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isWord(String word) {
        return containsKey(word);
    }

    public boolean isPrefix(String prefix) {
        if (prefix == null)
            return false;

        Set<String> keySet = keySet();

        for (String str : keySet) {
            if (str.startsWith(prefix))
                return true;
        }

        return false;
    }
}
