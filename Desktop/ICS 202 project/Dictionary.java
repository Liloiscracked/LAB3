import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {
    ///// part one the constructor:
        //initialize the Dictionary with 26 trees each tree for a letter
    AVLTree<String> [] words = new AVLTree[26];
    public Dictionary(){
        for(AVLTree<String> tree : words)
            tree = new AVLTree<>();
    }
    public Dictionary(String word){
        words[word.charAt(0)-97].insertAVL(word);
    }
    public Dictionary(File file){
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File Does not exist");
        }
        while (scanner.hasNextLine()) {
            String sample = scanner.nextLine();
            // process the line
            words[sample.charAt(0)-97].insertAVL(sample);
        }
    }
    // part two
    public void addWord(String s) throws WordAlreadyExistsException{
        if(words[s.charAt(0)-97].find(s))
            throw new WordAlreadyExistsException("The word already exists");
        else
            words[s.charAt(0)-97].insertAVL(s);
    }
    // part three
    public boolean findWord(String s){
       return words[s.charAt(0)-97].find(s);
    }
    // part four
    public void deleteWord(String s) throws WordNotFoundException{
        if(!findWord(s))
            throw new WordNotFoundException("The word doesnt exist in the Dictionary");
        else
            words[s.charAt(0)-97].deleteAVL(s);
    }
    //part five
    public String[] findSimilar (String s){
        return words[s.charAt(0)-97].collectSimilar(s);
    }
}
