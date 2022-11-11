package ovh.fejker.lottostatistics;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PopupController {
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;
    @FXML
    private BarChart barChart;

    @FXML
    public void initialize() {
        System.out.println("INIT BARCHART");
        XYChart.Series set = new XYChart.Series();
        for(int s : Raffle.getOccurrence().keySet()){
            System.out.println(s + " " + Raffle.getOccurrence().get(s));
            set.getData().add(new XYChart.Data(String.valueOf(s), Raffle.getOccurrence().get(s)));
        }
        barChart.getData().addAll(set);
    }

}
