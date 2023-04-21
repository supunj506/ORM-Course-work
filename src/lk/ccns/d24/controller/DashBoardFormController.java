/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 1:26 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.LoginBO;
import lk.ccns.d24.bo.Custom.ManageReserveBO;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.dto.CustomDTO;
import lk.ccns.d24.dto.ReservationDTO;
import lk.ccns.d24.dto.RoomDTO;
import lk.ccns.d24.dto.UserDTO;
import lk.ccns.d24.entity.User;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;
import lk.ccns.d24.util.ValidateUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class DashBoardFormController {
    private final ManageRoomBO manageRoomBO=(ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);
    private final ManageReserveBO manageReserveBO=(ManageReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_RESERVE);
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN_USER);

    public Label lblEmail;
    public Label lblUserName;
    public AnchorPane dashBoardMainAP;
    public AnchorPane dashBoardAP;
    public PieChart availableQTYPieChart;
    public ListView<String> keyMoneyStudentListview;
    public Label lblTime;
    public Label lblDate;
    public Label lblAccommodating;
    public Label lblKeyMoneyStudent;
    public Pane mangeUserPane;
    public JFXTextField txtUserName;
    public JFXTextField txtShowPassword;
    public JFXTextField txtShowRePassword;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtRePassword;
    public ImageView dontShowSigninPasswordIcon;
    public ImageView showSigninPasswordIcon;
    public JFXButton btnUpdateUser;

    public void initialize(){
        setAccommodatingCount();
        mangeUserPane.setVisible(false);
        setUser();
        setDataToPieChart();
        setRemainigKeyMoneyStudentListView();
        loadTimeDate();


    }

    private void setAccommodatingCount() {
        int count=0;
        try {
            ObservableList<ReservationDTO> allDetails = manageReserveBO.getAllDetails();
            ArrayList<ReservationDTO> all=new ArrayList<>();
            if(allDetails.isEmpty()){
                lblAccommodating.setText("0");

            }else {
                for(ReservationDTO reservationDTO:allDetails){
                    count++;
                }
                lblAccommodating.setText(String.valueOf(count));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUser() {
        try {
            UserDTO onlineUser = loginBO.getOnlineUser();
            if(onlineUser!=null){
                lblUserName.setText(onlineUser.getUser_name());
                lblEmail.setText(onlineUser.getEmail());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTimeDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline clock =new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime localTime=LocalTime.now();
            lblTime.setText(localTime.getHour()+":"+localTime.getMinute()+":"+localTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    private void setRemainigKeyMoneyStudentListView() {
        int count=0;
        try {
            ObservableList<CustomDTO> student=manageReserveBO.getRemainingKeyMoneyStudentDetails();
            ObservableList<String> listViewData=FXCollections.observableArrayList();
            for(CustomDTO customDTO:student){
                listViewData.add(customDTO.getStudent_id()
                        + " / "+customDTO.getName()
                        +" / " +customDTO.getDate()
                        +" / " +customDTO.getContact()
                );
                count++;
            }
            keyMoneyStudentListview.setItems(listViewData);
            lblKeyMoneyStudent.setText(String.valueOf(count));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setDataToPieChart() {
        ObservableList<PieChart.Data>pieChartData= FXCollections.observableArrayList();
        try {
            ObservableList<RoomDTO> allData = manageRoomBO.getAllData();
            for(RoomDTO roomDTO:allData){
                pieChartData.add(new PieChart.Data(roomDTO.getType(), roomDTO.getQty()));
            }
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(data.getName(),"  ",data.pieValueProperty())
                    ));
            availableQTYPieChart.setData(pieChartData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutOnAction(MouseEvent mouseEvent) {
        try {
            UserDTO userData = loginBO.getUserData(lblUserName.getText());
            boolean offline = loginBO.updateUser(new UserDTO(
                    userData.getUser_name(),
                    userData.getPassword(),
                    userData.getEmail(),
                    "offline"
            ));
            if(offline){
                Navigation.navigate(Routes.LOGIN,dashBoardAP);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userUpdateOnAction(MouseEvent mouseEvent) {

        mangeUserPane.setVisible(true);
        UserDTO userDTO= null;
        try {
            userDTO = loginBO.getUserData(lblUserName.getText());
            txtUserName.setText(userDTO.getUser_name());
            txtPassword.setText(userDTO.getPassword());
            txtShowPassword.setText(txtPassword.getText());
            txtRePassword.setText(userDTO.getPassword());
            txtShowRePassword.setText(txtRePassword.getText());
            txtEmail.setText(userDTO.getEmail());
            txtShowPassword.setVisible(false);
            txtShowRePassword.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();

        }


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

    public void userNameKeyReleasedOnAction(KeyEvent keyEvent) {
    }

    public void closeManageUserPaneOnAction(MouseEvent mouseEvent) {

        mangeUserPane.setVisible(false);
    }

    public void emailKeyReleasedOnAction(KeyEvent keyEvent) {
        ValidateUtil.singleValidate(Pattern.compile("^[a-z0-9]{4,40}@(gmail|yahoo|ymail).com$"),txtEmail);
    }

    public void passwordKeyReleasedOnAction(KeyEvent keyEvent) {

    }

    public void dontViewSigninPasswordOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        txtShowRePassword.setVisible(false);
        txtPassword.setVisible(true);
        txtRePassword.setVisible(true);
        dontShowSigninPasswordIcon.setVisible(false);
        showSigninPasswordIcon.setVisible(true);

    }

    public void viewSigninPasswordOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setText(txtPassword.getText());
        txtShowRePassword.setText(txtRePassword.getText());
        txtPassword.setVisible(false);
        txtRePassword.setVisible(false);
        showSigninPasswordIcon.setVisible(false);
        dontShowSigninPasswordIcon.setVisible(true);
        txtShowPassword.setVisible(true);
        txtShowPassword.setEditable(false);
        txtShowRePassword.setVisible(true);
        txtShowRePassword.setEditable(false);

    }

    public void updateUserOnAction(ActionEvent actionEvent) throws IOException {

        try {

        if(!txtUserName.getText().isEmpty() && !txtPassword.getText().isEmpty() && !txtRePassword.getText().isEmpty() && !txtEmail.getText().isEmpty()){

            String pw1=txtPassword.getText();
            String pw2=txtRePassword.getText();


                if(loginBO.getUserData(txtUserName.getText())!=null){
                    if(pw1.equals(pw2)){

                        boolean isADD = loginBO.updateUser(getUserDTO());
                        if(isADD){
                            new Alert(Alert.AlertType.CONFIRMATION,"Update User Successfully..!!! >>>").show();
                            clean();
                        }

                    }else {
                        new Alert(Alert.AlertType.ERROR,"Password Doesn't Match..!!! Try Again >>>").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"User Is Not In System..!!! Try Again >>>").show();

                }

        }else{
            new Alert(Alert.AlertType.ERROR,"Missing Data Found..!!! Try Again >>>").show();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private UserDTO getUserDTO() {
        return new UserDTO(
                txtUserName.getText(),
                txtPassword.getText(),
                txtEmail.getText(),
                "online"
        );
    }

    private void clean() {
        txtUserName.clear();
        txtPassword.clear();
        txtRePassword.clear();
        txtPassword.clear();
        txtUserName.clear();
        txtEmail.clear();
        mangeUserPane.setVisible(false);
    }
}
