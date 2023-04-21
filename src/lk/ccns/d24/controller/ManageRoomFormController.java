/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 9:41 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.dto.RoomDTO;
import lk.ccns.d24.util.ValidateUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageRoomFormController {
    private final ManageRoomBO manageRoomBO = (ManageRoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ROOM);
    public AnchorPane manageRoomAP;
    public JFXTextField txtRoomId;
    public JFXComboBox<String> cmbRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQTY;
    public JFXTextField txtSearchByRoomId;
    public TableView<RoomDTO> roomTableView;
    public TableColumn<RoomDTO, String> colRoomId;
    public TableColumn<RoomDTO, String> colRoomType;
    public TableColumn<RoomDTO, Double> colKeyMoney;
    public TableColumn<RoomDTO, Integer> colQTY;
    public JFXButton btnAddRoom;
    public JFXButton btnUpdateRoom;
    public JFXButton btnDeleteRoom;
    public JFXButton btnSearchId;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    public void initialize() {
        Pattern roomIDPattern = Pattern.compile("^(RM-)[0-9]{3,5}$");
        Pattern keyMoneyPattern = Pattern.compile("^[0-9]{3,5}$");
        Pattern roomQTYPattern = Pattern.compile("^[0-9]{2,3}$");
        map.put(txtRoomId, roomIDPattern);
        map.put(txtKeyMoney, keyMoneyPattern);
        map.put(txtRoomQTY, roomQTYPattern);


        cmbRoomTypeLoad();
        loadRoomDataToTable();
        roomTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setDatForField(newValue);
            }
        });
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    public void addRoomOnAction() {
        try {
            if (checkNoEmptyField()) {
                if (manageRoomBO.findById(txtRoomId.getText()) == null) {

                    if (manageRoomBO.add(new RoomDTO(
                            txtRoomId.getText(),
                            cmbRoomType.getValue(),
                            Double.parseDouble(txtKeyMoney.getText()),
                            Integer.parseInt(txtRoomQTY.getText())))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Add Room Successfully...!").show();
                        loadRoomDataToTable();
                        clearAllTextFieldOnAction();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "That Room Id Already In The System...!").show();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void UpdateRoomOnAction() {
        try {
            if (manageRoomBO.findById(txtRoomId.getText()) != null) {
                if (checkNoEmptyField()) {
                    boolean isUpdate = manageRoomBO.update(new RoomDTO(
                            txtRoomId.getText(),
                            cmbRoomType.getValue(),
                            Double.parseDouble(txtKeyMoney.getText()),
                            Integer.parseInt(txtRoomQTY.getText())
                    ));
                    if (isUpdate) {
                        loadRoomDataToTable();
                        clearAllTextFieldOnAction();
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Room Data Successfully...!").show();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "That Room ID is not in the System...!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoomOnAction() {
        try {
            if (manageRoomBO.findById(txtRoomId.getText()) != null) {
                if (checkNoEmptyField()) {
                    if (manageRoomBO.delete(new RoomDTO(
                            txtRoomId.getText(),
                            cmbRoomType.getValue(),
                            Double.parseDouble(txtKeyMoney.getText()),
                            Integer.parseInt(txtRoomQTY.getText())))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Delete Room Successfully...!").show();
                        loadRoomDataToTable();
                        clearAllTextFieldOnAction();
                    }
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "That Room ID is not in the System...!").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchRoomByIdOnAction() {
        try {
            if (txtSearchByRoomId.getText().trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Empty Field Found...! Check Careful...!!!").show();

            } else {

                if (manageRoomBO.findById(txtSearchByRoomId.getText()) != null) {
                    setDatForField(manageRoomBO.findById(txtSearchByRoomId.getText()));
                } else {
                    new Alert(Alert.AlertType.ERROR, "That Room ID is not in the System...!").show();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkNoEmptyField() {
        if (cmbRoomType.getValue() != null && !txtKeyMoney.getText().trim().isEmpty() && !txtRoomQTY.getText().trim().isEmpty()) {
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "Empty Field Found...! Check Careful...!!!").show();
            return false;
        }
    }

    private void setDatForField(RoomDTO roomDTO) {
        txtRoomId.setText(roomDTO.getRoom_id());
        txtRoomQTY.setText(String.valueOf(roomDTO.getQty()));
        txtKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        cmbRoomType.setValue(roomDTO.getType());
    }

    private void loadRoomDataToTable() {
        try {
            ObservableList<RoomDTO> roomList = manageRoomBO.getAllData();
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

    public void clearAllTextFieldOnAction() {
        txtSearchByRoomId.clear();
        txtRoomId.clear();
        txtRoomQTY.clear();
        txtKeyMoney.clear();
        cmbRoomType.setValue(null);
    }


    public void IdKeyReleasedOnAction() {
        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^(RM-)[0-9]{3,5}$"), txtRoomId));
    }

    public void keyMoneyKeyReleasedOnAction() {
        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^[0-9]{4,5}$"), txtKeyMoney));

    }

    public void roomQTYKeyReleasedOnAction() {
        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^[0-9]{1,3}$"), txtRoomQTY));
    }

    private void setButtonStatus(boolean singleValidate) {
        btnAddRoom.setDisable(singleValidate);
        btnDeleteRoom.setDisable(singleValidate);
        btnUpdateRoom.setDisable(singleValidate);
    }

    public void searchIdKeyReleasedOnAction() {
        btnSearchId.setDisable(ValidateUtil.singleValidate(Pattern.compile("^(RM-)[0-9]{3,5}$"), txtSearchByRoomId));
    }
}
