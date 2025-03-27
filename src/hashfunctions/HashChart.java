package hashfunctions;

import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class HashChart {
    public static void displayCharts(Map<String, Long> executionTimes, Map<String, Integer> collisionCounts) {
    
        DefaultCategoryDataset speedDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Long> entry : executionTimes.entrySet()) {
            speedDataset.addValue(entry.getValue(), "Brzina (ms)", entry.getKey());
        }

        DefaultCategoryDataset collisionDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : collisionCounts.entrySet()) {
            collisionDataset.addValue(entry.getValue(), "Kolizije", entry.getKey());
        }

        JFreeChart speedChart = ChartFactory.createBarChart(
                "Brzina heš funkcija",
                "Heš Algoritmi",
                "Vreme (ms)",
                speedDataset
        );

        JFreeChart collisionChart = ChartFactory.createBarChart(
                "Broj kolizija heš funkcija",
                "Heš Algoritmi",
                "Broj kolizija",
                collisionDataset
        );

        JFrame speedFrame = new JFrame("Grafikon - Brzina");
        speedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        speedFrame.add(new ChartPanel(speedChart));
        speedFrame.setSize(800, 600);
        speedFrame.setVisible(true);

        JFrame collisionFrame = new JFrame("Grafikon - Kolizije");
        collisionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        collisionFrame.add(new ChartPanel(collisionChart));
        collisionFrame.setSize(800, 600);
        collisionFrame.setVisible(true);
    }
}
