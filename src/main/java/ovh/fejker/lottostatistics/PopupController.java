package ovh.fejker.lottostatistics;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class PopupController {
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    public void initialize() {
        numberAxis.setAutoRanging(true);
    }

}
