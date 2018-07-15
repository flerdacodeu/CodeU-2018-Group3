import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

public class UnknownAlphabetTest {

    @Test
    public void getAlphabet_EmptyDictionary() {
        UnknownAlphabet alphabet = new UnknownAlphabet("empty.txt");
        assertEquals(new LinkedList<>(), alphabet.getAlphabet());
    }

    @Test
    public void getAlphabet_OneWord() {
        UnknownAlphabet alphabet = new UnknownAlphabet("oneWord.txt");

        LinkedList<Character> list = new LinkedList<>();
        list.add('R');
        list.add('A');
        list.add('C');

        assertEquals(list, alphabet.getAlphabet());
    }

    @Test
    public void getAlphabet_AssignmentInput() {
        UnknownAlphabet alphabet = new UnknownAlphabet("assignment.txt");

        LinkedList<Character> list = new LinkedList<>();
        list.add('T');
        list.add('A');
        list.add('R');
        list.add('C');

        assertEquals(list, alphabet.getAlphabet());
    }

    @Test
    public void getAlphabet_Romanian() {
        UnknownAlphabet alphabet = new UnknownAlphabet("romanian.txt");

        LinkedList<Character> list = new LinkedList<>();
        list.add('a');
        list.add('t');
        list.add('ă');
        list.add('r');
        list.add('o');
        list.add('ș');
        list.add('n');
        list.add('b');
        list.add('e');
        list.add('i');
        list.add('u');

        assertEquals(list, alphabet.getAlphabet());
    }

    @Test
    public void getAlphabet_moreWords() {
        UnknownAlphabet alphabet = new UnknownAlphabet("moreWords.txt");

        LinkedList<Character> list = new LinkedList<>();
        list.add('h');
        list.add('r');
        list.add('n');
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        list.add('e');
        list.add('f');
        list.add('g');
        list.add('l');
        list.add('m');
        list.add('i');
        list.add('o');
        list.add('p');
        list.add('s');
        list.add('t');
        list.add('w');
        list.add('u');

        assertEquals(list, alphabet.getAlphabet());
    }
}
