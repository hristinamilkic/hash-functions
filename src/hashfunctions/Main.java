package hashfunctions;

import java.util.Map; // ✅ Import za Map

public class Main {
    public static void main(String[] args) {
        HashBenchmark.runBenchmark();
        CollisionTester.testCollisions();


        Map<String, Long> executionTimes = HashBenchmark.executionTimes; 
        HashChart.displayChart(executionTimes);

        RealWorldUsage.hashPassword();
    }
}
