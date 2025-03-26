package hashfunctions;

import java.security.MessageDigest;

public class HashUtils {
    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String hash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] encodedHash = digest.digest(input.getBytes());
            return bytesToHex(encodedHash);
        } catch (Exception e) {
            throw new RuntimeException("Greška pri heširanju", e);
        }
    }
}
