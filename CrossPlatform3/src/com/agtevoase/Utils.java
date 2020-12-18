package agtevoase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String printable = "!#$%&'*+-/=?^_`{|}~.";

    public static void findEmailAddressesAndEditFile(String filename, String outputFilename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        StringBuilder text = new StringBuilder();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }

        String[] sentences = splitIntoSentences(text.toString());
        List<String> candidates = new ArrayList<>();
        List<String> emails = new ArrayList<>();

        for (int i = 0; i < sentences.length; ++i) {
            String[] words = sentences[i].substring(0, sentences[i].length() - 2).split(" ");
            for (String word: words) {
                if (word.contains("@")) {
                    candidates.add((word));
                    if (isValidEmailAddress(word)) {
                        emails.add(word);
                        sentences[i] = new String(sentences[sentences.length - 1]);
                    }
                }
            }
        }
        System.out.println("In file '" + filename + "'");
        System.out.println("Email candidates: " + candidates.toString());
        System.out.println("Email addresses: " + emails.toString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));
        writer.write(String.join("", sentences));
        writer.close();
        System.out.println("Edited text was written to '" + outputFilename + "'");
    }

    public static String[] splitIntoSentences(String str) {
        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(str);
        List<String> sentences = new ArrayList<String>();

        for (int index = 0; bi.next() != BreakIterator.DONE; index = bi.current()) {
            String sentence = str.substring(index, bi.current());
            sentences.add(sentence);
        }

        return sentences.toArray(new String[0]);
    }

    public static boolean isValidEmailAddress(String str) {
        if (!str.contains("@"))
            return false;

        String[] parts = str.split("@");
        if (parts.length != 2)
            return false;

        if (parts[0].length() < 1 || parts[0].length() > 64 || parts[1].length() < 4)
            return false;

        if (parts[0].contains("..") || parts[1].contains(".."))
            return false;

        if (parts[0].charAt(0) == '.' || parts[0].charAt(parts[0].length() - 1) == '.')
            return false;

        if (parts[1].charAt(0) == '.' || parts[1].charAt(parts[1].length() - 1) == '.')
            return false;

        for (char ch : parts[0].toCharArray()) {
            if (!(Character.isLetterOrDigit(ch) || printable.contains(String.valueOf(ch))))
                return false;
        }

        for (char ch : parts[1].toCharArray()) {
            if (!(Character.isLetterOrDigit(ch) || ch == '-' || ch == '.'))
                return false;
        }

        return true;
    }

    public static String removeConsonantsOfGivenLength(String text, int len) {
        String patternStr = "\\b[^aiueoAIUEO ]\\w{" + (len - 1) + "}\\b";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        StringBuilder cleared = new StringBuilder();
        int count = 0;
        int prevEnd = 0;

        while (matcher.find()) {
            ++count;
            cleared.append(text.substring(prevEnd, matcher.start()));
            prevEnd = matcher.end();
        }

        System.out.println(count + " words were removed.");

        if (prevEnd < text.length())
            cleared.append(text.substring(prevEnd, text.length()));

        return cleared.toString();
    }

    public static void removeStuffAndEditFile (String filename, String outputFilename, int len) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        StringBuilder text = new StringBuilder();

        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }

        System.out.println("Got text from '" + filename + "'");

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));
        writer.write(removeConsonantsOfGivenLength(text.toString(), len));
        writer.close();
        System.out.println("Edited text was written to '" + outputFilename + "'");
    }
}
