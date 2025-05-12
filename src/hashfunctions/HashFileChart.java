package hashfunctions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

public class HashFileChart {

    public static void displayChart(Map<String, Long> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Long> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Vreme (ms)", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Prosečno vreme heširanja fajlova po algoritmu",
                "Heš algoritam",
                "Vreme (ms)",
                dataset
        );

        ChartFrame frame = new ChartFrame("Hash File Benchmark", barChart);
        frame.pack();
        frame.setVisible(true);
    }
}
