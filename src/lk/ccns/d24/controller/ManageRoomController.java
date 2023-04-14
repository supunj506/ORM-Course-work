/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 9:41 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ManageStudentController {
    public AnchorPane manageRoomAP;
    public JFXTextField txtRoomId;
    public JFXComboBox<ArrayList> cmbRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQTY;
    public JFXTextField txtSearchByRoomId;
    public TableView roomTableView;
    public void initialize(){


    }

    public void addRoomOnAction(ActionEvent actionEvent) {
    }

    public void UpdateRoomOnAction(ActionEvent actionEvent) {
    }

    public void deleteRoomOnAction(ActionEvent actionEvent) {
    }

    public void clearTableOnAction(ActionEvent actionEvent) {
    }

    public void searchRoomByIdOnAction(ActionEvent actionEvent) {
    }
}
