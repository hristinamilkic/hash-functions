package hashfunctions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;

public class RealWorldUsage {
    public static String hashFile(String filePath, String algorithm) throws Exception {
    	
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(fileBytes);
        return HashUtils.bytesToHex(hashBytes);
    }

    public static void hashPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Unesite lozinku: ");
        System.out.flush(); 
        String password = scanner.nextLine();
        System.out.println("SHA-256 hash lozinke: " + HashUtils.hash(password, "SHA-256"));
    }

}
