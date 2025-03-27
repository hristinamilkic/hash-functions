package hashfunctions;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;

import java.security.MessageDigest;
import java.util.Formatter;

public class HashGenerator {

    private static final String TEST_STRING = "Hristina Milkic";

    public static void main(String[] args) {
        String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2", "RIPEMD-160"};

        System.out.println("=== Heš vrednosti za: \"" + TEST_STRING + "\" ===");
        for (String algorithm : algorithms) {
            String hashValue = generateHash(TEST_STRING, algorithm);
            System.out.printf("%s -> %s\n", algorithm, hashValue);
        }
    }

    private static String generateHash(String input, String algorithm) {
        try {
            byte[] hashBytes;
            switch (algorithm) {
                case "MD5":
                case "SHA-256":
                case "SHA-512":
                case "SHA3-256":
                    MessageDigest digest = MessageDigest.getInstance(algorithm);
                    digest.update(input.getBytes());
                    hashBytes = digest.digest();
                    break;

                case "BLAKE2":
                    BCMessageDigest blake2b = new Blake2b.Blake2b256();
                    blake2b.update(input.getBytes());
                    hashBytes = blake2b.digest();
                    break;

                case "RIPEMD-160":
                    BCMessageDigest ripemd160 = new RIPEMD160.Digest();
                    ripemd160.update(input.getBytes());
                    hashBytes = ripemd160.digest();
                    break;

                default:
                    throw new IllegalArgumentException("Nepoznat algoritam: " + algorithm);
            }
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        try (Formatter formatter = new Formatter()) {
            for (byte b : bytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }
}
