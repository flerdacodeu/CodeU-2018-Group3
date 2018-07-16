package edu.codeU.assignment5;

import java.util.ArrayList;

public class UnknownAlphabet {

    private ArrayList<String> dictionary;
    private int[][] graph;
    private int[] degree;
    private boolean[] flag;
    private final static int SIZE = 255;

    public UnknownAlphabet(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
        graph = new int[SIZE][SIZE];
        degree = new int[SIZE];
        flag = new boolean[SIZE];
        createGraph();
    }

    private void createGraph(){
        if (!checkCorrectness()){

        }
        for (int i = 0; i < dictionary.size() - 1; i++){
            int j = 0;
            String current = dictionary.get(i);
            String next = dictionary.get(i+1);
            while (j < current.length() && j < next.length() && dictionary.get(i).charAt(j) == dictionary.get(i + 1).charAt(j)) {
                j++;
            }
            if (j != current.length()){
                int from = (int)current.charAt(j);
                int to = (int)next.charAt(j);
                graph[from][to] = 1;
                degree[to]++;
                flag[from] = true;
                flag[to] = true;
            }
        }
    }

    private boolean checkCorrectness(){
        return true;
    }

    public ArrayList<Character> fihndAlphabet(){
        return getSomeAlphabet(graph, degree, flag);
    }

    private ArrayList<Character> getSomeAlphabet(int[][] curGraph, int[] degree, boolean[] flag){
        ArrayList<Character> alphabet = new ArrayList<>();
        for (int j = 0; j < SIZE; j++) {
            boolean isIntecation = false;
            for (int i = 0; i < SIZE; i++) {
                if (degree[i] == 0 && flag[i]) {
                    isIntecation = true;
                    alphabet.add((char) i);
                    flag[i] = false;
                    for (int k = 0; k < SIZE; k++) {
                        if (curGraph[i][k] == 1) {
                            degree[k]--;
                            curGraph[i][k] = 0;
                        }
                    }
                 break;
                }
            }
            if (!isIntecation){
                break;
            }
        }
        return alphabet;
    }

    public ArrayList<ArrayList<Character>> fihndAllAlphabets(){
        ArrayList<Character> alphabet = new ArrayList<>();
        ArrayList<ArrayList<Character>> set = new ArrayList<>();
        getAllAlphabets(graph, degree, flag, alphabet, set);
        return set;
    }

    public void getAllAlphabets(int[][] curGraph, int[] degree, boolean[] flag, ArrayList<Character> alphabet, ArrayList<ArrayList<Character>> setOfAlphabets){
        boolean isAlphabet = true;
        for (int i = 0; i < SIZE; i++) {
            if (degree[i] != 0 || flag[i]){
                isAlphabet = false;
            }
        }
        if (isAlphabet){
            setOfAlphabets.add(alphabet);
        }
        for (int i = 0; i < SIZE; i++){
            if (degree[i] == 0 && flag[i]){
                alphabet.add((char)i);
                flag[i] = false;
                for (int j = 0; j < SIZE; j++){
                    if (graph[i][j] == 1){
                        degree[j]--;
                        curGraph[i][j] = 0;
                    }
                }
                getAllAlphabets(graph, degree, flag, alphabet, setOfAlphabets);
                alphabet.remove(alphabet.size());
                flag[i] = true;
                for (int j = 0; j < graph.length; j++){
                    if (graph[i][j] == 1){
                        degree[j]--;
                        curGraph[i][j] = 1;
                    }
                }
            }
        }
    }
}
