package hashfunctions;

import java.util.HashSet;

public class CollisionTester {
    public static void testCollisions() {
        String[] algorithms = {"MD5", "SHA-256", "SHA3-256"};
        String[] testStrings = {"Hello", "hello", "HELLO", "HeLLo"};

        for (String algorithm : algorithms) {
            HashSet<String> hashes = new HashSet<>();
            boolean collisionFound = false;

            for (String input : testStrings) {
                String hash = HashComparator.hash(input, algorithm);
                if (!hashes.add(hash)) {
                    collisionFound = true;
                    System.out.println("⚠️ Kolizija pronađena u algoritmu: " + algorithm);
                    break;
                }
            }

            if (!collisionFound) {
                System.out.println("✅ Nema kolizija za algoritam: " + algorithm);
            }
        }
    }
}
