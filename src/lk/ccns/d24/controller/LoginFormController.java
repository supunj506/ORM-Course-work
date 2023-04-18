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
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.LoginBO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.entity.User;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFormController {
    private final LoginBO loginBO= (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);

    public AnchorPane loginFormAP;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public ImageView sowPasswordIcon;
    public Pane signUpPane;
    public JFXTextField txtSignUserName;
    public JFXTextField txtSignPassword;
    public JFXTextField txtSignRePassword;

    public void initialize(){
        signUpPane.setVisible(false);
    }

    public void loginOnAction(javafx.event.ActionEvent actionEvent){
        boolean login = false;
        try {
            login = loginBO.login(new UserDTO(txtUserName.getText(), txtPassword.getText()));
            if(login){
                new Alert(Alert.AlertType.CONFIRMATION,"Login Successfully...! ").showAndWait();
                Navigation.navigate(Routes.DASHBOARD,loginFormAP);
            }else {
                new Alert(Alert.AlertType.ERROR,"Wrong Input... Try Again...!").showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void signupOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            boolean isAdd = loginBO.addUser(new UserDTO(txtSignUserName.getText(), txtSignPassword.getText()));
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"SignIn Successfully...! ").showAndWait();
                signUpPane.setVisible(false);
                txtSignUserName.clear();
                txtSignPassword.clear();
                txtSignRePassword.clear();
            }else{
                txtSignUserName.clear();
                txtSignPassword.clear();
                txtSignRePassword.clear();
                new Alert(Alert.AlertType.ERROR,"User is Exists...! Try Different User Name...! ").showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgotPasswordOnAction(javafx.scene.input.MouseEvent mouseEvent) {

    }

    public void viewPasswordOnAction(javafx.scene.input.MouseEvent mouseEvent){
    }

    public void signupNavigationOnAction(MouseEvent actionEvent) {
        signUpPane.setVisible(true);

    }

    public void closeSignUpPane(MouseEvent mouseEvent) {
        signUpPane.setVisible(false);

    }
}
