/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 1:26 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXListView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.ManageReserveBO;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.dto.CustomDTO;
import lk.ccns.d24.dto.RoomDTO;
import lk.ccns.d24.util.Navigation;
import lk.ccns.d24.util.Routes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    ManageRoomBO manageRoomBO=(ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);
    ManageReserveBO manageReserveBO=(ManageReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_RESERVE);

    public Label lbluserName;
    public AnchorPane dashBoardMainAP;
    public AnchorPane dashBoardAP;
    public PieChart availableQTYPieChart;
    public ListView<String> keyMoneyStudentListview;
    public Label lblTime;
    public Label lblDate;

    public void initialize(){
        setDataToPieChart();
        setRemainigKeyMoneyStudentListView();
        loadTimeDate();
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
        try {
            ObservableList<CustomDTO> student=manageReserveBO.getRemainingKeyMoneyStudentDetails();
            ObservableList<String> listViewData=FXCollections.observableArrayList();
            for(CustomDTO customDTO:student){
                listViewData.add(customDTO.getStudent_id()+" / "+customDTO.getName()+" / "+customDTO.getDate());


            }
            keyMoneyStudentListview.setItems(listViewData);
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
