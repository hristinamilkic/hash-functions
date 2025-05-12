package hashfunctions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class AvalancheFileChart {

    public static void displayChart(Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Procenat razlike", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Rezultati avalanche efekta",
                "Heš algoritam",
                "Procenat razlike (%)",
                dataset
        );

        ChartFrame frame = new ChartFrame("Avalanche Effect Results", barChart);
        frame.pack();
        frame.setVisible(true);
    }
}
