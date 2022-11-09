package ovh.fejker.lottostatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button button;
    @FXML
    private ComboBox comboBox;
    @FXML
    private TextField textField;
    private int size;
    private String name;
    private Stage stage;

    public void onButtonClick(ActionEvent e) throws IOException {
        size = Integer.parseInt(textField.getText());
        name = (String) comboBox.getValue();
        new DataFetcher(name, size);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainview.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.setText1(Main.getRaffleList().get(0).toString());

        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("LottoStatistics");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        comboBox.getItems().add("Lotto");
        comboBox.getItems().add("LottoPlus");
        comboBox.getItems().add("MiniLotto");
        comboBox.getItems().add("EuroJackpot");
    }

}
