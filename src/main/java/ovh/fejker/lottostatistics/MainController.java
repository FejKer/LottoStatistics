package ovh.fejker.lottostatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
        ArrayList<Raffle> raffleList = new ArrayList<>();
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

    public void onButtonClick(ActionEvent event) {
        //change game or size
        //clean arraylist
    }
    public void updateLabels(String game, int size){
        gameLabel.setText(gameLabel.getText() + game);
        sizeLabel.setText(sizeLabel.getText() + size);
    }

}
