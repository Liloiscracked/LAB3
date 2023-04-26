import java.io.*;
import java.util.Scanner;

public class Dictionary {
    ///// part one the constructor:
    //initialize the Dictionary with 26 trees each tree for a letter
    AVLTree<String> [] words = new AVLTree[26];
    public Dictionary(){ // Empty dictionary
        for(int i = 0;i< words.length; i++)
            this.words[i] = new AVLTree<>();
    }
    public Dictionary(String word){ //dictionary with a single word
        for(int i = 0;i< words.length; i++)
            this.words[i] = new AVLTree<>();
        this.words[word.charAt(0)-97].root = new BTNode<>(word);
    }
    public Dictionary(File file){ // read from file
        for(int i = 0;i< words.length; i++)
            this.words[i] = new AVLTree<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // process the line
                String sample = scanner.nextLine();
                if(!this.findWord(sample)) // Do not allow duplicates
                    words[sample.charAt(0) - 97].insertAVL(sample);
            }
        }
            catch (FileNotFoundException e) {
            throw new RuntimeException("File Does not exist");
        }

    }
    // part two
    public void addWord(String s) throws WordAlreadyExistsException{ // O(log(n))
        if(words[s.charAt(0)-97].find(s))
            throw new WordAlreadyExistsException("The word already exists");
        else
            words[s.charAt(0)-97].insertAVL(s);
    }
    // part three
    public boolean findWord(String s){ // O(log(n))
       return words[s.charAt(0)-97].find(s);
    }
    // part four
    public void deleteWord(String s) throws WordNotFoundException{ // O(log(n))
        if(!findWord(s))
            throw new WordNotFoundException("The word doesnt exist in the Dictionary");
        else
            words[s.charAt(0)-97].deleteAVL(s);
    }
    //part five
    public String[] findSimilar (String s){ // O(n) n = number of letters in the tree with the same letter at the beginning
        return words[s.charAt(0)-97].collectSimilar(s);
    }
    // to display a part of the dictionary
    public void showLetter(char chr){
        words[chr-97].printTree();
    }
    // to display the whole dictionary
    public void showAll(){
        for(int i = 0; i< words.length;i++){
            words[i].printTree();
        }
    }
    // save the dictionary in a file !!!
    public void Save(String s) throws IOException { // O(n)
        PrintWriter output = new PrintWriter(new File(s));
        for(int i = 0;i<this.words.length;i++){
            SLL<String>sll = words[i].collect();
            while (!sll.isEmpty()){
                output.write(sll.deleteFromHead()+"\n");
            }
        }
        output.close();
    }
}
