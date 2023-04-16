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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.dto.RoomDTO;

import java.io.IOException;

public class ManageRoomController {
    private final ManageRoomBO manageRoomBO= (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);
    public AnchorPane manageRoomAP;
    public JFXTextField txtRoomId;
    public JFXComboBox<String> cmbRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQTY;
    public JFXTextField txtSearchByRoomId;
    public TableView <RoomDTO>roomTableView;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQTY;

    public void initialize(){
        cmbRoomTypeLoad();
        loadRoomDataToTable();
        roomTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setDatForField(newValue);
            }
        });
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    private void setDatForField(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoom_id());
        txtRoomQTY.setText(String.valueOf(roomDTO.getQty()));
        txtKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        cmbRoomType.setValue(roomDTO.getType());
    }

    private void loadRoomDataToTable() {
        try {
            ObservableList<RoomDTO> roomList=manageRoomBO.getAllData();
            roomTableView.setItems(roomList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cmbRoomTypeLoad() {
        cmbRoomType.getItems().add("AC");
        cmbRoomType.getItems().add("NON-AC");
        cmbRoomType.getItems().add("AC / FOOD");
        cmbRoomType.getItems().add("NON-AC / FOOD");
    }

    public void addRoomOnAction(ActionEvent actionEvent) {
        try {
            if(manageRoomBO.add(new RoomDTO(
                    txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtRoomQTY.getText())))){
                new Alert(Alert.AlertType.CONFIRMATION,"Add Room Successfully...!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadRoomDataToTable();
        clearAllTextFieldOnAction(actionEvent);
    }

    public void UpdateRoomOnAction(ActionEvent actionEvent)  {

        boolean isUpdate= false;
        try {
            isUpdate = manageRoomBO.update(new RoomDTO(
                    txtRoomId.getText(),
                    cmbRoomType.getValue(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtRoomQTY.getText())
                    ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Update Room Data Successfully...!").show();
        }
        loadRoomDataToTable();
        clearAllTextFieldOnAction(actionEvent);
    }

    public void deleteRoomOnAction(ActionEvent actionEvent) {

        try {
            if(manageRoomBO.delete(new RoomDTO(
                    txtRoomId.getText(),
                    cmbRoomType.getValue().toString(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtRoomQTY.getText())))){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Room Successfully...!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadRoomDataToTable();
        clearAllTextFieldOnAction(actionEvent);

    }

    public void clearAllTextFieldOnAction(ActionEvent actionEvent) {
        txtSearchByRoomId.clear();
        txtRoomId.clear();
        txtRoomQTY.clear();
        txtKeyMoney.clear();
        cmbRoomType.setValue(null);
    }

    public void searchRoomByIdOnAction(ActionEvent actionEvent) {
        try {
            setDatForField(manageRoomBO.findById(txtSearchByRoomId.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
