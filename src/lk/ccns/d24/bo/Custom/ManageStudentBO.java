/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 10:26 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */

package lk.ccns.d24.bo.Custom;

import javafx.collections.ObservableList;
import lk.ccns.d24.bo.SuperBO;
import lk.ccns.d24.dto.StudentDTO;

import java.io.IOException;

public interface ManageStudentBO extends SuperBO {

    ObservableList<StudentDTO> getAllStudent()throws IOException;

    boolean addStudent(StudentDTO studentDTO) throws IOException;

    boolean updateStudentData(StudentDTO studentDTO) throws IOException;

    boolean deleteStudent(StudentDTO studentDTO) throws IOException;

    StudentDTO findStudent(String text) throws IOException;
}
