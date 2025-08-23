import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\n=== RESULTS ===");
        System.out.println("Original Text : " + text);
        displayAscii(text);

        System.out.println("Encrypted Text: " + encrypted);
        displayAscii(encrypted);

        System.out.println("Decrypted Text: " + decrypted);
        System.out.println("Validation: " + text.equals(decrypted));

        sc.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                char e = (char) ((c - 'A' + shift) % 26 + 'A');
                sb.append(e);
            } else if (c >= 'a' && c <= 'z') {
                char e = (char) ((c - 'a' + shift) % 26 + 'a');
                sb.append(e);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAscii(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c + "(" + (int)c + ") ");
        }
        System.out.println("\n");
    }
}
