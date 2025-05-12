package hashfunctions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class CollisionFileChart {

    public static void displayChart(Map<String, Boolean> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Boolean> entry : data.entrySet()) {
            dataset.addValue(entry.getValue() ? 1 : 0, "Kolizija", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Rezultati testiranja kolizija",
                "Heš algoritam",
                "Kolizija (1 = DA, 0 = NE)",
                dataset
        );

        ChartFrame frame = new ChartFrame("Collision Test Results", barChart);
        frame.pack();
        frame.setVisible(true);
    }
}
