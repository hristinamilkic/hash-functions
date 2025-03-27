package hashfunctions;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashBenchmark.runBenchmark();
        CollisionTester.testCollisions();

        Map<String, Long> executionTimes = HashBenchmark.executionTimes;
        Map<String, Integer> collisionCounts = CollisionTester.collisionCounts;
        
        HashChart.displayCharts(executionTimes, collisionCounts);
    }
}
