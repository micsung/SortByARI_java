import java.util.Scanner;

public class ReadabilityScore {
    public int getARI(String content) {
        String line;
        Scanner sc = null;
        double wordCnt = 0;
        double character = 0;
        double sentence = 0;
        double ARI = 0;
        sc = new Scanner(content);
        sc.useDelimiter("[^A-Za-z]+");
        while (sc.hasNext()) {
            line = sc.next();
            wordCnt++;
            character += line.length();
        }
        sc = new Scanner(content);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == '.' && line.charAt(i + 1) == ' ') {
                    sentence++;
                }
            }
        }
        ARI = 0.37 * (wordCnt / sentence) + 5.84 * (character / wordCnt) - 26.01;
        sc.close();
        return (int) ARI;
    }
}
