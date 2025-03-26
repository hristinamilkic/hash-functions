package hashfunctions;

import java.util.HashMap;
import java.util.Map;

public class HashBenchmark {
    private static final String TEST_STRING = "Test12345";
    public static Map<String, Long> executionTimes = new HashMap<>();

    public static void runBenchmark() {
        String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256"};

        System.out.println("Benchmarking heš funkcija...");

        for (String algorithm : algorithms) {
            long startTime = System.nanoTime();


            for (int i = 0; i < 100000; i++) {
                HashComparator.hash(TEST_STRING, algorithm);
            }

            long endTime = System.nanoTime();
            long durationMs = (endTime - startTime) / 1_000_000; 

            executionTimes.put(algorithm, durationMs); 
            System.out.println(algorithm + " - Vreme izvršenja: " + durationMs + " ms");
        }
    }
}
