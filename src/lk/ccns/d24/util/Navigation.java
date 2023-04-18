/*
 * @author : Supun Jaysinghe
 * Date    : 03 April 2023
 * Time    : 2:24 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case LOGIN:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle("DashBoard Form");
                initUI("DashBoardForm.fxml");
                break;
            case MANAGE_ROOM:
                window.setTitle("Manage Room Form");
                initUI("ManageRoomForm.fxml");
                break;
            case MANAGE_STUDENT:
                window.setTitle("Manage Student Form");
                initUI("ManageStudentForm.fxml");
                break;
            case MAKE_RESERVE:
                window.setTitle("Make Reservation Form");
                initUI("ManageReservationForm.fxml");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("/lk/ccns/d24/view/" + location))));
    }
}
