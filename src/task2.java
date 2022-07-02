import java.io.*;
import java.util.*;

public class task2 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> docARI = new ArrayList<Integer>();
        ArrayList<String> docNo = new ArrayList<String>();
        ArrayList<String> newlist = new ArrayList<String>();
        ReadabilityScore readabilityScore = new ReadabilityScore();
        File file;
        PrintWriter fw = null;
        Scanner sc = null;
        String content = "";
        try {
            // get docNO
            file = new File("data/docset110_HW3.txt");
            sc = new Scanner(file);
            while (sc.hasNext()) {
                if (sc.next().equals("<DOCNO>")) {
                    docNo.add(sc.next());
                }
            }
            // read file
            file = new File("data/docset110_HW3.txt");
            sc = new Scanner(file);
            while (sc.hasNext()) {
                content = "";
                String current = sc.nextLine();
                while (current.equals("<TEXT>")) {
                    String text = sc.nextLine();
                    if (!text.equals("</TEXT>")) {
                        content += text;
                    } else
                        break;
                }
                if (!content.equals("")) {
                    newlist.add(content);
                }
            }
            // calculate ARI
            for (int i = 0; i < newlist.size(); i++) {
                docARI.add(readabilityScore.getARI(newlist.get(i)));
            }
            // sort by ARI
            for (int i = 0; i < docARI.size(); i++) {
                for (int j = 0; j < docARI.size() - i - 1; j++) {
                    if (docARI.get(j) < docARI.get(j + 1)) {
                        // change ARI
                        int temp = docARI.get(j);
                        docARI.set(j, docARI.get(j + 1));
                        docARI.set(j + 1, temp);
                        // change docNO
                        String t = docNo.get(j);
                        docNo.set(j, docNo.get(j + 1));
                        docNo.set(j + 1, t);
                    }
                }
            }
            // create file
            fw = new PrintWriter("src/docSortByARI.csv");
            for (int i = 0; i < docNo.size(); i++) {
                fw.printf("%04d %s %d %n", i + 1, docNo.get(i), docARI.get(i));
            }
            fw.flush();

        } catch (FileNotFoundException e) {
            System.out.println("FileCantFound");
        } catch (IOException e) {
            System.out.println("I/O error");
        } finally {
            sc.close();
        }

    }

}
