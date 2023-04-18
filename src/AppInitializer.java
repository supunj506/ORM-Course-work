/*
 * @author : Supun Jaysinghe
 * Date    : 03 April 2023
 * Time    : 2:01 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/lk/ccns/d24/view/DashBoardForm.fxml")))));
        primaryStage.setTitle("D24 Hotel Manage System");
        primaryStage.alwaysOnTopProperty();
        primaryStage.show();
    }
}
