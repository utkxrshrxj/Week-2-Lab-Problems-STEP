import java.util.Scanner;

public class CaseConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();

        String upper = toUpperCase(text);
        String lower = toLowerCase(text);
        String title = toTitleCase(text);

        System.out.println("\n=== RESULTS ===");
        System.out.println("Manual Uppercase   : " + upper);
        System.out.println("Built-in Uppercase : " + text.toUpperCase());
        System.out.println("Manual Lowercase   : " + lower);
        System.out.println("Built-in Lowercase : " + text.toLowerCase());
        System.out.println("Manual Title Case  : " + title);

        sc.close();
    }

    public static String toUpperCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') c = (char)(c - 32);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String toLowerCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') c = (char)(c + 32);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean startWord = true;

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                startWord = true;
            } else {
                if (startWord) {
                    if (c >= 'a' && c <= 'z') c = (char)(c - 32);
                    startWord = false;
                } else {
                    if (c >= 'A' && c <= 'Z') c = (char)(c + 32);
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
