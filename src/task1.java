import java.io.*;
import java.util.*;

public class task1 {
    public static void main(String[] args) throws Exception {
        ArrayList<String> newlist = new ArrayList<String>();
        ArrayList<String> stopword = new ArrayList<String>();
        Scanner in = null;
        PrintWriter fw = null;
        File file1,file2;
        try {
            // read file
            file1 = new File("data/docset110_HW3.txt");
            in = new Scanner(file1);
            while (in.hasNext()) {
                String current = in.nextLine();
                while (current.equals("<TEXT>")) {
                    String text = in.nextLine().toLowerCase();
                    if (text.equalsIgnoreCase("</TEXT>"))
                        break;
                        String[] word = text.split(" |\\?|\"|\'|&|,|\\.|:|;|-|\\(|\\)|\\[|\\]|\\{|\\}|0|1|2|3|4|5|6|7|8|9|/|_");
                    for (int i = 0; i < word.length; i++) {
                        if (word[i].length() != 0) {
                            newlist.add(word[i]);
                        }
                    }
                }
            }
            // remove stopwords
            file2 = new File("data/stop_words.txt");
            in = new Scanner(file2);
            while (in.hasNextLine()) {
                stopword.add(in.nextLine());
            }
            newlist.removeAll(stopword);

            // sort
            for (int i = 0; i < newlist.size(); i++) {
                int min = i;
                for (int j = i + 1; j < newlist.size(); j++) {
                    if (newlist.get(j).compareTo(newlist.get(min)) < 0) {
                        min = j;
                    }
                }
                String t = newlist.get(i);
                newlist.set(i, newlist.get(min));
                newlist.set(min, t);
            }

            // remove repeat words
            int[] cnt = new int[newlist.size()];
            for (int i = 0; i < newlist.size(); i++) {
                if (i > 0 && newlist.get(i).equals(newlist.get(i - 1))) {
                    ++cnt[i];
                    newlist.remove(i--);
                }
            }
            // create file
            fw = new PrintWriter("src/dictionaryTerm.csv");
            for (int i = 0; i < newlist.size(); i++) {
                fw.printf("%04d %s %d %n", i + 1, newlist.get(i), cnt[i + 1] + 1);
            }
            fw.flush();

        } catch (FileNotFoundException exp) {
            System.out.println("FileCantFound");
        } catch (IOException e) {
            System.out.println("I/O error");
        } finally {
            in.close();
            fw.close();
        }
        {
        }
    }
}
