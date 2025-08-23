import java.util.Scanner;

public class FindAndReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter main text:");
        String text = sc.nextLine();

        System.out.println("Enter substring to find:");
        String find = sc.nextLine();

        System.out.println("Enter substring to replace with:");
        String replace = sc.nextLine();

        String manualResult = manualReplace(text, find, replace);
        String builtInResult = text.replace(find, replace);

        System.out.println("Manual Replace Result: " + manualResult);
        System.out.println("Built-in Replace Result: " + builtInResult);
        System.out.println("Both results same? " + manualResult.equals(builtInResult));

        sc.close();
    }

    // Method to manually replace substring
    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            int index = text.indexOf(find, i);
            if (index == -1) {
                result.append(text.substring(i));
                break;
            }
            result.append(text.substring(i, index));
            result.append(replace);
            i = index + find.length();
        }
        return result.toString();
    }
}
