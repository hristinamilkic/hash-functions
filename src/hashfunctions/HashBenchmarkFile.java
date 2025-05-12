package hashfunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class HashBenchmarkFile {
	public static Map<String, Long> avgExecutionTimes = new HashMap<>();

    // Algoritmi koje testiram
    static String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2B-512", "RIPEMD160"};

    // Putanje do fajlova (prilagođeno za Desktop)
    static String basePath = "/Users/hristinamilkic/Desktop/files/";

    static String[] fileNames = {
        "smallfile.bin",
        "largefile.bin",
        "small_text.txt",
        "large_text.txt"
    };

    public static void runBenchmark() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        System.out.println("=== Uporedna analiza heš algoritama (binarni + tekstualni fajlovi) ===\n");

        for (String algorithm : algorithms) {
            long total = 0;

            System.out.println("Algoritam: " + algorithm);

            for (String fileName : fileNames) {
                String fullPath = basePath + fileName;
                long duration = hashFileWithTiming(algorithm, fullPath);
                System.out.printf("   %s -> %d ms%n", fileName, duration);
                total += duration;
            }

            long avg = total / fileNames.length;
            avgExecutionTimes.put(algorithm, avg);

            System.out.println("   Prosečno vreme: " + avg + " ms\n");
        }
    }



    private static long hashFileWithTiming(String algorithm, String filePath) throws Exception {
        byte[] buffer = new byte[8192];

        MessageDigest digest = MessageDigest.getInstance(algorithm, "BC");

        long startTime = System.currentTimeMillis();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hash = digest.digest();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static String bytesToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
