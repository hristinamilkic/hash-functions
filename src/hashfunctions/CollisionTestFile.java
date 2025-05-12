package hashfunctions;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.Security;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CollisionTestFile {

    static String[] algorithms = {"MD5", "SHA-256", "SHA-512", "SHA3-256", "BLAKE2B-512", "RIPEMD160"};
    static String basePath = "/Users/hristinamilkic/Desktop/files/";
    static String[] fileNames = {"smallfile.bin", "largefile.bin", "small_text.txt", "large_text.txt"};

    public static Map<String, Boolean> collisionResults = new HashMap<>();

    public static void runCollisionTest() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        for (String algo : algorithms) {
            Set<String> hashes = new HashSet<>();
            boolean collision = false;

            for (String fileName : fileNames) {
                String fullPath = basePath + fileName;
                byte[] fileData = readFile(fullPath);
                byte[] hash = hash(algo, fileData);
                String hex = bytesToHex(hash);
                if (!hashes.add(hex)) {
                    collision = true;
                    break;
                }
            }

            collisionResults.put(algo, collision);
            System.out.println("Algoritam: " + algo + " => Kolizija: " + (collision ? "DA!" : "NE ✅"));
        }
    }

    static byte[] hash(String algo, byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algo, "BC");
        return md.digest(data);
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    static byte[] readFile(String filePath) throws Exception {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return fis.readAllBytes();
        }
    }
}
