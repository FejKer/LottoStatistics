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
        XYChart.Series set = new XYChart.Series();
        for(Raffle raffle : MainController.getRaffleList()){
            //TODO count number appearance
        }
    }

}
