/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 1:26 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;

import java.io.IOException;

public class DashBoardFormController {
    public Label lbluserName;
    public AnchorPane dashBoardMainAP;
    public AnchorPane dashBoardAP;

    public void logOutOnAction(MouseEvent mouseEvent) {
        try {
            Navigation.navigate(Routes.LOGIN,dashBoardAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userUpdateOnAction(MouseEvent mouseEvent) {
    }

    public void dashBoardNavigateOnAction(MouseEvent mouseEvent) {
        try {
            Navigation.navigate(Routes.DASHBOARD,dashBoardAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageStudentNavigateOnAction(MouseEvent mouseEvent) {
        try {
            Navigation.navigate(Routes.MANAGE_STUDENT,dashBoardMainAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageRoomNavigateOnAction(MouseEvent mouseEvent) {
        try {
            Navigation.navigate(Routes.MANAGE_ROOM,dashBoardMainAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void reserveDetailsNavigateOnAction(MouseEvent mouseEvent) {
        try {
            Navigation.navigate(Routes.MAKE_RESERVE,dashBoardMainAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
