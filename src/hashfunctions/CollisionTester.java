package hashfunctions;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CollisionTester {
    private static final int NUM_TEST_STRINGS = 1_000_000; 
    private static final int STRING_LENGTH = 10;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    public static Map<String, Integer> collisionCounts = new HashMap<>();
    
    public static void testCollisions() {
        String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2", "RIPEMD-160"};
        SecureRandom random = new SecureRandom();

        System.out.println("\n=== Testiranje kolizija ===");

        for (String algorithm : algorithms) {
            HashSet<String> hashes = new HashSet<>();
            int collisionCount = 0;

            for (int i = 0; i < NUM_TEST_STRINGS; i++) {
                String input = generateRandomString(random);
                String hash = hash(input, algorithm);

                if (!hashes.add(hash)) {
                    collisionCount++;
                }
            }

            collisionCounts.put(algorithm, collisionCount);
            System.out.printf("%s - Kolizije: %d%n", algorithm, collisionCount);
        }
    }

    private static String generateRandomString(SecureRandom random) {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private static String hash(String input, String algorithm) {
        try {
            switch (algorithm) {
                case "MD5":
                case "SHA-256":
                case "SHA-512":
                case "SHA3-256":
                    MessageDigest digest = MessageDigest.getInstance(algorithm);
                    digest.update(input.getBytes());
                    return bytesToHex(digest.digest());

                case "BLAKE2":
                    BCMessageDigest blake2b = new Blake2b.Blake2b256();
                    blake2b.update(input.getBytes());
                    return bytesToHex(blake2b.digest());

                case "RIPEMD-160":
                    BCMessageDigest ripemd160 = new RIPEMD160.Digest();
                    ripemd160.update(input.getBytes());
                    return bytesToHex(ripemd160.digest());

                default:
                    throw new IllegalArgumentException("Nepoznat algoritam: " + algorithm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
