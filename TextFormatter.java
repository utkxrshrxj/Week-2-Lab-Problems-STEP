import java.util.*;

public class TextFormatter {

    // Method to split text into words without using split()
    public static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words;
    }

    // Justify text using StringBuilder
    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int lineLen = 0;
        List<String> currentWords = new ArrayList<>();

        for (String word : words) {
            if (lineLen + word.length() + currentWords.size() <= width) {
                currentWords.add(word);
                lineLen += word.length();
            } else {
                lines.add(justifyLine(currentWords, lineLen, width));
                currentWords.clear();
                currentWords.add(word);
                lineLen = word.length();
            }
        }
        // Last line - left aligned
        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < currentWords.size(); i++) {
            lastLine.append(currentWords.get(i));
            if (i < currentWords.size() - 1) lastLine.append(" ");
        }
        while (lastLine.length() < width) lastLine.append(" ");
        lines.add(lastLine.toString());

        return lines;
    }

    private static String justifyLine(List<String> words, int lineLen, int width) {
        if (words.size() == 1) {
            StringBuilder sb = new StringBuilder(words.get(0));
            while (sb.length() < width) sb.append(" ");
            return sb.toString();
        }
        int spaces = width - lineLen;
        int gaps = words.size() - 1;
        int spacePerGap = spaces / gaps;
        int extra = spaces % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < gaps) {
                for (int j = 0; j < spacePerGap; j++) sb.append(" ");
                if (i < extra) sb.append(" ");
            }
        }
        return sb.toString();
    }

    // Center align text
    public static List<String> centerAlign(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int lineLen = 0;

        for (String word : words) {
            if (lineLen + word.length() + (lineLen == 0 ? 0 : 1) <= width) {
                if (lineLen > 0) line.append(" ");
                line.append(word);
                lineLen = line.length();
            } else {
                lines.add(centerLine(line.toString(), width));
                line.setLength(0);
                line.append(word);
                lineLen = word.length();
            }
        }
        lines.add(centerLine(line.toString(), width));
        return lines;
    }

    private static String centerLine(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(text);
        while (sb.length() < width) sb.append(" ");
        return sb.toString();
    }

    // Performance comparison (StringBuilder vs concatenation)
    public static void performanceTest(List<String> words, int width) {
        long start1 = System.nanoTime();
        justifyText(words, width);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        justifyTextConcat(words, width);
        long end2 = System.nanoTime();

        System.out.println("\n=== PERFORMANCE ANALYSIS ===");
        System.out.println("Using StringBuilder: " + (end1 - start1) + " ns");
        System.out.println("Using Concatenation: " + (end2 - start2) + " ns");
    }

    // Same justify with + concatenation (inefficient)
    public static List<String> justifyTextConcat(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        String line = "";
        int lineLen = 0;
        List<String> currentWords = new ArrayList<>();

        for (String word : words) {
            if (lineLen + word.length() + currentWords.size() <= width) {
                currentWords.add(word);
                lineLen += word.length();
            } else {
                lines.add(justifyLineConcat(currentWords, lineLen, width));
                currentWords.clear();
                currentWords.add(word);
                lineLen = word.length();
            }
        }
        String lastLine = "";
        for (int i = 0; i < currentWords.size(); i++) {
            lastLine += currentWords.get(i);
            if (i < currentWords.size() - 1) lastLine += " ";
        }
        while (lastLine.length() < width) lastLine += " ";
        lines.add(lastLine);

        return lines;
    }

    private static String justifyLineConcat(List<String> words, int lineLen, int width) {
        if (words.size() == 1) {
            String s = words.get(0);
            while (s.length() < width) s += " ";
            return s;
        }
        int spaces = width - lineLen;
        int gaps = words.size() - 1;
        int spacePerGap = spaces / gaps;
        int extra = spaces % gaps;

        String s = "";
        for (int i = 0; i < words.size(); i++) {
            s += words.get(i);
            if (i < gaps) {
                for (int j = 0; j < spacePerGap; j++) s += " ";
                if (i < extra) s += " ";
            }
        }
        return s;
    }

    // Display with line numbers and char count
    public static void display(List<String> lines, String title) {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i+1) + ". (" + lines.get(i).length() + ") " + lines.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();

        List<String> words = extractWords(text);

        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(words, width);

        System.out.println("\n=== ORIGINAL TEXT ===");
        System.out.println(text);

        display(justified, "JUSTIFIED TEXT");
        display(centered, "CENTER ALIGNED TEXT");

        performanceTest(words, width);

        sc.close();
    }
}
