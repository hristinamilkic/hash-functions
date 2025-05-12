package hashfunctions;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Map;
import java.util.HashMap;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AvalancheEffectFile {

    static String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2B-512", "RIPEMD160"};
    static String basePath = "/Users/hristinamilkic/Desktop/files/"; 
    static String[] fileNames = {"smallfile.bin", "largefile.bin", "small_text.txt", "large_text.txt"};

    public static Map<String, Double> avalancheResults = new HashMap<>();

    public static void runAvalancheTest() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        for (String algo : algorithms) {
            for (String fileName : fileNames) {
                String fullPath = basePath + fileName;

                // Učitavanje originalnog fajla
                byte[] originalFileData = readFile(fullPath);
                byte[] modifiedFileData = originalFileData.clone();
                modifiedFileData[0] ^= 0x01; // Menjanje jednog bita

                // Heširanje originalnog i modifikovanog fajla
                byte[] h1 = hash(algo, originalFileData);
                byte[] h2 = hash(algo, modifiedFileData);

                // Računanje razlike u bitovima
                int diffBits = countBitDifferences(h1, h2);
                int totalBits = h1.length * 8;
                double percentage = (100.0 * diffBits) / totalBits;

                avalancheResults.put(algo, percentage);
                System.out.printf("Algoritam: %s | Fajl: %s | Razlika: %d bita (%.2f%%)%n", algo, fileName, diffBits, percentage);
            }
        }
    }

    static byte[] hash(String algo, byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algo, "BC");
        return md.digest(data);
    }

    static int countBitDifferences(byte[] a, byte[] b) {
        int diff = 0;
        for (int i = 0; i < a.length; i++) {
            diff += Integer.bitCount(a[i] ^ b[i]);
        }
        return diff;
    }

    static byte[] readFile(String filePath) throws Exception {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return fis.readAllBytes();
        }
    }
}
