import java.io.File;
import java.io.IOException;

public class dummy {
    public static void main(String[] args) throws WordAlreadyExistsException, WordNotFoundException, IOException {
        Dictionary dic1 = new Dictionary(new File("dictionary.txt"));
        dic1.showLetter('x');
    }
}
