package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private GridPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WeatherAPP - Author: R.Hryniewicki");
        showMainView();
    }

    private void showMainView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("template.fxml"));
        mainLayout = fxmlLoader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("css/style.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
