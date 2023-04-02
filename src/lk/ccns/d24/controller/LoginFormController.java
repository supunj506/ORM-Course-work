/*
 * @author : Supun Jaysinghe
 * Date    : 03 April 2023
 * Time    : 2:06 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginFormAP;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    public void forgotPasswordOnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void signupOnAction(javafx.event.ActionEvent actionEvent) {
    }

    public void loginOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.DASHBOARD,loginFormAP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewPasswordOnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
