package ovh.fejker.lottostatistics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    private static ArrayList<Raffle> raffleList;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LottoStatistics");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        raffleList = new ArrayList<>();
        launch();
    }

    public static void addToRaffleList(Raffle r){
        raffleList.add(r);
    }
    public static ArrayList<Raffle> getRaffleList(){
        return raffleList;
    }
}