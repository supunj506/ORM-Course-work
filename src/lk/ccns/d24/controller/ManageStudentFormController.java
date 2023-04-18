/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 8:43 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.ccns.d24.bo.Custom.ManageStudentBO;
import lk.ccns.d24.dto.StudentDTO;

import java.io.IOException;
import java.time.LocalDate;

public class ManageStudentFormController {
    private final ManageStudentBO manageStudentBO= (ManageStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_STUDENT);


    public AnchorPane manageStudentAP;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSearchById;
    public JFXTextField txtContact;
    public JFXDatePicker dpDOB;
    public JFXComboBox<String> cmbGender;
    public TableView <StudentDTO>studentTableView;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;

    public void initialize(){
        cmbGenderDataLoad();
        loadStudentDataToTable();
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setDataForField(newValue);
            }
        });
        colId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void setDataForField(StudentDTO studentDTO) {
        txtId.setText(studentDTO.getStudent_id());
        txtName.setText(studentDTO.getName());
        cmbGender.setValue(studentDTO.getGender());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact());
        dpDOB.setValue(LocalDate.parse(studentDTO.getDob()));

    }

    private void loadStudentDataToTable() {
        try {
            ObservableList<StudentDTO> studentList = manageStudentBO.getAllStudent();
            if(studentList!=null){
                studentTableView.setItems( studentList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cmbGenderDataLoad() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
    }

    public void addStudentOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdd=manageStudentBO.addStudent(getStudentDTO());
            loadStudentDataToTable();
            clearAllTextFieldOnAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate=manageStudentBO.updateStudentData(getStudentDTO());
            loadStudentDataToTable();
            clearAllTextFieldOnAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) {
        try {
            boolean isDelete=manageStudentBO.deleteStudent(getStudentDTO());
            loadStudentDataToTable();
            clearAllTextFieldOnAction(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAllTextFieldOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtSearchById.clear();
        cmbGender.setValue(null);
        dpDOB.setValue(null);
        txtAddress.clear();
    }

    public void searchRoomByIdOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO= null;
        try {
            studentDTO = manageStudentBO.findStudent(txtSearchById.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(studentDTO!=null){
            setDataForField(studentDTO);
        }else {

        }
    }

    private StudentDTO getStudentDTO(){
        return new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                cmbGender.getValue(),
                txtAddress.getText(),
                txtContact.getText(),
                dpDOB.getValue().toString()
        );

    }


}
