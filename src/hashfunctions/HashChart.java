package hashfunctions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.util.Map;

public class HashChart {
    public static void displayChart(Map<String, Long> executionTimes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Long> entry : executionTimes.entrySet()) {
            dataset.addValue(entry.getValue(), "Heš Algoritmi", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Poređenje brzine heš funkcija", "Heš Algoritmi", "Vreme (ms)", dataset);
        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("Grafikon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
