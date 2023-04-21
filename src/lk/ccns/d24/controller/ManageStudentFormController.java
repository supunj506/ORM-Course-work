/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 8:43 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.ManageStudentBO;
import lk.ccns.d24.dto.StudentDTO;
import lk.ccns.d24.util.ValidateUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class ManageStudentFormController {
    private final ManageStudentBO manageStudentBO = (ManageStudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_STUDENT);


    public AnchorPane manageStudentAP;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSearchById;
    public JFXTextField txtContact;
    public JFXDatePicker dpDOB;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentDTO> studentTableView;
    public TableColumn<StudentDTO, String> colId;
    public TableColumn<StudentDTO, String> colName;
    public TableColumn<StudentDTO, String> colAddress;
    public TableColumn<StudentDTO, String> colContact;
    public TableColumn<StudentDTO, LocalDate> colDOB;
    public TableColumn<StudentDTO, String> colGender;
    public JFXButton btnAddStudent;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;


    public void initialize() {
        cmbGenderDataLoad();
        loadStudentDataToTable();
        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
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

    public void addStudentOnAction() {
        try {

            if (checkAllDataFilled()) {
                new Alert(Alert.AlertType.ERROR, "Empty Field Found Check Carefully...!!!").show();
            } else {
                if(manageStudentBO.findStudent(txtId.getText())!=null){
                    new Alert(Alert.AlertType.ERROR, "Student Already In System...!!!").show();
                }else {
                    manageStudentBO.addStudent(getStudentDTO());
                    loadStudentDataToTable();
                    clearAllTextFieldOnAction();
                    new Alert(Alert.AlertType.CONFIRMATION, "Add Student Successfully...!!!").show();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkAllDataFilled() {
        return txtId.getText().isEmpty()
                || txtName.getText().isEmpty()
                || txtAddress.getText().isEmpty()
                || txtContact.getText().isEmpty()
                || cmbGender.getValue() == null
                || dpDOB.getValue() == null;
    }

    public void UpdateStudentOnAction() {
        try {
            if(checkAllDataFilled()){
                new Alert(Alert.AlertType.ERROR, "Empty Field Found Check Carefully...!!!").show();
            }else {
                if(manageStudentBO.findStudent(txtId.getText())==null){
                    new Alert(Alert.AlertType.ERROR, "Student Not In System...!!!").show();
                }else {
                    boolean isUpdate = manageStudentBO.updateStudentData(getStudentDTO());
                    loadStudentDataToTable();
                    clearAllTextFieldOnAction();
                    if(isUpdate){
                        new Alert(Alert.AlertType.CONFIRMATION, "Update Student Data Successfully...!!!").show();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentOnAction() {
        try {

            if(checkAllDataFilled()){
                new Alert(Alert.AlertType.ERROR, "Empty Field Found Check Carefully...!!!").show();
            }else {
                if (manageStudentBO.findStudent(txtId.getText()) == null) {
                    new Alert(Alert.AlertType.ERROR, "Student Not In System...!!!").show();
                } else {
                    boolean isDelete = manageStudentBO.deleteStudent(getStudentDTO());
                    loadStudentDataToTable();
                    clearAllTextFieldOnAction();
                    if (isDelete) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Delete Student Successfully...!!!").show();
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (studentList != null) {
                studentTableView.setItems(studentList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cmbGenderDataLoad() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
    }

    public void clearAllTextFieldOnAction() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtSearchById.clear();
        cmbGender.setValue(null);
        dpDOB.setValue(null);
        txtAddress.clear();
    }

    public void searchRoomByIdOnAction() {
        try {
            if(txtSearchById.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Empty Field Found Check Carefully...!!!").show();
            }else {
                if(manageStudentBO.findStudent(txtSearchById.getText())==null){
                    new Alert(Alert.AlertType.ERROR, "Student Not In System...!!!").show();
                }else {
                    setDataForField(manageStudentBO.findStudent(txtSearchById.getText()));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private StudentDTO getStudentDTO() {
        return new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                cmbGender.getValue(),
                txtAddress.getText(),
                txtContact.getText(),
                dpDOB.getValue().toString()
        );

    }



    public void IdKeyReleasedOnAction() {
        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^(ST-)[0-9]{3,5}$"),txtId));
    }

    public void nameKeyReleasedOnAction() {

        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^[A-z ]{3,30}$"),txtName));
    }

    public void addressKeyReleasedOnAction() {

        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^[A-z0-9/]{4,40}$"),txtAddress));
    }

    public void contactKeyReleasedOnAction() {
        setButtonStatus(ValidateUtil.singleValidate(Pattern.compile("^(\\+94|0)(70|71|72|75|76|77|78|51|52|66|81|54|63|67|65|26|25|27|32|37|21|23|24|35|45|91|47|41|55|57|36|11|33|31|34|38)[0-9]{7}$"),txtContact));

    }

    private void setButtonStatus(boolean singleValidate) {
        btnAddStudent.setDisable(singleValidate);
        btnUpdateStudent.setDisable(singleValidate);
        btnDeleteStudent.setDisable(singleValidate);
    }
}
