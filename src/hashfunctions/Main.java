package hashfunctions;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
//        HashBenchmark.runBenchmark();
//        CollisionTester.testCollisions();
//
//        Map<String, Long> executionTimes = HashBenchmark.executionTimes;
//        Map<String, Integer> collisionCounts = CollisionTester.collisionCounts;
//        
//        HashChart.displayCharts(executionTimes, collisionCounts);
        
        System.out.println("\nPokrećem testiranje fajlova...");
        
        // TEST - BENCHMARK
        HashBenchmarkFile.runBenchmark();
        HashFileChart.displayChart(HashBenchmarkFile.avgExecutionTimes);

        // TEST - KOLIZIJE
        System.out.println("\nPokrećem testiranje kolizija...");
        CollisionTestFile.runCollisionTest();
        CollisionFileChart.displayChart(CollisionTestFile.collisionResults);

        // TEST - AVALANCHE
        System.out.println("\nPokrećem testiranje avalanche efekta...");
        AvalancheEffectFile.runAvalancheTest();
        AvalancheFileChart.displayChart(AvalancheEffectFile.avalancheResults);
    }
}
