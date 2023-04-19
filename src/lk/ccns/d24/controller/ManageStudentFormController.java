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
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ccns.d24.bo.BOFactory;
import lk.ccns.d24.bo.Custom.ManageRoomBO;
import lk.ccns.d24.bo.Custom.ManageStudentBO;
import lk.ccns.d24.dto.StudentDTO;
import lk.ccns.d24.util.ValidateUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

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
    public JFXButton btnAddStudent;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;

    LinkedHashMap<JFXTextField, Pattern> map=new LinkedHashMap<>();

    public void initialize(){
        Pattern studentIDPattern = Pattern.compile("^(ST-)[0-9]{3,5}$");
        Pattern namePattern=Pattern.compile("^[A-z ]{3,30}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9/]{4,40}$");
        Pattern contactPattern=Pattern.compile("^(\\+94|0)(70|71|72|75|76|77|78|51|52|66|81|54|63|67|65|26|25|27|32|37|21|23|24|35|45|91|47|41|55|57|36|11|33|31|34|38)[0-9]{7}$");
        map.put(txtId, studentIDPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtContact, contactPattern);

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

    public void addStudentOnAction(ActionEvent actionEvent) {
        try {
            if(cmbGender.getValue()!=null &&dpDOB.getValue()!=(null)){
                boolean isAdd=manageStudentBO.addStudent(getStudentDTO());
                loadStudentDataToTable();
                clearAllTextFieldOnAction(actionEvent);
                new Alert(Alert.AlertType.CONFIRMATION, "Add Student Successfully...!!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Empty Field Found Check Carefully...!!!").show();
            }

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

    public void keyReleasedOnAction(KeyEvent keyEvent) {
        ValidateUtil.validate(map,btnAddStudent);

        if(keyEvent.getCode()== KeyCode.ENTER){
            Object response=ValidateUtil.validate(map,btnAddStudent);
            if(response instanceof JFXTextField){
                JFXTextField txt=(JFXTextField)response;
                txt.requestFocus();
            }
        }

    }


}
