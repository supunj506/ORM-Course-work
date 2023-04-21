/*
 * @author : Supun Jaysinghe
 * Date    : 03 April 2023
 * Time    : 2:06 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.LoginBO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;
import lk.ccns.d24.util.ValidateUtil;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginFormController {
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);

    public AnchorPane loginFormAP;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Pane signUpPane;
    public JFXTextField txtSignUserName;
    public JFXTextField txtPasswordShow;
    public ImageView dontShowPasswordIcon;
    public ImageView showPasswordIcon;
    public JFXTextField txtSignInEmail;
    public ImageView dontShowSigninPasswordIcon;
    public ImageView showSigninPasswordIcon;
    public JFXTextField txtShowSignPassword;
    public JFXTextField txtShowSignRePassword;
    public JFXPasswordField txtSigninPassword;
    public JFXPasswordField txtSigninRePassword;
    public JFXButton btnSignUp;



    public void initialize() {
        try {
            UserDTO onlineUser = loginBO.getOnlineUser();
            if(onlineUser!=null){
                loginBO.updateUser(new UserDTO(onlineUser.getUser_name(),onlineUser.getPassword(),onlineUser.getEmail(),"offline"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtPasswordShow.setVisible(false);
        signUpPane.setVisible(false);

    }

    public void loginOnAction(javafx.event.ActionEvent actionEvent) {

        try {
            if(txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Missing Data Found...!!! Try Again...!!! ").showAndWait();
            }else {
                UserDTO login = loginBO.login(txtUserName.getText());
                if(login==null){
                    new Alert(Alert.AlertType.ERROR, "User Is Not In System...!!! ").showAndWait();
                    clean();
                }else {
                    if(login.getPassword().equals(txtPassword.getText())){
                        UserDTO userDTO = loginBO.login(txtUserName.getText());
                        loginBO.updateUser(new UserDTO(
                                userDTO.getUser_name(),
                                userDTO.getPassword(),
                                userDTO.getEmail(),
                                "online"
                        ));
                        new Alert(Alert.AlertType.CONFIRMATION, "Login Successfully...!!! ").showAndWait();
                        Navigation.navigate(Routes.DASHBOARD, loginFormAP);
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Password Incorrect...!!! ").showAndWait();

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void signupOnAction(javafx.event.ActionEvent actionEvent) {

        if(!txtSignUserName.getText().isEmpty() && !txtSigninPassword.getText().isEmpty() && !txtSigninRePassword.getText().isEmpty() && !txtSignInEmail.getText().isEmpty()){

            String pw1=txtSigninPassword.getText();
            String pw2=txtSigninRePassword.getText();

            try {
                if(loginBO.getUserData(txtSignUserName.getText())==null){
                    if(pw1.equals(pw2)){

                        boolean isADD = loginBO.addUser(getUserDTO());
                        if(isADD){
                            new Alert(Alert.AlertType.CONFIRMATION,"Sign Up Successfully..!!! >>>").show();
                            clean();
                        }

                    }else {
                        new Alert(Alert.AlertType.ERROR,"Password Doesn't Match..!!! Try Again >>>").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"User Already In System..!!! Try Again >>>").show();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Missing Data Found..!!! Try Again >>>").show();
        }


    }

    private void clean() {
        txtSignUserName.clear();
        txtSigninPassword.clear();
        txtSigninRePassword.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtSignInEmail.clear();
        signUpPane.setVisible(false);
    }

    private UserDTO getUserDTO() {
        return new UserDTO(
                txtSignUserName.getText(),
                txtSigninPassword.getText(),
                txtSignInEmail.getText(),
                "offline"
        );
    }


    public void signupNavigationOnAction(MouseEvent actionEvent) {
        signUpPane.setVisible(true);

    }

    public void closeSignUpPane(MouseEvent mouseEvent) {
        signUpPane.setVisible(false);

    }

    public void dontViewPasswordOnAction(MouseEvent mouseEvent) {
        txtPasswordShow.setVisible(false);
        txtPassword.setVisible(true);
        dontShowPasswordIcon.setVisible(false);
        showPasswordIcon.setVisible(true);
    }

    public void viewPasswordOnAction(MouseEvent mouseEvent) {
        txtPasswordShow.setText(txtPassword.getText());
        txtPassword.setVisible(false);
        showPasswordIcon.setVisible(false);
        dontShowPasswordIcon.setVisible(true);
        txtPasswordShow.setVisible(true);
    }

    public void dontViewSigninPasswordOnAction(MouseEvent mouseEvent) {
        txtShowSignPassword.setVisible(false);
        txtShowSignRePassword.setVisible(false);
        txtSigninPassword.setVisible(true);
        txtSigninRePassword.setVisible(true);
        dontShowSigninPasswordIcon.setVisible(false);
        showSigninPasswordIcon.setVisible(true);

    }

    public void viewSigninPasswordOnAction(MouseEvent mouseEvent) {
        txtShowSignPassword.setText(txtSigninPassword.getText());
        txtShowSignRePassword.setText(txtSigninRePassword.getText());
        txtSigninPassword.setVisible(false);
        txtSigninRePassword.setVisible(false);
        showSigninPasswordIcon.setVisible(false);
        dontShowSigninPasswordIcon.setVisible(true);
        txtShowSignPassword.setVisible(true);
        txtShowSignPassword.setEditable(false);
        txtShowSignRePassword.setVisible(true);
        txtShowSignRePassword.setEditable(false);

    }

    public void userNameKeyReleasedOnAction(KeyEvent keyEvent) {

    }

    public void emailKeyReleasedOnAction(KeyEvent keyEvent) {
        ValidateUtil.singleValidate(Pattern.compile("^[a-z0-9]{4,40}@(gmail|yahoo|ymail).com$"),txtSignInEmail);


    }

    public void passwordKeyReleasedOnAction(KeyEvent keyEvent) {

    }
}
