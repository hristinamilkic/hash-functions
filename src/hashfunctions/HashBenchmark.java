package hashfunctions;

import org.bouncycastle.jcajce.provider.digest.BCMessageDigest;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class HashBenchmark {

    private static final String TEST_STRING = "Hristina Milkic";
    public static Map<String, Long> executionTimes = new HashMap<>();

    public static void runBenchmark() {
        String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2", "RIPEMD-160"};
        int numTests = 50; 
        int numIterations = 100000; 

        System.out.println("Benchmarking heš funkcija...");

        
        for (String algorithm : algorithms) {
            long totalDuration = 0;

            for (int test = 0; test < numTests; test++) {
            	System.gc();
            	try {
            	    Thread.sleep(10);
            	} catch (InterruptedException e) {
            	    Thread.currentThread().interrupt();
            	}


                long startTime = System.nanoTime();

                for (int i = 0; i < numIterations; i++) {
                    hash(TEST_STRING, algorithm);
                }

                long endTime = System.nanoTime();
                totalDuration += (endTime - startTime);
            }

            long avgDuration = (totalDuration / numTests) / 1_000_000; 
            executionTimes.put(algorithm, avgDuration);
            System.out.println(algorithm + " - Prosečno vreme: " + avgDuration + " ms");
        }
    }


    private static void hash(String input, String algorithm) {
        try {
            switch (algorithm) {
                case "MD5":
                case "SHA-256":
                case "SHA-512":
                case "SHA3-256":
                    MessageDigest digest = MessageDigest.getInstance(algorithm);
                    digest.update(input.getBytes());
                    digest.digest();
                    break;

                case "BLAKE2":
                    BCMessageDigest blake2b = new Blake2b.Blake2b256();
                    blake2b.update(input.getBytes());
                    blake2b.digest();
                    break;


                case "RIPEMD-160":
                    BCMessageDigest ripemd160 = new RIPEMD160.Digest();
                    ripemd160.update(input.getBytes());
                    ripemd160.digest();
                    break;

                default:
                    throw new IllegalArgumentException("Nepoznat algoritam: " + algorithm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        runBenchmark();

        System.out.println("\n=== REZULTATI ===");
        for (Map.Entry<String, Long> entry : executionTimes.entrySet()) {
            System.out.printf("%s -> %d ms\n", entry.getKey(), entry.getValue());
        }
    }
}
