/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 10:27 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.bo.Custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ccns.d24.bo.Custom.ManageStudentBO;
import lk.ccns.d24.dao.DAOFactory;
import lk.ccns.d24.dao.custom.StudentDAO;
import lk.ccns.d24.dto.StudentDTO;
import lk.ccns.d24.entity.Student;

import java.io.IOException;
import java.util.List;

public class ManageStudentBOImpl implements ManageStudentBO {
    private final StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ObservableList<StudentDTO> getAllStudent() throws IOException {
        List<Student> all = studentDAO.findAll();
        ObservableList<StudentDTO> allStudent= FXCollections.observableArrayList();
        for(Student student:all){
            allStudent.add(getStudentDTO(student));
        }
        return allStudent;
    }

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.add(getStudentEntity(studentDTO));

    }

    @Override
    public boolean updateStudentData(StudentDTO studentDTO) throws IOException {
        return studentDAO.update(getStudentEntity(studentDTO));
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.delete(getStudentEntity(studentDTO));
    }

    @Override
    public StudentDTO findStudent(String studentID) throws IOException {
        return getStudentDTO(studentDAO.find(studentID));
    }

    private Student getStudentEntity(StudentDTO studentDTO){
        return new Student(
                studentDTO.getStudent_id(),
                studentDTO.getName(),
                studentDTO.getGender(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob()
        );
    }

    private StudentDTO getStudentDTO(Student student){
        if(student==null){
            return null;
        }
        return new StudentDTO(
                student.getStudent_id(),
                student.getName(),
                student.getGender(),
                student.getAddress(),
                student.getContact(),
                student.getDob()
        );
    }
}
