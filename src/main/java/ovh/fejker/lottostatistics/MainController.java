package ovh.fejker.lottostatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private ListView listView;
    @FXML
    private Label gameLabel;
    @FXML
    private Label sizeLabel;
    @FXML
    private Button changeButton;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Raffle, Long> idColumn;
    @FXML
    private TableColumn<Raffle, String> dateColumn;
    @FXML
    private TableColumn<Raffle, String> resultsColumn;
    @FXML
    private TableColumn<Raffle, String> extraResultsColumn;
    @FXML
    private Button generateButton;
    private Stage stage;
    private static ArrayList<Raffle> raffleList;

    @FXML
    public void initialize() {
        for(Raffle s : Main.getRaffleList()){
            listView.getItems().add(s.getDrawDate());
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("drawId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("drawDate"));
        resultsColumn.setCellValueFactory(new PropertyValueFactory<>("results"));
        extraResultsColumn.setCellValueFactory(new PropertyValueFactory<>("specialResults"));
    }

    public void onChange(MouseEvent event) {
        updateValues();
    }
    public void onKeyChange(KeyEvent event) {
        updateValues();
    }

    private void updateValues() {
        raffleList = new ArrayList<>();
        for(Object s : listView.getSelectionModel().getSelectedItems()) {
            for(Raffle r : Main.getRaffleList()){
                if(String.valueOf(s).equals(r.getDrawDate())){
                    raffleList.add(r);
                }
            }
        }
        tableView.getItems().clear();
        tableView.getItems().addAll(raffleList);
    }

    public void onChangeButtonClick(ActionEvent e) throws IOException {
        Main.getRaffleList().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();

        stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("LottoStatistics");
        stage.setScene(scene);
        stage.show();
    }
    public void onGenerateButtonClick(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popup.fxml"));
        Parent root = fxmlLoader.load();

        PopupController popupControllercontroller = fxmlLoader.getController();

        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("LottoStatistics");
        stage.setScene(scene);
        stage.show();
    }
    public void updateLabels(String game, int size){
        gameLabel.setText(gameLabel.getText() + game);
        sizeLabel.setText(sizeLabel.getText() + size);
    }
    public static ArrayList<Raffle> getRaffleList(){
        return raffleList;
    }
}
