import edu.duke.*;

import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> word;

    public WordsInFiles() {
        map = new HashMap<>();
        word = new ArrayList<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String name = f.getName();
        for (String word : fr.words()) {
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<String>());
                map.get(word).add(name);
            } else if (map.containsKey(word)) {
                if (!map.get(word).contains(name)) {
                    map.get(word).add(name);
                }
            }
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);

        }
    }

    public int maxNumber() {
        int current = 0;
        int max = 0;
        for (String word : map.keySet()) {
            current = map.get(word).size();
            if (max < current) {
                max = current;
            }
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        int current = 0;
        ArrayList<String> list = new ArrayList<String>();
        for (String word : map.keySet()) {
            current = map.get(word).size();
            if (current == number) list.add(word);
        }
        return list;
    }

    private void printFilesIn(String word) {
        ArrayList<String> filenames = map.get(word);
        for (int i = 0; i < filenames.size(); i++) {
            System.out.println(filenames.get(i));
        }
    }

    public void test() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word appears in: " + max);
        System.out.println("All the words that are in " + max + " files:");
        ArrayList<String> words = wordsInNumFiles(max);

        System.out.println("Numbers of words in 7 file are " +wordsInNumFiles(7).size());
        System.out.println("number of words in 4 files:"+wordsInNumFiles(4).size());
        System.out.println("Words in 7 files: " + words.size());
        System.out.println("laid: ");
        printFilesIn("tree");
    }
}