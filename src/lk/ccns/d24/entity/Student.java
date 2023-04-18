/*
 * @author : Supun Jaysinghe
 * Date    : 14 April 2023
 * Time    : 12:29 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private String student_id;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String dob;

    public Student() {
    }

    public Student(String student_id, String name, String gender, String address, String contact, String dob) {
        this.student_id = student_id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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
