package ovh.fejker.lottostatistics;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    public void setText1(String text) {
        label1.setText(text);
    }
    public void setText2(String text) {
        label2.setText(text);
    }

}
