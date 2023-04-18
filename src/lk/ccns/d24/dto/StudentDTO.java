/*
 * @author : Supun Jaysinghe
 * Date    : 15 April 2023
 * Time    : 10:05 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dto;

public class StudentDTO {
    private String Student_id;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String dob;

    public StudentDTO() {
    }

    public StudentDTO(String student_id, String name, String gender, String address, String contact, String dob) {
        Student_id = student_id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "Student_id='" + Student_id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", contact_no='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
