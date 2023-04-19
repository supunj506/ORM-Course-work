/*
 * @author : Supun Jaysinghe
 * Date    : 19 April 2023
 * Time    : 12:34 AM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.dto;

import java.time.LocalDate;

public class CustomDTO {
    private String reserve_id;
    private LocalDate date;
    private String student_id;
    private String room_id;
    private String status;

    private String type;
    private double key_money;
    private int qty;

    private String name;
    private String gender;
    private String address;
    private String contact;
    private String dob;

    private String user_name;
    private String password;

    public CustomDTO() {
    }

    public CustomDTO(LocalDate date, String student_id, String name, String contact) {
        this.date = date;
        this.student_id = student_id;
        this.name = name;
        this.contact=contact;
    }
    public CustomDTO(String student_id, String name, LocalDate date, String room_id, String type, double key_money) {
        this.student_id = student_id;
        this.name = name;
        this.date = date;
        this.room_id = room_id;
        this.type = type;
        this.key_money=key_money;


    }

    public String getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(String reserve_id) {
        this.reserve_id = reserve_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getKey_money() {
        return key_money;
    }

    public void setKey_money(double key_money) {
        this.key_money = key_money;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
