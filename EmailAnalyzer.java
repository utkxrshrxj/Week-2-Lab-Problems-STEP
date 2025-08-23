import java.util.*;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of emails: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        String[] emails = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i+1) + ": ");
            emails[i] = sc.nextLine();
        }

        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();

        System.out.println("\n=== EMAIL DETAILS ===");
        System.out.printf("%-25s %-10s %-15s %-12s %-10s %-10s\n", 
                          "Email", "Username", "Domain", "DomainName", "Extension", "Valid?");

        for (String email : emails) {
            boolean valid = validateEmail(email);
            if (valid) validCount++; else invalidCount++;

            String username = "", domain = "", domainName = "", extension = "";
            if (valid) {
                username = getUsername(email);
                domain = getDomain(email);
                domainName = getDomainName(domain);
                extension = getExtension(domain);

                totalUsernameLength += username.length();

                domainCount.put(domainName, domainCount.getOrDefault(domainName, 0) + 1);
            }

            System.out.printf("%-25s %-10s %-15s %-12s %-10s %-10s\n", 
                              email, username, domain, domainName, extension, valid ? "Yes" : "No");
        }

        System.out.println("\n=== ANALYSIS ===");
        System.out.println("Valid emails   : " + validCount);
        System.out.println("Invalid emails : " + invalidCount);
        if (validCount > 0) {
            System.out.println("Average username length: " + (totalUsernameLength / validCount));
            System.out.println("Most common domain: " + mostCommonDomain(domainCount));
        }

        sc.close();
    }

    public static boolean validateEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAtPos = email.lastIndexOf('@');
        int dotPos = email.lastIndexOf('.');

        if (atPos == -1 || atPos != lastAtPos) return false;
        if (dotPos == -1 || dotPos < atPos) return false;
        if (atPos == 0 || atPos == email.length() - 1) return false;
        return true;
    }

    public static String getUsername(String email) {
        return email.substring(0, email.indexOf('@'));
    }

    public static String getDomain(String email) {
        return email.substring(email.indexOf('@') + 1);
    }

    public static String getDomainName(String domain) {
        int dotPos = domain.lastIndexOf('.');
        return domain.substring(0, dotPos);
    }

    public static String getExtension(String domain) {
        int dotPos = domain.lastIndexOf('.');
        return domain.substring(dotPos + 1);
    }

    public static String mostCommonDomain(Map<String, Integer> domainCount) {
        String common = "";
        int max = 0;
        for (String d : domainCount.keySet()) {
            if (domainCount.get(d) > max) {
                max = domainCount.get(d);
                common = d;
            }
        }
        return common;
    }
}
